package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Login_Activity extends AppCompatActivity {

    EditText username, password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        username = (EditText)findViewById(R.id.username_editText);
        password = (EditText)findViewById(R.id.password_editText);
    }

    public void login(View v) {
        if(username.getText().toString().equals("user") && password.getText().toString().equals("pass")) {
            Toast.makeText(getApplicationContext(), "Logging in...",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Login_Activity.this, Home_Activity.class));
        } else {
            Toast.makeText(getApplicationContext(), "Incorrect username or password.",Toast.LENGTH_SHORT).show();
            password.setText("");
            password.requestFocus();
        }
    }

    public void toWelcomeActivity(View v){
        startActivity(new Intent(Login_Activity.this, Welcome_Activity.class));
    }

    public void forgotPassword(View v)  {
        //future implementation
    }
}
