package com.thibault.dufour.channelmessaging;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.thibault.dufour.channelmessaging.model.Channel;

import java.util.List;

/**
 * Created by dufourth on 29/01/2018.
 */
public class ChannelArrayAdapter extends ArrayAdapter<Channel> {
    private final Context context;
    private final List<Channel> values;

    public ChannelArrayAdapter(Context context, List<Channel> values) {
        super(context, R.layout.activity_channellist, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
        TextView nomChannel =  (TextView) rowView.findViewById(R.id.NameTitle);
        nomChannel.setText(values.get(position).getName());
        TextView nbUser =  (TextView) rowView.findViewById(R.id.infos);
        nbUser.setText("Nombre d'utilisateurs connectés : "+values.get(position).getConnectedUsers());
        return rowView;
    }

}
