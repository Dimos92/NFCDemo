package com.dong.nfcsecurity.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.dong.nfcsecurity.db.model.Decoration;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DecorationDBHelper extends SQLiteOpenHelper {

    private static final String TAG = "DecorationDBHelper";
    private static final String DB_NAME = "DecorationDB.db";
    private static final int DB_VERSION = 0x01;

    private Context mContext;

    public DecorationDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            Log.i(TAG, "onUpgrade: oldVersion == " + oldVersion + " newVersion " + newVersion);
        }
    }

    public void createTable() {
        executeAssetsSQL(getWritableDatabase(), "CreateTable.sql");
    }

    public void insertData() {
        executeAssetsSQL(getWritableDatabase(), "InsertData.sql");
    }

    public Decoration selectDecoration(int id) {
        Log.i(TAG, "selectDecoration: id == " + id);

        Decoration decoration = queryDecoration(id);
        if (decoration != null) {
            queryFactory(id, decoration);
            querySecurity(id, decoration);
            queryReseller(id, decoration);
            //修改标志变量
            setSecurityChecked(id);
        }
        return decoration;
    }

    private void setSecurityChecked(int id) {
        getWritableDatabase().execSQL("update fw_check_table set 'check' = 1 where hzpId = " + id);
    }

    private void queryFactory(int id, Decoration decoration) {
        Decoration.Factory factory = new Decoration.Factory();
        Cursor cursor = getWritableDatabase().rawQuery("select * from cs_table where hzpId = ? ", new String[]{String.valueOf(id)});
        while (cursor.moveToNext()) {
            factory.setId(cursor.getString(cursor.getColumnIndex("csId")));
            factory.setAddress(cursor.getString(cursor.getColumnIndex("adress")));
            factory.setName(cursor.getString(cursor.getColumnIndex("csName")));
            factory.setProductDate(cursor.getString(cursor.getColumnIndex("chDate")));
            factory.setTelephone(cursor.getString(cursor.getColumnIndex("telephone")));
        }
        cursor.close();
        decoration.setFactory(factory);
    }

    private Decoration queryDecoration(int id) {
        Decoration decoration = new Decoration();
        Cursor cursor = getWritableDatabase().rawQuery("select * from hzp_table where hzpId = ?", new String[]{String.valueOf(id)});
        if (cursor.getCount() == 0) {
            return null;
        }
        while (cursor.moveToNext()) {
            decoration.setId(cursor.getString(cursor.getColumnIndex("hzpId")));
            decoration.setName(cursor.getString(cursor.getColumnIndex("hzpName")));
            decoration.setProduceDate(cursor.getString(cursor.getColumnIndex("scDate")));
            decoration.setExpiration(cursor.getString(cursor.getColumnIndex("bzq")));
        }
        cursor.close();
        return decoration;
    }


    private void querySecurity(int id, Decoration decoration) {
        Decoration.Security security = new Decoration.Security();
        Cursor cursor = getWritableDatabase().rawQuery("select * from fw_check_table where hzpId = ? ", new String[]{String.valueOf(id)});
        while (cursor.moveToNext()) {
            security.setNo(cursor.getInt(cursor.getColumnIndex("No")));
            security.setCheck(cursor.getInt(cursor.getColumnIndex("check")) != 0);
            security.setLabelID(cursor.getString(cursor.getColumnIndex("bqId")));
        }
        cursor.close();
        decoration.setSecurity(security);
    }

    private void queryReseller(int id, Decoration decoration) {
        Decoration.Reseller reseller = new Decoration.Reseller();
        Cursor cursor = getWritableDatabase().rawQuery("select * from jxs_table where hzpId = ? ", new String[]{String.valueOf(id)});
        while (cursor.moveToNext()) {
            reseller.setId(cursor.getString(cursor.getColumnIndex("jxsId")));
            reseller.setName(cursor.getString(cursor.getColumnIndex("jxsName")));
            reseller.setInDate(cursor.getString(cursor.getColumnIndex("jhDate")));
            reseller.setOutDate(cursor.getString(cursor.getColumnIndex("chDate")));
        }
        cursor.close();
        decoration.setReseller(reseller);
    }

    /**
     * 读取数据库文件（.sql），并执行sql语句
     *
     * @param db         数据库操作类
     * @param dbfilepath assets下的*.sql文件路径
     */
    private void executeAssetsSQL(SQLiteDatabase db, String dbfilepath) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(mContext.getAssets().open(dbfilepath)));
            System.out.println("路径:" + dbfilepath);
            String line;
            StringBuilder buffer = new StringBuilder();
            //开启事务
            db.beginTransaction();
            while ((line = in.readLine()) != null) {
                buffer.append(line);
                if (line.trim().endsWith(";")) {
                    db.execSQL(buffer.toString().replace(";", ""));
                    buffer = new StringBuilder();
                }
            }
            //设置事务标志为成功，当结束事务时就会提交事务
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("db-error", e.toString());
        } finally {
            //事务结束
            db.endTransaction();
            try {
                if (in != null)
                    in.close();
            } catch (Exception e) {
                Log.e("db-error", e.toString());
            }
        }
    }
}
