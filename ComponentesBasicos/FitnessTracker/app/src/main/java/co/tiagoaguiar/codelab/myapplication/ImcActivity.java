package co.tiagoaguiar.codelab.myapplication;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ImcActivity extends AppCompatActivity {

    private EditText editHeight;
    private EditText editWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);

        editHeight = findViewById(R.id.edit_imc_height);
        editWeight = findViewById(R.id.edit_imc_weight);
        Button btn_calcular = findViewById(R.id.btn_calcular);

        //metodo para escutar evento de click no botao de calcular o imc
        btn_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //método criado abaixo do OnCreate para validar os inputs do user
                boolean result = validate();

                if (!validate()) {
                    //Se nao for validado o input, nessa linha um toast será exibido na tela
                    Toast.makeText(ImcActivity.this, R.string.fields_msg, Toast.LENGTH_LONG).show();
                    return;
                }

                String sWeight = editWeight.getText().toString();
                String sHeight = editHeight.getText().toString();

                int weight = Integer.parseInt(sWeight);
                int height = Integer.parseInt(sHeight);

                double imc = calculateImc(weight, height);

                int imcResponseId = imcResponse(imc);

                //aparecer dialog com aresposta para o user
                AlertDialog dialog = new AlertDialog.Builder(ImcActivity.this)
                        .setTitle(getString(R.string.imc_response, imc))
                        .setMessage(imcResponseId)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .create();
                dialog.show();

                //para fechar o teclado quando aparecer o dialog
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE); //IMM é o gerenciador do teclado
                imm.hideSoftInputFromWindow(editHeight.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(editWeight.getWindowToken(), 0);
            }
        });
    }

    //metodo que transforma o imc gerado em um texto. Ele retorna int pq o recurso de string que vai ser a resposta para o user é um int
    @StringRes //essa anotacao permite que o dev apenas devolva como resultado um int q vai ser arquivo de resourse
    private int imcResponse(double imc){
        if(imc < 15)
            return R.string.imc_severely_low_weight;
        else if(imc < 16)
            return R.string.imc_very_low_weight;
        else if(imc < 18.5)
            return R.string.imc_low_weight;
        else if(imc < 25)
            return R.string.normal;
        else if(imc < 30)
            return R.string.imc_high_weight;
        else if(imc < 35)
            return R.string.imc_so_high_weight;
        else if(imc < 40)
            return R.string.imc_severely_high_weight;
        else
           return R.string.imc_extreme_weight;
    }

    private double calculateImc(int weight, int height) {
        return weight / (((double) height/100) * ((double) height/100));
    }

    private boolean validate() {
        //Verifica se o primeiro numero digitado no peso e na altura é != 0 e se nao é vazio
        return (!editHeight.getText().toString().startsWith("0")
                && !editWeight.getText().toString().startsWith("0")
                && !editHeight.getText().toString().isEmpty()
                && !editWeight.getText().toString().isEmpty()
        );
    }
}