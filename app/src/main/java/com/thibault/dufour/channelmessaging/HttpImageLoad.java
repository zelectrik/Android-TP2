package com.thibault.dufour.channelmessaging;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

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
 * Created by dufourth on 26/02/2018.
 */
public class HttpImageLoad extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public HttpImageLoad(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Log.i("url : ", urldisplay);
        Bitmap mIcon11 = null;
        try {
            URL url = new URL(urldisplay);
            mIcon11 = BitmapFactory.decodeStream(url.openConnection().getInputStream());

            if (null != mIcon11)
                Log.i("BITMAP", "ISONOTNULL");
            else
                Log.i("BITMAP", "ISNULL");
        } catch (Exception e) {
            Log.e("Error", " bitmap introuvable");

        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}