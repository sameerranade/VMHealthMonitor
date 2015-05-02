package com.cmpe283.vmhealthmonitor.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

import com.cmpe283.vmhealthmonitor.R;

public class EnlistHostActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enlist_host);
       /* TabHost tabHost = getTabHost();

        // Gallary Tab
        Intent intentGallery = new Intent().setClass(this, EnlistHostActivity.class);
        TabHost.TabSpec tabSpecGallery = tabHost
                .newTabSpec("Hosts")
                .setIndicator("Hosts")
                .setContent(intentGallery);

        // Uploads Tab
        Intent intentUpload = new Intent().setClass(this, EnlistVMSActivity.class);
        TabHost.TabSpec tabSpecUploadImage = tabHost
                .newTabSpec("VMs")
                .setIndicator("VMs")
                .setContent(intentUpload);

        // Share Tab
        Intent intentSharedImages = new Intent().setClass(this, HealthMonitorActivity.class);
        TabHost.TabSpec tabSpecSharedImages = tabHost
                .newTabSpec("Health Monitor")
                .setIndicator("Health Monitor")
                .setContent(intentSharedImages);

        tabHost.addTab(tabSpecGallery);
        tabHost.addTab(tabSpecUploadImage);
        tabHost.addTab(tabSpecSharedImages);
        tabHost.setCurrentTab(0);*/
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
}
