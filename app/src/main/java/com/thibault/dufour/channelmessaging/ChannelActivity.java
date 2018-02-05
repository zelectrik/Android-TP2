package com.thibault.dufour.channelmessaging;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.thibault.dufour.channelmessaging.model.ChannelList;
import com.thibault.dufour.channelmessaging.model.Message;
import com.thibault.dufour.channelmessaging.model.MessageList;
import com.thibault.dufour.channelmessaging.model.Response;

import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Created by dufourth on 29/01/2018.
 */
public class ChannelActivity extends AppCompatActivity implements OnDownloadListener,View.OnClickListener {

    ListView listView_messages;
    EditText text_message;
    Button buton_message;
    Handler handler;
    Runnable r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);

        listView_messages = (ListView)findViewById(R.id.listView_message);
        text_message = (EditText)findViewById(R.id.editText_message);
        buton_message = (Button)findViewById(R.id.button_envoyer);

        buton_message.setOnClickListener(this);

        handler = new Handler();
        r = new Runnable() {
            public void run() {
                DisplayMessage();
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(r, 1000);
    }

    @Override
    public void onClick(View v) {
        if(text_message.getText().length() > 0) {
            // Restore preferences
            SharedPreferences settings = getSharedPreferences(LoginActivity.PREFS_NAME, 0);
            String accestoken = settings.getString("accesstoken", "");
            String channelId = settings.getString("ChanelId", "");

            HashMap<String, String> temp = new HashMap<String, String>();
            temp.put("accesstoken", accestoken);
            temp.put("channelid", channelId);
            temp.put("message", text_message.getText().toString());
            HttpPostHandler sendMessage = (HttpPostHandler) new HttpPostHandler().execute(new PostRequest("?function=sendmessage", temp));

            sendMessage.addOnDownloadListener(new OnDownloadListener() {
                @Override
                public void onDownloadComplete(String downloadedContent) {
                    Toast.makeText(getApplicationContext(), "Envoyé", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onDownloadError(String error) {
                    Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
                }
            });
            text_message.setText("");
        }
    }

    @Override
    public void onBackPressed() {
        handler.removeCallbacks(r);
        finish();
        return;
    }



    private void DisplayMessage()
    {
        // Restore preferences
        SharedPreferences settings = getSharedPreferences(LoginActivity.PREFS_NAME, 0);
        String accestoken = settings.getString("accesstoken", "");
        String channelId = settings.getString("ChanelId","");

        HashMap<String,String> temp = new HashMap<String,String>();
        temp.put("accesstoken",accestoken);
        temp.put("channelid",channelId);

        HttpPostHandler connection = (HttpPostHandler) new HttpPostHandler().execute( new PostRequest("?function=getmessages",temp));
        connection.addOnDownloadListener(this);
    }

    @Override
    public void onDownloadComplete(String downloadedContent) {
        Gson gson = new Gson();
        MessageList listeMessage = gson.fromJson(downloadedContent, MessageList.class);
        Toast.makeText(getApplicationContext(), "LOL", Toast.LENGTH_SHORT).show();
        listView_messages.setAdapter(new MessageArrayAdaptater(getApplicationContext(), listeMessage.getMessages()));
    }



    @Override
    public void onDownloadError(String error) {

    }
}
