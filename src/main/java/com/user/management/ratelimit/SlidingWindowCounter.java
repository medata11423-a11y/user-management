package com.user.management.ratelimit;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * ⚖️ Sliding Log vs Sliding Counter
 * Feature	Sliding Log	Sliding Counter
 * Stores	Every timestamp	Count per time bucket
 * Memory	O(N requests)	O(window size)
 * Accuracy	Exact	Slightly approximate
 * Better for	Low traffic	High traffic
 */
public class SlidingWindowCounter {

    private final int maxRequests;
    private final long windowSizeInSeconds;

    private final Map<Long, Integer> buckets = new HashMap<>();
    private int currentTotal = 0;

    public SlidingWindowCounter(int maxRequests, long windowSizeInSeconds) {
        this.maxRequests = maxRequests;
        this.windowSizeInSeconds = windowSizeInSeconds;
    }

    public synchronized boolean allowRequest() {

        long currentTime = System.currentTimeMillis() / 1000; // current second
        long windowStart = currentTime - windowSizeInSeconds;

        // Step 1: Remove expired buckets
        Iterator<Map.Entry<Long, Integer>> iterator = buckets.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Long, Integer> entry = iterator.next();
            if (entry.getKey() <= windowStart) {
                currentTotal -= entry.getValue();
                iterator.remove();
            }
        }

        // Step 2: Check limit
        if (currentTotal >= maxRequests) {
            return false;
        }

        // Step 3: Add current request
        buckets.put(currentTime,
                buckets.getOrDefault(currentTime, 0) + 1);

        currentTotal++;
        return true;
    }
}

