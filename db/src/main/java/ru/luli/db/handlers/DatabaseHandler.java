package ru.luli.db.handlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import ru.luli.db.schema.objects.*;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "test_all_world2";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUsersSql = "CREATE TABLE if not exists " + DtbUsers.TABLE_NAME + "("
                + DtbUsers.CI_ID + " INTEGER IDENTITY(0,1) PRIMARY KEY," + DtbUsers.CS_FIO + " TEXT,"
                + DtbUsers.CS_LOGIN + " TEXT, "  +DtbUsers.CS_PASS + " TEXT,"
                + DtbUsers.CB_IS_ADMIN + " BOOLEAN," + DtbUsers.CB_ENABLE_TESTING + " BOOLEAN,"
                + DtbUsers.CB_IS_LOGIN + " BOOLEAN, " + DtbUsers.CI_CLASS_ID + " INTEGER)";
        db.execSQL(createUsersSql);
        String createClassesSql = "CREATE TABLE IF NOT EXISTS " + DtbClasses.TABLE_NAME + "("
                + DtbClasses.CI_ID + " INTEGER IDENTITY(0,1) PRIMARY KEY," + DtbClasses.CS_NAME + " TEXT )";
        db.execSQL(createClassesSql);
        String createSchedulesSql = "CREATE TABLE IF NOT EXISTS " + DtbSchedules.TABLE_NAME + "("
                + DtbSchedules.CI_ID + " INTEGER IDENTITY(0,1) PRIMARY KEY," + DtbSchedules.CI_TEST_ID + " INTEGER,"
                + DtbSchedules.CI_USER_ID + " INTEGER, "  +DtbSchedules.CS_STATUS + " TEXT " + DtbSchedules.CDT_PLANNED_DATE + " DATE )";
        db.execSQL(createSchedulesSql);
        String createTestsSql = "CREATE TABLE IF NOT EXISTS " + DtbTests.TABLE_NAME + "("
                + DtbTests.CI_ID + " INTEGER IDENTITY(0,1) PRIMARY KEY," + DtbTests.CS_NAME + " TEXT,"
                + DtbTests.CS_PATH + " TEXT )";
        db.execSQL(createTestsSql);
        String createTestingHistorySql = "CREATE TABLE IF NOT EXISTS " + DtbTestingHistory.TABLE_NAME + "("
                + DtbTestingHistory.CI_ID + " INTEGER IDENTITY(0,1) PRIMARY KEY," + DtbTestingHistory.CD_TEST_RESULT + " DOUBLE,"
                + DtbTestingHistory.CI_USER_ID + " INTEGER, " + DtbTestingHistory.CI_TEST_ID + " INTEGER,"
                + DtbTestingHistory.CDT_TESTING_DATE + " DATE )";
        db.execSQL(createTestingHistorySql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable = "DROP IF EXISTS TABLE " + DtbUsers.TABLE_NAME;
        db.execSQL(dropTable);
        dropTable = "DROP IF EXISTS TABLE " + DtbClasses.TABLE_NAME;
        db.execSQL(dropTable);
        dropTable = "DROP IF EXISTS TABLE " + DtbTests.TABLE_NAME;
        db.execSQL(dropTable);
        dropTable = "DROP IF EXISTS TABLE " + DtbSchedules.TABLE_NAME;
        db.execSQL(dropTable);
        dropTable = "DROP IF EXISTS TABLE " + DtbTestingHistory.TABLE_NAME;
        db.execSQL(dropTable);
        onCreate(db);
    }

    public void readDataSqlExecute(String sql) {
        SQLiteDatabase db = getReadableDatabase();
        //db.query(sql);
        db.close();
    }

    public void insertDataSqlExecute(ContentValues values, String tableName) {
        SQLiteDatabase db = getWritableDatabase();
        db.insert(tableName, null, values);
        db.close(); // Closing database connection
    }

    public void deleteDataSqlExecute(String tableName, String whereCause, String whereArg) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(tableName, whereCause, new String[]{whereArg});
        db.close();
    }



    public static void updateDataSqlExecute(String sql) {

    }
}
