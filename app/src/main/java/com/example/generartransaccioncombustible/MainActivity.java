package com.example.generartransaccioncombustible;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.generartransaccioncombustible.fragments.ConfigurationFragment;
import com.example.generartransaccioncombustible.fragments.CreateTransactionFragment;
import com.example.generartransaccioncombustible.fragments.InitFragment;
import com.example.generartransaccioncombustible.fragments.NavigationFragment;
import com.example.generartransaccioncombustible.listeners.MainListener;
import com.example.generartransaccioncombustible.util.CustomAnimation;

public class MainActivity extends AppCompatActivity implements MainListener {

    private static CreateTransactionFragment createTransactionFragment = CreateTransactionFragment.newInstance(null,null);

    private static InitFragment initFragment = InitFragment.newInstance(null,null);

    private static ConfigurationFragment configurationFragment = ConfigurationFragment.newInstance(null,null);

    public int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("flag","flag"+flag);
                if(flag == 3 || flag ==4){
                    goToInit();
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(false);
                    builder.setMessage("¿Desea Cerrar Sesión?");
                    builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            logOut();
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
        });

        //goToCreateTransaction();
        goToInit();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("¿Desea salir de la aplicación?");
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
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


    @Override
    public void logOut(){
        Toast.makeText(this,"Sesión terminada. ", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void goToCreateTransaction() {
        NavigationFragment.addFragment(null, createTransactionFragment, "CreateTransactionFragment", this,
                R.id.toolbar_main, false, CustomAnimation.IN_OUT);
        flag = 3;
        setTitle(R.string.createTransaction);
    }

    @Override
    public void goToInit() {

        NavigationFragment.addFragment(null, initFragment, "InitFragment", this,
                R.id.toolbar_main, false, CustomAnimation.IN_OUT);
        flag=2;
        setTitle(R.string.menuPrincipal);
    }

    @Override
    public void goToConfiguration(){
        NavigationFragment.addFragment(null, configurationFragment, "configurationFragment", this,
                R.id.toolbar_main, false, CustomAnimation.IN_OUT);
        flag=4;
        setTitle(R.string.configuration);
    }


    private void showMessage(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Información")
                .setMessage(message)
                //.setIcon(android.R.drawable.ic_dialog_info)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void closeKeyBoard(){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }
}
