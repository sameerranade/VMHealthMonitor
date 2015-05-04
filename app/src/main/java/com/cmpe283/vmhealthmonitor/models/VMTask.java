package com.cmpe283.vmhealthmonitor.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Varun on 5/4/2015.
 */
public class VMTask {
    @JsonProperty
    private String vmName;
    @JsonProperty
    private String vmTask;
    @JsonProperty
    private String vmTaskStatus;

    @JsonProperty("vmName")
    public String getVmName() {
        return vmName;
    }

    @JsonProperty("vmName")
    public void setVmName(String vmName) {
        this.vmName = vmName;
    }

    @JsonProperty("vmTask")
    public String getVmTask() {
        return vmTask;
    }

    @JsonProperty("vmTask")
    public void setVmTask(String vmTask) {
        this.vmTask = vmTask;
    }

    @JsonProperty("vmTaskStatus")
    public String getVmTaskStatus() {
        return vmTaskStatus;
    }

    @JsonProperty("vmTaskStatus")
    public void setVmTaskStatus(String vmTaskStatus) {
        this.vmTaskStatus = vmTaskStatus;
    }
}
