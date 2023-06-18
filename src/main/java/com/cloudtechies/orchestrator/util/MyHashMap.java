package com.cloudtechies.orchestrator.util;

import java.util.LinkedHashMap;

public class MyHashMap extends LinkedHashMap<Object,Object> {

    public String put(Object key, Object val){
        String upperCase = key.toString().trim().toUpperCase();
        if(super.containsKey(upperCase) && super.get(upperCase)!=null){
            return (String) super.put(upperCase,super.get(upperCase)+","+val);
        }
        return (String) super.put(upperCase,val);
    }
}
