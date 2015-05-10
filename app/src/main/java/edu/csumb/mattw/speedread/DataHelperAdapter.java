package edu.csumb.mattw.speedread; /**
 * Created by Matt on 5/9/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Message;
import android.widget.Toast;

public class DataHelperAdapter {

    DataHelper helper;
    public DataHelperAdapter(Context context) {
        helper = new DataHelper(context);
    }

    public long insertData(String userName, String password) {
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataHelper.NAME, userName);
        contentValues.put(DataHelper.PASSWORD, password);
        long id = db.insert(DataHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    public String getAllData() {
        SQLiteDatabase db = helper.getReadableDatabase();

        String[] columns = {DataHelper.NAME};
        Cursor cursor = db.query(DataHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int cid = cursor.getInt(0);
            String name=cursor.getString(1);
            String password = cursor.getString(2);
            buffer.append(cid+ " " + name + " " + password + "\n");
        }
        return buffer.toString();
    }

    public void getUserName() {
        SQLiteDatabase db = helper.getReadableDatabase();

        String[] columns = {DataHelper.NAME};
        Cursor cursor = db.query(DataHelper.TABLE_NAME, columns, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex(DataHelper.UID);
            int cid = cursor.getInt(index1);
            cursor.getString(index1);
        }
    }

    static class DataHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "projectdatabase";
        private static final String TABLE_NAME = "USERTABLE";
        private static final String UID = "_id";
        private static final String NAME = "userName";
        private static final String PASSWORD = "password";
        private static final int DATABASE_VERSION = 1;
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " VARCHAR(16), password VARCHAR(16));";
        private static final String DROP_TABLE = "DROP TABLE If EXISTS" + TABLE_NAME;
        private Context context;

        public DataHelper(Context context) {

            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }
        @Override
        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
            } catch (SQLException e) {
                Toast.makeText(context, new StringBuilder().append("").append(e).toString(), Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL(DROP_TABLE);
            } catch (SQLException e) {
                Toast.makeText(context, new StringBuilder().append("").append(e).toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
}

