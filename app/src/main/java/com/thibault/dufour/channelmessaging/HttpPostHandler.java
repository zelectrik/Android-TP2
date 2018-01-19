package com.thibault.dufour.channelmessaging;

import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by dufourth on 19/01/2018.
 */
public class HttpPostHandler extends AsyncTask<String,String,String> {
    private ArrayList<OnDownloadListener> list_listener = new ArrayList<OnDownloadListener>();

    public void addOnDownloadListener(OnDownloadListener listener)
    {
        list_listener.add(listener);
    }


    @Override
    protected String doInBackground(String... params) {
        return null;
    }
}
