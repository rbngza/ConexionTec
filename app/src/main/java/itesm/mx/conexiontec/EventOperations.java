package itesm.mx.conexiontec;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

public class EventOperations {
    private SQLiteDatabase db;
    private EventDBHelper dbHelper;
    private Historial historial;

    public EventOperations(Context context) {
        dbHelper = new EventDBHelper(context);
    }

    public void open() throws SQLException {
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLException e) {
            Log.e("SQLOPEN", e.toString());
        }
    }

    public void close() {
        db.close();
    }

    //Methods to convert the date to a number that's possible to save in the database...
    public static Long persistDate(Date date) {
        if (date != null) {
            return date.getTime();
        }
        return null;
    }

    //...and back to a Date
    public static Date loadDate(Long time) {
        return new Date(time);
    }

    public long addExam (Historial historial) {
        long newRowId = 0;
        try {
            ContentValues values = new ContentValues();
            values.put(DataBaseSchema.EventTable.COLUMN_NAME_TIPO, historial.getTipo());
            values.put(DataBaseSchema.EventTable.COLUMN_NAME_CORRECTAS, historial.getCorrectas());
            values.put(DataBaseSchema.EventTable.COLUMN_NAME_INCORRECTAS, historial.getIncorrectas());
            values.put(DataBaseSchema.EventTable.COLUMN_NAME_PREGUNTAS, historial.getPreguntas());
            values.put(DataBaseSchema.EventTable.COLUMN_NAME_LASTEXAM, historial.getLastexam());

            newRowId = db.insert(DataBaseSchema.EventTable.TABLE_NAME, null, values);
        } catch (SQLException e) {
            Log.e("SQLADD", e.toString());
        }
        return newRowId;
    }

    public boolean deleteEvent(long id) {
        boolean result = false;
        try {db.delete(DataBaseSchema.EventTable.TABLE_NAME,
                DataBaseSchema.EventTable._ID + " = ?",
                new String[]{String.valueOf(id)});
            result = true;
        } catch (SQLiteException e) {
            Log.e("SQLDELETE", e.toString());
        }
        return result;
    }

    public ArrayList<Historial> getAllEvents() {
        ArrayList<Historial> listEvents = new ArrayList<Historial>();
        String selectQuery = "SELECT * FROM " + DataBaseSchema.EventTable.TABLE_NAME;
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    historial = new Historial(cursor.getString(1),
                            Integer.parseInt(cursor.getString(2)),
                            Integer.parseInt(cursor.getString(3)),
                            Integer.parseInt(cursor.getString(4)),
                            cursor.getInt(5) > 0);
                    listEvents.add(historial);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLException e) {
            Log.e("SQLLIST", e.toString());
        }
        return listEvents;
    }
}
