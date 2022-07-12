package com.example.tourguide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tourguide.ui.gallery.GalleryFragment;

public class DatabaseAccess {
    private static Context context;
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c = null;

    public DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance() {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.db = openHelper.getWritableDatabase();
    }

    public void close() {
        if (db != null) {
            this.db.close();
        }
    }

    public String getServices(String name) {
        c = db.rawQuery("select name from services where name = '" + name + "'", new String[]{});
        StringBuffer stringBuffer = new StringBuffer();
        while (c.moveToNext()) {
            String serviceName = c.getString(0);
            stringBuffer.append("" + serviceName);
        }
        return stringBuffer.toString();
    }

    public Cursor readAllHotelServices() {
        String query = "SELECT * FROM services";
        SQLiteDatabase db = openHelper.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor readAllTransactions() {
        String query = "SELECT * FROM payments";
        SQLiteDatabase db = openHelper.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor readAllHotelOrders() {
        String query = "SELECT * FROM hotel_service_orders";
        SQLiteDatabase db = openHelper.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor readAllTransportOrders() {
        String query = "SELECT * FROM transport_service_orders";
        SQLiteDatabase db = openHelper.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor readAllServiceProviders() {
        String query = "SELECT * FROM services";
        SQLiteDatabase db = openHelper.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Boolean checkusername(String name) {
        SQLiteDatabase MyDB = openHelper.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from admin where name = ?", new String[]{name});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String name, String password) {
        SQLiteDatabase MyDB = openHelper.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from admin where name = ? and password = ?", new String[]{name, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusername1password1(String user, String pass) {
        SQLiteDatabase MyDB = openHelper.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[]{user, pass});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean registerAdmin(String user, String pass) {
        SQLiteDatabase MyDB = openHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", user);
        contentValues.put("password", pass);
        long result = MyDB.insert("admin", null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    public Boolean registerUser(EditText username, String email, EditText phone, EditText password, EditText service_provided) {
        SQLiteDatabase db = openHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("username", String.valueOf(username));
        cv.put("email", email);
        cv.put("phone", String.valueOf(phone));
        cv.put("password", String.valueOf(password));
        long result = db.insert("users", null, cv);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public Boolean bookHotel(String phone, String type, String number_of_occupants, String date) {
        SQLiteDatabase db = openHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("phone", String.valueOf(phone));
        cv.put("type", String.valueOf(type));
        cv.put("number_of_occupants", String.valueOf(number_of_occupants));
        cv.put("date", String.valueOf(date));
        long result = db.insert("hotel_service_orders", null, cv);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean addService(String service, String cate, String ty, String provName, String provPhone, String provEmail, String locat) {
        SQLiteDatabase db = openHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("name", String.valueOf(service));
        cv.put("category", String.valueOf(cate));
        cv.put("service_type", String.valueOf(ty));
        cv.put("provider_name", String.valueOf(provName));
        cv.put("phone", String.valueOf(provPhone));
        cv.put("email", String.valueOf(provEmail));
        cv.put("location", String.valueOf(locat));
        long result = db.insert("services", null, cv);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
}
