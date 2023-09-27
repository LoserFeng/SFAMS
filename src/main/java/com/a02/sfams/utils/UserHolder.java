package com.a02.sfams.utils;

public class UserHolder {
    private static final ThreadLocal<String> tl = new ThreadLocal<>();

    public static void saveUser(String user){
        tl.set(user);
    }

    public static String getUser(){
        return tl.get();
    }

    public static void removeUser(){
        tl.remove();
    }


}