package baseDeDonnees;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by mohsen raeisi on 07/12/2014.
 */
public class DbUser  {

    public static final String ROWID = "_id";
    public static final String  AGE = "age";
    public static final String POIDS = "poids";
    public static final String TAILLE = "taille";
    public static final String ACTIVITY = "activiry";
    public static final String IMC = "IMC";
    public static final String ACTIVITYPREFERE = "activityprefere";
    public static final String SEXE= "sexe";
    public static final String CALORIEIDEAL = "calorieideal";


    private static final String TAG = "userDbAdapter";
    private DatabaseHelper userDbHelper;
    private SQLiteDatabase userDb;

    private static final String DATABASE_NAME = "utilisateur";
    private static final String SQLITE_TABLE = "userinfo";
    private static final int DATABASE_VERSION = 1;

    private final Context userCtx;

    private static final String DATABASE_CREATE =
            "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
                    ROWID + " integer PRIMARY KEY autoincrement," +
                    AGE + "," +
                    POIDS  +","+
                    TAILLE  +","+
                    ACTIVITY  +","+
                    IMC  +","+
                    ACTIVITYPREFERE  +","+
                    SEXE  +","+
                    CALORIEIDEAL+");";



    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            //  db.execSQL("DROP TABLE IF EXISTS "+SQLITE_TABLE);
            //onUpgrade(db,DATABASE_VERSION,2);
            Log.w(TAG, DATABASE_CREATE);

            db.execSQL(DATABASE_CREATE);
            //onUpgrade(db,DATABASE_VERSION,2);
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


    public DbUser(Context ctx) {
        this.userCtx = ctx;
    }

    public DbUser open() throws SQLException {
        userDbHelper = new DatabaseHelper(userCtx);
        userDb = userDbHelper.getWritableDatabase();
        return this;
    }


    public void close() {
        if (userDbHelper != null) {
            userDbHelper.close();
        }
    }

    public long createRecord(String age,
                             String poid , String taille ,String activity ,String imc , String activityprefere ,String sexe, String calorieideal  ) {

        ContentValues initialValues = new ContentValues();
        initialValues.put(AGE, age);
        initialValues.put(POIDS, poid);
        initialValues.put(TAILLE, taille);
        initialValues.put(ACTIVITY, activity);
        initialValues.put(IMC, imc);
        initialValues.put(ACTIVITYPREFERE,activityprefere);
        initialValues.put(SEXE,sexe);
        initialValues.put(CALORIEIDEAL, calorieideal);

        return userDb.insert(SQLITE_TABLE, null, initialValues);
    }

    public boolean deleteAll() {

        int doneDelete = 0;
        doneDelete = userDb.delete(SQLITE_TABLE, null , null);

        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;

    }



    public boolean deleteItemId(String id) {

        int doneDelete = 0;
        doneDelete = userDb.delete(SQLITE_TABLE,ROWID + "=" + id, null);

        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;

    }




    public Cursor getUser() {

        Cursor mCursor = userDb.query(SQLITE_TABLE, new String[] {ROWID ,
                        AGE , POIDS ,TAILLE ,ACTIVITY,IMC ,ACTIVITYPREFERE ,SEXE,CALORIEIDEAL   },
                null, null, null, null, null);


        if (mCursor != null) {
            mCursor.moveToFirst();

        }else{
            Log.e("error" , "null");
        }
        return mCursor;
    }

}
