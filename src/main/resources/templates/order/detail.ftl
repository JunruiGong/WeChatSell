<html>

<head>
    <meta charset="UTF-8">
    <title>卖家订单详情列表</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-4 column">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>订单ID</th>
                    <th>订单总金额</th>

                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${orderDTO.getOrderId()}</td>
                    <td>${orderDTO.getOrderAmount()}</td>
                </tr>

                </tbody>
            </table>

        </div>

    <#--订单详情表数据-->
        <div class="col-md-12 column">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>商品ID</th>
                    <th>商品名称</th>
                    <th>价格</th>
                    <th>数量</th>
                    <th>总额</th>
                </tr>
                </thead>
                <tbody>

                    <#list orderDTO.getOrderDetailList() as detailList>
                    <tr>
                        <td>${detailList.getProductId()}</td>
                        <td>${detailList.getProductName()}</td>
                        <td>${detailList.getProductPrice()}</td>
                        <td>${detailList.getProductQuantity()}</td>
                        <td>${detailList.getProductQuantity() * detailList.getProductPrice()}</td>
                    </tr>
                    </#list>

                </tbody>
            </table>
        </div>

        <div class="col-md-12 column">
            <#if orderDTO.getOrderStatusEnum().message == "新订单">
                <a type="button" class="btn btn-default btn-primary" href="/sell/seller/order/finish?orderId=${orderDTO.getOrderId()}">完结订单</a>
                <a type="button" class="btn btn-default btn-danger" href="/sell/seller/order/cancel?orderId=${orderDTO.getOrderId()}">取消订单</a>
            </#if>
        </div>

    </div>
</div>


</body>
</html>