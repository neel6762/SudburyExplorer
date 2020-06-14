package com.example.sudburyexplorer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {

    Button login_btn;
    Button reg_btn;
    EditText password,user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //        Buttons
        login_btn = findViewById(R.id.login_button);
        reg_btn = findViewById(R.id.register_button);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dashboard();
            }});
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }});


        // EditText
        user = findViewById(R.id.user_en);
        password = findViewById(R.id.et_password);

    }

    private void dashboard() {

        StringRequest request= new StringRequest(Request.Method.POST, "https://svnfua.000webhostapp.com/login.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.contains("1"))
                        {
                            startActivity(new Intent(getApplicationContext(), Home_Page.class));
                        }
                        else if (enrollment.length()==0)
                        {
                            enrollment.requestFocus();
                            enrollment.setError("FIELD CAN NOT BE EMPTY");
                        }
                        else if (password.length()==0)
                        {
                            password.requestFocus();
                            password.setError("FIELD Password");
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Wrong Username or password",Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params= new HashMap<>();
                params.put("enrollment",enrollment.getText().toString());
                params.put("password",password.getText().toString());
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);


    }
    private void register() {
        Intent intent = new Intent(this, register.class);
        startActivity(intent);
    }
}
