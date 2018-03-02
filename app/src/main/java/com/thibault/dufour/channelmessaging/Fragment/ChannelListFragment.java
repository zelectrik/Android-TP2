package com.thibault.dufour.channelmessaging.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.thibault.dufour.channelmessaging.ChannelActivity;
import com.thibault.dufour.channelmessaging.ChannelArrayAdapter;
import com.thibault.dufour.channelmessaging.HttpPostHandler;
import com.thibault.dufour.channelmessaging.LoginActivity;
import com.thibault.dufour.channelmessaging.OnDownloadListener;
import com.thibault.dufour.channelmessaging.PostRequest;
import com.thibault.dufour.channelmessaging.R;
import com.thibault.dufour.channelmessaging.model.Channel;
import com.thibault.dufour.channelmessaging.model.ChannelList;

import java.util.HashMap;

/**
 * Created by dufourth on 26/02/2018.
 */
public class ChannelListFragment extends Fragment implements OnDownloadListener, AdapterView.OnItemClickListener {

    private ListView listView_listeChannel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_channellist, container);

        listView_listeChannel = (ListView) v.findViewById(R.id.listView_channellist);


        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Restore preferences

        SharedPreferences settings = getContext().getSharedPreferences(LoginActivity.PREFS_NAME, 0);
        String accestoken = settings.getString("accesstoken", "");

        HashMap<String, String> temp = new HashMap<String, String>();
        temp.put("accesstoken", accestoken);


        HttpPostHandler connection = (HttpPostHandler) new HttpPostHandler().execute(new PostRequest("?function=getchannels", temp));

        connection.addOnDownloadListener(this);
    }


    @Override
    public void onDownloadComplete(String downloadedContent) {
        Gson gson = new Gson();
        ChannelList listeChannels = gson.fromJson(downloadedContent, ChannelList.class);
        Toast.makeText(this.getContext().getApplicationContext(), downloadedContent, Toast.LENGTH_SHORT).show();
        listView_listeChannel.setAdapter(new ChannelArrayAdapter(this.getContext().getApplicationContext(), listeChannels.getListeChannel()));
        listView_listeChannel.setOnItemClickListener(this);
    }

    @Override
    public void onDownloadError(String error) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Channel item = (Channel) parent.getItemAtPosition(position);


        // Restore preferences
        SharedPreferences settings = getContext().getSharedPreferences(LoginActivity.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString("ChanelId", item.getChannelId());

        editor.commit();

        ChannelListFragment fragChannelList = (ChannelListFragment)getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment2);
        if(fragChannelList == null|| !fragChannelList.isInLayout()){
            Intent intent = new Intent(getContext().getApplicationContext(), ChannelActivity.class);
            startActivity(intent);
        } else {
            fragChannelList = new ChannelListFragment();
        }

    }
}
