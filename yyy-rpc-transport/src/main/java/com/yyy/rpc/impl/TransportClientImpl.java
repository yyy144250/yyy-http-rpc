package com.yyy.rpc.impl;

import com.yyy.rpc.TransportClient;
import model.Point;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TransportClientImpl implements TransportClient {
    private String url;
    @Override
    public void connect(Point point) {
        this.url="http://"+point.getHost()+":"+point.getPort();
    }

    @Override
    public InputStream write(InputStream data) {
        try {
            HttpURLConnection httpURLConnection= (HttpURLConnection) new URL(url).openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setUseCaches(false);

            httpURLConnection.connect();
            IOUtils.copy(data,httpURLConnection.getOutputStream());
            int code=httpURLConnection.getResponseCode();
            if(code==httpURLConnection.HTTP_OK){
                return httpURLConnection.getInputStream();
            }
            else{
                return httpURLConnection.getErrorStream();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() {

    }
}
