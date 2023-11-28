package com.ardi.projectuas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class UpdateUserActivity extends AppCompatActivity {
    private Button btnUpdate;
    SharedPreferences preferences;
    private static final String sharedpref="MYDATA";
    private static final String username = "user";
    private static final String password = "pass";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        getSupportActionBar().hide();
        final EditText etUser, etPw;
        etUser = (EditText) findViewById(R.id.updateuser) ;
        etPw = (EditText) findViewById(R.id.updatepw);
        preferences = getSharedPreferences(sharedpref, MODE_PRIVATE);
        String oldUser = preferences.getString(username, null);
        String oldPass = preferences.getString(password, null);
        if(oldUser!=null||oldPass!=null){
            etUser.setText(oldUser);
            etPw.setText(oldPass);
        }

        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUser.getText().toString().trim();
                String password = etPw.getText().toString().trim();
                if(username.isEmpty() || password.isEmpty()){
                    massage("Harap isi semua data");
                }else{
                    StringRequest stringRequest = new StringRequest(Request.Method.POST,
                            "https://ppb13790.000webhostapp.com/crud_uas/update_user.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    SharedPreferences prefer =
                                            getSharedPreferences("MYDATA",MODE_PRIVATE);
                                    String newUser = etUser.getText().toString();
                                    String newPass = etPw.getText().toString();
                                    SharedPreferences.Editor edit = prefer.edit();
                                    edit.putString("user", newUser);
                                    edit.putString("pass",newPass);
                                    edit.commit();
                                    Intent iLogin = new Intent(UpdateUserActivity.this,
                                            LoginActivity.class);
                                    startActivity(iLogin);
                                    massage(response);
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            massage(error.getMessage());
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> updateParams = new HashMap<>();
                            updateParams.put("username", username);
                            updateParams.put("password", password);
                            updateParams.put("oldUsername", oldUser);
                            return updateParams;
                        }
                    };
                    RequestQueue queue = Volley.newRequestQueue(UpdateUserActivity.this);
                    queue.add(stringRequest);
                }
            }
        });
    }

    public void massage(String massage){
        Toast.makeText(this, massage, Toast.LENGTH_SHORT).show();
    }
}