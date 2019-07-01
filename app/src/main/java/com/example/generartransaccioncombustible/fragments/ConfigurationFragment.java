package com.example.generartransaccioncombustible.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.generartransaccioncombustible.MainActivity;
import com.example.generartransaccioncombustible.R;
import com.example.generartransaccioncombustible.adapter.StationAdapter;
import com.example.generartransaccioncombustible.entities.StationORMEntity;
import com.example.generartransaccioncombustible.listeners.MainListener;

import java.util.ArrayList;
import java.util.Calendar;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class ConfigurationFragment extends Fragment {

    private Spinner spinnerStation;
    private CheckBox checkTurno,checkContometro;
    private MainListener mListener;
    private ImageView imgVHoraInicio, imgVHoraFin;
    private TextView txtHoraInicio,txtHoraFin;
    private EditText etxtTimeHInicio,etxtTimeHFin,etxtContoInicio,etxtContoFin;
    private LinearLayout layoutContoInicio, layoutContoFin;
    private Button btnGrabarConfiguracion;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private int GL_ID_STATION = 0;
    private String GL_STATION_NAME = "";



    /**TimePicker*/
    private static final String CERO = "0";
    private static final String DOS_PUNTOS = ":";
    private Calendar c;
    private int hora,minuto ;


    private String mParam1;
    private String mParam2;

    public ConfigurationFragment() {
        // Required empty public constructor
    }

    public static ConfigurationFragment newInstance(String param1, String param2) {
        ConfigurationFragment fragment = new ConfigurationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_configuration, container, false);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (MainActivity) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
    }

    private void init(){
        spinnerStation = (Spinner)getView().findViewById(R.id.spinnerStation);
        checkTurno = (CheckBox)getView().findViewById(R.id.checkTurno);

        imgVHoraFin = (ImageView)getView().findViewById(R.id.imgVHoraFin);
        imgVHoraInicio =  (ImageView)getView().findViewById(R.id.imgVHoraInicio);
        txtHoraFin = (TextView)getView().findViewById(R.id.txtHoraFin);
        txtHoraInicio = (TextView)getView().findViewById(R.id.txtHoraInicio);
        etxtTimeHFin = (EditText)getView().findViewById(R.id.etxtTimeHFin);
        etxtTimeHInicio = (EditText)getView().findViewById(R.id.etxtTimeHInicio);

        checkContometro = (CheckBox)getView().findViewById(R.id.checkContometro);
        etxtContoInicio = (EditText)getView().findViewById(R.id.etxtContoInicio);
        etxtContoFin = (EditText)getView().findViewById(R.id.etxtContoFin);

        layoutContoFin = (LinearLayout) getView().findViewById(R.id.layoutContoFin);
        layoutContoInicio = (LinearLayout)getView().findViewById(R.id.layoutContoInicio);
        llenadoSpinner();

        btnGrabarConfiguracion = (Button)getView().findViewById(R.id.btnGrabarConfiguracion);

        grabarConfiguracion grabarconfiguracion = new grabarConfiguracion();
        btnGrabarConfiguracion.setOnClickListener(grabarconfiguracion);

        clicCheckTurno cliccheckTurno = new clicCheckTurno();
        checkTurno.setOnClickListener(cliccheckTurno);

        clicCheckContometro cliccheckContometro = new clicCheckContometro();
        checkContometro.setOnClickListener(cliccheckContometro);

        timePicker1 timepicker1 = new timePicker1();
        etxtTimeHInicio.setOnClickListener(timepicker1);

        timePicker2 timepicker2 = new timePicker2();
        etxtTimeHFin.setOnClickListener(timepicker2);


    }

    public void llenadoSpinner(){
        ArrayList<StationORMEntity> listaestacion = new ArrayList<>();
        StationORMEntity stationORMEntity = new StationORMEntity();

        stationORMEntity.setIdStation(1);
        stationORMEntity.setStationName("SELECCIONE");
        listaestacion.add(stationORMEntity);

        stationORMEntity = new StationORMEntity();
        stationORMEntity.setIdStation(2);
        stationORMEntity.setStationName("PLANTA");
        listaestacion.add(stationORMEntity);

        stationORMEntity = new StationORMEntity();
        stationORMEntity.setIdStation(3);
        stationORMEntity.setStationName("CARACHUGO");
        listaestacion.add(stationORMEntity);

        final StationAdapter stationAdapter = new StationAdapter(getContext(),listaestacion);
        spinnerStation.setAdapter(stationAdapter);

        spinnerEstacion hs = new spinnerEstacion();
        spinnerStation.setOnItemSelectedListener(hs);
    }

    private class timePicker1 implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    //Formateo el hora obtenido: antepone el 0 si son menores de 10
                    String horaFormateada =  (hourOfDay < 10)? String.valueOf(CERO + hourOfDay) : String.valueOf(hourOfDay);
                    //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                    String minutoFormateado = (minute < 10)? String.valueOf(CERO + minute):String.valueOf(minute);
                    //Obtengo el valor a.m. o p.m., dependiendo de la selección del usuario
                    String AM_PM;
                    if(hourOfDay < 12) {
                        AM_PM = "a.m.";
                    } else {
                        AM_PM = "p.m.";
                    }
                    //Muestro la hora con el formato deseado
                    etxtTimeHInicio.setText(horaFormateada + DOS_PUNTOS + minutoFormateado + " " + AM_PM);
                }
            }, hora, minuto, false);
            timePickerDialog.show();
        }
    }

    private class timePicker2 implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    //Formateo el hora obtenido: antepone el 0 si son menores de 10
                    String horaFormateada =  (hourOfDay < 10)? String.valueOf(CERO + hourOfDay) : String.valueOf(hourOfDay);
                    //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                    String minutoFormateado = (minute < 10)? String.valueOf(CERO + minute):String.valueOf(minute);
                    //Obtengo el valor a.m. o p.m., dependiendo de la selección del usuario
                    String AM_PM;
                    if(hourOfDay < 12) {
                        AM_PM = "a.m.";
                    } else {
                        AM_PM = "p.m.";
                    }
                    //Muestro la hora con el formato deseado
                    etxtTimeHFin.setText(horaFormateada + DOS_PUNTOS + minutoFormateado + " " + AM_PM);
                }
            }, hora, minuto, false);
            timePickerDialog.show();
        }
    }

    private class clicCheckTurno implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(checkTurno.isChecked() == true){
                imgVHoraFin.setEnabled(true);
                imgVHoraInicio.setEnabled(true);
                txtHoraFin.setTextColor(Color.parseColor("#000000"));
                txtHoraInicio.setTextColor(Color.parseColor("#000000"));
                etxtTimeHFin.setEnabled(true);
                etxtTimeHInicio.setEnabled(true);
                checkTurno.setText("Automático");

            }else if (checkTurno.isChecked() == false){
                imgVHoraFin.setEnabled(false);
                imgVHoraInicio.setEnabled(false);
                txtHoraFin.setTextColor(Color.parseColor("#8fa3ad"));
                txtHoraInicio.setTextColor(Color.parseColor("#8fa3ad"));
                etxtTimeHFin.setEnabled(false);
                etxtTimeHInicio.setEnabled(false);
                checkTurno.setText("Manual");
                etxtTimeHInicio.setText("");
                etxtTimeHFin.setText("");
            }
        }
    }

    private class clicCheckContometro implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            LinearLayout.LayoutParams loparams = (LinearLayout.LayoutParams) layoutContoInicio.getLayoutParams();

            if(checkContometro.isChecked() == true){
                loparams.height = 0;
                loparams.weight= 1f;
                loparams.width=MATCH_PARENT;
                checkContometro.setText("Habilitado");
                layoutContoInicio.setLayoutParams(loparams);
                layoutContoFin.setLayoutParams(loparams);
            }else if(checkContometro.isChecked() == false){
                loparams.height = 0;
                loparams.weight=0;
                loparams.width=MATCH_PARENT;
                checkContometro.setText("Deshabilitado");
                layoutContoInicio.setLayoutParams(loparams);
                layoutContoFin.setLayoutParams(loparams);
                etxtContoFin.setText("");
                etxtContoInicio.setText("");
            }
        }
    }

    private class spinnerEstacion implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            StationORMEntity stationORMEntity = (StationORMEntity) spinnerStation.getAdapter().getItem(position);

            GL_ID_STATION = stationORMEntity.getIdStation();
            GL_STATION_NAME = stationORMEntity.getStationName();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class grabarConfiguracion implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            String estacion = GL_STATION_NAME;
            String turno = (checkTurno.isChecked() ? "Automático" : "Manual");
            String horaInicio = etxtTimeHInicio.getText().toString();
            String horaFin = etxtTimeHFin.getText().toString();

            String contoIni = etxtContoInicio.getText().toString();
            String contoFin = etxtContoFin.getText().toString();


            String contometro  = (checkContometro.isChecked() ? "Habilitado" : "Deshabilitado");
            if(contometro.equals("Habilitado")){
                contometro = contometro + "\n"+"Contometro Inicio: "+contoIni;
            }
            if(turno.equals("Automático")){
                turno = turno + "\nHora Inicio: "+horaInicio+" - "+"Hora Fin: "+horaFin;
            }


            if (estacion == "SELECCIONE") {
                Toast.makeText(getActivity(),"Seleccione una Estación",Toast.LENGTH_SHORT).show();
            } else {

                Log.v("horainicio",""+horaInicio);


                if(checkTurno.isChecked()==true){
                    Toast.makeText(getActivity(),"Tiene que llenar los campos hora inicio - hora fin",Toast.LENGTH_SHORT).show();
                }else{

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setCancelable(false);
                    builder.setTitle("Se guardara la siguiente configuracion:");
                    builder.setMessage("-Estación: "+estacion+"\n" + "-Turno: "+turno+ "\n" + "-Contometro: "+contometro);
                    builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mListener.goToInit();
                        }
                    });

                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();
            }
         }
        }
    }
}
