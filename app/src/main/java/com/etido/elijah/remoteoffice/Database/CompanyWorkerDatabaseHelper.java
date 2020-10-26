package com.etido.elijah.remoteoffice.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CompanyWorkerDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CompanyWorkerData.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_COMPANY = "company";
    private static final String TABLE_WORKER = "worker";

    private static final String COLUMN_WORKER_ID = "worker_id";
    private static final String COLUMN_FULL_NAME = "full_name";
    private static final String COLUMN_WORKER_CATEGORY = "worker_category";
    private static final String COLUMN_WORKER_EMAIL = "worker_email";
    private static final String COLUMN_WORKER_PASSWORD = "worker_password";

    private static final String COLUMN_USER_ID = "company_id";
    private static final String COLUMN_COMPANY_NAME = "company_name";
    private static final String COLUMN_COMPANY_CATEGORY = "company_category";
    private static final String COLUMN_COMPANY_EMAIL = "company_email";
    private static final String COLUMN_COMPANY_PASSWORD = "company_password";

    private String CREATE_COMPANY_TABLE = "CREATE TABLE " + TABLE_COMPANY + "("
            + COLUMN_USER_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_COMPANY_NAME + "TEXT,"
            + COLUMN_COMPANY_CATEGORY + "TEXT," + COLUMN_COMPANY_EMAIL + "TEXT,"
            + COLUMN_COMPANY_PASSWORD + "TEXT" + ")";

    private String CREATE_WORKER_TABLE = "CREATE TABLE " + TABLE_WORKER + "("
            + COLUMN_WORKER_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_FULL_NAME + "TEXT,"
            + COLUMN_WORKER_CATEGORY + "TEXT," + COLUMN_WORKER_EMAIL + "TEXT,"
            + COLUMN_WORKER_PASSWORD + "TEXT" + ")";

    private String DROP_WORKER_TABLE = "DROP TABLE IF EXISTS " + TABLE_WORKER;
    private String DROP_COMPANY_TABLE = "DROP TABLE IF EXISTS " + TABLE_COMPANY;


    public CompanyWorkerDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_COMPANY_TABLE);
        db.execSQL(CREATE_WORKER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_COMPANY_TABLE);
        db.execSQL(DROP_WORKER_TABLE);
        onCreate(db);
    }
}
