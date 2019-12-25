<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


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
<script src="../static/js/jquery-3.2.1.min.js"></script>
</head>

<body>

	<div class="super_container">
		<div id="dd_div">
			<script>
				$("#dd_div").load("/pages/sidebar.jsp");
			</script>
		</div>
		<!-- Home -->
		<div class="home">
		<div class="parallax_background parallax-window"
			 data-parallax="scroll" data-image-src="/static/images/blog.jpg"
			 data-speed="0.8"></div>
		<div class="home_container">
			<div class="home_content">
				<div class="home_title">订单详情:未支付</div>
			</div>
		</div>
	</div>
		<!-- Blog -->
		<div class="blog">
			<div class="section_container">
                <input type="text" id="ins" value="${order.oid}" type="hidden" hidden="hidden"/>
				<div class="container">
					<div class="row">
							<h1>倒计时:距离支付还剩 <label id="showtime"></label></h1>
							<input type="hidden" >
					</div>
					<div class="section_container">
						<div class="container">
							<div class="row">
								<div class="col">
									<div class="cart_container">
											<!-- Cart Bar -->
											<div class="cart_bar">
												<ul class="cart_bar_list item_list d-flex flex-row align-items-center justify-content-start">
													<li>商品名称</li>
													<li>商品数量</li>
													<li>价格</li>
													<li>总计</li>
												</ul>
											</div>
											<!-- Cart Items -->
											<div class="cart_items">
												<ul class="cart_items_list">

													<c:forEach items="${order.orderInfos}" var="map">
														<li  class="cart_item item_list d-flex flex-lg-row flex-column align-items-lg-center align-items-start justify-content-start">
															<div class="product d-flex flex-lg-row flex-column align-items-lg-center align-items-start justify-content-start">
																<div>
																	<div class="product_image">
																		<img  src="${map.goods.gimage}" alt="" >
																	</div>
																</div>
																<div class="product_name">
																	<a href="product.html">${map.goods.gname}</a>
																</div>
															</div>
															<div class="product_price text-lg-center product_text">¥ ${map.odnumber}</div>
															<div class="product_price text-lg-center product_text">¥ ${map.odprice}</div>
															<div class="product_price text-lg-center product_text">¥ ${map.odprice}*${map.odnumber}</div>
														</li>
													</c:forEach>
												</ul>
												<a href="pay?oid=${order.oid}" class="dorders">支付</a>
											</div>
											<!-- Cart Buttons -->
									</div>
								</div>
							</div>
						</div>
					</div>

					<%--<div class="row load_more_row">
						<div class="col">
							<h2>${order.oid} - ${order.ototal}</h2>
							<hr>
							<div class="">

								<ul>
									<c:forEach items="${order.orderInfos}" var="detail">

										<li>${detail.odnumber} - ${detail.goods.gname} - ${detail.odprice }</li>

									</c:forEach>
								</ul>
							</div>
							
							<a href="pay?oid=${order.oid}">支付</a>
							
						</div>
					</div>--%>
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
    var currenttime=0;
    $.ajax({
		url:"/getOdate",
		type:"post",
		data:{"oid":$("#ins").val()},
		success:function (msg) {
		    console.log(msg)
		    if(msg.code==100){
                currenttime=msg.map.date;
                var date = new Date();
                var nowtime=date.getTime();
                currenttime=10*60*1000-(nowtime-currenttime);
                date.setTime(currenttime);//应该是一个动态的,以后台的计数为标准
                /*$("#showtime").text("10:00");*/
                        var a=setInterval(function(){
                            var str;
                            date.setTime(date.getTime()-1000);
                            str = date.getMinutes() + ":" + (date.getSeconds()<10 ? '0'+date.getSeconds() : date.getSeconds());
                            console.log(date.setTime(date.getTime()-1000));
                            $("#showtime").text(str);

                            if(date.setTime(date.getTime()-1000)<0){
                                $("#showtime").text("订单失效");
                            $(".dorders").hide();
                            clearInterval(a);
                        }
                }, 1000);
			}
        }
	});


});


</script>

</html>