package com.cmpe283.vmhealthmonitor.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cmpe283.vmhealthmonitor.R;
import com.cmpe283.vmhealthmonitor.models.Host;
import com.cmpe283.vmhealthmonitor.models.VM;

import java.util.ArrayList;

/**
 * Created by Varun on 5/3/2015.
 */
public class ListViewAdapterForVMs extends ArrayAdapter {

    private Context context;
    private int layoutResourceId;
    private ArrayList data = new ArrayList();

    public ListViewAdapterForVMs(Context context, int layoutResourceId, ArrayList data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.name = (TextView) row.findViewById(R.id.tv_host_name);
            holder.ipAddr = (TextView) row.findViewById(R.id.tv_host_ip);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        VM item = (VM) data.get(position);
        holder.name.setText(item.getName());
        holder.ipAddr.setText(item.getGuestOS());
        return row;
    }

    static class ViewHolder {
        TextView name;
        TextView ipAddr;
    }
}