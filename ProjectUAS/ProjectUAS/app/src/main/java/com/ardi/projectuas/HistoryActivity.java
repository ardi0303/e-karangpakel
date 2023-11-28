package com.ardi.projectuas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.ardi.projectuas.Adapter.CartAdapter;
import com.ardi.projectuas.Adapter.HistoryAdapter;
import com.ardi.projectuas.Model.Cart;
import com.ardi.projectuas.Model.History;
import com.ardi.projectuas.Model.Produk;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HistoryActivity extends AppCompatActivity{

    private final static  String STATUS = "Data Barang";
    public ArrayList<History> historyModel;
    public RecyclerView recyclerView;
    public  RecyclerView.LayoutManager layoutManager;
    private ProgressDialog progressDialog;
    SharedPreferences preferences;
    private static final String sharedpref="MYDATA";
    private static final String username = "user";
    HistoryAdapter historyAdapter;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    GoogleSignInAccount acct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        getSupportActionBar().setTitle("History Pemesanan");

        recyclerView = findViewById(R.id.rvHistory);
        recyclerView.setHasFixedSize(true);
        historyModel = new ArrayList<>();
        AndroidNetworking.initialize(getApplicationContext());

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        acct = GoogleSignIn.getLastSignedInAccount(this);

        if(acct == null){
            loadData();
        }else{
            loadDataGoogle();
        }


        layoutManager = new LinearLayoutManager(HistoryActivity.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        historyAdapter = new HistoryAdapter(this, historyModel);
        recyclerView.setAdapter(historyAdapter);
    }

    private void loadDataGoogle() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Loading");
        progressDialog.show();

        String email = acct.getEmail();
        AndroidNetworking.post("https://ppb13790.000webhostapp.com/crud_uas/view_history.php")
                .addBodyParameter("action", "tampil_data")
                .setPriority(Priority.MEDIUM)
                .addBodyParameter("username", email)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        try{
                            JSONArray jsonArray = response.getJSONArray("Databarang");
                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                History item = new History(
                                        jsonObject.getInt("id_penjualan"),
                                        jsonObject.getString("tgl_beli"),
                                        jsonObject.getString("username"),
                                        jsonObject.getString("total"),
                                        jsonObject.getString("Status"),
                                        jsonObject.getString("resi")
                                );
                                historyModel.add(item);
                                progressDialog.dismiss();
                                String string = String.valueOf(jsonArray.length());
                                Log.d("eror", string);
                            }
                        }catch (JSONException e){
                            Toast.makeText(getApplicationContext(), "Data gagal Ditampilkan" + e, Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                            progressDialog.dismiss();
                        }
                        historyAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onError(ANError error) {
                        Toast.makeText(getApplicationContext(), "Data gagal DITAMPILKANN" + error, Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void loadData() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Loading");
        progressDialog.show();

        preferences = getSharedPreferences(sharedpref, MODE_PRIVATE);
        String user = preferences.getString(username, null);

        AndroidNetworking.post("https://ppb13790.000webhostapp.com/crud_uas/view_history.php")
                .addBodyParameter("action", "tampil_data")
                .setPriority(Priority.MEDIUM)
                .addBodyParameter("username", user)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        try{
                            JSONArray jsonArray = response.getJSONArray("Databarang");
                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                History item = new History(
                                        jsonObject.getInt("id_penjualan"),
                                        jsonObject.getString("tgl_beli"),
                                        jsonObject.getString("username"),
                                        jsonObject.getString("total"),
                                        jsonObject.getString("Status"),
                                        jsonObject.getString("resi")
                                );
                                historyModel.add(item);
                                progressDialog.dismiss();
                                String string = String.valueOf(jsonArray.length());
                                Log.d("eror", string);
                            }
                        }catch (JSONException e){
                            Toast.makeText(getApplicationContext(), "Data gagal Ditampilkan" + e, Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                            progressDialog.dismiss();
                        }
                        historyAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onError(ANError error) {
                        Toast.makeText(getApplicationContext(), "Data gagal DITAMPILKANN" + error, Toast.LENGTH_LONG).show();
                    }
                });
    }
}