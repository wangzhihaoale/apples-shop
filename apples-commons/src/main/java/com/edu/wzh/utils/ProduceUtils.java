package com.edu.wzh.utils;

import java.util.UUID;

public class ProduceUtils {

    public static String produceUUID(){
        return  UUID.randomUUID().toString().replace("-","");
    }
}
