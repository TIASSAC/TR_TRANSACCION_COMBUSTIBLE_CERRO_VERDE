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

import com.example.generartransaccioncombustible.MainActivity;
import com.example.generartransaccioncombustible.R;
import com.example.generartransaccioncombustible.listeners.LoginListener;
import com.example.generartransaccioncombustible.listeners.MainListener;


public class InitFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ImageView btnCerrarSesion,btnCrearTransa, btnConfiguration;
    private MainListener mMainListener;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private MainListener mListener;

    public InitFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
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

        initB();
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



    private void initB(){
        btnCrearTransa = (ImageView)getView().findViewById(R.id.btnCrearTransa);
        btnCerrarSesion = (ImageView)getView().findViewById(R.id.btnCerrarSesion);
        btnConfiguration = (ImageView)getView().findViewById(R.id.btnConfiguration);


        /**boton crear transaccion*/
        crearTransa creartransa = new crearTransa();
        btnCrearTransa.setOnClickListener(creartransa);

        /**boton cerrar sesion*/
        cerrarSesion cerrarsesion = new cerrarSesion();
        btnCerrarSesion.setOnClickListener(cerrarsesion);

        /**boton ir a configuracion*/
        goConfiguration goconfiguration =  new goConfiguration();
        btnConfiguration.setOnClickListener(goconfiguration);
    }

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
}
