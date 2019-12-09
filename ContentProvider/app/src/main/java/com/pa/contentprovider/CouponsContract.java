package com.pa.contentprovider;

import android.net.Uri;

public class CouponsContract {

    public static final String TABLE_COUPON = "coupon";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_STORE = "STORE";
    public static final String COLUMN_COUPON = "COUPON";
    public static final String COLUMN_EXPIRY = "EXPIRES";


    public static final String AUTHORITY = "com.zoftino.coupon.provider";

    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);

    public static final Uri CONTENT_URI =
            Uri.withAppendedPath(AUTHORITY_URI, TABLE_COUPON);

}
