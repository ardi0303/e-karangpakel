package com.ardi.projectuas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class UpdateCartActivity extends AppCompatActivity {
    String nmBrg, jmlBeli;
    TextView tvNmBrg;
    EditText etJmlBeli;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_cart);

        getSupportActionBar().setTitle("Update Keranjang");

        Intent intent = getIntent();
        nmBrg = intent.getStringExtra("nm_brg");
        jmlBeli = intent.getStringExtra("jml_beli");

        tvNmBrg = (TextView) findViewById(R.id.tvNamaBrg);
        etJmlBeli = (EditText)findViewById(R.id.etJmlBeli);

        tvNmBrg.setText(nmBrg);
        etJmlBeli.setText(jmlBeli);
    }

    public void EditBarang(View view) {
        AndroidNetworking.post("https://ppb13790.000webhostapp.com/crud_uas/update_cart.php")
                .addBodyParameter("nmBrg", tvNmBrg.getText().toString())
                .addBodyParameter("jmlBeli", etJmlBeli.getText().toString())
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getString("status").equals("Data berhasil Diupdate")){
                                Toast.makeText(getApplicationContext(), "Data berhasil Diupdate", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(UpdateCartActivity.this, CartActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Data gagal Disimpan 1", Toast.LENGTH_LONG).show();
                            }
                        }catch (JSONException exception){
                            Toast.makeText(getApplicationContext(), "Data gagal Disimpan 2" + exception, Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        Toast.makeText(getApplicationContext(), "Data gagal Disimpan 3" + error, Toast.LENGTH_LONG).show();
                    }
                });
    }
}