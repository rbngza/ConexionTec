package itesm.mx.conexiontec;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
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
    String type = "QuickExam";
    Double velcorte, diametro, revpormin, avancepordiente, numdedientes, veldeavance, avanceporrev, volumenmm3;
    TextView tvPregunta;
    Switch swHint;
    EventOperations dao;
    boolean switch2 = false;
    TextView tvHint;
    EditText etRespuesta;
    String ayuda = "AYUUUDAAA";
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
        tvHint = (TextView) v.findViewById(R.id.text_help);
        etRespuesta = (EditText) v.findViewById(R.id.respuesta);
        updateQuestion();
        swHint.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("WrongConstant")
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switch2){
                    tvHint.setVisibility(0);
                    tvHint.setText(" ");
                    switch2 = false;
                } else {
                    tvHint.setVisibility(1);
                    tvHint.setText(ayuda);
                    switch2 = true;
                }

            }
        });
        btnNextQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (preguntas < 10){
                    if (!((etRespuesta.getText().toString()).matches(""))) {
                        View view = getActivity().getCurrentFocus();
                        if (view != null) {
                            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                        Double respuesta = Double.parseDouble(etRespuesta.getText().toString());
                        preguntas++;
                        if (checaRespuesta(respuesta)){
                            correctos++;
                            Toast.makeText(getActivity(), "Bien", Toast.LENGTH_SHORT).show();
                        } else {
                            incorrectos++;
                            Toast.makeText(getActivity(), "Mal.", Toast.LENGTH_SHORT).show();
                        }
                        updateQuestion();
                    }
                } else {
                    if (!((etRespuesta.getText().toString()).matches(""))) {
                        Double respuesta = Double.parseDouble(etRespuesta.getText().toString());
                        if (checaRespuesta(respuesta)){
                            correctos++;
                            Toast.makeText(getActivity(), "Bien", Toast.LENGTH_SHORT).show();
                        } else {
                            incorrectos++;
                            Toast.makeText(getActivity(), "Mal.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    dao = new EventOperations(getActivity());
                    dao.open();
                    View view = getActivity().getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                    Historial historial = new Historial(type, preguntas, correctos, incorrectos);
                    dao.addExam(historial);

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
        velcorte = round((0 + (100 - 0) * r.nextDouble()), 0);
        diametro = round((0 + (100 - 0) * r.nextDouble()), 0);
        avancepordiente = round((0 + (100 - 0) * r.nextDouble()), 0);
        numdedientes = round((0 + (100 - 0) * r.nextDouble()), 0);
        veldeavance = round((0 + (100 - 0) * r.nextDouble()), 0);
        avanceporrev = round((0 + (100 - 0) * r.nextDouble()), 0);
        volumenmm3 = round((0 + (100 - 0) * r.nextDouble()), 0);
        revpormin = round((0 + (100 - 0) * r.nextDouble()), 0);
        pregunta = " ";
        if (i1 <= 333){
            tipo = 1;
            int i2 = (r.nextInt(1000) + 0);
            if (i2 <= 500) {
                subtipo = 1;
                pregunta = "Saca las RPM con las siguientes variables \n" +
                        "Velocidad de corte = " + Double.toString(velcorte) +"\n" +
                        "Diamtetro = " + Double.toString(diametro);
                ayuda = "((velcorte*1000)/(PI*diametro)";
            } else if (i2 > 500 && i2 <=1000){
                subtipo = 2;
                pregunta = "Saca la Velocidad de corte con las siguientes variables \n" +
                        "RPM = " + Double.toString(revpormin) +"\n" +
                        "Diamtetro = " + Double.toString(diametro);
                ayuda = "((PI*diametro)/(velcorte*1000))";
            }
        }else if (i1 > 333 && i1 <=666){
            tipo = 2;
            int i2 = (r.nextInt(1000) + 0);
            if (i2 <= 250) {
                subtipo = 1;
                pregunta = "Saca el avance por diente con las siguientes variables \n" +
                        "Velocidad de avance = " + Double.toString(veldeavance) +"\n" +
                        "RPM = " + Double.toString(revpormin) + "\n" +
                        "# de dientes = " + Double.toString(numdedientes) ;
                ayuda = "(veldeavance/(revpormin*numdedientes))";
            } else if (i2 > 250 && i2 <=500){
                subtipo = 2;
                pregunta = "Saca el Numero de dientes con las siguientes variables \n" +
                        "Velocidad de avance = " + Double.toString(veldeavance) +"\n" +
                        "RPM = " + Double.toString(revpormin) + "\n" +
                        "Avance por diente = " + Double.toString(avancepordiente);
                ayuda = "(veldeavance/(revpormin*avancepordiente))";
            } else if (i2 > 500 && i2 <= 1000){
                subtipo = 3;
                pregunta = "Saca la Velocidad de avance con las siguientes variables \n" +
                        "Numero de dientes = " + Double.toString(numdedientes) +"\n" +
                        "RPM = " + Double.toString(revpormin) + "\n" +
                        "Avance por diente = " + Double.toString(avancepordiente);
                ayuda = "numdedientes*revpormin*avancepordiente";
            }
        } else if (i1 > 666){
            tipo = 3;
            int i2 = (r.nextInt(1000) + 0);
            if (i2 <= 500) {
                subtipo = 1;
                pregunta = "Saca la Velocidad de avance con las siguientes variables \n" +
                        "Avance por RPM = " + Double.toString(avanceporrev) +"\n" +
                        "RPM = " + Double.toString(revpormin);
                ayuda = "avanceporrev * revpormin";
            } else if (i2 > 500 && i2 <=1000){
                subtipo = 2;
                pregunta = "Saca el Avance por RPM con las siguientes variables \n" +
                        "Velocidad de avance = " + Double.toString(veldeavance) +"\n" +
                        "RPM = " + Double.toString(revpormin);
                ayuda = "veldeavance/revpormin";
            }
        }
        swHint.setChecked(false);
        etRespuesta.setText("");
        tvPregunta.setText(pregunta);
    }

    public boolean checaRespuesta(Double ans){
        boolean respuesta = false;
        if (tipo == 1){
            if (subtipo == 1){
                if(round((velcorte*1000)/(round(Math.PI, 4)*diametro),4)==ans){
                    respuesta = true;
                }
            } else if (subtipo == 2){
                if(round(((round(Math.PI, 4)*diametro)/(revpormin*1000)), 4)==ans){
                    respuesta=true;
                }
            }
        } else if (tipo == 2){
            if (subtipo == 1){
                if (round((veldeavance/(revpormin*numdedientes)), 4)==ans){
                    respuesta = true;
                }
            } else if (subtipo == 2){
                if (round((veldeavance/(revpormin*avancepordiente)), 4)==ans){
                    respuesta = true;
                }
            } else if (subtipo == 3){
                if (round((numdedientes*revpormin*avancepordiente), 4)==ans){
                    respuesta = true;
                }
            }
        } else if (tipo == 3){
            if (subtipo == 1){
                if (round((avanceporrev * revpormin), 4)==ans){
                    respuesta = true;
                }
            } else if (subtipo == 2){
                if (round(veldeavance/revpormin, 4)==ans){
                    respuesta = true;
                }
            }
        }


        return respuesta;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}
