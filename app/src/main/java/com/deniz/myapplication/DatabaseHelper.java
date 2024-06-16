package com.deniz.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "categories.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "selected_categories";
    public static final String COLUMN_NAME = "category_name";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_NAME + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addCategory(String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Tek tırnakları kaçış karakteriyle değiştirerek SQL sorgusunu oluşturun
        String INSERT_CATEGORY = "INSERT INTO " + TABLE_NAME + "(" + COLUMN_NAME + ") VALUES('" + category.replace("'", "''") + "')";
        db.execSQL(INSERT_CATEGORY);
        db.close();
    }
}
