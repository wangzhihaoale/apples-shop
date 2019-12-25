package com.edu.wzh.code;

import java.util.HashMap;
import java.util.Map;

public class Msg {
    private  int code;
    private  String  msg;
    private int total;
    private float totalCost;
    private float currentPrice;
    private Map<String,Object> map=new HashMap<String, Object>();

    private  float selectPrice;

    public float getSelectPrice() {
        return selectPrice;
    }

    public void setSelectPrice(float selectPrice) {
        this.selectPrice = selectPrice;
    }

    public  static Msg  success(){
        Msg result=new Msg();
        result.setCode(100);
        result.setMsg("处理成功");
        return result;
    }
    public  static Msg  fail(){
        Msg result=new Msg();
        result.setCode(200);
        result.setMsg("该请求异常");
        return result;
    }

    public Msg add(String key,Object value){
        this.getMap().put(key,value);
        return  this;
    }
    public Msg addto(float currentPrice){
        this.setCurrentPrice(currentPrice);
        return  this;
    }
    public Msg add(int total){
        this.setTotal(total);
        return  this;
    }
    public Msg add(float totalCost){
        this.setTotalCost(totalCost);
        return  this;
    }
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public float getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(float currentPrice) {
        this.currentPrice = currentPrice;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
