<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html>
<html lang="zh">

<head>
<title>购物车页面</title>
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
<link rel="stylesheet" type="text/css" href="/static/styles/cart_responsive.css">
<script src="/static/js/jquery-3.2.1.min.js"></script>
</head>

<body>

	<div class="super_container">

		<div id="dd_div">
			<script>
				$("#dd_div").load("sidebar.jsp");
			</script>
		</div>

		<div class="home">
			<div class="parallax_background parallax-window"
				data-parallax="scroll"
				data-image-src="/static/images/product_background.jpg" data-speed="0.8"></div>
			<div class="home_container">
				<div class="home_content">
					<div class="home_title">Cart</div>
					<div class="breadcrumbs">
						<ul
							class="d-flex flex-row align-items-center justify-content-start">
							<li><a href="index">主页</a></li>
							<li>俺的购物车</li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<!-- Cart -->

		<div class="cart_section">
			<div class="section_container">
				<div class="container">
					<div class="row">
						<div class="col">
							<div class="cart_container">
							<form id="for" method="post" action="/create">
								<!-- Cart Bar -->
								<div class="cart_bar">
									<ul
										class="cart_bar_list item_list d-flex flex-row align-items-center justify-content-start">
										<li>商品</li>
										<li>类别</li>
										<li>价格</li>
										<li>数量</li>
										<li>总计</li>
										<li><input type="checkbox" id="allcheck"/></li>
									</ul>
								</div>

								<!-- Cart Items -->
								<div class="cart_items">
									<ul class="cart_items_list">

										<c:forEach items="${shopcar}" var="map">
											<li id="li_${map.value.gid}" class="cart_item item_list d-flex flex-lg-row flex-column align-items-lg-center align-items-start justify-content-start">
												<div class="product d-flex flex-lg-row flex-column align-items-lg-center align-items-start justify-content-start">
													<div>
														<div class="product_image">
															<img src="${map.value.gimage}" alt="">
														</div>
													</div>
													<div class="product_name">
														<a href="product.html">${map.value.gname}</a>
													</div>
												</div>
												<div class="product_color text-lg-center product_text">${map.value.types.tname}</div>

												<div class="product_price text-lg-center product_text">¥ ${map.value.gprice}</div>
												<div class="product_quantity_container">
													<div
														class="product_quantity ml-lg-auto mr-lg-auto text-center">
														<span class="product_text product_num">${map.value.num}</span>
														<div class="qty_sub qty_button trans_200 text-center" gid="${map.value.gid}">
															<span>-</span>
														</div>
														<div class="qty_add qty_button trans_200 text-center" gid="${map.value.gid}">
															<span>+</span>
														</div>
													</div>
												</div>
												<div class="product_total text-lg-center product_text">
													¥<label id="lab_${map.value.gid}"><fmt:formatNumber value="${map.value.num * map.value.gprice}" pattern="#.00"></fmt:formatNumber></label>
												</div>
												<div class="product_size text-lg-center product_text">
													<input type="checkbox" name="gids" value="${map.value.gid}" />
												</div>
											</li>
										</c:forEach>
									</ul>
								</div>

								<!-- Cart Buttons -->
								<div
									class="cart_buttons d-flex flex-row align-items-start justify-content-start">
									<div
										class="cart_buttons_inner ml-auto d-flex flex-row align-items-start justify-content-start flex-wrap">
										<div class="button button_continue trans_200">
											<a href="/queryGoods">继续购物</a>
										</div>
										<div class="button button_clear trans_200">
											<a href="javascript:void(0);" onclick="if(confirm('你确定清空吗?')){window.location.href='/cleanShopCar'}">清空购物车</a>
										</div>
										<div class="button button_update trans_200">
											<a href="javascript:void(0);" onclick="sub()">结算</a>
										</div>
									</div>
								</div>
							</form>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="section_container cart_extra_container">
				<div class="container">
					<div class="row">

						<!-- Cart Total -->
						<div class="col-xxl-6">
							<div class="cart_extra cart_extra_2">
								<div class="cart_extra_content cart_extra_total">
									<div class="cart_extra_title">结算</div>
									<ul class="cart_extra_total_list">
										<li
											class="d-flex flex-row align-items-center justify-content-start">
											<div class="cart_extra_total_title">总价</div>
											<div class="cart_extra_total_value ml-auto" id="zone">¥:00.00</div>
										</li>
										<li
											class="d-flex flex-row align-items-center justify-content-start">
											<div class="cart_extra_total_title">快递</div>
											<div class="cart_extra_total_value ml-auto">随意</div>
										</li>
										<li
											class="d-flex flex-row align-items-center justify-content-start">
											<div class="cart_extra_total_title">总计</div>
											<div class="cart_extra_total_value ml-auto" id="ztwo">¥:00.00</div>
										</li>
									</ul>
									<div class="checkout_button trans_200">
										<a href="javascript:void (0)" onclick="sub()">立即结算</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

	<script>
		function sub() {
			$("#for").submit();
        }
	</script>

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
	<script src="/static/js/cart.js"></script>
</body>

</html>



<%--
<!-- Shipping & Delivery -->
<div class="col-xxl-6">
	<div class="cart_extra cart_extra_1">
		<!--<div class="cart_extra_content cart_extra_coupon">
        <div class="cart_extra_title">Coupon code</div>
        <div class="coupon_form_container">
            <form action="#" id="coupon_form" class="coupon_form">
                <input type="text" class="coupon_input" required="required">
                <button class="coupon_button">apply code</button>
            </form>
        </div>
        <div class="shipping">
            <div class="cart_extra_title">Shipping Method</div>
            <ul>
                <li class="shipping_option d-flex flex-row align-items-center justify-content-start">
                    <label class="radio_container">
                        <input type="radio" id="radio_1" name="shipping_radio" class="shipping_radio">
                        <span class="radio_mark"></span>
                        <span class="radio_text">Next day delivery</span>
                    </label>
                    <div class="shipping_price ml-auto">$4.99</div>
                </li>
                <li class="shipping_option d-flex flex-row align-items-center justify-content-start">
                    <label class="radio_container">
                        <input type="radio" id="radio_2" name="shipping_radio" class="shipping_radio">
                        <span class="radio_mark"></span>
                        <span class="radio_text">Standard delivery</span>
                    </label>
                    <div class="shipping_price ml-auto">$1.99</div>
                </li>
                <li class="shipping_option d-flex flex-row align-items-center justify-content-start">
                    <label class="radio_container">
                        <input type="radio" id="radio_3" name="shipping_radio" class="shipping_radio" checked>
                        <span class="radio_mark"></span>
                        <span class="radio_text">Personal Pickup</span>
                    </label>
                    <div class="shipping_price ml-auto">Free</div>
                </li>
            </ul>
        </div>
    </div>-->
	</div>
</div>
--%>
