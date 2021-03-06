/* JS Document */

/******************************

[Table of Contents]

1. Vars and Inits
2. Init Menu
3. InitQty


******************************/

$(document).ready(function()
{

	"use strict";

    $.ajax({
        url:"/echoShopCar",
        type:"post",
        data:{},
        success:function (msg) {
            console.log(msg)
            if(msg.code==100){
                $("#cart_num").text(msg.total);
                $("#cart_price").html(msg.totalCost);

                $("#zone").text(msg.totalCost);
                $("#ztwo").text(msg.totalCost);
            }
        }
    });
	//这里是点击结算按钮,直接提交表单
/*	$("#a_account").click(function(){

		$("form").attr('action','orderinfo.jsp');
		/!*$("form").attr('action','jiesuan?type=bufen');*!/
		$("form").submit();
	});*/
	
	
	//全选和全不选的复选框
	$("#allcheck").change(function(){
		
		$("input[name='gids']").prop("checked",$("#allcheck").prop("checked"));
		
	});
	
	$("input[name='gids']").change(function(){
		
		var ck = $("input[name='gids']").length == $("input[name='gids']:checked").length;
		
		$("#allcheck").prop("checked",ck);
		
	});
	
	var menu = $('.menu');
	var burger = $('.hamburger');
	var menuActive = false;

	$(window).on('resize', function()
	{
		setTimeout(function()
		{
			$(window).trigger('resize.px.parallax');
		}, 375);
	});

	initMenu();
	initQty();

	/* 

	2. Init Menu

	*/

	function initMenu()
	{
		if(menu.length)
		{
			if($('.hamburger').length)
			{
				burger.on('click', function()
				{
					if(menuActive)
					{
						closeMenu();
					}
					else
					{
						openMenu();

						$(document).one('click', function cls(e)
						{
							if($(e.target).hasClass('menu_mm'))
							{
								$(document).one('click', cls);
							}
							else
							{
								closeMenu();
							}
						});
					}
				});
			}
		}
	}

	function openMenu()
	{
		menu.addClass('active');
		menuActive = true;
	}

	function closeMenu()
	{
		menu.removeClass('active');
		menuActive = false;
	}

	/* 

	3. Init Qty

	*/

	function initQty()
	{
		if($('.product_quantity').length)
		{
			var qtys = $('.product_quantity');

			qtys.each(function()
			{
				var qty = $(this);
				var sub = qty.find('.qty_sub');
				var add = qty.find('.qty_add');
				var num = qty.find('.product_num');
				var original;
				var newValue;

				sub.on('click', function()
				{
					original = parseFloat(qty.find('.product_num').text());
					if(original > 0)
						{
							newValue = original - 1;
						}
					num.text(newValue);
					
					//向后台发送一个ajax请求,对购物车的某个商品进行减
					var gid = $(this).attr("gid");
					
					$.ajax({
						url:"/subShopCar",
						type:"post",
						data:{"gid":gid},
						success:function(msg){
							if(msg.code==200){
								alert(msg.msg)
							}
							if(msg.code==100){ //数量减1成功

								/*var str = json.resultMsg.split(","); // str[0] 总数量 str【1】 总价格 str【2】当前商品总价
								var countstr = "￥"+str[1]+'('+str[0]+')';
								$("#cart_num").html(str[0]);
								$("#cart_price").html(countstr);*/
                                $("#cart_num").text(msg.total);
                                $("#cart_price").html(msg.totalCost);
								var id = "#lab_"+gid;
								$(id).html(msg.currentPrice);//当前商品的总价格
                                $("#zone").text(msg.totalCost);
                                $("#ztwo").text(msg.totalCost);

							}else if(msg.code==300){ //商品移除成功
								console.log(msg)
								/*/!*var str = json.resultMsg.split(","); // str[0] 总数量 str【1】 总价格 str【2】当前商品总价
								var countstr = "￥"+str[1]+'('+str[0]+')';
								$("#cart_num").html(str[0]);
								$("#cart_price").html(countstr*!/);*/

                                $("#cart_num").text(msg.total);
                                $("#cart_price").text(msg.totalCost);
                                console.log(msg.totalCost)
								//把当前数据行移除掉
								var id = "#li_"+gid;
								$(id).remove();
                                $("#zone").text(msg.totalCost);
                                $("#ztwo").text(msg.totalCost);
							}

						}
					});
					
				});

				<!--当我点击+号时,会触发的js方法-->
				add.on('click', function()
				{
					original = parseFloat(qty.find('.product_num').text());
					newValue = original + 1;
					num.text(newValue);
					
					//发送ajax请求,然后得到结果以后,需要将数量+1
					//$(this) :代表当前点击的那个DIV
					var gid = $(this).attr("gid");
					
					$.ajax({
						url:"/addShopCar",
						type:"post",
						data:{"gid":gid},
						success:function(msg){
							console.log(msg)
							if(msg.code==100){
                                $("#cart_num").text(msg.total);
                                $("#cart_price").html(msg.totalCost);
                                var lb ="#lab_"+gid;
                                $(lb).html(msg.currentPrice);
                                $("#zone").text(msg.totalCost);
                                $("#ztwo").text(msg.totalCost);

								/*var str = json.resultMsg.split(",");  //"1,200.00"
								var countstr = "￥"+str[1]+'('+str[0]+')';
								$("#cart_num").html(str[0]);
								$("#cart_price").html();

								var id = "#lab_"+gid;
								$(id).html(str[2]);//当前商品的总价格*/
							}
							
						}
					});
					
				});

				/*add1.on('click',function () {

                });*/
			});
		}
	}





});