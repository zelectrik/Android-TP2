package com.thibault.dufour.channelmessaging.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.thibault.dufour.channelmessaging.HttpPostHandler;
import com.thibault.dufour.channelmessaging.LoginActivity;
import com.thibault.dufour.channelmessaging.MessageArrayAdaptater;
import com.thibault.dufour.channelmessaging.OnDownloadListener;
import com.thibault.dufour.channelmessaging.PostRequest;
import com.thibault.dufour.channelmessaging.R;
import com.thibault.dufour.channelmessaging.model.MessageList;

import java.util.HashMap;

/**
 * Created by dufourth on 02/03/2018.
 */
public class MessageFragment extends Fragment implements OnDownloadListener,View.OnClickListener {

    ListView listView_messages;
    EditText text_message;
    Button buton_message;
    Handler handler;
    Runnable r;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_channel, container);

        listView_messages = (ListView)v.findViewById(R.id.listView_message);
        text_message = (EditText)v.findViewById(R.id.editText_message);
        buton_message = (Button)v.findViewById(R.id.button_envoyer);

        handler = new Handler();
        r = new Runnable() {
            public void run() {
                DisplayMessage();
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(r, 1000);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Restore preferences

        buton_message.setOnClickListener(this);



    }



    @Override
    public void onClick(View v) {
        if(text_message.getText().length() > 0) {
            // Restore preferences
            SharedPreferences settings = getContext().getSharedPreferences(LoginActivity.PREFS_NAME, 0);
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
                    Toast.makeText(getActivity(), "J'envoie", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onDownloadError(String error) {

                }
            });
            text_message.setText("");
        }
    }

    /*@Override
    public void onBackPressed() {
        handler.removeCallbacks(r);
        finish();
        return;
    }*/



    public void DisplayMessage()
    {
        // Restore preferences
        SharedPreferences settings = getContext().getSharedPreferences(LoginActivity.PREFS_NAME, 0);
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
        SharedPreferences settings = getContext().getSharedPreferences(LoginActivity.PREFS_NAME, 0);

        Toast.makeText(getContext().getApplicationContext(), settings.getString("ChanelId",""), Toast.LENGTH_SHORT).show();

        Gson gson = new Gson();
        MessageList listeMessage = gson.fromJson(downloadedContent, MessageList.class);
        listView_messages.setAdapter(new MessageArrayAdaptater(getContext().getApplicationContext(), listeMessage.getMessages()));
    }



    @Override
    public void onDownloadError(String error) {

    }
}
