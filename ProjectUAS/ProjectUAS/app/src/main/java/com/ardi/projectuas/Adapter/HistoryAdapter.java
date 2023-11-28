package com.ardi.projectuas.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.ardi.projectuas.BayarActivity;
import com.ardi.projectuas.CheckoutActivity;
import com.ardi.projectuas.DetailActivity;
import com.ardi.projectuas.HistoryActivity;
import com.ardi.projectuas.MainActivity;
import com.ardi.projectuas.Model.History;
import com.ardi.projectuas.Model.Produk;
import com.ardi.projectuas.R;
import com.ardi.projectuas.UpdateCartActivity;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.Holder>{
    public interface ItemClickListener {
        void onClick(View view, int position);
    }
    Context context;
    int singledata;
    ArrayList<History> historyArrayList;
    Activity activity;

    public HistoryAdapter(ArrayList<History> historyArrayList){ this.historyArrayList = historyArrayList; }
    private ItemClickListener clickListener;
    public double tot=0;

    //generate constructor

    public HistoryAdapter(Activity activity, ArrayList<History> historyArrayList) {
        this.activity = activity;
        this.historyArrayList = historyArrayList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_singledata, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        final History history = historyArrayList.get(position);
        holder.tglBeli.setText(history.getTglBeli());
        holder.total.setText(history.getTotal());
        holder.status.setText("Status : "+history.getStatus());
        holder.resi.setText("No. Resi : "+history.getResi());
        String status = history.getStatus();
        if(status.equals("Belum Bayar")){
            holder.bayar.setVisibility(View.VISIBLE);
            holder.bayar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity.getApplication(), BayarActivity.class);
                    intent.putExtra("total2", historyArrayList.get(position).getTotal());
                    activity.startActivity(intent);
                }
            });
        }else{
            holder.bayar.setVisibility(View.GONE);
        }
        holder.history = history;
    }

    @Override
    public int getItemCount() {
        return (historyArrayList != null) ? historyArrayList.size() : 0;
    }
    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }
    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tglBeli, total, status, resi;
        Button bayar;
        History history;
        public Holder(View itemView) {
            super(itemView);
            tglBeli = (TextView) itemView.findViewById(R.id.tvTglBeli);
            total = (TextView) itemView.findViewById(R.id.tvTotal);
            status = (TextView) itemView.findViewById(R.id.tvStatus);
            resi = (TextView) itemView.findViewById(R.id.tvResi);
            bayar = (Button) itemView.findViewById(R.id.btBayar);
        }
        @Override
        public void onClick(View view) {
            if(clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }
}
