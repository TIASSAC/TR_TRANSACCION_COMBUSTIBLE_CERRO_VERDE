package com.example.generartransaccioncombustible;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.generartransaccioncombustible.fragments.LoginFragment;
import com.example.generartransaccioncombustible.fragments.NavigationFragment;
import com.example.generartransaccioncombustible.listeners.LoginListener;
import com.example.generartransaccioncombustible.util.CustomAnimation;

public class LoginActivity extends AppCompatActivity implements LoginListener {

    private LoginFragment loginFragment = LoginFragment.newInstance(null,null);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        goToLogin();
    }
//
    @Override
    public void goToMain(String User) {

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("USUARIO", User);
        startActivity(intent);
        this.finish();
    }


    private void goToLogin(){
        NavigationFragment.addFragment(null, loginFragment, "loginFragment", this,
                R.id.linear_container, false, CustomAnimation.LEFT_RIGHT);
    }
}
