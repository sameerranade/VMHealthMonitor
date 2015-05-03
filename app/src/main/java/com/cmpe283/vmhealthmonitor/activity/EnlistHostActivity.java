package com.cmpe283.vmhealthmonitor.activity;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.cmpe283.vmhealthmonitor.R;
import com.cmpe283.vmhealthmonitor.adapter.ListViewAdapter;
import com.cmpe283.vmhealthmonitor.models.Host;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

public class EnlistHostActivity extends TabActivity {
    ListView hostList;
    ListViewAdapter listViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enlist_host);
        HttpRequestTask requestTask = new HttpRequestTask();
        requestTask.execute();
        hostList = (ListView) findViewById(R.id.lv_host_list);
        hostList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Host item = (Host) parent.getItemAtPosition(position);
                Log.d("EnlistHostActivity","HostName: "+item.getName());
                //Create intent
                Toast.makeText(EnlistHostActivity.this,"Clicked on Item " + position + "Host Name " + item.getName(), Toast.LENGTH_SHORT ).show();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(EnlistHostActivity.this);

                // set title
                alertDialogBuilder.setTitle("Host Info");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Name "+ item.getName() + "\nIPAddress" + item.getIpAddr()
                        + "\nProduct Name " + item.getProductFullName() + "\nOverAllStatus " + item.getOverAllStatus())
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.dismiss();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                // show it
                alertDialog.show();
            }
        });
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
            ArrayList<Host> hosts = new ArrayList<Host>();
            if(host != null){
                Log.d("Host is :", host[0].getName());
                Toast.makeText(EnlistHostActivity.this, "Host is " + host[1].getName(), Toast.LENGTH_SHORT).show();
                Log.d("Host is :", String.valueOf(host[1].getId()));
                for(Host h: host) {
                    Log.d("EnlistHostActivity", h.toString());
                    hosts.add(h);
                }
                listViewAdapter = new ListViewAdapter(EnlistHostActivity.this, R.layout.list_view_item, hosts);
                hostList.setAdapter(listViewAdapter);
            }
            else
                Log.d("Host is null", "");
            //greetingIdText.setText(String.valueOf(host.getId()));
            //greetingContentText.setText(host.getName());
        }

    }
}
