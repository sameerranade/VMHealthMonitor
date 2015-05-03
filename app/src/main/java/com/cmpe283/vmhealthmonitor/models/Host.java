package com.cmpe283.vmhealthmonitor.models;

import java.util.ArrayList;

/**
 * Created by Varun on 5/2/2015.
 */
public class Host {

    private long id;

    private String name;


    private String ipAddr;


    private String productFullName;


    private String overAllStatus;

    private ArrayList<String> vmlist=new ArrayList<String>();

    public Host() {
    }

    public Host(String name)
    {
        this.name = name;
    }

    public Host(long id, String name) {
        this.id = id;
        this.name = name;
    }


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




    public String getProductFullName() {
        return productFullName;
    }


    public void setProductFullName(String productFullName) {
        this.productFullName = productFullName;
    }


    public String getOverAllStatus() {
        return overAllStatus;
    }


    public void setOverAllStatus(String overAllStatus) {
        this.overAllStatus = overAllStatus;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("HostInfo\n").append("Name: ").append(this.getName()).append("\nIpAddr: ").append(this.getIpAddr())
                .append("\nProductFullName: ").append(this.getProductFullName()).append("\nOverAllStatus: ").append(this.getProductFullName());
        return sb.toString();
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }
}
