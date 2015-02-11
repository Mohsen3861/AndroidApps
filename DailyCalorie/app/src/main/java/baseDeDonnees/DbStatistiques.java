package baseDeDonnees;
 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
 
public class DbStatistiques {
 
 public static final String KEY_ROWID = "_id";
 public static final String KEY_NAME = "name";
 public static final String KEY_CALORIE = "calorie";
 public static final String KEY_DATE = "date";
 
 private static final String TAG = "caloriesDbAdapter";
 private DatabaseHelper mDbHelper;
 private SQLiteDatabase mDb;
 
 private static final String DATABASE_NAME = "statistiques";
 private static final String SQLITE_TABLE = "calories";
 private static final int DATABASE_VERSION = 2;
 
 private final Context mCtx;
 
 private static final String DATABASE_CREATE =
  "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
  KEY_ROWID + " integer PRIMARY KEY autoincrement," +
  KEY_NAME + "," +
  KEY_CALORIE  +","+KEY_DATE+");";
 
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
  /*
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
   Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
     + newVersion + ", which will destroy all old data");
   db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE);
   onCreate(db);
  }
 }
 */
  
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    String upgradeQuery = "ALTER TABLE calories ADD COLUMN date TEXT";
	    if (newVersion == 2){
	         db.execSQL(upgradeQuery);
	         Log.e("worked !!!!!!!", " on upgrate");
	    }
	}
 }
 
 public DbStatistiques(Context ctx) {
  this.mCtx = ctx;
 }
 
 public DbStatistiques open() throws SQLException {
  mDbHelper = new DatabaseHelper(mCtx);
  mDb = mDbHelper.getWritableDatabase();
  return this;
 }
 
 public void close() {
  if (mDbHelper != null) {
   mDbHelper.close();
  }
 }
 
 public long createRecord(String name, 
   String cal , String date) {
 
  ContentValues initialValues = new ContentValues();
  initialValues.put(KEY_NAME, name);
  initialValues.put(KEY_CALORIE, cal);
  initialValues.put(KEY_DATE, date);
 
  return mDb.insert(SQLITE_TABLE, null, initialValues);
 }
 
 public boolean deleteAll() {
 
  int doneDelete = 0;
  doneDelete = mDb.delete(SQLITE_TABLE, null , null);
 
  Log.w(TAG, Integer.toString(doneDelete));
  return doneDelete > 0;
 
 }
 
 public boolean deleteItem(String nom ,  String date) {
	 
	  int doneDelete = 0;
	  doneDelete = mDb.delete(SQLITE_TABLE,KEY_NAME + " = " + nom + " AND "+ KEY_DATE
			  + "=" + date, null);
	 
	  Log.w(TAG, Integer.toString(doneDelete));
	  return doneDelete > 0;
	 
	 }
 
 
 public boolean deleteItemId(String id) {
	 
	  int doneDelete = 0;
	  doneDelete = mDb.delete(SQLITE_TABLE,KEY_ROWID + "=" + id, null);
	  
	  Log.e(TAG, Integer.toString(doneDelete));
	  return doneDelete > 0;
	 
	 }
 
 
 public boolean updateItemId(String id,String calorie) {
	 
	 String strFilter = "_id=" + id;
	 ContentValues args = new ContentValues();
	 args.put(KEY_CALORIE, calorie);
	 
	 
	  int doneDelete = 0;
	  doneDelete = mDb.update(SQLITE_TABLE, args, strFilter, null);
	  
	  Log.w(TAG, Integer.toString(doneDelete));
	  return doneDelete > 0;
	 
	 }
 
 
 
 
 public Cursor fetchCountriesByDate(String inputText) throws SQLException {
  Log.w(TAG, inputText);
  Cursor mCursor = null;
  if (inputText == null  ||  inputText.length () == 0)  {
   mCursor = mDb.query(SQLITE_TABLE, new String[] {KEY_ROWID,
     KEY_NAME, KEY_CALORIE,KEY_DATE}, 
     null, null, null, null, null);
 
  }
  else {
   mCursor = mDb.query(true, SQLITE_TABLE, new String[] {KEY_ROWID,
     KEY_NAME, KEY_CALORIE,KEY_DATE}, 
     KEY_DATE + " like '%" + inputText + "%'", null,
     null, null, null, null);
  }
  if (mCursor != null) {
   mCursor.moveToFirst();
  }
  return mCursor;
 
 }
 
 
 public Cursor fetchCountriesById(String inputText) throws SQLException {
	  Log.w(TAG, inputText);
	  Cursor mCursor = null;
	  if (inputText == null  ||  inputText.length () == 0)  {
	   mCursor = mDb.query(SQLITE_TABLE, new String[] {KEY_ROWID,
	     KEY_NAME, KEY_CALORIE,KEY_DATE}, 
	     null, null, null, null, null);
	 
	  }
	  else {
	   mCursor = mDb.query(true, SQLITE_TABLE, new String[] {KEY_ROWID,
	     KEY_NAME, KEY_CALORIE,KEY_DATE}, 
	     KEY_ROWID + " like '%" + inputText + "%'", null,
	     null, null, null, null);
	  }
	  if (mCursor != null) {
	   mCursor.moveToFirst();
	  }
	  return mCursor;
	 
	 }

 
 public Cursor fetchAllCountries() {
 
  Cursor mCursor = mDb.query(SQLITE_TABLE, new String[] {KEY_ROWID,
     KEY_NAME, KEY_CALORIE,KEY_DATE}, 
    null, null, null, null, null);


  if (mCursor != null) {
   mCursor.moveToFirst();

  }
  return mCursor;
 }
 
 
 
 
 public void insertStatistique(String nom , String cal,String date) {
 
  createRecord(nom,cal,date);

 
 }
 
}