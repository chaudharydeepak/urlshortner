package com.dccorp.urlshortner.util;

@FunctionalInterface
public interface AppUtility {

    void printLog(String logString);

    default String provideDefFuntionality()
    {
        System.out.println("providing provideDefFuntionality");
        return "provideDefFuntionality";
    }
}
