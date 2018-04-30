package itesm.mx.conexiontec;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DefinicionFragment extends Fragment {
    public static String ARG_POSITION = "position";
    int position;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.definicion_fragment, container, false);
        Bundle args = getArguments();
        if (args!=null){
            position = args.getInt(ARG_POSITION);
        }
        loadDefinicion(v, position);
        return v;
    }

    public void loadDefinicion(View v, int pos){
        TextView tvDef = (TextView) v.findViewById(R.id.text_definicion);
        tvDef.setText(Glosario.Definicion[position]);

    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
