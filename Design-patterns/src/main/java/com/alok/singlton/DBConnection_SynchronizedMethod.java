package com.alok.singlton;

// Problem with this method is "It is very expensive"
// When 1000 of request are came at the same time then 1000 time lock will be captured and
// 1000 time is will be removed, So this method is very expensive.

public class DBConnection_SynchronizedMethod {
    private static DBConnection_SynchronizedMethod conObject;

    private DBConnection_SynchronizedMethod(){
    }
    synchronized public static DBConnection_SynchronizedMethod getInstance(){
        if(conObject == null){
            conObject = new DBConnection_SynchronizedMethod();
        }
        return conObject;
    }
}

