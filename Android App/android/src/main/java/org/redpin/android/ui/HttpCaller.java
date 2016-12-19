package org.redpin.android.ui;

/**
 * Created by chimawarade on 11/22/2016.
 */
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;


public class HttpCaller extends AsyncTask<String, Void, Void> {
    public static String URL = "http://35.166.37.140:3000/test";
    private final HttpClient Client = new DefaultHttpClient();
    private String Content;
    private String Error = null;
    private final String TAG = null;

    // private ProgressDialog Dialog = new ProgressDialog(MainActivity.this);

    @Override
    protected void onPreExecute() {
        // Dialog.setMessage("Wait..");
        //Dialog.show();
        Log.e(TAG, "------------------------------------- Here");
    }

    protected Void doInBackground(String... urls) {

        try {
            String nm=urls[0];
            System.out.println("nm:" + nm);
            if(nm.equals("1")){
                URL = "http://35.166.37.140:3000/test3";
            }else if(nm.equals("three")){
                URL = "http://35.166.37.140:3000/test4";
            }else{
                URL = "http://35.166.37.140:3000/test";
            }
            java.net.URL url = new URL(URL);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write("values= ,"+nm);
/*            String[] data  = nm.split(" ");
            String address = data[0];
            String txpower = data[1];
            String rssi = data[2];
            String distance = data[3];
            //String s = "navjot";
            // writer.write("value=1&anotherValue=1");
            writer.write("address="+address+"");
            writer.write("&");
            writer.write("txpower="+ txpower+"");
            writer.write("&");
            writer.write("rssi="+ rssi+"");
            writer.write("&");
            writer.write("distance="+ distance+"");*/
            writer.write(",\n");
            writer.flush();
            String line;
            // log.finer("I am before bufferreader: " + loc_id);
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            // log.finer("I am before close");
            writer.close();
            reader.close();
            // log.finer("I am after close");
           /*     Log.e("Response", "--------------------------------" + Content);*/

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void result) {
        // TODO Auto-generated method stub
        // Dialog.dismiss();
        Log.e("ERROR", "--------------------------------" + Content);
        //  Toast.makeText(getBaseContext(), Content, Toast.LENGTH_LONG).show();
    }

}
