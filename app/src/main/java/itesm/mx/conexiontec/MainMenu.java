package itesm.mx.conexiontec;

import android.os.Parcel;
import android.os.Parcelable;

public class MainMenu implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public MainMenu createFromParcel(Parcel in) {
            return new MainMenu(in);
        }

        public MainMenu[] newArray(int size) {
            return new MainMenu[size];
        }
    };

    private String title;

    public MainMenu (String title){
        this.title = title;
    }

    public void setTitle(String title) {this.title=title;}
    public String getTitle(){return title;}

    public MainMenu(Parcel in){
        this.title = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
    }
}
