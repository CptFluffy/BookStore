package com.example.fluffy.bookstore;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fluffy.bookstore.data.BookContract;
import com.example.fluffy.bookstore.data.BookDbHelper;

public class EntryActivity extends AppCompatActivity {

    private EditText mProductName;

    private EditText mPrice;

    private EditText mQuantity;

    private EditText mSupplierName;

    private EditText mSupplierNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        mProductName = findViewById(R.id.edit_product_name);
        mPrice = findViewById(R.id.edit_price);
        mQuantity = findViewById(R.id.edit_quantity);
        mSupplierName = findViewById(R.id.edit_supplier_name);
        mSupplierNumber = findViewById(R.id.edit_supplier_number);
    }

    private void insertBook() {
        String nameString = mProductName.getText().toString().trim();
        String priceString = mPrice.getText().toString().trim();
        String quantityString = mQuantity.getText().toString();
        String supplierNameString = mSupplierName.getText().toString();
        String supplierNumberString = mSupplierNumber.getText().toString();
        double price = Double.parseDouble(priceString);
        int quantity = Integer.parseInt(quantityString);

        // Create database helper
        BookDbHelper mDbHelper = new BookDbHelper(this);

        //Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(BookContract.BookEntry.COLUMN_PRODUCT_NAME, nameString);
        values.put(BookContract.BookEntry.COLUMN_PRICE, price);
        values.put(BookContract.BookEntry.COLUMN_QUANTITY, quantity);
        values.put(BookContract.BookEntry.COLUMN_SUPPLIER_NAME, supplierNameString);
        values.put(BookContract.BookEntry.COLUMN_SUPPLIER_NUMBER, supplierNumberString);

        // Insert a new row for books in the database.
        long newRowId = db.insert(BookContract.BookEntry.TABLE_NAME, null, values);

        if (newRowId == -1){
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving book", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Book saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_entry, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Save book to data base
                insertBook();
                //Exit activity
                finish();
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
