package com.example.todolist;

import org.astonbitecode.j4rs.api.Instance;

public class RustDatabase {
    static {
        System.loadLibrary("todo_database");
    }

    private static boolean databaseStarted = false;

    static public void startDatabase(){
        if(!databaseStarted){
            databaseStarted = true;
            startDatabaseService();
        }
    }

    private static native void startDatabaseService();
    public static native void addTodoItem(Instance<String> todoItem,
                                               Instance<String> category);
    public static native Instance<String> getTodoList();
}
