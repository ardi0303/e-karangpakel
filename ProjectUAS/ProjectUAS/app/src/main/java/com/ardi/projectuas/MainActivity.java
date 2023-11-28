package com.ardi.projectuas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.ardi.projectuas.Adapter.ProdukAdapter;
import com.ardi.projectuas.Model.Produk;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements ProdukAdapter.ItemClickListener {
    private final static  String STATUS = "Data Barang";
    public ArrayList<Produk> dataProduk;
    public RecyclerView recyclerView;
    public  RecyclerView.Adapter adapter;
    public  RecyclerView.LayoutManager layoutManager;
    private ProgressDialog progressDialog;
    private GridLayoutManager gridLayoutManager;
    public double tot = 0;
    public String nama_brg;
    FloatingActionButton fbCart;
    ProdukAdapter produkAdapter;
    Button btBayar;
    SharedPreferences preferences;
    private static final String sharedpref="MYDATA";
    private static final String username = "user";

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    GoogleSignInAccount acct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("E-Karangpakel");
        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        dataProduk = new ArrayList<>();
        AndroidNetworking.initialize(getApplicationContext());
        loadData();

//        layoutManager = new LinearLayoutManager(read_data.this, RecyclerView.VERTICAL, false);
//        recyclerView.setLayoutManager(layoutManager);
        gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        produkAdapter = new ProdukAdapter(this, dataProduk);
        recyclerView.setAdapter(produkAdapter);
        produkAdapter.setClickListener(this);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        acct = GoogleSignIn.getLastSignedInAccount(this);


        fbCart = (FloatingActionButton) findViewById(R.id.fbAdd);
        fbCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        btBayar = (Button) findViewById(R.id.btBayar);
        btBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String total = Integer.toString((int) tot);
                Intent intent = new Intent(MainActivity.this, CheckoutActivity.class);
                intent.putExtra("total", total);
                startActivity(intent);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.callcenter) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:082235593947"));
            startActivity(intent);
        } else if (id == R.id.smscenter) {
            Uri uri = Uri.parse("smsto:082235593947");
            Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
            intent.putExtra("sms_body", "Saya mau pesan Es!");
            startActivity(intent);
        } else if (id == R.id.lokasi) {
            Uri uri2 = Uri.parse("geo:0,0?q=Karangpakel, Kabupaten Klaten, Jawa Tengah");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri2);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        } else if (id == R.id.updateuser) {
            Intent intent =  new Intent(MainActivity.this, UpdateUserActivity.class);
            startActivity(intent);
        }else if (id == R.id.history) {
            Intent intent =  new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(intent);
        } else if (id == R.id.logout) {
            gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    finish();
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            });
        } else if (id == R.id.action_exit) {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void postTotals() {
        TextView txtTot=(TextView) findViewById(R.id.tvTotal);
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        txtTot.setText("Rp. "+decimalFormat.format(tot));
    }

    public void onClick(View view, int position) {
        final Produk produk = dataProduk.get(position);
        switch (view.getId()) {
            case R.id.displayHarga:
                tot = tot + produk.getHarga();
                postTotals();
                nama_brg = produk.getNmbrg();
                if(acct == null) {
                    addcart(nama_brg);
                }else{
                    addCartGoogle(nama_brg);
                }

                return;
            default:
                break;
        }
    }

    private void loadData() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");
        progressDialog.show();

        AndroidNetworking.post("https://ppb13790.000webhostapp.com/crud_uas/view_data.php")
                .addBodyParameter("action", "tampil_data")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        try{
                            JSONArray jsonArray = response.getJSONArray("Databarang");
                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Produk item = new Produk(
                                        jsonObject.getString("kd_brg"),
                                        jsonObject.getString("nm_brg"),
                                        jsonObject.getInt("harga"),
                                        jsonObject.getString("deskripsi"),
                                        jsonObject.getInt("stok"),
                                        jsonObject.getString("gambar")
                                );
                                dataProduk.add(item);
                                progressDialog.dismiss();
                                String string = String.valueOf(jsonArray.length());
                                Log.d("eror", string);
                            }
                        }catch (JSONException e){
                            Toast.makeText(getApplicationContext(), "Data gagal Ditampilkan" + e, Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                            progressDialog.dismiss();
                        }
                        produkAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onError(ANError error) {
                        Toast.makeText(getApplicationContext(), "Data gagal DITAMPILKANN" + error, Toast.LENGTH_LONG).show();
                    }
                });
    }
    private void addcart(String nm_brg){
        preferences = getSharedPreferences(sharedpref, MODE_PRIVATE);
        String user = preferences.getString(username, null);
        final String nama_brg = nm_brg;
        final int jml_beli = 1;
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://ppb13790.000webhostapp.com/crud_uas/cart.php",
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
                params.put("username", user);
                params.put("nm_brg", nama_brg);
                params.put("jml_beli", String.valueOf(jml_beli));
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(stringRequest);
    }


    private void addCartGoogle(String nm_brg) {
        String email = acct.getEmail();
        final String nama_brg = nm_brg;
        final int jml_beli = 1;
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://ppb13790.000webhostapp.com/crud_uas/cart.php",
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
                params.put("username", email);
                params.put("nm_brg", nama_brg);
                params.put("jml_beli", String.valueOf(jml_beli));
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(stringRequest);
    }

    public void massage(String massage){
        Toast.makeText(this, massage, Toast.LENGTH_SHORT).show();
    }
}