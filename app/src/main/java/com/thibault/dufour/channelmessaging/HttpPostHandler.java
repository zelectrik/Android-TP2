package com.thibault.dufour.channelmessaging;

import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by dufourth on 19/01/2018.
 */
public class HttpPostHandler extends AsyncTask<String, String, String> {

    private ArrayList<OnDownloadListener> list_listener = new ArrayList<OnDownloadListener>();

    public void addOnDownloadListener(OnDownloadListener listener) {
        list_listener.add(listener);
    }

    @Override
    protected String doInBackground(String... params) {
        HashMap param = new HashMap();
        param.put("username",params[0]);
        param.put("password",params[1]);
        String retour = performPostCall("http://raphaelbischof.fr/messaging/?function=connect",param);
        return retour;
    }

    @Override
    protected void onPostExecute(String result) {
        for (OnDownloadListener oneListener : list_listener)
        {
            Gson gson = new Gson();
            Response temp = gson.fromJson(result,Response.class);
            if(temp.getCode().equals("500"))
            {
                oneListener.onDownloadError(temp.getResponse());

            }
            else if(temp.getCode().equals("200"))
            {
                oneListener.onDownloadComplete(temp.getAccesstoken());

            }

        }
    }

    public String performPostCall(String requestURL, HashMap<String, String> postDataParams) {
        URL url;
        String response = "";
        try {
            url = new URL(requestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));
            writer.flush();
            writer.close();
            os.close();
            int responseCode= conn.getResponseCode();
            if(responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else {
                response= "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first) first = false;
            else result.append("&");
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }


}
