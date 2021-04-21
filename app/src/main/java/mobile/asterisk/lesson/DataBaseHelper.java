package mobile.asterisk.lesson;

import android.database.sqlite.SQLiteDatabase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Lesson.db";
    public static final String TABLE_NAME = "Test";

    public static SQLiteDatabase DataBase ;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Test(id VARCHAR, name VARCHAR);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public int InsertRow(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert("Test",null ,contentValues);
        return (int)result;
    }

    public Cursor Select(String sql, String[] args) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(sql,args);
        return res;
    }



    public Integer deleteRow (String table, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(table, "ID = ?",new String[] {id});
    }

    public int updateRow(String table, ContentValues contentValues, String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.update(table, contentValues, "ID = ?",new String[] { id });
    }

    private String getSqLiteType(String type){
        switch (type){
            case "int":
                return "INTEGER";
            case "Double":
                return "REAL";
            case "Float":
                return "REAL";
            case "String":
                return  "TEXT";
            case "Date":
                return  "TEXT";
            case "byte[]":
                return "BLOB";
        }
        return null;
    }



}