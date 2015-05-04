package com.cmpe283.vmhealthmonitor.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cmpe283.vmhealthmonitor.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;

public class HealthMonitorActivity extends Activity {

    TextView xAxisLabel;
    TextView yAxisLabel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_monitor);
        xAxisLabel = (TextView) findViewById(R.id.textView5);
        yAxisLabel = (TextView) findViewById(R.id.textView6);
        new HttpRequestTask().execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_health_monitor, menu);
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

    private class HttpRequestTask extends AsyncTask<Void, Void, HashMap<String, Object>> {
        @Override
        protected HashMap<String, Object> doInBackground(Void... params) {
            try {
                final String url = "http://52.8.70.178:8080/vm/vm2-team6/start/2015-05-03T14:00:00Z/end/2015-05-03T15:00:00Z";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                HashMap<String, Object> stats = restTemplate.getForObject(url, HashMap.class);

                System.out.println("Hello");
                return stats;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(HashMap<String, Object> stats) {

            Toast.makeText(HealthMonitorActivity.this, "Hashmap Printing", Toast.LENGTH_SHORT).show();
            Log.d("HealthMonitorActivity", stats.toString());

            final ArrayList<Integer> cpuUsage = (ArrayList<Integer>) stats.get("cpu.usage.average");
            final ArrayList<Integer> memoryUsage = (ArrayList<Integer>) stats.get("memory.usage.average");
            final ArrayList<Integer> diskUsage = (ArrayList<Integer>) stats.get("disk.usage.average");
            final ArrayList<Integer> netUsage = (ArrayList<Integer>) stats.get("net.usage.average");

            Log.d("HealthMonitorActivity", cpuUsage.toString());

            //CPU USAGE
            Button cpuUsageBtn = (Button) findViewById(R.id.cpuusage);

            cpuUsageBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    GraphView graph = (GraphView) findViewById(R.id.graph);
                    graph.removeAllSeries();
                    LineGraphSeries<DataPoint> cpuUsageSeries = new LineGraphSeries<DataPoint>();

                    for(int i=0; i<cpuUsage.size();i++){
                        cpuUsageSeries.appendData(new DataPoint(i,cpuUsage.get(i)),true,cpuUsage.size());
                    }

                    graph.addSeries(cpuUsageSeries);
                    graph.setVisibility(View.VISIBLE);
                    yAxisLabel.setText("CPU\nUsage");
                    xAxisLabel.setText("Time");
                }
            });

            //Memory Usage
            Button memUsageBtn = (Button) findViewById(R.id.memoruusage);
            memUsageBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    GraphView graph = (GraphView) findViewById(R.id.graph);
                    graph.removeAllSeries();
                    LineGraphSeries<DataPoint> memoryUsageSeries = new LineGraphSeries<DataPoint>();

                    for (int i = 0; i < memoryUsage.size(); i++) {
                        memoryUsageSeries.appendData(new DataPoint(i, memoryUsage.get(i)), true, memoryUsage.size());
                    }

                    graph.addSeries(memoryUsageSeries);
                    graph.setVisibility(View.VISIBLE);
                    yAxisLabel.setText("Mem\nUsage");
                    xAxisLabel.setText("Time");
                }
            });

            //Disk Usage
            Button diskUsageBtn = (Button) findViewById(R.id.diskusage);
            diskUsageBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    GraphView graph = (GraphView) findViewById(R.id.graph);
                    graph.removeAllSeries();
                    LineGraphSeries<DataPoint> diskUsageSeries = new LineGraphSeries<DataPoint>();

                    for(int i=0; i<diskUsage.size();i++){
                        diskUsageSeries.appendData(new DataPoint(i, diskUsage.get(i)), true, diskUsage.size());
                    }

                    graph.addSeries(diskUsageSeries );
                    graph.setVisibility(View.VISIBLE);
                    yAxisLabel.setText("Disk\nUsage");
                    xAxisLabel.setText("Time");
                }
            });

            //Network Usage
            Button netUsageBtn = (Button) findViewById(R.id.netusage);
            netUsageBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    GraphView graph = (GraphView) findViewById(R.id.graph);
                    graph.removeAllSeries();

                    LineGraphSeries<DataPoint> networkUsageSeries = new LineGraphSeries<DataPoint>();

                    for(int i=0; i<netUsage.size();i++){
                        networkUsageSeries.appendData(new DataPoint(i, netUsage.get(i)), true, netUsage.size());
                    }

                    graph.addSeries(networkUsageSeries);
                    graph.setVisibility(View.VISIBLE);
                    yAxisLabel.setText("N"+"\\"+ "W"+"\nUsage");
                    xAxisLabel.setText("Time");
                }
            });
        }
    }
}