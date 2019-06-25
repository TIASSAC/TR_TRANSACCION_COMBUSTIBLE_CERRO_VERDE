package com.example.generartransaccioncombustible.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.generartransaccioncombustible.R;


public class VerificarInternet extends AsyncTask<Void,Void, Boolean> {

    private ProgressDialog progressDialog;
    private Context context;
    private Verificar verificar;

    public interface Verificar{
        void ConexionExitosa();
        void ConexionFallida();

    }

    public VerificarInternet(Context context,Verificar verificar){
        this.context = context;
        this.verificar = verificar;
    }

    @Override
    protected void onPreExecute() {
        // preparamos el cuadro de dialogo
        progressDialog = new ProgressDialog(context, R.style.AppTheme_Dark_Dialog);
        progressDialog.setMessage("Verificando conexión a servidor");
        progressDialog.setCancelable(false);
        progressDialog.show();

        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        return new NetworkUtil().connectionNetwork(Const.HTTP_SITE, context);
    }

    @Override
    protected void onPostExecute(Boolean response) {
        super.onPostExecute(response);

        progressDialog.dismiss();

        if (response){
            verificar.ConexionExitosa();
        }else{
            verificar.ConexionFallida();
        }
    }
}
