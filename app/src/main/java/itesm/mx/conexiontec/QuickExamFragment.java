package itesm.mx.conexiontec;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class QuickExamFragment extends Fragment {

    int preguntas = 1;
    int correctos = 0;

    public QuickExamFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_quick_exam, container, false);
        Button btnNextQ = (Button) v.findViewById(R.id.nextquestion);
        TextView tvPregunta = (TextView) v.findViewById(R.id.pregunta);
        Switch swHint = (Switch) v.findViewById(R.id.switchrespuesta);
        final EditText etRespuesta = (EditText) v.findViewById(R.id.respuesta);

        btnNextQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (preguntas < 10){
                    if (!((etRespuesta.getText().toString()).matches(""))) {

                    }
                }
            }
        });


        return v;
    }

}
