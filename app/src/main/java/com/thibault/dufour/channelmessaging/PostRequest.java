package com.thibault.dufour.channelmessaging;

import java.util.HashMap;

/**
 * Created by dufourth on 22/01/2018.
 */
public class PostRequest {
    private String URL;
    private HashMap<String,String> params;

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }

    public PostRequest(String _url, HashMap<String,String> _params)
    {
        setURL(_url);
        setParams(_params);
    }
}
