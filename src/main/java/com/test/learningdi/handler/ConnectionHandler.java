package com.test.learningdi.handler;

import com.test.learningdi.db.DbApi;

/***
 * ConnectionHandler opens and closes the db connection, safe exit is a must for db connections
 */
public class ConnectionHandler {

    private DbApi dbApiInstance;
    private DbAuthentication dbAuthentication;

    public ConnectionHandler(DbAuthentication dbAuthentication){
        this.dbAuthentication = dbAuthentication;
        this.dbApiInstance = new DbApi();
    }

    public void openConnectionGracefully(){
        if(this.dbAuthentication.isSuccess()) {
            this.dbApiInstance.open();
        }
    }

    public void closeConnectionGracefully(){
        this.dbApiInstance.close();
    }

    public DbApi getDbApiInstance(){
        return this.dbApiInstance;
    }
}
