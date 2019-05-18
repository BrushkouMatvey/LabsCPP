package com.Cache;

import com.Entity.Parameters;
import com.Entity.Triangle;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class CacheMap {
    private HashMap<Parameters, Triangle> map;

    public CacheMap() {
        map = new HashMap<>();
    }

    public void put(Parameters key, Triangle rectangle) {
        map.put(key, rectangle);
    }

    public Triangle get(Parameters key) {
        return map.get(key);
    }

    public boolean containKey(Parameters key) {
        if (map.size() == 0)
            return false;
        Set<Parameters> keys = map.keySet();
        return keys.contains(key);
    }

    public Set<Map.Entry<Parameters, Triangle>> getEntrySet(){
        return map.entrySet();
    }

    public boolean isEmpty(){
        return map.isEmpty();
    }
}
