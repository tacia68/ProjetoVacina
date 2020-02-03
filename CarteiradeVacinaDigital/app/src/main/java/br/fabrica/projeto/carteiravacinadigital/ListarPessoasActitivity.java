package br.fabrica.projeto.carteiravacinadigital;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.fabrica.projeto.carteiravacinadigital.DAO.PessoaDAO;
import br.fabrica.projeto.carteiravacinadigital.adapters.PessoaAdapter;
import br.fabrica.projeto.carteiravacinadigital.models.Pessoa;

public class ListarPessoasActitivity extends AppCompatActivity {

    private List<Pessoa> pessoasFiltradas = new ArrayList<>();

    private List<Pessoa> pessoas;

    private RecyclerView rv;

    private PessoaDAO dao;


    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pessoas);

        if(android.os.Build.VERSION.SDK_INT >= 21){
            getWindow().setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rv = findViewById(R.id.rView);
        rv.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        dao = new PessoaDAO(this);
        pessoas = dao.obterTodos();
        pessoasFiltradas.addAll(pessoas);

        PessoaAdapter adaptador = new PessoaAdapter(pessoasFiltradas, this);
        rv.setAdapter(adaptador);
        registerForContextMenu(rv);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListarPessoasActitivity.this, CadastroPessoa.class));
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }
        });


}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.painel_pessoa, menu);

        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            //verifica as letras que o usuario digitar
            @Override
            public boolean onQueryTextChange(String s) {
                procurarPessoas(s);
                return true;
            }
        });
        return true;
    }

    //Buscar os valores digitados
    public void procurarPessoas(String nome){
        pessoasFiltradas.clear();
        for(Pessoa p : pessoas){
            if (p.getNome().toLowerCase().contains(nome.toLowerCase())){
                pessoasFiltradas.add(p);
            }

        }
           rv.invalidate();

    }


    //clicar no icone "+" chama a tela de cadastro
    public void cadastrar(MenuItem item){
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }

    public void atualizar(MenuItem item){

        //verifica a posição que esta sendo clicada
        AdapterView.AdapterContextMenuInfo menuInfo =(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Pessoa pessoaAtualizar = pessoasFiltradas.get(menuInfo.position);
        Intent it = new Intent(this, MainActivity.class);
        it.putExtra("pessoa", pessoaAtualizar);
        startActivity(it);
    }

    // atualizar a lista de pessoas cadastradas quando voltar

    @Override
    public void onResume() {
        super.onResume();
        pessoas = dao.obterTodos();
        pessoasFiltradas.clear();
        pessoasFiltradas.addAll(pessoas);

        rv.invalidate();
    }

    /*public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v,menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.option_crud, menu);
    }*/

    public void excluir(MenuItem item) {
            AdapterView.AdapterContextMenuInfo menuInfo =(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            final Pessoa pessoaExcluir = pessoasFiltradas.get(menuInfo.position);
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle("Atenção")
                    .setMessage("Realmente deseja excluir a vacina?")
                    .setNegativeButton("NÂO", null)
                    .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            pessoasFiltradas.remove(pessoaExcluir);
                            pessoas.remove(pessoaExcluir);
                            dao.excluir(pessoaExcluir);
                            rv.invalidate();
                        }
                    }).create();
            dialog.show();
        }
    /*public void onBackPressed(){
        if(backPressedTime + 2000> System.currentTimeMillis()){
            moveTaskToBack(true);
            finish();
            //android.os.Process.killProcess(android.os.Process.myPid());
        }else{
            Toast.makeText(getBaseContext(),"Aperte Novamente para sair", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }*/


}
