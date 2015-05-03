package com.cmpe283.vmhealthmonitor.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Varun on 5/2/2015.
 */
public class VM {
    @JsonProperty
    private String name;

    @JsonProperty
    private String guestOS;

    @JsonProperty
    private String guestState;

    @JsonProperty
    private String powerState;

    public VM(){

    }

    @JsonProperty("guestOS")
    public String getGuestOS() {
        return guestOS;
    }

    @JsonProperty("guestOS")
    public void setGuestOS(String guestOS) {
        this.guestOS = guestOS;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("guestState")
    public String getGuestState() {
        return guestState;
    }

    @JsonProperty("guestState")
    public void setGuestState(String guestState) {
        this.guestState = guestState;
    }

    @JsonProperty("powerState")
    public String getPowerState() {
        return powerState;
    }

    @JsonProperty("powerState")
    public void setPowerState(String powerState) {
        this.powerState = powerState;
    }
}
