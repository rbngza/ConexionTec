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
    private Double velcorte;
    private Double diametro;
    private Double rpm;
    private Double velavance;
    private Double avancepordiente;
    private Double numdedientes;
    private Double avanceporrev;
    private Double volenmm3;



    public Problemas (String tipo, Double velcorte, Double diametro, Double rpm, Double velavance, Double avancepordiente, Double numdedientes, Double avanceporrev, Double volenmm3){
        this.tipo = tipo;
        this.velcorte = velcorte;
        this.diametro = diametro;
        this.rpm = rpm;
        this.velavance = velavance;
        this.avancepordiente = avancepordiente;
        this.numdedientes = numdedientes;
        this.avanceporrev = avanceporrev;
        this.volenmm3 = volenmm3;
    }

    public void setTipo(String title) {this.tipo = tipo;}
    public String getTipo(){return tipo;}

    public void setVelcorte (Double velcorte) {this.velcorte = velcorte;}
    public Double getVelcorte (){return velcorte;}

    public void setDiametro (Double diametro) {this.diametro = diametro;}
    public Double getDiametro (){return diametro;}

    public void setRpm (Double rpm) {this.rpm = rpm;}
    public Double getRpm (){return rpm;}

    public void setVelavance (Double velavance) {this.velavance = velavance;}
    public Double getVelavance (){return velavance;}

    public void setAvancepordiente (Double avancepordiente) {this.avancepordiente = avancepordiente;}
    public Double getAvancepordiente (){return avancepordiente;}

    public void setNumdedientes (Double numdedientes) {this.numdedientes = numdedientes;}
    public Double getNumdedientes (){return numdedientes;}

    public void setAvanceporrev (Double avanceporrev) {this.avanceporrev = avanceporrev;}
    public Double getAvanceporrev (){return avanceporrev;}

    public void setVolenmm3 (Double volenmm3) {this.volenmm3 = volenmm3;}
    public Double getVolenmm3 (){return volenmm3;}


    public Problemas(Parcel in){
        this.tipo = in.readString();
        this.velcorte = in.readDouble();
        this.diametro = in.readDouble();
        this.rpm = in.readDouble();
        this.velavance = in.readDouble();
        this.avancepordiente = in.readDouble();
        this.numdedientes = in.readDouble();
        this.avanceporrev = in.readDouble();
        this.volenmm3 = in.readDouble();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tipo);
        dest.writeDouble(this.velcorte);
        dest.writeDouble(this.diametro);
        dest.writeDouble(this.rpm);
        dest.writeDouble(this.velavance);
        dest.writeDouble(this.avancepordiente);
        dest.writeDouble(this.numdedientes);
        dest.writeDouble(this.avanceporrev);
        dest.writeDouble(this.volenmm3);

    }
}
