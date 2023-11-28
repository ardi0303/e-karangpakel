package com.ardi.projectuas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PDFActivity extends AppCompatActivity {

    String user, total;
    EditText etUser, etTot;
    Button btnPrint;
    Bitmap bitmap, scaleBitmap;
    int pageWidth = 1200;
    Date dateTime;
    DateFormat dateFormat;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    GoogleSignInAccount acct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfactivity);
        getSupportActionBar().setTitle("Cetak PDF");

        etUser = findViewById(R.id.etName);
        etTot = findViewById(R.id.etTotal);
        btnPrint = findViewById(R.id.btnPrint);

        Intent intent = getIntent();
        user = intent.getStringExtra("username");
        total = intent.getStringExtra("total");

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
        acct = GoogleSignIn.getLastSignedInAccount(this);

        if(acct == null) {
            etUser.setText(user);
            etTot.setText(total);
        }else{
            String email = acct.getEmail();
            etUser.setText(email);
            etTot.setText(total);
        }
        //cover header
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img);
        scaleBitmap = Bitmap.createScaledBitmap(bitmap, 1200, 518, false);

        //permission
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        createPDF();
    }

    private void createPDF() {
        btnPrint.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SimpleDateFormat")
            @Override
            public void onClick(View v) {

                dateTime = new Date();

                //get input
                if (etTot.getText().toString().length() == 0) {
                    Toast.makeText(PDFActivity.this, "Data tidak boleh kosong!", Toast.LENGTH_LONG).show();
                } else {

                    PdfDocument pdfDocument = new PdfDocument();
                    Paint paint = new Paint();
                    Paint titlePaint = new Paint();

                    PdfDocument.PageInfo pageInfo
                            = new PdfDocument.PageInfo.Builder(1200, 800, 1).create();
                    PdfDocument.Page page = pdfDocument.startPage(pageInfo);

                    Canvas canvas = page.getCanvas();
                    canvas.drawBitmap(scaleBitmap, 0, 0, paint);

                    paint.setColor(Color.WHITE);
                    paint.setTextSize(30f);
                    paint.setTextAlign(Paint.Align.RIGHT);
                    canvas.drawText("Berbagai macam es", 1160, 40, paint);
                    canvas.drawText("Pesan di : 082235593947", 1160, 80, paint);

                    paint.setTextAlign(Paint.Align.LEFT);
                    paint.setColor(Color.BLACK);
                    paint.setTextSize(35f);
                    canvas.drawText("Nama/Email Pemesan: " + etUser.getText(), 20, 590, paint);
                    canvas.drawText("Total Bayar: " + etTot.getText(), 20, 640, paint);

                    paint.setTextAlign(Paint.Align.RIGHT);
                    canvas.drawText("No. Pesanan: " + "232425", pageWidth - 20, 590, paint);

                    dateFormat = new SimpleDateFormat("dd/MM/yy");
                    canvas.drawText("Tanggal: " + dateFormat.format(dateTime), pageWidth - 20, 640, paint);

                    dateFormat = new SimpleDateFormat("HH:mm:ss");
                    canvas.drawText("Pukul: " + dateFormat.format(dateTime), pageWidth - 20, 690, paint);

                    pdfDocument.finishPage(page);

                    File file = new File(Environment.getExternalStorageDirectory(), "download/Pesanan.pdf");
                    try {
                        pdfDocument.writeTo(new FileOutputStream(file));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    pdfDocument.close();
                    Toast.makeText(PDFActivity.this, "PDF sudah dibuat", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}