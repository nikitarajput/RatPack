package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Welcome_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void toLoginActivity(View view){
        startActivity(new Intent(Welcome_Activity.this, Login_Activity.class));
    }

    public void toRegisterActivity(View view){
        startActivity(new Intent(Welcome_Activity.this, Register_Activity.class));
    }
}
