package com.joyce.recyclerview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.joyce.recyclerview.R;
import com.joyce.recyclerview.model.Filme;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Filme> filmeList;

    public Adapter(List<Filme> lista) {
        this.filmeList = lista;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_lista, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder( Adapter.MyViewHolder holder, int position) {

        Filme filme = filmeList.get(position);
        holder.titulo.setText(filme.getNomeFilme());
        holder.ano.setText(filme.getAno());
        holder.genero.setText(filme.getGenero());
    }

    @Override
    public int getItemCount() {
        return filmeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView titulo, ano, genero;

        public MyViewHolder(View itemView) {
            super(itemView);

        titulo = itemView.findViewById(R.id.textTitulo);
        ano = itemView.findViewById(R.id.textAno);
        genero = itemView.findViewById(R.id.textGenero);

        }
    }

}
