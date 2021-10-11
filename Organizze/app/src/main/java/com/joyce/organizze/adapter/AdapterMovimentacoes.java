package com.joyce.organizze.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joyce.organizze.R;
import com.joyce.organizze.model.Movimentacoes;

import java.util.List;

public class AdapterMovimentacoes extends RecyclerView.Adapter<AdapterMovimentacoes.MyViewHolder> {

    List<Movimentacoes> movimentacoes;
    Context context;

    public AdapterMovimentacoes(List<Movimentacoes> movimentacoes, Context context) {
        this.movimentacoes = movimentacoes;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Movimentacoes movimentacao = movimentacoes.get(position);

        holder.titulo.setText(movimentacao.getDescricao());
        holder.valor.setText(String.valueOf(movimentacao.getValor()));
        holder.categoria.setText(movimentacao.getCategoria());

        holder.valor.setTextColor(context.getResources().getColor(R.color.colorPrimaryReceita));

        if (movimentacao.getTipo() == "d" || movimentacao.getTipo().equals("d")) {
            holder.valor.setTextColor(context.getResources().getColor(R.color.colorPrimaryDespesa));
            holder.valor.setText("-" + movimentacao.getValor());
        }

    }

    @Override
    public int getItemCount() {
        return movimentacoes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titulo, valor, categoria;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.textAdapterTitulo);
            valor = itemView.findViewById(R.id.textAdapterValor);
            categoria = itemView.findViewById(R.id.textAdapterCategoria);
        }
    }
}
