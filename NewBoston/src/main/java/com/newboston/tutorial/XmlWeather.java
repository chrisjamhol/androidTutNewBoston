package com.newboston.tutorial;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Chris on 01.10.13.
 */
public class XmlWeather extends Activity implements View.OnClickListener {

    static final String baseUrl = "http://api.openweathermap.org/data/2.5/weather?q=";
    TextView tv;
    EditText city, state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xmlweather);
        Button b = (Button) findViewById(R.id.bSearchWeather);
        tv = (TextView) findViewById(R.id.tvCurrentWeather);
        city = (EditText) findViewById(R.id.etCity);
        state = (EditText) findViewById(R.id.etState);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String c = city.getText().toString();
        String s = state.getText().toString();
        StringBuilder url = new StringBuilder(baseUrl);
        url.append(c + "," + s + "&mode=xml&units=metric");
        String fullUrl = url.toString();
        try {
            new readingData().execute(new URL(fullUrl));
        } catch (Exception e) {
            e.printStackTrace();
            tv.setText("error");
        }
    }

    private class readingData extends AsyncTask<URL, Integer, Long> {
        String information;

        @Override
        protected Long doInBackground(URL... params) {
            URL website = params[0];
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = null;
            try {
                sp = spf.newSAXParser();
                XMLReader xr = sp.getXMLReader();
                HandlingXMLStuff handler = new HandlingXMLStuff();
                xr.setContentHandler(handler);
                xr.parse(new InputSource(website.openStream()));
                information = handler.getInformation();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        // This is called when doInBackground() is finished
        protected void onPostExecute(Long result) {
            tv.setText(information);
        }
    }
}
