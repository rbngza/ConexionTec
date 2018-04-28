package itesm.mx.conexiontec;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class EventDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "EventDB.db";
    private static final int DATABASE_VERSION = 1;

    public EventDBHelper(Context context) {
        //Create database
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EVENTS_TABLE = "CREATE TABLE " +
                DataBaseSchema.EventTable.TABLE_NAME +
                "(" +
                DataBaseSchema.EventTable._ID + " INTEGER PRIMARY KEY," +
                DataBaseSchema.EventTable.COLUMN_NAME_TIPO + " TEXT," +
                DataBaseSchema.EventTable.COLUMN_NAME_PREGUNTAS + " INTEGER," +
                DataBaseSchema.EventTable.COLUMN_NAME_CORRECTAS + " INTEGER," +
                DataBaseSchema.EventTable.COLUMN_NAME_INCORRECTAS + " INTEGER," +
                DataBaseSchema.EventTable.COLUMN_NAME_LASTEXAM + " BOOLEAN" +
                ")";
        Log.i("Eventhelper onCreate", CREATE_EVENTS_TABLE);
        db.execSQL(CREATE_EVENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DELETE_EVENT_TABLE = "DROP TABLE IF EXISTS  " +
                DataBaseSchema.EventTable.TABLE_NAME;
        db.execSQL(DELETE_EVENT_TABLE);
        onCreate(db);
    }

    public  void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //actualize database version
    }
}