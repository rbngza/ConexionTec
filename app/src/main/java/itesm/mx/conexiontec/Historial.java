package itesm.mx.conexiontec;

import android.os.Parcel;
import android.os.Parcelable;

public class Historial implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public MainMenu createFromParcel(Parcel in) {
            return new MainMenu(in);
        }

        public MainMenu[] newArray(int size) {
            return new MainMenu[size];
        }
    };

    private String tipo;
    private int preguntas;
    private int correctas;
    private int incorrectas;
    private boolean lastexam;



    public Historial (String tipo, int preguntas, int correctas, int incorrectas){
        this.tipo = tipo;
        this.preguntas = preguntas;
        this.correctas = correctas;
        this.incorrectas = incorrectas;
    }

    public void setTipo(String title) {this.tipo = tipo;}
    public String getTipo(){return tipo;}

    public void setPreguntas(int preguntas) {this.preguntas = preguntas;}
    public int getPreguntas(){return preguntas;}

    public void setCorrectas(int correctas) {this.correctas = correctas;}
    public int getCorrectas(){return correctas;}

    public void setIncorrectas(int incorrectas) {this.incorrectas = incorrectas;}
    public int getIncorrectas(){return incorrectas;}


    public Historial(Parcel in){
        this.tipo = in.readString();
        this.preguntas = in.readInt();
        this.correctas = in.readInt();
        this.incorrectas = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tipo);
        dest.writeInt(this.preguntas);
        dest.writeInt(this.correctas);
        dest.writeInt(this.incorrectas);
    }
}
