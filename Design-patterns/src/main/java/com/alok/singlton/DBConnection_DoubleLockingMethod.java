package com.alok.singlton;


// this method is used in the industry for the singleton Design pattern

public class DBConnection_DoubleLockingMethod {
    private static DBConnection_DoubleLockingMethod conObject;

    private DBConnection_DoubleLockingMethod(){
    }
    synchronized public static DBConnection_DoubleLockingMethod getInstance(){
        if(conObject == null){
            conObject = new DBConnection_DoubleLockingMethod();
        }
        return conObject;
    }
}
