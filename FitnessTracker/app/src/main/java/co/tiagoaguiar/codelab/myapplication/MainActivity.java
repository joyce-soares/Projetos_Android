package co.tiagoaguiar.codelab.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	/*Quando criamos um projeto android ele já cria essa estrutura base da MainActivity(atividade que vai gerenciar um layout em específico) */

	private View btn_imc; //referencia para o linear layout que é o botao imc. Está como View pq todo LinearLayout é filho de uma View
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); //essa linha atribui o layout que essa activity vai gerenciar

		btn_imc = findViewById(R.id.btn_imc);

		//evento de clique para ir pra outra tela
		btn_imc.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				/*Aqui vamos abrir uma nova tela. Para fazer isso é necessário instanciar um Intent (é a intençao de abrir um novo recurso)
				* É preciso passar como parametro qual é o contexto atual que aplicacao está rodando e qual é a que ela vai abrir*/
				Intent intent = new Intent(MainActivity.this, ImcActivity.class);

				//Depois de criar a intencao tem que dizer o que esta intencao vai fazer
				startActivity(intent); //dar start na tela
			}
		});
	}
}