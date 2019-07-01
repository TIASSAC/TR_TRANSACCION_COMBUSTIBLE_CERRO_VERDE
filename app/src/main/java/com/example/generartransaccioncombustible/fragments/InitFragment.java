package com.example.generartransaccioncombustible.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.generartransaccioncombustible.MainActivity;
import com.example.generartransaccioncombustible.R;
import com.example.generartransaccioncombustible.listeners.LoginListener;
import com.example.generartransaccioncombustible.listeners.MainListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class InitFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    /******************************************Declarar Variables**********************************/
    private ImageView btnCerrarSesion,btnCrearTransa, btnConfiguration, btnStartTurn;
    private String mParam1,mParam2;
    private MainListener mListener;
    private TextView txtViewCurrentDate , txtViewCurrentHour, txtStartTurn;

    /**Para obtener la fecha actual*/

    private String currentDate;
    private SimpleDateFormat formatCurrentDate;
    private Date currentDated;

    /**Para Obtener la Hora actual-Contador*/

    /**Inicio turno - Fin Turno*/
    private int flag;

    /**********************************************************************************************/


    public InitFragment() {
        // Required empty public constructor
    }


    public static InitFragment newInstance(String param1, String param2) {
        InitFragment fragment = new InitFragment();
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
        return inflater.inflate(R.layout.fragment_init, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    /**Carga inicial*/
    private void init(){
        /**vinculamos los parametros con los ids-layout*/
        btnCrearTransa = (ImageView)getView().findViewById(R.id.btnCrearTransa);
        btnCerrarSesion = (ImageView)getView().findViewById(R.id.btnCerrarSesion);
        btnConfiguration = (ImageView)getView().findViewById(R.id.btnConfiguration);
        btnStartTurn = (ImageView)getView().findViewById(R.id.btnStartTurn);

        txtViewCurrentDate = (TextView)getView().findViewById(R.id.txtViewCurrentDate);
        txtViewCurrentHour = (TextView)getView().findViewById(R.id.txtViewCurrentHour);
        txtStartTurn = (TextView)getView().findViewById(R.id.txtStartTurn);

        flag = 1;
        fechaActual();

        /**************************************EVENTO CLIC*****************************************/
        /**boton crear transaccion*/
        crearTransa creartransa = new crearTransa();
        btnCrearTransa.setOnClickListener(creartransa);

        /**boton cerrar sesion*/
        cerrarSesion cerrarsesion = new cerrarSesion();
        btnCerrarSesion.setOnClickListener(cerrarsesion);

        /**boton ir a configuracion*/
        goConfiguration goconfiguration =  new goConfiguration();
        btnConfiguration.setOnClickListener(goconfiguration);

        /**boton para iniciar turno - fin turno*/
        startTurn startturn =  new startTurn();
        btnStartTurn.setOnClickListener(startturn);

        /******************************************************************************************/
    }

    /**************************************EVENTO CLIC*****************************************/
    private class crearTransa implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            mListener.goToCreateTransaction();
        }
    }

    private class cerrarSesion implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setCancelable(false);
            builder.setMessage("¿Desea Cerrar Sesión?");
            builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mListener.logOut();
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

    private class goConfiguration implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            mListener.goToConfiguration();
        }
    }

    private class startTurn implements  View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(flag == 1){
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setCancelable(false);
                builder.setTitle("INICIO DE TURNO");
                builder.setMessage("¿Esta seguro de querer iniciar el turno?" + "Tener en cuenta que se generara un nuevo turno");
                builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        txtStartTurn.setText(R.string.endTurn);
                        flag = flag +1;
                        btnStartTurn.setImageResource(R.drawable.finturno);
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();


            }else if(flag == 2){

                AlertDialog.Builder builder =  new AlertDialog.Builder(getActivity());
                builder.setCancelable(false);
                builder.setTitle("FIN DE TURNO");
                builder.setMessage("¿Esta seguro de querer finalizar el turno?" + "Tener en cuenta que se cerrara su sesion");
                builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        flag = flag - 1;
                        txtStartTurn.setText(R.string.initTurn);
                        btnStartTurn.setImageResource(R.drawable.inicioturno);
                        //dialog.cancel();
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();



            }

        }
    }
    /**********************************************************************************************/


    /******************************************METODOS*********************************************/
    /**Metodo para obtener la fecha actual*/
    private void fechaActual(){

        formatCurrentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        currentDated = new Date();

        currentDate = formatCurrentDate.format(currentDated);

        txtViewCurrentDate.setText(currentDate);
    }

    /**Metodo para obtener la hora actual*/


    /**********************************************************************************************/
}
