package com.ardi.projectuas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private Button btnLogin,btnRegis;
    private EditText etName, etUser, etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        etName = (EditText) findViewById(R.id.inputNama);
        etUser = (EditText) findViewById(R.id.inputUser);
        etPass = (EditText) findViewById(R.id.inputPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegis = (Button) findViewById(R.id.btnDaftar);
        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRegister();
                Intent regis = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(regis);
                finish();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(login);
                finish();
            }
        });
    }

    private void userRegister(){
        final String name = etName.getText().toString();
        final String username = etUser.getText().toString();
        final String password = etPass.getText().toString();
        if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
            massage("Harap isi semua data");
        } else {
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    "https://ppb13790.000webhostapp.com/crud_uas/register.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            massage(response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    massage(error.getMessage());
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("name", name);
                    params.put("username", username);
                    params.put("password", password);
                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
            queue.add(stringRequest);
        }
    }

    public void massage(String massage){
        Toast.makeText(this, massage, Toast.LENGTH_SHORT).show();
    }
}