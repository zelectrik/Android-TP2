package com.thibault.dufour.channelmessaging;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.thibault.dufour.channelmessaging.model.Channel;
import com.thibault.dufour.channelmessaging.model.Message;

import java.util.List;

/**
 * Created by dufourth on 05/02/2018.
 */
public class MessageArrayAdaptater  extends ArrayAdapter<Message> {
    private final Context context;
    private final List<Message> values;

    public MessageArrayAdaptater(Context context, List<Message> values) {
        super(context, R.layout.activity_channel, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowmessagelayout, parent, false);
        TextView UserName =  (TextView) rowView.findViewById(R.id.UserText);
        UserName.setText((CharSequence) values.get(position).getUsername() + " : ");
        TextView MessageText =  (TextView) rowView.findViewById(R.id.MessageText);
        MessageText.setText(values.get(position).getMessage());
        TextView DateText =  (TextView) rowView.findViewById(R.id.dateText);
        DateText.setText(values.get(position).getDate());
        return rowView;
    }

}
