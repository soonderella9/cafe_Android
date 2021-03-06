package com.example.gginiggini.Class;


import android.os.AsyncTask;

import com.example.gginiggini.Class.BaseURL;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 이용준 on 2016-11-26.
 * This class is used for serverconnection with access database
 */
public class SendPost extends AsyncTask<Void, Void, String> {
    private String http;
    private BaseURL base= new BaseURL();

    public void setJsonParam(JSONObject jsonParam) {
        this.jsonParam = jsonParam;
    }

    JSONObject jsonParam = new JSONObject();
    protected String doInBackground(Void... unused) {
        String content = executeClient();
        return content;
    }

    // first param = json object, second param = url
    public SendPost(JSONObject jsonParam, String Http){this.jsonParam = jsonParam; this.http = base.getURL() + Http;};

    protected void onPostExecute(String result) {
    }

    public String executeClient() {

        StringBuilder sb = new StringBuilder();
        String return_test;

        HttpURLConnection urlConnection=null;

        try {
            URL url = new URL(http);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setUseCaches(false);
            urlConnection.setConnectTimeout(10000);
            urlConnection.setReadTimeout(10000);
            urlConnection.setRequestProperty("Content-Type","application/json");
            urlConnection.setRequestProperty("Host", "android.schoolportal.gr");
            urlConnection.connect();


            OutputStreamWriter out = new   OutputStreamWriter(urlConnection.getOutputStream());
            out.write(jsonParam.toString());
            out.close();

            int HttpResult =urlConnection.getResponseCode();
            if(HttpResult == HttpURLConnection.HTTP_OK){
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        urlConnection.getInputStream(),"utf-8"));
                String line = null;

                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                System.out.println(""+sb.toString());
            }else{
                System.out.println(urlConnection.getResponseMessage());
            }
        } catch (MalformedURLException e) {

            e.printStackTrace();
        }
        catch (IOException e) {

            e.printStackTrace();
        } finally{
            if(urlConnection!=null)
                urlConnection.disconnect();
        }
        return_test = sb.toString();
        return return_test;
    }
}