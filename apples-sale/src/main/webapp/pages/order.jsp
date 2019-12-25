<%--
  Created by IntelliJ IDEA.
  User: six
  Date: 2019/12/19
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <title>Blog</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="aStar Fashion Template Project">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css"
          href="/static/styles/bootstrap-4.1.3/bootstrap.css">
    <link href="/static/plugins/font-awesome-4.7.0/css/font-awesome.min.css"
          rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css"
          href="/static/plugins/OwlCarousel2-2.2.1/owl.carousel.css">
    <link rel="stylesheet" type="text/css"
          href="/static/plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
    <link rel="stylesheet" type="text/css"
          href="/static/plugins/OwlCarousel2-2.2.1/animate.css">
    <link rel="stylesheet" type="text/css" href="/static/styles/cart.css">
    <link rel="stylesheet" type="text/css" href="/static/styles/blog.css">
    <link rel="stylesheet" type="text/css" href="/static/styles/blog_responsive.css">
    <script src="/static/js/jquery-3.2.1.min.js"></script>
</head>

<body>

<div class="super_container">
    <div id="dd_div">
        <script>
            $("#dd_div").load("/pages/sidebar.jsp");
        </script>
    </div>
    <div class="blog">
        <div class="section_container" style="padding-left: 10px;padding-right: 10px">
            <div class="container">
                <div class="section_container">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <c:forEach items="${orders}" var="order">
                                <div class="cart_container" style="padding-top: 10px">

                                        <div class="cart_bar" >
                                            <ul class="cart_bar_list item_list d-flex flex-row align-items-center justify-content-start" style="width: auto">
                                                <li>订单编号:<br>
                                                        ${order.oid}</li>
                                                <li style="width: 17%;">订单时间:<br>
                                                    <fmt:formatDate value="${order.odate}" pattern="yyyy-MM-dd HH-mm-ss"/> </li>
                                                <li style="width: 20%;">总价:${order.ototal}</li>
                                                <li>订单状态:<br>
                                                    <c:if test="${order.ostatus eq 1}">
                                                        未支付
                                                    </c:if>

                                                    <c:if test="${order.ostatus eq 2}">
                                                        已支付
                                                    </c:if>
                                                    <c:if test="${order.ostatus eq 3}">
                                                        已发货
                                                    </c:if>
                                                    <c:if test="${order.ostatus eq 4}">
                                                        超时失效
                                                    </c:if>
                                                </li>
                                                <li style="width:16%;">操作：<br>
                                                    <div class="product_price text-lg-center product_text">
                                                       <%-- <a href="javascript:void (0)" class="aorder" value="${order.oid}">查看订单</a>--%>
                                                        <a href="getorderinfo?oid=${order.oid}">查看订单</a>
                                                    </div>
                                                </li>
                                            </ul>
                                        </div>
                                     <%--   <div>
                                            <div class="cart_items">
                                                <ul>

                                                </ul>
                                                <c:forEach items="${order.orderInfos}" var="info">
                                                    <ul class="cart_items_list">
                                                        <li  class="cart_item item_list d-flex flex-lg-row flex-column align-items-lg-center align-items-start justify-content-start">
                                                            <div class="product d-flex flex-lg-row flex-column align-items-lg-center align-items-start justify-content-start">
                                                                <div class="product d-flex flex-lg-row flex-column align-items-lg-center align-items-start justify-content-start">
                                                                    <div>
                                                                        <div class="product_image">
                                                                            <img src="${info.goods.gimage}" alt="">
                                                                        </div>
                                                                    </div>
                                                                    <div class="product_name">
                                                                        <a href="product.html">${info.goods.gname}</a>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="product_price text-lg-center product_text">
                                                                <c:if test="${order.ostatus eq 1}">
                                                                    <a href="/pay?oid=${order.oid}">支付</a>
                                                                </c:if>
                                                            </div>
                                                        </li>
                                                    </ul>
                                                </c:forEach>

                                            </div>
                                        </div>--%>



                                </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<script src="/static/styles/bootstrap-4.1.3/popper.js"></script>
<script src="/static/styles/bootstrap-4.1.3/bootstrap.min.js"></script>
<script src="/static/plugins/greensock/TweenMax.min.js"></script>
<script src="/static/plugins/greensock/TimelineMax.min.js"></script>
<script src="/static/plugins/scrollmagic/ScrollMagic.min.js"></script>
<script src="/static/plugins/greensock/animation.gsap.min.js"></script>
<script src="/static/plugins/greensock/ScrollToPlugin.min.js"></script>
<script src="/static/plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
<script src="/static/plugins/easing/easing.js"></script>
<script src="/static/plugins/parallax-js-master/parallax.min.js"></script>
<script src="/static/js/blog.js"></script>
</body>

<script type="text/javascript">

    $(document).ready(function(){
     /*   $(".aorder").on(click,function () {
            $.ajax({
                url:"getorderinfo",
                data:{"oid":$(this).val()},
                type:"post",
                success:function (msg) {
                    if(msg.code==100){

                    }
                }


            });
        });   */

    });


</script>

</html>
