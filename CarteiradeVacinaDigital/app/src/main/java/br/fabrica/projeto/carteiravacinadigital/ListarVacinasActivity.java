package br.fabrica.projeto.carteiravacinadigital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import br.fabrica.projeto.carteiravacinadigital.adapters.AdapterVacina;
import br.fabrica.projeto.carteiravacinadigital.models.Vacina;

public class ListarVacinasActivity extends AppCompatActivity {
    private ArrayList<Vacina> vacina = new ArrayList<>();
    private RecyclerView rv;
    AdapterVacina adapterVacina;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painel_vacina);
        if(android.os.Build.VERSION.SDK_INT >= 21){
            getWindow().setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rv = findViewById(R.id.rView);
        rv.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListarVacinasActivity.this, CadastroVacina.class));
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        for (int i = 0; i<10; i++){
            vacina.add(new Vacina());
        }

        adapterVacina = new AdapterVacina(vacina, this);
        rv.setAdapter(adapterVacina);
        rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListarVacinasActivity.this, ListarVacinasActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.painel_pessoa, menu);
        return true;
    }
}
