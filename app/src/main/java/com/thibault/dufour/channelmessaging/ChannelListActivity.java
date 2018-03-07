package com.thibault.dufour.channelmessaging;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.thibault.dufour.channelmessaging.Fragment.ChannelListFragment;
import com.thibault.dufour.channelmessaging.Fragment.MessageFragment;
import com.thibault.dufour.channelmessaging.model.Channel;
import com.thibault.dufour.channelmessaging.model.ChannelList;

import java.util.HashMap;

/**
 * Created by tdufo on 21/01/2018.
 */

public class ChannelListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channellist);


        // Restore preferences
        /*SharedPreferences settings = getSharedPreferences(LoginActivity.PREFS_NAME, 0);
        String accestoken = settings.getString("accesstoken", "");

        HashMap<String,String> temp = new HashMap<String,String>();
        temp.put("accesstoken",accestoken);



        HttpPostHandler connection = (HttpPostHandler) new HttpPostHandler().execute( new PostRequest("?function=getchannels",temp));

        connection.addOnDownloadListener(this);*/
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Channel item = (Channel) parent.getItemAtPosition(position);


        // Restore preferences
        SharedPreferences settings = getSharedPreferences(LoginActivity.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString("ChanelId", item.getChannelId());

        editor.commit();


        ChannelListFragment fragChannelList = (ChannelListFragment)getSupportFragmentManager().findFragmentById(R.id.fragChannelList);
        MessageFragment fragMessage = (MessageFragment)getSupportFragmentManager().findFragmentById(R.id.fragChannel);
        if(fragMessage == null|| !fragMessage.isInLayout()){
            Intent intent = new Intent(getApplicationContext(), ChannelActivity.class);
            startActivity(intent);
        } else {
            fragMessage.DisplayMessage();
        }

    }






    /*@Override
    public void onDownloadComplete(String downloadedContent) {
        Gson gson =  new Gson();
        ChannelList listeChannels = gson.fromJson(downloadedContent, ChannelList.class);
        Toast.makeText(getApplicationContext(),downloadedContent,Toast.LENGTH_SHORT).show();
        listView_listeChannel.setAdapter(new ChannelArrayAdapter(getApplicationContext(),listeChannels.getListeChannel()));
        listView_listeChannel.setOnItemClickListener(this);
    }

    @Override
    public void onDownloadError(String error) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Channel item = (Channel)parent.getItemAtPosition(position);


        // Restore preferences
        SharedPreferences settings = getSharedPreferences(LoginActivity.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString("ChanelId", item.getChannelId());

        editor.commit();
        Intent intent = new Intent( getApplicationContext(), ChannelActivity.class);
        startActivity(intent);
    }*/
}
