package com.joyce.cardview.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.joyce.cardview.R;
import com.joyce.cardview.model.Postagens;

import java.util.List;

public class PostagemAdapter extends RecyclerView.Adapter<PostagemAdapter.MyViewHolder> {

    private List<Postagens> postagens;

    public PostagemAdapter(List<Postagens> p) {
        this.postagens = p;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.layout_adapter, parent, false
        );
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Postagens pos = postagens.get(position);
        holder.nomeUsuario.setText(pos.getNome());
        holder.postagem.setText(pos.getPostagem());
        holder.imagem.setImageResource(pos.getImagem());
    }

    @Override
    public int getItemCount() {
        return postagens.size();
    }

    public class  MyViewHolder extends RecyclerView.ViewHolder{

        TextView nomeUsuario, postagem;
        ImageView imagem;


        @SuppressLint("CutPasteId")
        public MyViewHolder(View itemView) {
            super(itemView);
            nomeUsuario = itemView.findViewById(R.id.textNomeUsuario);
            postagem = itemView.findViewById(R.id.textPublicacao);
            imagem = itemView.findViewById(R.id.imagePublicacao);
        }
    }
}
