package ru.luli.decompiler.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import ru.luli.db.handlers.DatabaseHandler;
import ru.luli.db.schema.objects.*;

import java.util.Date;
import java.util.Map;

public class DBUtils {

    private static DatabaseHandler handler = null;

    public static void initDb(Context context) {
        if (handler == null ){
            handler = new DatabaseHandler(context);
        }
    }

    public static boolean getBooleanValue(String table, String[] attrNames, String whereCause) {
        SQLiteQueryBuilder qBuilder = new SQLiteQueryBuilder();
        qBuilder.setTables(table);
        Cursor cursor= null;
        try {
            cursor = qBuilder.query(handler.getReadableDatabase(), attrNames, whereCause, new String[]{}, "", "", "");
            if (cursor.moveToNext()) {
                String result = cursor.getString(0);
                return "true".equalsIgnoreCase(result);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor!=null) {
                cursor.close();
            }
        }
        return false;
    }

    public static boolean getStringCollectionValues(String table, String[] attrNames, String whereCause) {
        SQLiteQueryBuilder qBuilder = new SQLiteQueryBuilder();
        qBuilder.setTables(table);
        Cursor cursor= null;
        try {
            cursor = qBuilder.query(handler.getReadableDatabase(), attrNames, whereCause, new String[]{}, "", "", "");
            if (cursor.moveToNext()) {
                String result = cursor.getString(0);
                return "true".equalsIgnoreCase(result);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor!=null) {
                cursor.close();
            }
        }
        return false;
    }

    public static int getIntValue(String table, String[] attrNames, String whereCause) {
        SQLiteQueryBuilder qBuilder = new SQLiteQueryBuilder();
        qBuilder.setTables(table);
        Cursor cursor= null;
        try {
            cursor = qBuilder.query(handler.getReadableDatabase(), attrNames, whereCause, new String[]{}, "", "", "");
            if (cursor.moveToNext()) {
                return cursor.getInt(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor!=null) {
                cursor.close();
            }
        }
        return -1;
    }

    public static Cursor execQuery(String table, String whereCause) {
        SQLiteQueryBuilder qBuilder = new SQLiteQueryBuilder();
        qBuilder.setTables(table);
        return qBuilder.query(handler.getReadableDatabase(), new String []{"*"}, whereCause, new String[]{}, "", "", "");
    }

    public static void insertClass(String className) {
        ContentValues values = new ContentValues();
        values.put(DtbClasses.CS_NAME, className);
        insert(values, DtbClasses.TABLE_NAME);
    }

    public static void insertUser(String login, String pass, String fio, int classId) {
        ContentValues values = new ContentValues();
        values.put(DtbUsers.CB_ENABLE_TESTING, true);
        values.put(DtbUsers.CS_FIO, fio);
        values.put(DtbUsers.CS_LOGIN, login);
        values.put(DtbUsers.CS_PASS, pass);
        values.put(DtbUsers.CB_IS_ADMIN, false);
        values.put(DtbUsers.CB_IS_LOGIN, false);
        values.put(DtbUsers.CI_CLASS_ID, classId);
        insert(values, DtbUsers.TABLE_NAME);
    }

    public static void insertTest(String testName, String pathToTest) {
        ContentValues values = new ContentValues();
        values.put(DtbTests.CS_NAME, testName);
        values.put(DtbTests.CS_PATH, pathToTest);
        insert(values, DtbTests.TABLE_NAME);
    }


    public static void insertTestingHistory(Date testingDate, int testId, int userId, double testingResult) {
        ContentValues values = new ContentValues();
        values.put(DtbTestingHistory.CD_TEST_RESULT, testingResult);
        values.put(DtbTestingHistory.CDT_TESTING_DATE, testingDate.getTime());
        values.put(DtbTestingHistory.CI_TEST_ID, testId);
        values.put(DtbTestingHistory.CI_USER_ID, userId);
        insert(values, DtbTestingHistory.TABLE_NAME);
    }

    public static void insertSchedule(String status, int testId, int userId) {
        ContentValues values = new ContentValues();
        values.put(DtbSchedules.CI_TEST_ID, testId);
        values.put(DtbSchedules.CI_USER_ID, userId);
        values.put(DtbSchedules.CS_STATUS, status);
        insert(values, DtbSchedules.TABLE_NAME);
    }

    public static void insert(ContentValues values, String tableName) {
        SQLiteDatabase db = null;
        try {
            db = handler.getWritableDatabase();
            int newId = -1;
            Cursor c = db.query(tableName, new String[] {"max(ci_id)"}, "", null, "", "", "" );
            if (c.moveToNext()) {
                newId = c.getInt(0) + 1;
            }
            values.put("ci_id", newId);
            db.insert(tableName, "", values);
        } finally {
            if (db!=null) {
                db.close();
            }
        }
    }

    public static void update(ContentValues values, String tableName, String where) {
        SQLiteDatabase db = null;
        try {
            db = handler.getWritableDatabase();
            db.update(tableName, values, where, null);
        } finally {
            if (db!=null) {
                db.close();
            }
        }
    }


    public static void delete(String tableName, String where) {
        SQLiteDatabase db = null;
        try {
            db = handler.getWritableDatabase();
            db.delete(tableName, where, null);
        } finally {
            if (db!=null) {
                db.close();
            }
        }
    }

}
