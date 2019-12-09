package com.pa.contentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CouponsContentResolverActivity extends AppCompatActivity {

    ListView cpnLst;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_resolver);

        cpnLst = findViewById(R.id.couponsList);
    }

    public void addCouponsToCouponsContentProvider(View view) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CouponsContract.COLUMN_ID , 2);
        contentValues.put(CouponsContract.COLUMN_STORE , "amazon");
        contentValues.put(CouponsContract.COLUMN_COUPON , "Get Upto 40% Off on Shoes");
        contentValues.put(CouponsContract.COLUMN_EXPIRY , "2017/02/21");

        getContentResolver().insert(CouponsContract.CONTENT_URI, contentValues);
    }

    public void viewCouponsFromCouponsContentProvider(View view) {

        Cursor cursor = getCouponsFromProvider();

        String[] cursorColumns =
                {
                        CouponsContract.COLUMN_STORE,
                        CouponsContract.COLUMN_COUPON,
                        CouponsContract.COLUMN_EXPIRY
                };

        int[] viewIds = {R.id.storeName, R.id.coupon, R.id.expirationDt};


        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(
                getApplicationContext(),
                R.layout.coupon_row,
                cursor,
                cursorColumns,
                viewIds,
                0);

        cpnLst.setAdapter(simpleCursorAdapter);
    }

    private Cursor getCouponsFromProvider(){
        String[] mProjection =
                {
                        CouponsContract.COLUMN_ID,
                        CouponsContract.COLUMN_STORE,
                        CouponsContract.COLUMN_COUPON,
                        CouponsContract.COLUMN_EXPIRY
                };

        String mSelectionClause = CouponsContract.COLUMN_STORE+ " = ?";


        String[] mSelectionArgs = {"amazon"};

        String orderBy =  CouponsContract.COLUMN_EXPIRY+" ASC";

        return getContentResolver().query(CouponsContract.CONTENT_URI,mProjection,mSelectionClause,mSelectionArgs,orderBy );
    }
}
