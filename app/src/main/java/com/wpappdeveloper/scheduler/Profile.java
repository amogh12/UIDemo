package com.wpappdeveloper.scheduler;

import java.util.Date;
import java.util.List;

/**
 * Created by aphadke on 10/9/2014.
 */
public class Profile {
    private String profileName;
    private Date time;
    private List<Integer> days;
    private boolean wifi = false;
    private boolean bluetooth = false;
    private boolean data = false;
    private String notificationType;

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public List<Integer> getDays() {
        return days;
    }

    public void setDays(List<Integer> days) {
        this.days = days;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(boolean bluetooth) {
        this.bluetooth = bluetooth;
    }

    public boolean isData() {
        return data;
    }

    public void setData(boolean data) {
        this.data = data;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }


    public enum NotificationTypes {
        RINGER("ringer"), VIBRATE("vibrate"), SILENT("silent");

        private  String code;

        private NotificationTypes (String code) {
            this.code = code;
        }

        public String getCode() {
            return this.code;
        }

    }
}
