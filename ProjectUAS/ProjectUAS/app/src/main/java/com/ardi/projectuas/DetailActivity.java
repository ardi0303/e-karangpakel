package com.ardi.projectuas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    TextView tv_nama, tv_deskripsi;
    ImageView iv_gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        getSupportActionBar().setTitle("Detail Produk");

        iv_gambar = (ImageView) findViewById(R.id.iv_detail);
        tv_nama = (TextView) findViewById(R.id.tv_nama);
        tv_deskripsi = (TextView) findViewById(R.id.tv_deskripsi);

        Intent intent = getIntent();
        String nama = intent.getStringExtra("nama");
        String deskripsi = intent.getStringExtra("deskripsi");
//        if(getIntent().hasExtra("gambar")){
////            Bitmap bmp = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("gambar"), 0, getIntent().getByteArrayExtra("gambar").length);
////            iv_gambar.setImageBitmap(bmp);
//        }
        String gambar = intent.getStringExtra("gambar");
        Glide.with(this).load(gambar).into(iv_gambar);
        tv_nama.setText(nama);
        tv_deskripsi.setText(deskripsi);
    }
}