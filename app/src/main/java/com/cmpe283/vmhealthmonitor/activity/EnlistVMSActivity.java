package com.cmpe283.vmhealthmonitor.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.cmpe283.vmhealthmonitor.R;
import com.cmpe283.vmhealthmonitor.adapter.ListViewAdapter;
import com.cmpe283.vmhealthmonitor.adapter.ListViewAdapterForVMs;
import com.cmpe283.vmhealthmonitor.models.Host;
import com.cmpe283.vmhealthmonitor.models.VM;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

public class EnlistVMSActivity extends Activity {

    ListView hostList;
    ListViewAdapterForVMs listViewAdapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enlist_vms);
        progressDialog = new ProgressDialog(EnlistVMSActivity.this);
        HttpRequestTask requestTask = new HttpRequestTask();
        requestTask.execute();
        hostList = (ListView) findViewById(R.id.lv_host_list);
        hostList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VM item = (VM) parent.getItemAtPosition(position);
                Log.d("EnlistVMSActivity", "VMName: " + item.getName());
                //Create intent
               // Toast.makeText(EnlistVMSActivity.this, "Clicked on Item " + position + "VM Name " + item.getName(), Toast.LENGTH_SHORT).show();

                Bundle vmBundle = new Bundle();
                vmBundle.putString("VMName", item.getName());
                vmBundle.putString("HostName", item.getHostName());
                vmBundle.putString("GuestOS", item.getGuestOS());
                vmBundle.putString("GuestState", item.getGuestState());
                vmBundle.putString("PowerState", item.getPowerState());
                vmBundle.putString("GuestCPUUsage", item.getGuestCPUUsage());
                vmBundle.putString("HostMemoryUsage", item.getHostMemoryUsage());
                vmBundle.putString("GuestMemoryUsage", item.getGuestMemoryUsage());
                vmBundle.putString("GuestUptime", item.getGuestUpTime());

                Intent intent = new Intent(EnlistVMSActivity.this,VMInfoActivity.class);
                intent.putExtras(vmBundle);
                startActivity(intent);

                /*AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(EnlistVMSActivity.this);

                // set title
                alertDialogBuilder.setTitle("VM Info");

                // set dialog message
                alertDialogBuilder
                        .setMessage(Html.fromHtml("<b>" + "Name: " + "</b>" + item.getName() + "<br/><b>" + "\nGuest OS: " + "</b>" + item.getGuestOS()
                                + "<br/><b>" + "\nGuest OS State: " + "</b>" + item.getGuestState() + "<br/><b>" + "\nPower Status: " + "</b>" + item.getPowerState()))
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                // show it
                alertDialog.show();*/
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_enlist_vm, menu);
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

    @Override
    protected void onResume(){
        super.onResume();
        HttpRequestTask requestTask = new HttpRequestTask();
        //requestTask.execute();
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, VM[]> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Fetching VM List...");
            progressDialog.show();
        }
        @Override
        protected VM[] doInBackground(Void... params) {
            try {
                final String url = "http://52.8.70.178:8080/vms";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                VM[] vms = restTemplate.getForObject(url, VM[].class);

                System.out.println("Hello");
                progressDialog.dismiss();
                return vms;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(VM[] vms) {
            progressDialog.dismiss();
            progressDialog.cancel();
            //progressDialog = null;
                //TextView greetingIdText = (TextView) findViewById(R.id.id_value);
            //TextView greetingContentText = (TextView) findViewById(R.id.content_value);
            ArrayList<VM> vmList = new ArrayList<VM>();
            if(vms != null){
                Log.d("VM is :", vms[0].getName());
                //Toast.makeText(EnlistVMSActivity.this, "VM is " + vms[1].getName(), Toast.LENGTH_SHORT).show();
                Log.d("Host is :", String.valueOf(vms[1].getName()));
                for(VM vm: vms) {
                    Log.d("EnlistVMSActivity", vm.toString());
                    vmList.add(vm);
                }
                listViewAdapter = new ListViewAdapterForVMs(EnlistVMSActivity.this, R.layout.list_view_item, vmList);
                hostList.setAdapter(listViewAdapter);
            }
            else
                Log.d("Host is null", "");
            //greetingIdText.setText(String.valueOf(host.getId()));
            //greetingContentText.setText(host.getName());
        }

    }
}
