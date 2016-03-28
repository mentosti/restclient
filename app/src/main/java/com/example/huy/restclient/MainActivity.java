package com.example.huy.restclient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import com.example.huy.restclient.User;

public class MainActivity extends Activity {
    ListView txtResponseJava;
    private CustomAdapter adapter;

    private String getB64Auth (String login, String pass) {
        String source=login+":"+pass;
        String ret="Basic "+ Base64.encodeToString(source.getBytes(), Base64.URL_SAFE | Base64.NO_WRAP);
        return ret;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtResponseJava = (ListView) findViewById(R.id.txtResponseJava);
        String SERVER_URL = "http://192.168.100.8:8080/restful/user/all";

        new LongOperation(this).execute(SERVER_URL);
    }
    private class LongOperation extends AsyncTask<String, Void, Void> {
        private String jsonResponse;
        private Context mContext;
        private ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        protected void onPreExecute() {
            dialog.setMessage("Please wait..");
            dialog.show();
        }
        public LongOperation (Context context){
            mContext = context;
        }
        protected Void doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);  //argument supplied in the call to AsyncTask
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                urlConnection.setRequestProperty("User-Agent", "");
                urlConnection.setRequestMethod("GET");
                urlConnection.addRequestProperty("Authorization", getB64Auth("admin", "admin"));
                urlConnection.setDoInput(true);
                urlConnection.connect();
                InputStream isResponse = urlConnection.getInputStream();
                BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(isResponse));
                String myLine = "";
                StringBuilder strBuilder = new StringBuilder();
                while ((myLine = responseBuffer.readLine()) != null) {
                    strBuilder.append(myLine);
                }
                jsonResponse = strBuilder.toString();
                Log.e("RESPONSE", jsonResponse);
            } catch (Exception e) {
                Log.e("RESPONSE Error", e.getMessage());
            }

            return null;
        }
        protected void onPostExecute(Void unused) {
            try {
                dialog.dismiss();
                Gson gson = new Gson();
                Log.e("PostExecute", "content: " + jsonResponse);
                Type listType = new TypeToken<ArrayList<User>>() { }.getType();
                Log.e("PostExecute", "arrayType: " + listType.toString());
                ArrayList<User> personList = gson.fromJson(jsonResponse, listType);
                Log.e("PostExecute", "OutputData: " + personList.toString());
                adapter = new CustomAdapter(mContext, personList);
                txtResponseJava.setAdapter(adapter);
            } catch (JsonSyntaxException e) {
                Log.e("POST-Execute", e.getMessage());
            }
        }
    }
}
