package com.example.bankingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(1234567890,'Sai',10000.00,'sai.0208@gmail.com','XXXXXXXXXXXX1234','SAI123456789')");
        db.execSQL("insert into user_table values(2345678901,'Akki',9000.00,'akki.1999@gmail.com','XXXXXXXXXXXX2341','AKKI12345678')");
        db.execSQL("insert into user_table values(3456789012,'Mouli',8000.00,'mouli.719@gmail.com','XXXXXXXXXXXX3412','MOULI12345')");
        db.execSQL("insert into user_table values(4567890123,'Prana',1500.01,'prana.02@gmail.com','XXXXXXXXXXXX4123','PRANA12345')");
        db.execSQL("insert into user_table values(5678901234,'Lalli',2603.48,'lalli.456@gmail.com','XXXXXXXXXXXX2345','LALLI12345')");
        db.execSQL("insert into user_table values(6789012345,'Akhil',1000.16,'akhil.07@gmail.com','XXXXXXXXXXXX3452','AKHIL12345')");
        db.execSQL("insert into user_table values(7890123456,'Ashraf',6000.00,'ashraf.10@gmail.com','XXXXXXXXXXXX4523','ASHRAF123')");
        db.execSQL("insert into user_table values(8901234567,'Abdul Rehman',7500.22,'abdulrehman.0999@gmail.com','ABDUL1234','BCA32109876')");
        db.execSQL("insert into user_table values(9012345678,'Umar',4398.46,'umar.1560@gmail.com','XXXXXXXXXXXX3456','UMAR12345')");
        db.execSQL("insert into user_table values(1234567809,'Mansoor',273.90,'mansoor.01@gmail.com','XXXXXXXXXXXX4563','MANSOOR123')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
