package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;


public class Register_Activity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);
    }

    public void register(View v){
        //will need to add account info to library of usernames/passwords
        startActivity(new Intent(Register_Activity.this, Home_Activity.class));
    }

    public void toLoginActivity(View v){
        startActivity(new Intent(Register_Activity.this, Login_Activity.class));
    }
}