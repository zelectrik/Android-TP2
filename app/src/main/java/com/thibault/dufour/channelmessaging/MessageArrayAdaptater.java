package com.thibault.dufour.channelmessaging;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.thibault.dufour.channelmessaging.model.Channel;
import com.thibault.dufour.channelmessaging.model.Message;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
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
        ImageView userImage =  (ImageView) rowView.findViewById(R.id.UserImage);
        Glide.with(getContext()).load("https://lh3.googleusercontent.com/m77Uxeqy0bxagYXD73tf_rvBK0uq3NrS-_icomE2ZPAYEJo3tUVTLK5Ca7E-YXFcSg5EldJ62LA=w128-h128-e365").into(userImage);

        TextView UserName =  (TextView) rowView.findViewById(R.id.UserText);
        UserName.setText((CharSequence) values.get(position).getUsername() + " : ");
        TextView MessageText =  (TextView) rowView.findViewById(R.id.MessageText);
        MessageText.setText(values.get(position).getMessage());
        TextView DateText =  (TextView) rowView.findViewById(R.id.dateText);
        DateText.setText(values.get(position).getDate());
        return rowView;
    }



}
