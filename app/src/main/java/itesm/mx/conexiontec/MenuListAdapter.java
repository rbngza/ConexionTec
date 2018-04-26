package itesm.mx.conexiontec;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuListAdapter extends ArrayAdapter<MainMenu> {

    public MenuListAdapter(Context context, ArrayList<MainMenu> mainmenu) {
        super(context,0,mainmenu);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        MainMenu mainmenu = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row, parent, false);
        }


        TextView tvTitulo = (TextView) convertView.findViewById(R.id.text_title);

        tvTitulo.setText(mainmenu.getTitle());

        return convertView;
    }

}
