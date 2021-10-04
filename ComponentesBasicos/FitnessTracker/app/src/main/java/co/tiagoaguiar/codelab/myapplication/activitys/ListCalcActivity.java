package co.tiagoaguiar.codelab.myapplication.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.List;

import co.tiagoaguiar.codelab.myapplication.R;
import co.tiagoaguiar.codelab.myapplication.Register;
import co.tiagoaguiar.codelab.myapplication.adapter.ListCalcAdapter;
import co.tiagoaguiar.codelab.myapplication.sqlhelper.SqlHelper;

public class ListCalcActivity extends AppCompatActivity {

    RecyclerView recycler_list_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_calc);

        recycler_list_type = findViewById(R.id.recycler_view_type);

        Bundle extras = getIntent().getExtras();

        if(extras != null){
            String type = extras.getString("type");
            List<Register> list = SqlHelper.getInstance(this).getRegisterBy(type);

            ListCalcAdapter listCalcAdapter = new ListCalcAdapter(list);

            recycler_list_type.setLayoutManager(new LinearLayoutManager(this));
            //recycler_list_type.setHasFixedSize(true);
            //recycler_list_type.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
            recycler_list_type.setAdapter(listCalcAdapter);

        }
    }
}