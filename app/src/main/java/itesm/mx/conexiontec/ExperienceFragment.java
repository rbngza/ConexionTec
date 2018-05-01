package itesm.mx.conexiontec;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ExperienceFragment extends Fragment{
    TextView tvInc, tvCor, tvPreg;
    EventOperations dao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_experience, container, false);
        dao = new EventOperations(getActivity());
        dao.open();
        tvInc = (TextView) v.findViewById(R.id.text_incorrectos);
        tvCor = (TextView) v.findViewById(R.id.text_correctos);
        tvPreg = (TextView) v.findViewById(R.id.text_preguntas);

        updateStats();

        return v;
    }

    public void updateStats(){
        int incorrectas = dao.getTotalIncorrectos();
        tvInc.setText(String.valueOf(incorrectas));
        int correctas = dao.getTotalCorrectos();
        tvCor.setText(String.valueOf(correctas));
        int preguntas = dao.getTotalPreguntas();
        tvPreg.setText(String.valueOf(preguntas));
    }
}
