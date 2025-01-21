package com.alok.singlton;

public class DBConnection_EagerMethod {
    private static DBConnection_EagerMethod conObject = new DBConnection_EagerMethod();

    private DBConnection_EagerMethod(){
    }

    public static DBConnection_EagerMethod getInstance(){
        return conObject;
    }
}
