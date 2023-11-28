package com.ardi.projectuas.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.ardi.projectuas.DetailActivity;
import com.ardi.projectuas.MainActivity;
import com.ardi.projectuas.Model.Produk;
import com.ardi.projectuas.R;
import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProdukAdapter extends RecyclerView.Adapter<ProdukAdapter.Holder>{
    public interface ItemClickListener {
        void onClick(View view, int position);
    }
    Context context;
    int singledata;
    ArrayList<Produk> produkArrayList;
    Activity activity;

    public ProdukAdapter(ArrayList<Produk> produkArrayList){ this.produkArrayList = produkArrayList; }
    private ItemClickListener clickListener;
    public double tot=0;

    //generate constructor

    public ProdukAdapter(Activity activity, ArrayList<Produk> produkArrayList) {
        this.activity = activity;
        this.produkArrayList = produkArrayList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singledata, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        final Produk produk = produkArrayList.get(position);
        holder.nama.setText(produk.getNmbrg());
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        int harga = produk.getHarga();
        holder.harga.setText("Rp. "+decimalFormat.format(harga));
        holder.stok.setText("Stok : "+(String.valueOf(produk.getStok())));
        Glide.with(activity).load(produk.getGambar()).into(holder.gambar);
        holder.nama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity.getApplication(), DetailActivity.class);
                intent.putExtra("gambar", produkArrayList.get(position).getGambar());
                intent.putExtra("nama", produkArrayList.get(position).getNmbrg());
                intent.putExtra("deskripsi", produkArrayList.get(position).getDeskripsi());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (produkArrayList != null) ? produkArrayList.size() : 0;
    }
    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }
    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView gambar;
        TextView nama, harga, kode, stok;
        public Holder(View itemView) {
            super(itemView);
            gambar = (ImageView) itemView.findViewById(R.id.displayGambar);
            nama = (TextView) itemView.findViewById(R.id.displayNama);
            harga = (TextView) itemView.findViewById(R.id.displayHarga);
            stok = (TextView) itemView.findViewById(R.id.tvStok);
            harga.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            if(clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }
    public double getTot()
    {
        return  tot;
    }
    public void setTot(double tot)
    {
        this.tot=tot;
    }
}
