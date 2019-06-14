package com.example.generartransaccioncombustible;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.generartransaccioncombustible.fragments.CreateTransactionFragment;
import com.example.generartransaccioncombustible.fragments.NavigationFragment;
import com.example.generartransaccioncombustible.listeners.MainListener;
import com.example.generartransaccioncombustible.util.CustomAnimation;

public class MainActivity extends AppCompatActivity implements MainListener {

    private static CreateTransactionFragment createTransactionFragment = CreateTransactionFragment.newInstance(null,null);

    //  Menu
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private TextView SessionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loadDrawer();
        prepararDrawer(navigationView);
        goToCreateTransaction();
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
                R.id.toolbar_main, false, CustomAnimation.LEFT_RIGHT);
    }


    private void loadDrawer(){
        //  Menu
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        SessionName = (TextView) header.findViewById(R.id.nav_header_textView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                closeKeyBoard();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                closeKeyBoard();
            }
        };

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }


    private void prepararDrawer(NavigationView navigationView)  {
        if(navigationView!=null) {
            navigationView.setNavigationItemSelectedListener(
                    new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(MenuItem menuItem) {
                            menuItem.setChecked(true);

                            String title = "";

                            switch (menuItem.getItemId()) {
                                case R.id.nav_item_one:
                                    //goToCreateTransaction();
                                    title = "Crear Transac.";
                                    break;
                                case R.id.nav_item_two:
                                    //goToListTransactions();
                                    break;
                                case R.id.nav_item_six:
                                    logOut();
                                    break;
                            }

                            getSupportActionBar().setTitle(title);

                            drawerLayout.closeDrawers();
                            return true;
                        }
                    });
        }

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
