package co.tiagoaguiar.codelab.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	/*Quando criamos um projeto android ele já cria essa estrutura base da MainActivity(atividade que vai gerenciar um layout em específico) */

	//private View btn_imc; //referencia para o linear layout que é o botao imc. Está como View pq todo LinearLayout é filho de uma View

	private RecyclerView rvMain;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); //essa linha atribui o layout que essa activity vai gerenciar

		//btn_imc = findViewById(R.id.btn_imc);

		List<MainItem> mainItems = new ArrayList<>();
		mainItems.add(new MainItem(1, R.drawable.ic_baseline_wb_sunny_24,R.string.label_imc, R.color.colorAccent));
		mainItems.add(new MainItem(2, R.drawable.ic_baseline_star_24,R.string.label_tmb, R.color.colorPrimary));

		rvMain = findViewById(R.id.rv_main);

		rvMain.setLayoutManager(new LinearLayoutManager(this)); //define o comportamento de exibiçao do layout, podendo ser: Mosaico, Grid, Linear(vertical e horizontal)
		MainAdapter adapter = new MainAdapter(mainItems);  //cria um adapter(faz com que a recycler se adapte a qq codigo)
		rvMain.setAdapter(adapter);


		//evento de clique para ir pra outra tela
		//btn_imc.setOnClickListener(new View.OnClickListener() {
			//@Override
			//public void onClick(View v) {

				/*Aqui vamos abrir uma nova tela. Para fazer isso é necessário instanciar um Intent (é a intençao de abrir um novo recurso)
				* É preciso passar como parametro qual é o contexto atual que aplicacao está rodando e qual é a que ela vai abrir*/
				//Intent intent = new Intent(MainActivity.this, ImcActivity.class);

				//Depois de criar a intencao tem que dizer o que esta intencao vai fazer
				//startActivity(intent);  //dar start na tela

			//}
		//});
	}

	private class MainAdapter extends RecyclerView.Adapter<MainViewHolder>{

		List<MainItem> mainItems = new ArrayList<>();

		public MainAdapter(List<MainItem> mainItems) {
			this.mainItems = mainItems;
		}

		@NonNull
		@Override
		public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			return new MainViewHolder(getLayoutInflater().inflate(R.layout.main_item, parent, false));
		}

		@Override
		public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {

			MainItem mainItemCurrent = mainItems.get(position);
			holder.bind(mainItemCurrent);
		}

		@Override
		public int getItemCount() {
			return mainItems.size();
		}
	}

	private class MainViewHolder extends RecyclerView.ViewHolder{
		//essa classe tem as referencias dos componentes criados para o item da recycler. Ele é a view deste item
		public MainViewHolder(@NonNull View itemView) {
			super(itemView);
		}

		public void bind(MainItem mainItem){

			TextView text = itemView.findViewById(R.id.item_txt_name);
			ImageView img = itemView.findViewById(R.id.item_img);
			LinearLayout container = (LinearLayout) itemView.findViewById(R.id.btn_main);

			text.setText(mainItem.getName());
			img.setImageResource(mainItem.getDrawableId());
			container.setBackgroundColor(mainItem.getColor());
		}

	}
}