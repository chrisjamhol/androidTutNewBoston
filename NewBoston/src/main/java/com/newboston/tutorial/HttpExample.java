package com.newboston.tutorial;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Chris on 30.09.13.
 */
public class HttpExample extends Activity {

    final static String URL = "http://echo.jsontest.com/text/thisisthetesttext";
    TextView httpStuff;
    HttpClient client;
    JSONObject json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.httpexample);
        httpStuff = (TextView) findViewById(R.id.tvHttp);
        client = new DefaultHttpClient();
        new Read().execute("text");
    }

    public JSONObject lastTweet(String username) throws ClientProtocolException, IOException, JSONException {
        StringBuilder url = new StringBuilder(URL);
        //url.append(username);

        HttpGet get = new HttpGet(url.toString());
        HttpResponse response = client.execute(get);
        int status = response.getStatusLine().getStatusCode();
        Log.d("mytag", "" + status);
        if (status == 200) {
            HttpEntity e = response.getEntity();
            String data = EntityUtils.toString(e);
            Log.d("mytag", data);
            JSONArray timeline = new JSONArray(data);
            JSONObject last = timeline.getJSONObject(0);
            return last;
        } else {
            Toast.makeText(HttpExample.this, "error", Toast.LENGTH_SHORT);
            return null;
        }
    }

    public class Read extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                json = lastTweet("mybringback");
                Log.d("mytag", json.getString(params[0]));
                return json.getString(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            httpStuff.setText(s);
        }
    }
}
