package co.tiagoaguiar.codelab.myapplication.sqlhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import co.tiagoaguiar.codelab.myapplication.Register;

public class SqlHelper extends SQLiteOpenHelper {

    private static final String NOME_DB = "fitness.db";
    private static final int VERSION = 1;

    private static SqlHelper INSTANCE;

    public static SqlHelper getInstance(Context context){
        if(INSTANCE == null)
            INSTANCE = new SqlHelper(context);
            return INSTANCE;
    }

    public SqlHelper(@Nullable Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE calc (id INTEGER primary key, type_calc TEXT, res DECIMAL, create_data DATETIME)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addItem(String type, double response){

        SQLiteDatabase db = getWritableDatabase();
        long calcId = 0;

        try{
            db.beginTransaction();

            ContentValues values = new ContentValues();
            values.put("type_calc", type);
            values.put("res", response);

            SimpleDateFormat sdf = new  SimpleDateFormat("yyyy/MM/dd HH:mm:ss", new Locale("pt", "BR"));
            String now = sdf.format(new Date());

            values.put("create_data", now );

            calcId = db.insertOrThrow("calc", null,values);
            db.setTransactionSuccessful();

        }catch (Exception e){

            Log.e("SQLite", e.getMessage());

        }finally {
            if(db.isOpen())
                db.endTransaction();
        }
        return calcId;
    }

    public List<Register> getRegisterBy(String type){

        List<Register> registers = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM calc WHERE type_calc=?", new String[]{type});

        try{

            if(cursor.moveToFirst()){
                do {

                    Register register = new Register();
                    register.type = cursor.getString(cursor.getColumnIndex("type"));
                    register.response = cursor.getDouble(cursor.getColumnIndex("res"));
                    register.createdDate = cursor.getString(cursor.getColumnIndex("create_data"));

                    registers.add(register);

                }while (cursor.moveToNext());
            }

        }catch (Exception e){
            Log.e("SQLite", e.getMessage(), e);
        }finally {
            if(cursor != null && !cursor.isClosed()){
                cursor.close();
            }
        }

        return registers;
    }
}
