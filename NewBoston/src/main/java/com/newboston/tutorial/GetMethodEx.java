package com.newboston.tutorial;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * Created by Chris on 30.09.13.
 */
public class GetMethodEx {

    public String getInternetData() {
        BufferedReader in = null;
        String data = null;
        try {
            Log.d("mytag", "trying to get data");
            HttpClient client = new DefaultHttpClient();
            URI website = new URI("http://www.mybringback.com");
            Log.d("mytag", "2");
            HttpGet request = new HttpGet();
            request.setURI(website);
            Log.d("mytag", "3");
            try {
                HttpResponse response = client.execute(request);
                Log.d("mytag", "3.5");
                in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                Log.d("mytag", "4");
                StringBuffer sb = new StringBuffer("");
                String line = "";
                Log.d("mytag", "5");
                String newLine = System.getProperty("line.separator");
                Log.d("mytag", "6");
                while ((line = in.readLine()) != null) {
                    Log.d("mytag", "reading line");
                    sb.append(line + newLine);
                }
                Log.d("mytag", "7");
                in.close();
                data = sb.toString();
                Log.d("mytag", data);
                return data;
            } catch (Exception e) {
                Log.d("mytag", e.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) try {
                in.close();
                return data;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data;
    }

}
