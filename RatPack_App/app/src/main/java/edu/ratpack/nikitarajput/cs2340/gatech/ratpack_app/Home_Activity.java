package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;


public class Home_Activity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);
        TextView greeting = (TextView)findViewById(R.id.login_status_textView);
        if(Login_Activity.currentUser.getIsAdmin()){
            greeting.setText(greeting.getText()+"\nYou Are An Admin");
        } else{
            greeting.setText(greeting.getText()+"\nYou Are A User");
        }
    }

    public void onLogout(View v) {
        startActivity(new Intent(Home_Activity.this,Welcome_Activity.class));
    }
}