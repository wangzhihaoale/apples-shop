package com.edu.sale.web;

import com.edu.sale.service.SaleService;
import com.edu.sale.timeTask.TimeTasks;
import com.edu.wzh.code.Msg;
import com.edu.wzh.entity.*;
import com.edu.wzh.utils.ObjectUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.number.money.CurrencyUnitFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.nio.channels.SeekableByteChannel;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@SessionAttributes("user")
public class SaleController {
    @Autowired
    private  SaleService saleService;

 /*   @RequestMapping("getGoodsType")
    @ResponseBody
    public Msg getGoodsType(Goods goods){
        List<GoodsType> types =saleService.getGoodsType();//查询三条类型
        List<Goods> goodsList=saleService.getGoods(goods);

        return Msg.success().add("types",types).add("goodsList",goodsList);
    }*/

    @RequestMapping("getGoodsType")
    public String getGoodsType(Goods goods, Model model){
        List<GoodsType> types =saleService.getGoodsType();//查询三条类型
        List<Goods> goodsList=saleService.getGoods(goods);
        model.addAttribute("types",types);
        model.addAttribute("goodList",goodsList);
        return "/index.jsp";
    }

    @RequestMapping("queryGoods")
    public  String queryGoods(Goods good,Model model){
        List<Goods> goods =saleService.getGoods(good);
        model.addAttribute("goods",goods);
        model.addAttribute("good",good);
        PageInfo<Goods> goodsPage=new PageInfo<>(goods);
        model.addAttribute("goodsPage",goodsPage);
        return "/pages/prolist.jsp";
    }

    @RequestMapping("login")
    public  String  UserLogin(Customer customer,Model model){
        if(ObjectUtils.checkNull(customer)){
            model.addAttribute("errorMsg","用户或密码为空");
            return "/pages/login.jsp";
        }
        customer =saleService.login(customer);
        if(customer!=null){
            model.addAttribute("user",customer);
            return  "getGoodsType";
        }

        model.addAttribute("errorMsg","用户或密码错误");
       return "/pages/login.jsp";
    }


    /*
    * 添加购物车 添加商品
    * */
    @RequestMapping("addShopCar")
    @ResponseBody
    public  Msg addshopcar(HttpSession session,String gid){
        Map<String,Goods> shopcar= (Map<String, Goods>) session.getAttribute("shopcar");
        Goods goods=null;
        if(shopcar==null){
            shopcar=new HashMap<>();
        }
        if(shopcar.get(gid)==null){
             goods=saleService.selectGoodByGid(gid);
        }else {
             goods=shopcar.get(gid);
            goods.setNum(goods.getNum()+1);
        }
        shopcar.put(gid,goods);
        session.setAttribute("shopcar",shopcar);
        Msg msg=totalCost(shopcar);
        float  currentPrice=goods.getNum()*goods.getGprice();
        msg.addto(currentPrice);
        return msg;
    }


    public Msg totalCost(Map<String,Goods> map){
        HttpSession session=null;
            Set<String> gids =map.keySet();
            int total=0;
            float totalCost=0.0f;

            for (String gid : gids) {
                total+=map.get(gid).getNum();
                totalCost+=map.get(gid).getGprice()*map.get(gid).getNum();
            }
            return Msg.success().add(total).add(totalCost);
    }
    @RequestMapping("subShopCar")
    @ResponseBody
    public  Msg  subshopcar(HttpSession session,String gid){
        Map<String,Goods> shopcar= (Map<String, Goods>) session.getAttribute("shopcar");
        Goods goods=shopcar.get(gid);
        if(shopcar==null){
            return Msg.fail();
        }
        if(goods==null){
            return Msg.fail();
        }
        if(goods.getNum()==1){
            shopcar.remove(gid);
            Msg msg=totalCost(shopcar);
            msg.setCode(300);
            return  msg;

        }else {
            goods.setNum(goods.getNum()-1);
        }
        shopcar.put(gid,goods);
        session.setAttribute("shopcar",shopcar);

        Msg msg=totalCost(shopcar);
        float  currentPrice=goods.getNum()*goods.getGprice();
        msg.addto(currentPrice);
        return msg;
    }

    /*
    * 回显总价
    * */
    @RequestMapping("echoShopCar")
    @ResponseBody
    public  Msg  echoTotalCaost(HttpSession session,String[] gids) {
        Map<String,Goods> shopcar= (Map<String, Goods>) session.getAttribute("shopcar");
        Goods goods=null;
        float selectPrice=0.0f;
        if(shopcar==null){
            shopcar=new HashMap<>();
        }
        if(gids!=null){
            for (String gid : gids) {
                goods=shopcar.get(gid);
                selectPrice+=goods.getGprice()*goods.getGdiscount()*goods.getNum();
            }
        }
        Msg msg=totalCost(shopcar);
        msg.setSelectPrice(selectPrice);
        return msg;
    }
    @RequestMapping("cleanShopCar")
    public  String  cleanShopCar(HttpSession session) {
         session.removeAttribute("shopcar");
        return "redirect:/pages/shopcart.jsp";
    }

    @RequestMapping("create")
    public String CreateOrders(String[] gids,HttpSession session,Model model){
        if(gids==null){
            return "redirect:/pages/shopcart.jsp";
        }
        Customer user = (Customer) session.getAttribute("user");
        if(user==null){
            return "redirect:/pages/login.jsp";
        }
        Map<String,Goods> shopcar = (Map<String, Goods>) session.getAttribute("shopcar");
        if(shopcar.size()==0){
            return "redirect:/pages/shopcart.jsp";
        }
        int addressId=1;
        Order order =saleService.createOrders(shopcar,addressId,gids,user.getAid());

        Timer timer=new Timer();
        TimeTasks tasks=new TimeTasks(order.getOid(),timer,session);
        timer.schedule(tasks,600000);

        session.getServletContext().setAttribute("timer",timer);

        session.setAttribute("shopcar",shopcar);
        model.addAttribute("order",order);
        return "/pages/orderinfo.jsp";
    }


    @RequestMapping("pay")
    public String payment(String oid,HttpSession session){
        Timer timer = (Timer) session.getServletContext().getAttribute("timer");
        if(timer==null){
            return "redirect:/pages/shopcart.jsp";
        }
        boolean b=saleService.updateOrderPay(oid);
        if(b){
            timer.cancel();
            session.getServletContext().removeAttribute("timer");
            return "/pages/pay.jsp";
        }
        return "redirect:/pages/shopcart.jsp";
    }

    @RequestMapping("getOrder")
    public  String getOrders(Model model,HttpSession session){
        Customer customer= (Customer) session.getAttribute("user");
        if(customer==null){
            return "redirect:/pages/login.jsp";
        }
        List<Order> orders=saleService.getOrders(customer.getAid());
        model.addAttribute("orders",orders);
        return "/pages/order.jsp";
    }

    @RequestMapping("getcustomer")
    public  String getCustomer(HttpSession session){
        Customer customer = (Customer) session.getAttribute("user");
        /*
        * 如果需要新表可以获取aid 查询
        * */
        return  "redirect:/pages/custominfo.jsp";
    }
    @RequestMapping("getOdate")
    @ResponseBody
    public  Msg  getOdate(String oid){
        Order order = saleService.getOrder(oid);
        long time = order.getOdate().getTime();
        Date date=new Date();
        long time1 = date.getTime();

        return Msg.success().add("date",time);
    }

    @RequestMapping("getorderinfo")
    public  String getOrderInfo(String oid,Model model){
        Order order = saleService.getOrder(oid);
        model.addAttribute("order",order);
        return "/pages/orderinfo.jsp";
    }


}


