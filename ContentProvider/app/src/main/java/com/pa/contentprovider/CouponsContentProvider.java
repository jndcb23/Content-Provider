package com.pa.contentprovider;

import android.annotation.SuppressLint;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CouponsContentProvider extends ContentProvider {

    private CouponSQLiteOpenHelper sqLiteOpenHelper;

    private static final String COUPONS_DBNAME = "zoftino";

    private static final String COUPON_TABLE = "coupon";

    private SQLiteDatabase cpnDB;

    private static final String SQL_CREATE_COUPON = "CREATE TABLE " +
            COUPON_TABLE +
            "(" +
            "_id INTEGER PRIMARY KEY, " +
            "STORE TEXT, " +
            "COUPON TEXT, " +
            "EXPIRES TEXT)";

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI("com.zoftino.coupon.provider", COUPON_TABLE, 1);
    }

    @Override
    public boolean onCreate() {
        //this way db create or open is delayed till getWritableDatabase() is called frist time
        sqLiteOpenHelper = new CouponSQLiteOpenHelper( getContext(), COUPONS_DBNAME, SQL_CREATE_COUPON );
        return true;
    }

    @SuppressLint("Recycle")
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        String tableName = "";

        switch (uriMatcher.match(uri)) {
            case 1:
                break;
            default:
                return null;
        }

        cpnDB = sqLiteOpenHelper.getWritableDatabase();

        return cpnDB.query(tableName, projection, selection, selectionArgs,
                null, null, sortOrder);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        String tableNme = "";
        switch(uriMatcher.match(uri)){
            case 1 :
                tableNme = COUPON_TABLE;
                break;
            default:
                return null;
        }

        cpnDB = sqLiteOpenHelper.getWritableDatabase();
        long rowid = cpnDB.insert(tableNme, null, values);
        return getContentUriRow(rowid);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        String tableNme = "";
        switch(uriMatcher.match(uri)){
            case 1 :
                tableNme = COUPON_TABLE;
                break;
            default:
                return 0;
        }

        cpnDB = sqLiteOpenHelper.getWritableDatabase();

        return cpnDB.delete(tableNme, selection, selectionArgs);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        String tableNme = "";
        switch(uriMatcher.match(uri)){
            case 1 :
                tableNme = COUPON_TABLE;
                break;
            default:
                return 0;
        }
        cpnDB = sqLiteOpenHelper.getWritableDatabase();
        return cpnDB.update(tableNme,values,selection,selectionArgs);
    }

    private Uri getContentUriRow(long rowid){
        return Uri.fromParts("com.zoftino.coupon.provider", COUPON_TABLE, Long.toString(rowid));
    }
}
