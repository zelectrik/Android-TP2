package com.thibault.dufour.channelmessaging;

/**
 * Created by dufourth on 19/01/2018.
 */
public interface OnDownloadListener {
    public void onDownloadComplete(String downloadedContent);
    public void onDownloadError(String error);
}
