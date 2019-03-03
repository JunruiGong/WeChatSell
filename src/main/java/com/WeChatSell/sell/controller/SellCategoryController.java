package com.WeChatSell.sell.controller;

import com.WeChatSell.sell.dataobject.ProductCategory;
import com.WeChatSell.sell.dataobject.ProductInfo;
import com.WeChatSell.sell.exception.SellException;
import com.WeChatSell.sell.form.CategoryForm;
import com.WeChatSell.sell.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/category")
public class SellCategoryController {

    @Autowired
    private CategoryService categoryService;


    /***
     * 类目列表
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {
        List<ProductCategory> productCategoryList = categoryService.findAll();

        map.put("productCategoryList", productCategoryList);

        return new ModelAndView("/category/list", map);
    }


    /***
     * 商品类目列表
     * @param categoryId
     * @param map
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId, Map<String, Object> map) {

        if (categoryId != null && categoryId > 0) {
            ProductCategory productCategory = categoryService.findOne(categoryId);
            map.put("productCategory", productCategory);
        }

        return new ModelAndView("category/index", map);

    }


    /***
     * 保存类目的更新
     * @param categoryForm
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm, BindingResult bindingResult, Map<String, Object> map) {

        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("common/error", map);
        }

        ProductCategory productCategory = new ProductCategory();
        try {
            if (categoryForm.getCategoryId() != null && categoryForm.getCategoryId() > 0) {
                productCategory = categoryService.findOne(categoryForm.getCategoryId());

            }

            BeanUtils.copyProperties(categoryForm, productCategory);
            categoryService.save(productCategory);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/seller/category/list");
        return new ModelAndView("common/success", map);


    }
}
