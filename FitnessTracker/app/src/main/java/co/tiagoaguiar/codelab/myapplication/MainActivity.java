package co.tiagoaguiar.codelab.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	/*Quando criamos um projeto android ele já cria essa estrutura base da MainActivity(atividade que vai gerenciar um layout em específico) */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); //essa linha atribui o layout que essa activity vai gerenciar
	}
}