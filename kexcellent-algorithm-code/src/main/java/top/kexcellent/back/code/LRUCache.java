/**
 * LY.com Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package top.kexcellent.back.code;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author kanglele
 * @version $Id: LRUCache, v 0.1 2025/2/24 14:50 kanglele Exp $
 */
public class LRUCache {
    Map<Integer, Integer> lru = new LinkedHashMap<>();
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Integer value = lru.remove(key);
        if (value == null) {
            return -1;
        }
        lru.put(key, value);
        return value;
    }

    public void put(int key, int value) {
        if (lru.remove(key) != null) {
            lru.put(key, value);
            return;
        }
        if (lru.size() >= capacity) {
            Integer old = lru.keySet().iterator().next();
            lru.remove(old);
        }
        lru.put(key, value);
    }
}
