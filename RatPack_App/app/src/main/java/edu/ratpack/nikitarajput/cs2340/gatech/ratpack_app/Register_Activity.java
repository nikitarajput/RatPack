package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;

        import java.util.HashMap;
        import java.util.Map;


public class Register_Activity extends AppCompatActivity {

    EditText username, password, confirmPassword;
    Map<String, String> accountInformation = new HashMap<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);
        username = (EditText)findViewById(R.id.register_username_editText);
        password = (EditText)findViewById(R.id.register_password_editText);
        confirmPassword = (EditText)findViewById(R.id.confirm_password_editText);
    }

    public void register(View v){
        if(!password.getText().toString().equals(confirmPassword.getText().toString())) {
            // need to say that passwords don't match and try again
        } else  if (accountInformation.containsKey(username.getText().toString())) {
            // need to say that account exists for that username
        } else {
            accountInformation.put(username.getText().toString(), password.getText().toString());
            // need to say success and do the redirect
        }
        startActivity(new Intent(Register_Activity.this, Home_Activity.class));
    }

    public void toWelcomeActivity(View v){
        startActivity(new Intent(Register_Activity.this, Welcome_Activity.class));
    }
}