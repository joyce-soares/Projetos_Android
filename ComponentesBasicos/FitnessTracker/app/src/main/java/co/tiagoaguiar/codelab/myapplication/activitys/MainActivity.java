package co.tiagoaguiar.codelab.myapplication.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import co.tiagoaguiar.codelab.myapplication.model.MainItem;
import co.tiagoaguiar.codelab.myapplication.interfaces.OnItemClickListener;
import co.tiagoaguiar.codelab.myapplication.R;

public class MainActivity extends AppCompatActivity {

    /*Quando criamos um projeto android ele já cria essa estrutura base da MainActivity(atividade que vai gerenciar um layout em específico) */

    //private View btn_imc; //referencia para o linear layout que é o botao imc. Está como View pq todo LinearLayout é filho de uma View

    private RecyclerView rvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //essa linha atribui o layout que essa activity vai gerenciar

        List<MainItem> mainItems = new ArrayList<>();
        mainItems.add(new MainItem(1, R.drawable.ic_baseline_wb_sunny_24, R.string.label_imc, Color.GREEN));
        mainItems.add(new MainItem(2, R.drawable.ic_baseline_star_24, R.string.label_tmb, Color.BLACK));

        rvMain = findViewById(R.id.rv_main);

        rvMain.setLayoutManager(new GridLayoutManager(this, 2)); //define o comportamento de exibiçao do layout, podendo ser: Mosaico, Grid, Linear(vertical e horizontal)
        MainAdapter adapter = new MainAdapter(mainItems);  //cria um adapter(faz com que a recycler se adapte a qq codigo)

        adapter.setListener(id -> {

            switch (id){
                case 1:
                    /*Aqui vamos abrir uma nova tela. Para fazer isso é necessário instanciar um Intent (é a intençao de abrir um novo recurso)
                     * É preciso passar como parametro qual é o contexto atual que aplicacao está rodando e qual é a que ela vai abrir*/
                    //Intent intent = new Intent(MainActivity.this, ImcActivity.class);

                    //Depois de criar a intencao tem que dizer o que esta intencao vai fazer
                    startActivity(new Intent(MainActivity.this, ImcActivity.class));  //dar start na tela
                    break;
                case 2:
                    startActivity(new Intent(MainActivity.this, TmbActivity.class));
                    break;
            }

        });
        rvMain.setAdapter(adapter);
    }

    private class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

        private List<MainItem> mainItems; // = new ArrayList<>();
        private OnItemClickListener listener;

        public MainAdapter(List<MainItem> mainItems) {
            this.mainItems = mainItems;
        }

        public void setListener(OnItemClickListener listener) {
            this.listener = listener;
        }

        @NonNull
        @NotNull
        @Override
        public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MainViewHolder(getLayoutInflater().inflate(R.layout.main_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull @NotNull MainViewHolder holder, int position) {

            MainItem mainItemCurrent = mainItems.get(position);
            holder.bind(mainItemCurrent);
        }

        @Override
        public int getItemCount() {
            return mainItems.size();
        }

        private class MainViewHolder extends RecyclerView.ViewHolder {
            //essa classe tem as referencias dos componentes criados para o item da recycler. Ele é a view deste item
            public MainViewHolder(@NonNull View itemView) {
                super(itemView);
            }

            public void bind(MainItem mainItem) {

                TextView text = itemView.findViewById(R.id.item_txt_name);
                ImageView img = itemView.findViewById(R.id.item_img);
                LinearLayout btnImc = itemView.findViewById(R.id.btn_imc);

                //evento de clique para ir pra outra tela
                btnImc.setOnClickListener(v -> {

                    listener.onClick(mainItem.getId());
                    Intent intent = new Intent(MainActivity.this, ImcActivity.class);
                    startActivity(intent);
                });

                text.setText(mainItem.getName());
                img.setImageResource(mainItem.getDrawableId());
                btnImc.setBackgroundColor(mainItem.getColor());
            }

        }
    }
}