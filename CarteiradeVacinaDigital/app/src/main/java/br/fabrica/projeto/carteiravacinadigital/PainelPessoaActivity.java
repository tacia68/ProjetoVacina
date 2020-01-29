package br.fabrica.projeto.carteiravacinadigital;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.Toast;

import java.util.ArrayList;

import br.fabrica.projeto.carteiravacinadigital.adapters.AdapterPessoa;
import br.fabrica.projeto.carteiravacinadigital.models.Pessoa;

public class PainelPessoaActivity extends AppCompatActivity {
    private ArrayList<Pessoa> pessoa = new ArrayList<>();
    private RecyclerView rv;
    AdapterPessoa adapterPessoa;
    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painel_pessoa);
        if(android.os.Build.VERSION.SDK_INT >= 21){
            getWindow().setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rv = findViewById(R.id.rView);
        rv.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PainelPessoaActivity.this, CadastroPessoa.class));
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }
        });


        for (int i = 0; i<10; i++){
            pessoa.add(new Pessoa());
        }

        adapterPessoa = new AdapterPessoa(pessoa, this);
        rv.setAdapter(adapterPessoa);

}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.painel_pessoa, menu);
        return true;
    }
    public void onBackPressed(){
        if(backPressedTime + 2000> System.currentTimeMillis()){
            moveTaskToBack(true);
            finish();
            //android.os.Process.killProcess(android.os.Process.myPid());
        }else{
            Toast.makeText(getBaseContext(),"Aperte Novamente para sair", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }


}
