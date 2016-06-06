package com.spottechnicians.caudit.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.spottechnicians.caudit.models.Atm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ganesh on 6/1/2016.
 */
public class DbHelper extends SQLiteOpenHelper
{
    private static final String DB_NAME="CAudit";
    private static final  int DB_VERSION=3;
    public static final String COLUMN_ID_ATM="id";
    public static final String COLUMN_SUPERVISOR_ID_ATM="supervisor_id";
    public static final String COLUMN_ATM_ID_ATM="atm_id";
    public static final String TABLE_ATM="atm";
    public static final String COLUMN_BANK_NAME_ATM="bank_name";
    public static final String COLUMN_CUSTOMER_NAME_ATM="customer_name";
    public static final String COLUMN_ADDRESS_ATM="address";
    public static final String COLUMN_CITY_ATM="city";
    public static final String COLUMN_STATE_ATM="state";
    public static final String COLUMN_TYPE_ATM="type";


    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    public List<Atm> fetchAtms()
{
    List<Atm> atmList=new ArrayList<Atm>();
    String selectQuery="SELECT * FROM "+TABLE_ATM;
    SQLiteDatabase databaseRead=this.getReadableDatabase();
    Cursor cursor=databaseRead.rawQuery(selectQuery,null);
    if(cursor.moveToFirst())
    {
        do{

            atmList.add(new Atm(cursor.getString(cursor.getColumnIndex(COLUMN_SUPERVISOR_ID_ATM)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_ATM_ID_ATM)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_BANK_NAME_ATM)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_NAME_ATM)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_ATM)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_CITY_ATM)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_STATE_ATM))
                    ,cursor.getString(cursor.getColumnIndex(COLUMN_TYPE_ATM))));
        }while(cursor.moveToNext());
    }
    return atmList;
}

    public List<Atm> fetchAtmsByType(String type)
    {
        List<Atm> atmList=new ArrayList<Atm>();
        String selectQuery="SELECT * FROM "+TABLE_ATM+ " WHERE "+COLUMN_TYPE_ATM+" = "+type;
        SQLiteDatabase databaseRead=this.getReadableDatabase();
        Cursor cursor=databaseRead.rawQuery(selectQuery,null);
        if(cursor.moveToFirst())
        {
            do{

                atmList.add(new Atm(cursor.getString(cursor.getColumnIndex(COLUMN_SUPERVISOR_ID_ATM)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ATM_ID_ATM)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_BANK_NAME_ATM)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_NAME_ATM)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_ATM)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_CITY_ATM)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_STATE_ATM))
                        ,cursor.getString(cursor.getColumnIndex(COLUMN_TYPE_ATM))));
            }while(cursor.moveToNext());
        }
        return atmList;
    }
    public  boolean insertATM(List<Atm> atmList)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        long errorCode;
        boolean insertStatus=true;
        ContentValues contentValues=new ContentValues();
        for(int i=0;i<atmList.size();i++)
        {
            contentValues.put(COLUMN_SUPERVISOR_ID_ATM,atmList.get(i).getSupervisorId());
            contentValues.put(COLUMN_ATM_ID_ATM,atmList.get(i).getAtmId());
            contentValues.put(COLUMN_BANK_NAME_ATM,atmList.get(i).getBankName());
            contentValues.put(COLUMN_CUSTOMER_NAME_ATM,atmList.get(i).getCustomerName());
            contentValues.put(COLUMN_ADDRESS_ATM,atmList.get(i).getAddress());
            contentValues.put(COLUMN_CITY_ATM,atmList.get(i).getCity());
            contentValues.put(COLUMN_STATE_ATM,atmList.get(i).getState());
            contentValues.put(COLUMN_STATE_ATM,atmList.get(i).getType());
            errorCode= database.insert(TABLE_ATM,null,contentValues);
            if(errorCode==-1)
            {
                insertStatus=false;
            }
        }



        return insertStatus;
    }
    
    
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

            try
            {
                String createDb = "CREATE TABLE " + TABLE_ATM + "(" + COLUMN_ID_ATM + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                        + COLUMN_SUPERVISOR_ID_ATM + " TEXT ,"
                        + COLUMN_ATM_ID_ATM + " TEXT ,"
                        + COLUMN_BANK_NAME_ATM + " TEXT ,"
                        + COLUMN_CUSTOMER_NAME_ATM + " TEXT ,"
                        + COLUMN_ADDRESS_ATM + " TEXT ,"
                        + COLUMN_CITY_ATM + " TEXT ,"
                        + COLUMN_STATE_ATM + " TEXT ,"
                        + COLUMN_TYPE_ATM+" TEXT)";

                sqLiteDatabase.execSQL(createDb);

                Log.e("Mangal","Table ATM create successfully");

            }catch(SQLException e)
            {
                Log.e("Mangal",e.toString());
            }



    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+TABLE_ATM);
        onCreate(sqLiteDatabase);

    }


}
