package com.user.management.ratelimit;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Component
public class SlidingWindowRateLimiter {

    @Value("${rate.limit.maxRequest:10}")
    private int maxRequests;

    @Value("${rate.limit.windowSizeInMillis:10}")
    private long windowSizeInMillis;

    private Queue<Long> requestTimestamps;

    @PostConstruct
    public void initConfig() {
        this.maxRequests = maxRequests;
        this.windowSizeInMillis = windowSizeInMillis * 1000;
        this.requestTimestamps = new LinkedList<>();
    }

    public synchronized boolean allowRequest() {

        long currentTime = System.currentTimeMillis();

        // Step 1: Remove old requests outside window
        while (!requestTimestamps.isEmpty() &&
                currentTime - requestTimestamps.peek() > windowSizeInMillis) {
            requestTimestamps.poll();
        }

        // Step 2: Check current size
        if (requestTimestamps.size() < maxRequests) {
            requestTimestamps.offer(currentTime);
            return true; // allow
        }

        return false; // reject
    }
}
