package baseDeDonnees;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by mohsen raeisi on 10/12/2014.
 */
public class DbDates {


        public static final String ROWID = "_id";
        public static final String  SOMME = "somme";
        public static final String  DATE = "date";



        private static final String TAG = "dateDbAdapter";
        private DatabaseHelper datesDbHelper;
        private SQLiteDatabase datesDb;

        private static final String DATABASE_NAME = "dates";
        private static final String SQLITE_TABLE = "dates";
        private static final int DATABASE_VERSION = 1;

        private final Context datesCtx;

        private static final String DATABASE_CREATE =
                "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
                        ROWID + " integer PRIMARY KEY autoincrement," +
                        DATE + "," +
                       SOMME+");";



        private static class DatabaseHelper extends SQLiteOpenHelper {

            DatabaseHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
            }


            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL("DROP TABLE IF EXISTS "+SQLITE_TABLE);
                Log.w(TAG, DATABASE_CREATE);

                db.execSQL(DATABASE_CREATE);
            }

            @Override

            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
                db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE);
                onCreate(db);
            }


/*
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String upgradeQuery = "ALTER TABLE userinfo ADD COLUMN date TEXT";
            if (newVersion == 2){
                db.execSQL(upgradeQuery);
               // Log.e("worked !!!!!!!", " on upgrate");
            }
        }
        */


        }


        public DbDates(Context ctx) {
            this.datesCtx = ctx;
        }

        public DbDates open() throws SQLException {
            datesDbHelper = new DatabaseHelper(datesCtx);
            datesDb = datesDbHelper.getWritableDatabase();
            return this;
        }



        public void close() {
            if (datesDbHelper != null) {
                datesDbHelper.close();
            }
        }

        public long createRecord(String somme,String date) {

            ContentValues initialValues = new ContentValues();
            initialValues.put(SOMME, somme);
            initialValues.put(DATE, date);
            return datesDb.insert(SQLITE_TABLE, null, initialValues);

        }



    public boolean updateRecordByDate(String id,String Somme){

            String strFilter = "_id=" + id;
            ContentValues args = new ContentValues();
            args.put(SOMME,Somme);


            int doneDelete = 0;

            doneDelete = datesDb.update(SQLITE_TABLE, args, strFilter, null);

            Log.w(TAG, Integer.toString(doneDelete));
            return doneDelete > 0;



    }
        public boolean deleteAll() {

            int doneDelete = 0;
            doneDelete = datesDb.delete(SQLITE_TABLE, null , null);

            Log.w(TAG, Integer.toString(doneDelete));
            return doneDelete > 0;

        }



        public boolean deleteItemId(String id) {

            int doneDelete = 0;
            doneDelete = datesDb.delete(SQLITE_TABLE,ROWID + "=" + id, null);

            Log.w(TAG, Integer.toString(doneDelete));
            return doneDelete > 0;

        }




        public Cursor getUser() {

            Cursor mCursor = datesDb.query(SQLITE_TABLE, new String[] {ROWID ,
                            SOMME ,DATE},
                    null, null, null, null, null);


            if (mCursor != null) {
                mCursor.moveToFirst();

            }else{
                Log.e("error" , "null");
            }
            return mCursor;
        }

    }


