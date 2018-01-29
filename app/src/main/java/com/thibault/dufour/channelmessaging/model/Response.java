package com.thibault.dufour.channelmessaging.model;

import java.util.Hashtable;

/**
 * Created by tdufo on 21/01/2018.
 */

public class Response {


    private String response;
    private String code;
    private String accesstoken;


    public Response(String... params)
    {
        switch(params.length)
        {
            case 2:
                response = params[0];
                code = params[1];
                break;
            case 3:
                response = params[0];
                code = params[1];
                accesstoken = params[2];
                break;
        }
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }

}
