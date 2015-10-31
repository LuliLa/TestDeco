package ru.luli.equipment;

import android.content.Context;

import java.io.Serializable;

public class SessionEquipment implements Serializable{
    private static final long serialVersionUID = 2529318407576760307L;

    private Context context;
    private String activeUserName;
    private int activeUserId;
    private boolean isAdmin = false;

    public SessionEquipment(Context context, String activeUserName, int activeUserId) {
        this.context = context;
        this.activeUserName = activeUserName;
        this.activeUserId = activeUserId;
    }

    public SessionEquipment(Context context, String activeUserName, int activeUserId, boolean isAdmin) {
        this.context = context;
        this.activeUserName = activeUserName;
        this.isAdmin = isAdmin;
        this.activeUserId = activeUserId;
    }


    public int getActiveUserId() {
        return activeUserId;
    }

    public void setActiveUserId(int activeUserId) {
        this.activeUserId = activeUserId;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public Context getContext() {
        return context;
    }

    public String getActiveUserName() {
        return activeUserName;
    }
}
