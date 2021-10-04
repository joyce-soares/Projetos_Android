package co.tiagoaguiar.codelab.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import co.tiagoaguiar.codelab.myapplication.Register;

public class ListCalcAdapter extends RecyclerView.Adapter<ListCalcAdapter.MyViewHolder> {

    private List<Register> list;

    public ListCalcAdapter(List<Register> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       // View itemLista = LayoutInflater.from(parent.getContext())
                //.inflate(android.R.layout.simple_list_item_1, parent, false);
        return new MyViewHolder(View.inflate(parent.getContext(), android.R.layout.simple_list_item_1, parent));

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Register register = list.get(position);
        holder.bind(register);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

        }

        public void bind(Register register) {
            ((TextView) itemView).setText(register.response + " ");
        }
    }
}
