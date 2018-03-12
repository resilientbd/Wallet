package com.suman.msi.wallet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by msi on 2/26/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="Wallet.db";
    private static final String TABLE_NAME="Wallet_details";
    private static final Integer VERSION_NUMBER=1;
    private static final String ID="_Id";
    private static final String AMOUNT="amount";
    private static final String NOTE="Name";
    private static final String CATEGORY="category";
    private static final String DATE="date";
    private static final String LOG="databaseHelper";
    private Context context;
    private static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ( "+ID+" INT PRIMARY KEY AUTOINCREMENT" +
            ","+AMOUNT+" DOUBLE,"+NOTE+" TEXT,"+CATEGORY+" TEXT,"+DATE+" TEXT)";
    private static final String DROP_TABLE="DROP TABLE IF EXISTS "+TABLE_NAME;
    private static final String SELECT_ALL="SELECT * FROM "+TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(CREATE_TABLE);
            Log.d(LOG, "success! table created successfully: ");
        }catch (Exception e){
            Toast.makeText(context,"Exception"+e,Toast.LENGTH_LONG);
            Log.d(LOG,"Error: "+e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        try{
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);
        }catch (Exception e){
            Toast.makeText(context,"Exception"+e,Toast.LENGTH_SHORT);
        }
    }


    public boolean insertRecords(Records records) //void
    {
        boolean flag=false;
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(AMOUNT, records.getAmount());
            values.put(NOTE, records.getNote());
            values.put(CATEGORY, records.getCategory());
            values.put(DATE, records.getDate());
            db.insert(TABLE_NAME, null, values);
            flag=true;
            Log.d(LOG, "success! data inserted successfully: ");
            db.close();
        }catch (Exception e)
        {
            Log.d(LOG,"Error: "+e);
        }
        return flag;
    }

    public boolean updateRecords(Records records) {
        boolean flag = false;
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(AMOUNT, records.getAmount());
            values.put(NOTE, records.getNote());
            values.put(CATEGORY, records.getCategory());
            values.put(DATE, records.getDate());
            db.update(TABLE_NAME, values, ID + "?", new String[]{Integer.toString(records.getRec_id())});
            flag = true;
            Log.d(LOG, "success! data updated successfully: ");
            db.close();
        } catch (Exception e) {
            Log.d(LOG, "Error: " + e);
        }
        return flag;
    }
    public boolean deleteRecords(int id) {
        boolean flag = false;
        try {
            SQLiteDatabase db = this.getWritableDatabase();


            db.delete(TABLE_NAME,ID + "?", new String[]{Integer.toString(id)});
            flag = true;
            Log.d(LOG, "success! data deleted successfully: ");
            db.close();
        } catch (Exception e) {
            Log.d(LOG, "Error: " + e);
        }
        return flag;
    }
    public List<Records> getData(){
        List<Records> list=new ArrayList<Records>();
        String sql="SELECT * FROM "+TABLE_NAME;
        try{
            SQLiteDatabase db=this.getReadableDatabase();
            Cursor rs=db.rawQuery(sql,null);
            if (rs!=null)
            {
                while(rs.moveToNext())
                {
                    list.add(new Records(rs.getInt(1),rs.getDouble(2),rs.getString(3),rs.getString(4),rs.getString(5)));
                }
            }
            Log.d(LOG, "success! data retrived successfully: ");
            db.close();
        }catch (Exception e) {
            Log.d(LOG, "Error: " + e);
        }
        return list;
    }
}
