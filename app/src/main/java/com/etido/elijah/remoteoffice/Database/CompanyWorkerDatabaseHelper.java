package com.etido.elijah.remoteoffice.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.etido.elijah.remoteoffice.DataModel.Company;
import com.etido.elijah.remoteoffice.DataModel.Worker;

import java.util.ArrayList;
import java.util.List;

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

    private static final String COLUMN_COMPANY_ID = "company_id";
    private static final String COLUMN_COMPANY_NAME = "company_name";
    private static final String COLUMN_COMPANY_CATEGORY = "company_category";
    private static final String COLUMN_COMPANY_EMAIL = "company_email";
    private static final String COLUMN_COMPANY_PASSWORD = "company_password";

    private String CREATE_COMPANY_TABLE = "CREATE TABLE " + TABLE_COMPANY + "("
            + COLUMN_COMPANY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_COMPANY_NAME + "TEXT,"
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

    public void addWorker(Worker worker){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FULL_NAME, worker.fullName);
        values.put(COLUMN_WORKER_CATEGORY, worker.category);
        values.put(COLUMN_WORKER_EMAIL, worker.email);
        values.put(COLUMN_WORKER_PASSWORD, worker.password);

        db.insert(TABLE_WORKER,null, values);
        db.close();
    }

    public void addCompany(Company company){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_COMPANY_NAME, company.companyName);
        values.put(COLUMN_COMPANY_CATEGORY, company.category);
        values.put(COLUMN_COMPANY_EMAIL, company.email);
        values.put(COLUMN_COMPANY_PASSWORD, company.password);

        db.insert(TABLE_WORKER,null, values);
        db.close();
    }

    public List<Worker> getAllWorkers(){

        String [] columns = {
                COLUMN_WORKER_ID,
                COLUMN_FULL_NAME,
                COLUMN_WORKER_CATEGORY,
                COLUMN_WORKER_EMAIL,
                COLUMN_WORKER_PASSWORD
        };
        String sortOrder =
                COLUMN_FULL_NAME + " ASC ";
        List<Worker> workerList = new ArrayList<Worker>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_WORKER, columns,
                null,
                null,
                null,
                null,
                sortOrder);

        if(cursor.moveToFirst()){
            do{
                Worker worker = new Worker();
                worker.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_WORKER_ID))));
                worker.setFullName(cursor.getString(cursor.getColumnIndex(COLUMN_FULL_NAME)));
                worker.setCategory(cursor.getString(cursor.getColumnIndex(COLUMN_WORKER_CATEGORY)));
                worker.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_WORKER_EMAIL)));
                worker.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_WORKER_PASSWORD)));

                workerList.add(worker);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return workerList;

    }

    public List<Company> getAllCompanies(){
        String [] columns = {
                COLUMN_COMPANY_ID,
                COLUMN_COMPANY_NAME,
                COLUMN_COMPANY_CATEGORY,
                COLUMN_COMPANY_EMAIL,
                COLUMN_COMPANY_PASSWORD
        };
        String sortOrder =
                COLUMN_COMPANY_NAME + " ASC ";
        List<Company> companyList = new ArrayList<Company>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_COMPANY, columns,
                null,
                null,
                null,
                null,
                sortOrder);

        if(cursor.moveToFirst()){
            do{
                Company company = new Company();
                company.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_COMPANY_ID))));
                company.setCompanyName(cursor.getString(cursor.getColumnIndex(COLUMN_COMPANY_NAME)));
                company.setCategory(cursor.getString(cursor.getColumnIndex(COLUMN_COMPANY_CATEGORY)));
                company.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_COMPANY_EMAIL)));
                company.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_COMPANY_PASSWORD)));

                companyList.add(company);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return companyList;
    }

    public void updateWorker(Worker worker){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FULL_NAME, worker.getFullName());
        values.put(COLUMN_WORKER_CATEGORY, worker.getCategory());
        values.put(COLUMN_WORKER_EMAIL, worker.getEmail());
        values.put(COLUMN_WORKER_PASSWORD, worker.getPassword());

        db.update(TABLE_WORKER, values, COLUMN_WORKER_ID + " = ?",
                new String[]{String.valueOf(worker.getId())});
        db.close();
    }
    public void updateCompany(Company company){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_COMPANY_NAME, company.getCompanyName());
        values.put(COLUMN_COMPANY_CATEGORY, company.getCategory());
        values.put(COLUMN_COMPANY_EMAIL, company.getEmail());
        values.put(COLUMN_COMPANY_PASSWORD, company.getPassword());

        db.update(TABLE_COMPANY, values, COLUMN_COMPANY_ID + " = ?",
                new String[]{String.valueOf(company.getId())});
        db.close();
    }

    public void deleteWorker(Worker worker){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WORKER, COLUMN_WORKER_ID + " = ?",
                new String[]{String.valueOf(worker.getId())});
        db.close();
    }
    public void deleteCompany(Company company){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_COMPANY, COLUMN_COMPANY_ID + " = ?",
                new String[]{String.valueOf(company.getId())});
        db.close();
    }

    public boolean checkWorker(String email, String password){
        String[] columns = {COLUMN_WORKER_ID};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_WORKER_EMAIL + " = ?" + " AND "
                + COLUMN_WORKER_PASSWORD + " = ? ";
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(TABLE_WORKER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if(cursorCount > 0){
            return true;
        }
        return false;
    }

//    public Worker authenticate( Worker)

    public boolean checkCompany(String email, String password){
        String[] columns = {
                COLUMN_COMPANY_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_COMPANY_EMAIL + " = ?" + " AND "
                + COLUMN_COMPANY_PASSWORD + " = ? ";
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(TABLE_COMPANY,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if(cursorCount > 0){
            return true;
        }
        return false;
    }
}
