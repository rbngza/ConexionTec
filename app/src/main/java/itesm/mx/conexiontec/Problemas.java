package itesm.mx.conexiontec;

import android.os.Parcel;
import android.os.Parcelable;

public class Problemas implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public MainMenu createFromParcel(Parcel in) {
            return new MainMenu(in);
        }

        public MainMenu[] newArray(int size) {
            return new MainMenu[size];
        }
    };

    private String tipo;
    private String parte1;
    private String parte2;
    private String parte3;


    public Problemas (String title){
        this.tipo = tipo;
    }

    public void setTipo(String title) {this.tipo = tipo;}
    public String getTipo(){return tipo;}

    public void setParte1(String parte1) {this.parte1 = parte1;}
    public String getParte1(){return parte1;}

    public void setParte2(String parte2) {this.parte2 = parte2;}
    public String getParte2(){return parte2;}

    public void setParte3(String parte3) {this.parte3 = parte3;}
    public String getParte3(){return parte3;}

    public Problemas(Parcel in){
        this.tipo = in.readString();
        this.parte1 = in.readString();
        this.parte2 = in.readString();
        this.parte3 = in.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tipo);
        dest.writeString(this.parte1);
        dest.writeString(this.parte2);
        dest.writeString(this.parte3);
    }
}
