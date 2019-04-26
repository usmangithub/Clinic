package com.veterinaryclinic.ui.fragments.home.model;

/**
 * The data set from configure URL
 */
public class ConfigData {
    boolean isChatEnabled;
    boolean isCallEnabled;
    String workHours;

    public boolean isChatEnabled() {
        return isChatEnabled;
    }

    public void setChatEnabled(boolean chatEnabled) {
        isChatEnabled = chatEnabled;
    }

    public boolean isCallEnabled() {
        return isCallEnabled;
    }

    public void setCallEnabled(boolean callEnabled) {
        isCallEnabled = callEnabled;
    }

    public String getWorkHours() {
        return workHours;
    }

    public void setWorkHours(String workHours) {
        this.workHours = workHours;
    }
}
