package itesm.mx.conexiontec;

import android.provider.BaseColumns;

public class DataBaseSchema {
    private DataBaseSchema() {}

    public static class EventTable implements BaseColumns {
        public static final String TABLE_NAME = "historial";
        public static final String COLUMN_NAME_TIPO = "tipo";
        public static final String COLUMN_NAME_PREGUNTAS = "preguntas";
        public static final String COLUMN_NAME_CORRECTAS = "correctas";
        public static final String COLUMN_NAME_INCORRECTAS = "incorrectas";
        public static final String COLUMN_NAME_LASTEXAM = "ultimoexamen";
    }
}