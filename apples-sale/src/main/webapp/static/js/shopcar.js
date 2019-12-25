
//页面加载的事件
$(document).ready(function(){
	
	//给添加商品到购物车的按钮,绑定一个点击事件;
	var fun = function(event) {

        $.ajax({
            url: "addShopCar",
            data: {"gid": $(this).attr("gid")}, //$(this):代表当前(点击)的对象
            type: "post",
            success: function (msg) {
                console.log(msg);
                if (msg.code == 100) {
                    $("#cart_num").text(msg.total);
                    $("#cart_price").html(msg.totalCost);

                }
            }
            /*if(json.result == '0000'){ //添加成功

                //实现一个动画效果;
                div_left = $(event.target).offset().left;
                div_top = $(event.target).offset().top;

                //1:创建一个div;并且制定这个div的位置:

                $("body").append($("<div id='sc_div'>").addClass("shopcar_div").css('left',div_left).css('top',div_top));


                //2:使用动画效果,将div移到目标位置,并且删掉/隐藏;
                target_left = $("#cart_num").offset().left;
                target_top = $("#cart_num").offset().top;

                $("#sc_div").animate({left:target_left,top:target_top,width:"0px",height:"0px"},2000);

                setTimeout(function(){
                    $("#sc_div").remove();
                    $(".addshopcar").click(fun);
                }, 2010);

                //是对数据的展示

                //  numberCount:0
                //  priceCount:0.00
                var tt = json.resultMsg.split(",");

                var number = tt[0]; //商品的总数
                var price = tt[1];//商品的总价
                var str = '¥'+price+'('+number+')';

                $("#cart_num").html(number);
                $("#cart_price").html(str);

                // var str = json.msg.split(",");//"255.00,3"
                // $("#cart_num").html(str[1]);//str[1]:咱们的数量
                // $("#cart_price").html('¥'+str[0]+'('+str[1]+')');
            }else{
                alert(json.msg);
            }*/


        });
    }
	$(".addshopcar").click(fun);
	
});