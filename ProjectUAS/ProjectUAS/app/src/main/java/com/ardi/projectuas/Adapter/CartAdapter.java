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
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.ardi.projectuas.DetailActivity;
import com.ardi.projectuas.MainActivity;
import com.ardi.projectuas.Model.Cart;
import com.ardi.projectuas.Model.Produk;
import com.ardi.projectuas.R;
import com.ardi.projectuas.UpdateCartActivity;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.Holder>{
    public interface ItemClickListener {
        void onClick(View view, int position);
    }
    Context context;
    int singledata;
    ArrayList<Cart> cartArrayList;
    Activity activity;

    public CartAdapter(ArrayList<Cart> cartArrayList){ this.cartArrayList = cartArrayList; }
    private ItemClickListener clickListener;
    public double tot=0;

    //generate constructor

    public CartAdapter(Activity activity, ArrayList<Cart> cartArrayList) {
        this.activity = activity;
        this.cartArrayList = cartArrayList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_singledata, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        final Cart cart = cartArrayList.get(position);
        holder.namaBrg.setText(cart.getNmBrg());
        holder.jmlBeli.setText("Jumlah beli : "+String.valueOf(cart.getJmlBeli()));
        holder.ivEdit.setOnClickListener(view -> {
            String jml_beli = Integer.toString(cart.getJmlBeli());
            Intent intent = new Intent(activity, UpdateCartActivity.class);
            intent.putExtra("nm_brg", cart.getNmBrg());
            intent.putExtra("jml_beli", jml_beli);
            activity.startActivity(intent);
        });
        holder.ivDelete.setOnClickListener(view -> {
            holder.hapusData();
            cartArrayList.remove(position);
        });
        holder.cart = cart;
    }

    @Override
    public int getItemCount() {
        return (cartArrayList != null) ? cartArrayList.size() : 0;
    }
    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }
    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView namaBrg, jmlBeli;
        ImageView ivEdit, ivDelete;
        Cart cart;
        public Holder(View itemView) {
            super(itemView);
            namaBrg = (TextView) itemView.findViewById(R.id.tvNamaBrg);
            jmlBeli = (TextView) itemView.findViewById(R.id.tvJmlBeli);
            ivEdit = (ImageView) itemView.findViewById(R.id.ivEdit);
            ivDelete = (ImageView) itemView.findViewById(R.id.ivDelete);
        }
        @Override
        public void onClick(View view) {
            if(clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
        public void hapusData() {
            AndroidNetworking.post("https://ppb13790.000webhostapp.com/crud_uas/delete_cart.php")
                    .addBodyParameter("kdCart", cart.getKdCart())
                    .setTag("test")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (response.getString("status").equals("Data Berhasil Dihapus")) {
                                    Toast.makeText(activity, "Data Berhasil Dihapus", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(activity, "Data Gagal Dihapus", Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException exception) {
                                Toast.makeText(activity, "Data Gagal Dihapus" + exception, Toast.LENGTH_LONG).show();
                            }

                        }

                        @Override
                        public void onError(ANError error) {
                            Toast.makeText(activity, "Data Gagal Dihapus" + error, Toast.LENGTH_LONG).show();
                        }

                    });
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
