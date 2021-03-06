package com.WeChatSell.sell.controller;

import com.WeChatSell.sell.dataobject.ProductCategory;
import com.WeChatSell.sell.dataobject.ProductInfo;
import com.WeChatSell.sell.dto.OrderDTO;
import com.WeChatSell.sell.enums.ResultEnum;
import com.WeChatSell.sell.exception.SellException;
import com.WeChatSell.sell.form.ProductForm;
import com.WeChatSell.sell.service.CategoryService;
import com.WeChatSell.sell.service.ProductService;
import com.WeChatSell.sell.util.KeyUtil;
import com.sun.tools.internal.xjc.reader.xmlschema.BindGreen;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 卖家端商品
 */

@Controller
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;


    /***
     * 列表
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "2") Integer size,
                             Map<String, Object> map) {

        PageRequest pageRequest = new PageRequest(page - 1, size);  // PageRequest 默认page=0是第一页，所以前端想查第一页，需要page-1。
        Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);

        return new ModelAndView("product/list", map);

    }

    /***
     * 上架商品
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId, Map<String, Object> map) {

        ProductInfo productInfo = new ProductInfo();
        try {
            productInfo = productService.findOne(productId);
            productService.onSale(productId);
        } catch (Exception e) {
            log.error("【卖家端上架商品】发生异常{}", e.getMessage());

            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }

        map.put("msg", ResultEnum.PRODUCT_ONSELL_SUCCESS.getMessage());
        map.put("url", "/sell/seller/product/list");

        return new ModelAndView("common/success");
    }

    /***
     * 下架商品
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId, Map<String, Object> map) {

        ProductInfo productInfo = new ProductInfo();
        try {
            productInfo = productService.findOne(productId);
            productService.offSale(productId);
        } catch (Exception e) {
            log.error("【卖家端下架商品】发生异常{}", e.getMessage());

            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }

        map.put("msg", ResultEnum.PRODUCT_OFFSELL_SUCCESS.getMessage());
        map.put("url", "/sell/seller/product/list");

        return new ModelAndView("common/success");
    }

    /***
     * 新增/修改商品
     * @param productId
     * @param map
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId, Map<String, Object> map) {

        if (!StringUtils.isEmpty(productId)) {

            ProductInfo productInfo;
            try {
                productInfo = productService.findOne(productId);
            } catch (Exception e) {
                log.error("【卖家端修改商品】发生异常{}", e.getMessage());

                map.put("msg", e.getMessage());
                map.put("url", "/sell/seller/product/list");
                return new ModelAndView("common/error", map);
            }
            map.put("productInfo", productInfo);
        }

        // 查询所有的类目
        List<ProductCategory> productCategoryList = categoryService.findAll();
        map.put("productCategoryList", productCategoryList);
        return new ModelAndView("/product/index", map);

    }

    /**
     * 保存新增/修改的商品
     *
     * @param productForm
     * @param bindingResult
     * @param map
     * @return
     */
    @RequestMapping("/save")
//    @CachePut(cacheNames = "product", key = "123")
//    @CacheEvict(cacheNames = "product",key = "123")  // 这当前方法之后清除指定的缓存
    public ModelAndView save(@Valid ProductForm productForm, BindingResult bindingResult, Map<String, Object> map) {


        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        ProductInfo productInfo = new ProductInfo();
        try {

            // 如果productId不为空，则该操作是更新商品
            if(!StringUtils.isEmpty(productForm.getProductId())){
                productInfo = productService.findOne(productForm.getProductId());
            }else{
                productForm.setProductId(KeyUtil.genUniqueKey());
            }

            BeanUtils.copyProperties(productForm, productInfo);
            productService.save(productInfo);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }
}
