package com.joyce.listadetarefas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joyce.listadetarefas.R;
import com.joyce.listadetarefas.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.MyViewHolder> {

    private List<Tarefa> list = new ArrayList<>();

    public TarefaAdapter(List<Tarefa> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemDaLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_tarefa_adapter, parent, false);

        return new MyViewHolder(itemDaLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Tarefa tarefa = list.get(position);
        holder.txtTarefa.setText(tarefa.getDescricao());
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txtTarefa;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTarefa = itemView.findViewById(R.id.txtTarefa);
        }
    }
}
