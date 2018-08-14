package com.example.fluffy.bookstore.data;

import android.provider.BaseColumns;

public final class BookContract {

    public static abstract class BookEntry implements BaseColumns {

        public static final String TABLE_NAME = "Books";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PRODUCT_NAME = "name";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_SUPPLIER_NAME = "supplier";
        public static final String COLUMN_SUPPLIER_NUMBER = "phone_number";
    }
}
