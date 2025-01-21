package com.alok.singlton;


// Problem in this method is when 2 threads are came at same time then their may occur race condition
// and this problem is overcome with the synchronized method

public class DBConnection_LazyMethod {
    private static DBConnection_LazyMethod conObject;

    private DBConnection_LazyMethod(){
    }
    public static DBConnection_LazyMethod getInstance(){
        if(conObject == null){
            conObject = new DBConnection_LazyMethod();
        }
        return conObject;
    }
}
