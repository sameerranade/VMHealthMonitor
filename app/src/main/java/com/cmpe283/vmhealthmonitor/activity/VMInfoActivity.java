package com.cmpe283.vmhealthmonitor.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.cmpe283.vmhealthmonitor.R;
import com.cmpe283.vmhealthmonitor.adapter.ListViewAdapterForVMs;
import com.cmpe283.vmhealthmonitor.models.VM;
import com.cmpe283.vmhealthmonitor.models.VMTask;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

public class VMInfoActivity extends ActionBarActivity {

    TextView name;
    TextView host;
    TextView guestOS;
    TextView guestState;
    TextView powerState;
    TextView guestCPUUsage;
    TextView hostMemUsage;
    TextView guestMemUSage;
    TextView guestUptime;

    ImageButton playBtn;
    ImageButton pauseBtn;
    ImageButton stopBtn;
    Button showGraphBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vminfo);

        final Bundle vmBundle = getIntent().getExtras();

        name = (TextView) findViewById(R.id.textView7);
        host = (TextView) findViewById(R.id.textView16);
        guestOS = (TextView) findViewById(R.id.textView17);
        guestState = (TextView) findViewById(R.id.textView18);
        powerState = (TextView) findViewById(R.id.textView19);
        guestCPUUsage = (TextView) findViewById(R.id.textView20);
        hostMemUsage = (TextView) findViewById(R.id.textView21);
        guestMemUSage = (TextView) findViewById(R.id.textView22);
        guestUptime = (TextView) findViewById(R.id.textView23);

        name.setText(vmBundle.getString("VMName"));
        host.setText(vmBundle.getString("HostName"));
        guestOS.setText(vmBundle.getString("GuestOS"));
        guestState.setText(vmBundle.getString("GuestState"));
        powerState.setText(vmBundle.getString("PowerState"));
        guestCPUUsage.setText(vmBundle.getString("GuestCPUUsage"));
        hostMemUsage.setText(vmBundle.getString("HostMemoryUsage"));
        guestMemUSage.setText(vmBundle.getString("GuestMemoryUsage"));
        guestUptime.setText(vmBundle.getString("GuestUptime"));

        showGraphBtn = (Button) findViewById(R.id.button);
        showGraphBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VMInfoActivity.this, HealthMonitorActivity.class);
                intent.putExtra("VMName",vmBundle.getString("VMName"));
                startActivity(intent);
            }
        });

        playBtn = (ImageButton) findViewById(R.id.imageButton);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String url = "http://52.8.70.178:8080/vms/startVM/"+vmBundle.getString("VMName");
                    new HttpRequestTask().execute(url);
            }
        });

        pauseBtn = (ImageButton) findViewById(R.id.imageButton2);
        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://52.8.70.178:8080/vms/pauseVM/"+vmBundle.getString("VMName");
                new HttpRequestTask().execute(url);
            }
        });

        stopBtn = (ImageButton) findViewById(R.id.imageButton3);
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://52.8.70.178:8080/vms/stopVM/"+vmBundle.getString("VMName");
                new HttpRequestTask().execute(url);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vminfo, menu);
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

    private class HttpRequestTask extends AsyncTask<String, Void, VMTask> {
        @Override
        protected VMTask doInBackground(String... url) {
            try {
                //final String url = "http://192.168.0.7:8080/vms";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());


                VMTask vmTask = restTemplate.getForObject(url[0], VMTask.class);

                System.out.println("Hello");
                return vmTask;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(VMTask vmTask) {

            if(vmTask != null){
               // Toast.makeText(VMInfoActivity.this, "VM "+vmTask.getVmName()+" "+ vmTask.getVmTask() + " operation "+ vmTask.getVmTaskStatus(), Toast.LENGTH_SHORT).show();
            }
            else
                Log.d("Host is null", "");
        }
    }
}