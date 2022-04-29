package com.example.tourguide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseAccess {
    private static Context context;
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c = null;

    DatabaseAccess(Context context){
        this.openHelper = new DatabaseOpenHelper(context);
    }
    public static DatabaseAccess getInstance(){
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }
    public void open(){
        this.db=openHelper.getWritableDatabase();
    }
    public void close(){
        if (db != null) {
            this.db.close();
        }
    }

    public String getServices(String name){
        c=db.rawQuery("select name from services where name = '"+name+"'",new String[]{});
        StringBuffer stringBuffer = new StringBuffer();
        while(c.moveToNext()){
            String serviceName = c.getString(0);
            stringBuffer.append(""+serviceName);
        }
        return stringBuffer.toString();
    }
    void addService(String title, String author, String pages){
        SQLiteDatabase db = openHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("name", title);
        cv.put("district", author);
        cv.put("shortname", pages);
        long result = db.insert("universities",null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllServices(){
        String query = "SELECT * FROM services";
        SQLiteDatabase db = openHelper.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


    public Boolean insertData(String name, String email, String password ){
        SQLiteDatabase MyDB = openHelper.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = MyDB.insert("admin", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkusername(String name) {
        SQLiteDatabase MyDB = openHelper.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from admin where name = ?", new String[]{name});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String name, String password){
        SQLiteDatabase MyDB = openHelper.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from admin where name = ? and password = ?", new String[] {name,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
