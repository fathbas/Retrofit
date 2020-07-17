package com.fatihb.retrofir.adaptor;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fatihb.retrofir.R;
import com.fatihb.retrofir.model.Cripto;

import java.util.ArrayList;

public class RecyleViewAdapter extends RecyclerView.Adapter<RecyleViewAdapter.RowHolder> {

    private ArrayList<Cripto> criptoList;
    private String[] color={"#FF0000","#e3a624","#00dacd","#6c6680","#afc9c9","#f7bf86","#084596","#6c6680"};

    public RecyleViewAdapter(ArrayList<Cripto> criptoList) {
        this.criptoList = criptoList;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_layout,parent,false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        holder.bind(criptoList.get(position),color,position);

    }

    @Override
    public int getItemCount() {
        return criptoList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {
        TextView textName;
        TextView textPrice;
        public RowHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(Cripto cripto,String[] colors,int position){
            itemView.setBackgroundColor(Color.parseColor(colors[position%8]));
            textName = itemView.findViewById(R.id.textName);
            textPrice = itemView.findViewById(R.id.textPrice);
            textName.setText(cripto.currency);
            textPrice.setText(cripto.price);
        }
    }
}
