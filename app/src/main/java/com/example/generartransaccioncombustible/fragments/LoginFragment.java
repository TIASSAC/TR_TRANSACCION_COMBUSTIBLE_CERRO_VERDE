package com.example.generartransaccioncombustible.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.generartransaccioncombustible.LoginActivity;
import com.example.generartransaccioncombustible.R;
import com.example.generartransaccioncombustible.listeners.LoginListener;

import java.util.List;


public class LoginFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText etUser;
    private EditText etPassword;
    private Button btnLogin;

    private LoginListener mLoginListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mLoginListener = (LoginActivity) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
    }

    private void init() {
        etUser=(EditText)getView().findViewById(R.id.eteUsername);
        etPassword=(EditText)getView().findViewById(R.id.etePassword);
        btnLogin=(Button)getView().findViewById(R.id.btnLogin);

        loginclick loginc = new loginclick();
        btnLogin.setOnClickListener(loginc);
    }

    private class loginclick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            signup();
        }
    }

    private boolean validate() {

        boolean valid = true;

        String user_login = etUser.getText().toString().trim();
        String pass_login = etPassword.getText().toString().trim();

        if (user_login.isEmpty() || user_login.length() < 3)
        {
            etUser.setError("Ingrese al menos 3 caracteres");
            valid = false;
        } else {
            etUser.setError(null);
        }

        if (pass_login.isEmpty() ||  pass_login.length() < 4 || pass_login.length() > 10) {
            etPassword.setError("Ingrese entre 4 y 10 caracteres");
            valid = false;
        } else  {
            etPassword.setError(null);
        }

        return valid;
    }

    private void signup(){

        if (!validate()){
            onLoginFailed();
        }else {

            btnLogin.setEnabled(false);

            final ProgressDialog progressDialog = new ProgressDialog(getContext(), R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Autenticando...");
            progressDialog.show();

            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {

                            onLoginSuccess();
                            progressDialog.dismiss();
                        }
                    }, 3000);
        }
    }

    public void onLoginSuccess() {

        String user_login = etUser.getText().toString().trim();
        String pass_login = etPassword.getText().toString().trim();

        if(user_login.equals("TIASSAC") && pass_login.equals("123456")){
            mLoginListener.goToMain(user_login);
        }else{
            onLoginFailed();
        }

        btnLogin.setEnabled(true);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void onLoginFailed() {
        Toast.makeText(getContext(), "Datos incorrectos", Toast.LENGTH_LONG).show();
        btnLogin.setEnabled(true);
    }
}
