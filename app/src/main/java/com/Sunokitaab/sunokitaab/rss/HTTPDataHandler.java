package com.Sunokitaab.sunokitaab.rss;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HTTPDataHandler {
    static String stream ;

    public HTTPDataHandler() {
    }

    public InputStream inputStream(URL url){

        try {
                return url.openConnection().getInputStream();
        }
        catch (IOException e){
            return null;
        }

    }

    public String GetHTTPData(String urlString)
    {
        try{
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();

            if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                BufferedReader r = new BufferedReader(new InputStreamReader(in));
                StringBuilder sb = new StringBuilder();
                String line;
                while((line = r.readLine()) != null)
                    sb.append(line);
                stream = sb.toString();
               // urlConnection.disconnect();
            }else{
                Log.d("HTTPDATAHANDLER", "else httpurlconncection");
            }

        } catch (MalformedURLException e) {

            Log.d("HTTPDATAHANDLER", "Malformed Url exception");
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("HTTPDATAHANDLER", "IOException");
            e.printStackTrace();
        }

        return stream;
    }
}
