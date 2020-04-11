package com.test.learningdi.handler;

import com.test.learningdi.db.DbResponseConstants;
import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;

@Getter
@Setter
public class DbAuthentication {

    String urlOfDb;
    String password;

    public DbAuthentication(String urlOfDb, String password){
        this.urlOfDb = urlOfDb;
        this.password = password;
    }

    public boolean isSuccess(){
        return this.urlOfDb != null
        && this.password != null &&
                this.urlOfDb.contains(DbResponseConstants.URL) && this.password.contains(DbResponseConstants.password);
    }
}
