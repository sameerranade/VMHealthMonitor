package com.cmpe283.vmhealthmonitor.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.cmpe283.vmhealthmonitor.R;
import com.cmpe283.vmhealthmonitor.models.Host;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

public class EnlistHostActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enlist_host);
        HttpRequestTask requestTask = new HttpRequestTask();
        requestTask.execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_enlist_host, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, Host[]> {
        @Override
        protected Host[] doInBackground(Void... params) {
            try {
                final String url = "http://192.168.0.7:8080/hosts";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Host[] hosts = restTemplate.getForObject(url, Host[].class);

                System.out.println("Hello");
                return hosts;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Host[] host) {
            //TextView greetingIdText = (TextView) findViewById(R.id.id_value);
            //TextView greetingContentText = (TextView) findViewById(R.id.content_value);

            if(host != null){
                Log.d("Host is :", host[0].getName());
                Toast.makeText(EnlistHostActivity.this, "Host is " + host[1].getName(), Toast.LENGTH_SHORT).show();
                Log.d("Host is :", String.valueOf(host[1].getId()));
            }
            else
                Log.d("Host is null", "");
            //greetingIdText.setText(String.valueOf(host.getId()));
            //greetingContentText.setText(host.getName());
        }

    }
}
