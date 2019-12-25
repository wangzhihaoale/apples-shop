<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="zh">
<head>
<title>商品列表</title>
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
<link rel="stylesheet" type="text/css" href="/static/styles/categories.css">
<link rel="stylesheet" type="text/css"
	href="/static/styles/categories_responsive.css">

<script src="/static/js/jquery-3.2.1.min.js"></script>

</head>
<body>

	<div class="super_container">

		<div id="dd_div">
			<script>
			<!--会重新向服务器请求一下 sidebar.jsp页面-->
				$("#dd_div").load("pages/sidebar.jsp");
			</script>

		</div>

		<!-- Home -->

		<div class="home">
			<div class="parallax_background parallax-window"
				data-parallax="scroll" data-image-src="/static/images/categories.jpg"
				data-speed="0.8"></div>
			<div class="home_container">
				<div class="home_content">
					<div class="home_title">商品列表</div>
					<div class="breadcrumbs">
						<ul
							class="d-flex flex-row align-items-center justify-content-start">
							<li><a href="getGoodsType">主页</a></li>
							<li>

							<a href="queryGoods?gsex=${goods[0].gsex}">
								<c:if test="${goods[0].gsex eq 0}">
									女装
								</c:if>
								<c:if test="${goods[0].gsex eq 1}">
									男装
								</c:if>
								<c:if test="${goods[0].gsex eq 2}">
									童装
								</c:if>
								<c:if test="${goods[0].gsex eq 3}">
									lotita装
								</c:if>
							</a>


							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<!-- Products -->

		<div class="products">

			<!-- Sorting & Filtering -->
			<div class="products_bar">
				<div class="section_container">
					<div class="container">
						<div class="row">
							<div class="col">
								<div
									class="products_bar_content d-flex flex-column flex-xxl-row align-items-start align-items-xxl-center justify-content-start">
									<div class="product_categories">
										<ul
											class="d-flex flex-row align-items-start justify-content-start flex-wrap">
											<li class="active"><a href="goods.do?type=list">所有</a></li>
											<li><a href="goods.do?type=list&glabel=2">热门</a></li>
											<li><a href="goods.do?type=list&glabel=1">新款</a></li>
											<li><a href="goods.do?type=list&glabel=3">折扣</a></li>
										</ul>
									</div>
									<div
										class="products_bar_side ml-xxl-auto d-flex flex-row align-items-center justify-content-start">
										<div class="products_dropdown product_dropdown_sorting">
											<div class="isotope_sorting_text">
												<span>默认排序</span><i class="fa fa-caret-down"
													aria-hidden="true"></i>
											</div>
											<ul>
												<li class="item_sorting_btn"
													data-isotope-option='{ "sortBy": "original-order" }'>默认</li>
												<li class="item_sorting_btn"
													data-isotope-option='{ "sortBy": "price" }'>价格</li>
												<li class="item_sorting_btn"
													data-isotope-option='{ "sortBy": "name" }'>名称</li>
											</ul>
										</div>
										<div
											class="product_view d-flex flex-row align-items-center justify-content-start">
											<!--<div class="view_item active"><img src="images/view_1.png" alt=""></div>
										<div class="view_item"><img src="images/view_2.png" alt=""></div>
										<div class="view_item"><img src="images/view_3.png" alt=""></div>-->
											<div class="isotope_filter_text">
											<form action="/queryGoods?type=list" method="post">
												<span>价格</span><input type="number" name="gpricemin"
													style="text-align: center;" />-<input type="number"  name="gpricemax"
													style="text-align: center;" />
													<button type="submit">搜索</button>
											</form>
											</div>
										</div>
										<div
											class="products_dropdown text-right product_dropdown_filter">
											<div class="isotope_filter_text">
												<span>过滤</span><i class="fa fa-caret-down"
													aria-hidden="true"></i>
											</div>
											<ul>
												<li class="item_filter_btn" data-filter="*">所有</li>
												<li class="item_filter_btn" data-filter=".hot">热销</li>
												<li class="item_filter_btn" data-filter=".new">新品</li>
												<li class="item_filter_btn" data-filter=".sale">折扣</li>
											</ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="section_container">
				<div class="container">
					<div class="row">
						<div class="col">
							<div class="products_container grid">

								<!--展示商品的列表-->
								<c:forEach items="${goodsPage.list}" var="goods">
									<!-- Product -->
									<div
										class="product grid-item 
									
										<c:if test="${goods.glabel eq 1 }">new</c:if>
										<c:if test="${goods.glabel eq 2 }">hot</c:if>
										<c:if test="${goods.glabel eq 3 }">sale</c:if>
										
									">
										<div class="product_inner">
											<div class="product_image">
												<img src="${goods.gimage}" alt="">

												<c:if test="${goods.glabel eq 1}">
													<div class="product_tag">new</div>
												</c:if>
												<c:if test="${goods.glabel eq 2}">
													<div class="product_tag">hot</div>
												</c:if>
												<c:if test="${goods.glabel eq 3}">
													<div class="product_tag">sale</div>
												</c:if>


											</div>
											<div class="product_content text-center">
												<div class="product_title">
													<a href="queryGoodsDetail?gid=${goods.gid}">${goods.gname }</a>
												</div>
												<div class="product_price">¥${goods.gprice }</div>
												<div class="product_button ml-auto mr-auto trans_200">
													<a href="javascript:void(0);" class="addshopcar" gid="${goods.gid}">添加到购物车</a>
												</div>
											</div>
										</div>
									</div>
								</c:forEach>

							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="section_container">

                <form method="post" action="/queryGoods">
                    <input name="glabel" value="${good.glabel}" type="hidden">
                    <input name="gpricemin" type="hidden" value="${good.gpricemin}">
                    <input name="gpricemax" type="hidden" value="${good.gpricemax}">
                    <input name="gsex" value="${good.gsex}" type="hidden">
					<input name="pageNum" type="hidden">

				<a class="prev" href="javascript:void(0);" onclick="smbits()">&lt;&lt;</a>
				<c:forEach items="${goodsPage.navigatepageNums}" var="pe">
					<c:if test="${goodsPage.pageNum eq pe}">
						<span>
							${pe}
						</span>
					</c:if>
					<c:if test="${goodsPage.pageNum !=pe}">
						<a href="javascript:void(0);" onclick="smbits(${pe})">
							${pe}
						</a>
					</c:if>
				</c:forEach>
				<a class="next" href="javascript:void(0);" onclick="smbits(${goodsPage.pages})">&gt;&gt;</a>
                </form>
			<%--	<div>

					<a class="num" href="">1</a>
					<span class="current">2</span>
					<a class="num" href="">3</a>
					<a class="num" href="">489</a>


				</div>--%>
			</div>
			<%--<form id="pageform" method="post">
				<input name="dataCount" type="hidden" value="${goodsPojo.page.dataCount}"/>
				<input name="pageSize" type="hidden" value="${goodsPojo.page.pageSize}"/>
				<input name="currentPage" type="hidden" value="${goodsPojo.page.currentPage}"/>
			
				<label>总共:${goodsPojo.page.dataCount}条 当前是第 ${goodsPojo.page.startRecord+1} 条 到 第 ${goodsPojo.page.endRecord}条</label>
				<label style="text-align: right;padding-left:300px;"> 
					<a href="javascript:void(0);" onclick="jumpList('${goodsPage.page.currentPage - 1 eq 0 ? 1 : goodsPojo.page.currentPage - 1}')">上一页</a>
							
						<c:forEach items="${pageList}" var="pg">
							<a href="javascript:void(0);" onclick="jumpList('${pg}')"
							
								<c:if test='${pg eq goodsPojo.page.currentPage}'>style='background-color:red;'</c:if>
							
							>${pg}</a>
						</c:forEach>
										
					<a href="javascript:void(0);" onclick="jumpList('${goodsPojo.page.currentPage + 1 >= goodsPojo.page.pageCount ? goodsPojo.page.pageCount : goodsPojo.page.currentPage + 1}')">下一页</a> 
				 </label>
			 </form>--%>
			</div>
			
		</div>

	</div>
	<script type="text/javascript" src="/static/js/shopcar.js"></script>
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
	<script src="/static/plugins/Isotope/isotope.pkgd.min.js"></script>
	<script src="/static/plugins/Isotope/fitcolumns.js"></script>
	<script src="/static/js/categories.js"></script>
	
	<script type="text/javascript">

	   function smbits(pageNum) {
		   $("input[name='pageNum']").val(pageNum)
		   $("form").submit();
       }

	</script>
</body>
</html>