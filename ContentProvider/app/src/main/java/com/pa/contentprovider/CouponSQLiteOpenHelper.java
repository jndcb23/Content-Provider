package com.pa.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CouponSQLiteOpenHelper extends SQLiteOpenHelper {

    private String sql;
    CouponSQLiteOpenHelper(Context context, String dbName, String msql) {
        super(context, dbName, null, 1);
        sql = msql;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // For upgrade codes
    }
}
