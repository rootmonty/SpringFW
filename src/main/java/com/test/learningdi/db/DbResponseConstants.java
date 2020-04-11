package com.test.learningdi.db;

public class DbResponseConstants {

    static final Integer ADDED_SUCCESS = 1;
    static final Integer ALREADY_EXISTS = 2;
    static final Integer UPDATED_SUCCESS = 3;
    static final Integer DELETED_SUCCESS = 4;
    static final Integer READ_SUCCESS = 5;
    static final Integer DEFAULT = 0;
    static final Integer FAILED = -1;

    static final Integer DB_CLOSED = -1;
    static final Integer DB_OPEN = 1;

    public static final String URL = "java://my.db";
    public static final String password = "jiny123";
}
