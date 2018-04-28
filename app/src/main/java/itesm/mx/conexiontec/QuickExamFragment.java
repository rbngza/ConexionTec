package itesm.mx.conexiontec;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class QuickExamFragment extends Fragment {
    int tipo, subtipo;
    int preguntas = 1;
    int correctos = 0;
    int incorrectos = 0;
    Double velcorte, diametro, revpormin, avancepordiente, numdedientes, veldeavance, avanceporrev, volumenmm3;
    TextView tvPregunta;
    Switch swHint;


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
        tvPregunta = (TextView) v.findViewById(R.id.pregunta);
        swHint = (Switch) v.findViewById(R.id.switchrespuesta);
        final EditText etRespuesta = (EditText) v.findViewById(R.id.respuesta);
        updateQuestion();
        btnNextQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (preguntas < 10){
                    if (!((etRespuesta.getText().toString()).matches(""))) {
                        Double respuesta = Double.parseDouble(etRespuesta.getText().toString());
                        if (checaRespuesta(respuesta)){
                            Toast.makeText(getActivity(), "Wow la sacaste bien", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                    builder.setCancelable(true);
                    builder.setTitle("Felicidades! Terminaste el examen.");
                    builder.setMessage("Tu puntuacion fue de: " + String.valueOf(correctos) + " de 10 problemas correctos\n Deseas jugar de nuevo?");

                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            getFragmentManager().popBackStack();
                        }
                    });

                    builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            correctos = 0;
                            preguntas = 1;
                            updateQuestion();
                            etRespuesta.setText("");
                            dialogInterface.cancel();
                        }
                    });
                    builder.show();
                }
            }
        });


        return v;
    }

    public void updateQuestion(){
        Random r = new Random();
        int i1 = (r.nextInt(999) + 0);
        String pregunta;
        velcorte = 0 + (100 - 0) * r.nextDouble();
        diametro = 0 + (100 - 0) * r.nextDouble();
        avancepordiente = 0 + (100 - 0) * r.nextDouble();
        numdedientes = 0 + (100 - 0) * r.nextDouble();
        veldeavance = 0 + (100 - 0) * r.nextDouble();
        avanceporrev = 0 + (100 - 0) * r.nextDouble();
        volumenmm3 = 0 + (100 - 0) * r.nextDouble();
        pregunta = " ";
        if (i1 <= 333){
            tipo = 1;
            int i2 = (r.nextInt(1000) + 0);
            if (i2 <= 500) {
                subtipo = 1;
                pregunta = "Saca las RPM con las siguientes variables \n" +
                        "Velocidad de corte = " + Double.toString(velcorte) +"\n" +
                        "Diamtetro = " + Double.toString(diametro);
            } else if (i2 > 500 && i2 <=1000){
                subtipo = 2;
                pregunta = "Saca la Velocidad de corte con las siguientes variables \n" +
                        "RPM = " + Double.toString(revpormin) +"\n" +
                        "Diamtetro = " + Double.toString(diametro);
            }
        }else if (i1 > 333 && i1 <=666){
            tipo = 2;
            int i2 = (r.nextInt(1000) + 0);
            if (i2 <= 250) {
                subtipo = 1;
                pregunta = "Saca el avance por diente con las siguientes variables \n" +
                        "Velocidad de avance = " + Double.toString(veldeavance) +"\n" +
                        "RPM = " + Double.toString(revpormin) +
                        "# de dientes = " + Double.toString(numdedientes) ;
            } else if (i2 > 250 && i2 <=500){
                subtipo = 2;
                pregunta = "Saca el Numero de dientes con las siguientes variables \n" +
                        "Velocidad de avance = " + Double.toString(veldeavance) +"\n" +
                        "RPM = " + Double.toString(revpormin) + "\n" +
                        "Avance por diente = " + Double.toString(avancepordiente);
            } else if (i2 > 500 && i2 <= 1000){
                subtipo = 3;
                pregunta = "Saca la Velocidad de avance con las siguientes variables \n" +
                        "Numero de dientes = " + Double.toString(numdedientes) +"\n" +
                        "RPM = " + Double.toString(revpormin) + "\n" +
                        "Avance por diente = " + Double.toString(avancepordiente);
            }
        } else if (i1 > 666){
            tipo = 3;
            int i2 = (r.nextInt(1000) + 0);
            if (i2 <= 500) {
                subtipo = 1;
                pregunta = "Saca la Velocidad de avance con las siguientes variables \n" +
                        "Avance por RPM = " + Double.toString(avanceporrev) +"\n" +
                        "RPM = " + Double.toString(revpormin);
            } else if (i2 > 500 && i2 <=1000){
                subtipo = 2;
                pregunta = "Saca el Avance por RPM con las siguientes variables \n" +
                        "Velocidad de avance = " + Double.toString(veldeavance) +"\n" +
                        "RPM = " + Double.toString(revpormin);
            }
        }

        tvPregunta.setText(pregunta);
    }

    public boolean checaRespuesta(Double ans){
        boolean respuesta = false;
        if (tipo == 1){
            if (subtipo == 1){
                if(((Math.PI*diametro)/1000)==ans){
                    respuesta = true;
                }
            } else if (subtipo == 2){
                if(((velcorte*1000)/(Math.PI*diametro))==ans){
                    respuesta=true;
                }
            }
        } else if (tipo == 2){
            if (subtipo == 1){
                if ((veldeavance/(revpormin*numdedientes))==ans){
                    respuesta = true;
                }
            } else if (subtipo == 2){
                if ((veldeavance/(revpormin*avancepordiente))==ans){
                    respuesta = true;
                }
            } else if (subtipo == 3){

            }
        } else if (tipo == 3){
            if (subtipo == 1){

            } else if (subtipo == 2){

            }
        }

        return respuesta;
    }

}
