package com.example.mob;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.mob.entity.CustomerModel;


import java.util.ArrayList;
import java.util.List;

public  class DbHandler extends SQLiteOpenHelper {
    private static final int VERSION = 2 ;

    private static final String DATABASE_NAME = "CUSTOMER_DB";

    //TABLES NAMES
    private static final String CUSTOMER_TABLE ="customer";

    //COLUMN NAME
    private static final String ID ="id";
    private static final String NAME = "name";
    private static final String ID_CARD="id_card";
    private static final String JOP ="jop";
    private static final String AGE = "age";
    private static final String ADDRESS="address";
    private static final String PHONE_NUMBER ="phone_number";
    private static final String GENDER = "gender";







    //CREATE TABLES
    private static final String CREATE_TABLE_CUSTOMER= " CREATE TABLE IF NOT EXISTS "
            + CUSTOMER_TABLE + " ( "
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + NAME + " TEXT NOT NULL , "
            + AGE + " TEXT , "
            + GENDER + " TEXT ,"
            + JOP + " TEXT NOT NULL, "
            + ID_CARD + " TEXT NOT NULL , "
            + PHONE_NUMBER + " TEXT NOT NULL , "
            + ADDRESS + " TEXT ) ";

    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_CUSTOMER);

        Log.w("db","onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CUSTOMER_TABLE);

        onCreate(db);
    }


    public void addCustomer(CustomerModel customer ){
        SQLiteDatabase db = getWritableDatabase();
        Long cusId = db.insert(CUSTOMER_TABLE,null,setValueCustomerTable(customer));

    }

    private ContentValues setValueCustomerTable(CustomerModel customer){
        ContentValues values =new ContentValues();
        values.put(NAME,customer.getName());
        values.put(AGE,customer.getAge());
        values.put(GENDER,customer.getGender());
        values.put(JOP,customer.getJop());
        values.put(ID_CARD,customer.getIdCard());
        values.put(PHONE_NUMBER,customer.getPhoneNumber());
        values.put(ADDRESS,customer.getAddress());
        return  values;
    }//true


    private void dropAllTables(){
        SQLiteDatabase db =this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + CUSTOMER_TABLE);
    }



    private int getNameId(String name){
        SQLiteDatabase db =getReadableDatabase();
        Cursor cursor = db.query(
                CUSTOMER_TABLE,
                new String[]{ID},
                NAME + "=?",
                new String[]{name},null,null,null,null
        );
        int id = -1;
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            id = cursor.getInt(0);
            return id;
        }else{
            return -1;
        }
    }



    public List<CustomerModel> getAllCustomers(){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+ CUSTOMER_TABLE ;
        Cursor cursor = db.rawQuery(query,null);
        List customers =new ArrayList();
        CustomerModel customer;
        if(cursor.moveToFirst()){
            do {
                customer = new CustomerModel(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7)
                );
                customers.add(customer);
            }while(cursor.moveToNext());
        }
        return customers;
    }


    public void dropAlltables() {
    }
}
