package ru.luli.decompiler.utils;

import ru.luli.db.schema.objects.DtbUsers;

public class UserUtils {
    public static boolean isUserLogin(String userName) {
        String whereCause =  DtbUsers.CB_IS_LOGIN + "='true' " + " AND " + DtbUsers.CS_LOGIN + "='" + userName + '\'';
        return DBUtils.getBooleanValue(DtbUsers.TABLE_NAME, new String [] {DtbUsers.CI_ID}, whereCause);
    }

    public static boolean relogin(String userName) {
        logoff(userName);
        login(userName);
        return true;
    }

    public static boolean logoff(String userName) {
        return true;
    }

    public static boolean login(String userName) {

        return true;
    }

    public static boolean isAnyUserLogin() {
        return DBUtils.getBooleanValue(DtbUsers.TABLE_NAME, new String [] {DtbUsers.CI_ID}, DtbUsers.CB_IS_LOGIN + "='true'");
    }

    public static boolean getLoginUserName(String userName) {
        return DBUtils.getBooleanValue(DtbUsers.TABLE_NAME, new String [] {DtbUsers.CI_ID}, DtbUsers.CB_IS_LOGIN + "='true'" + " AND " + DtbUsers.CB_IS_ADMIN + "='false'");
    }

    public static boolean isAnyScholarsLogin() {
        return DBUtils.getBooleanValue(DtbUsers.TABLE_NAME, new String [] {DtbUsers.CI_ID}, DtbUsers.CB_IS_LOGIN + "='true'" + " AND " + DtbUsers.CB_IS_ADMIN + "='false'");
    }
}
