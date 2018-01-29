package com.thibault.dufour.channelmessaging;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.gson.Gson;
import com.thibault.dufour.channelmessaging.model.ChannelList;

import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Created by dufourth on 29/01/2018.
 */
public class ChannelActivity extends AppCompatActivity implements OnDownloadListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);


        // Restore preferences
        SharedPreferences settings = getSharedPreferences(LoginActivity.PREFS_NAME, 0);
        String accestoken = settings.getString("accesstoken", "");
        String channelId = settings.getString("ChanelId","marche pas");
        Toast.makeText(getApplicationContext(),channelId ,Toast.LENGTH_SHORT).show();

        HashMap<String,String> temp = new HashMap<String,String>();
        temp.put("accesstoken",accestoken);
        HttpPostHandler connection = (HttpPostHandler) new HttpPostHandler().execute( new PostRequest("?function=getchannels",temp));

        connection.addOnDownloadListener(this);
    }

    @Override
    public void onDownloadComplete(String downloadedContent) {
        Gson gson =  new Gson();
        ChannelList listeChannels = gson.fromJson(downloadedContent, ChannelList.class);

    }

    @Override
    public void onDownloadError(String error) {

    }
}
