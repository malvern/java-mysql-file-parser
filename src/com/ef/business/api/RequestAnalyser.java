package com.ef.business.api;

@FunctionalInterface
public interface RequestAnalyser {
   void requestAnalytics(String startDate,String duration,String threshold);
}
