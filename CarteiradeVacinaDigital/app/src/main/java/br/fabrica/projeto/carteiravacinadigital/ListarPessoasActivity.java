package br.fabrica.projeto.carteiravacinadigital;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ListarPessoasActivity extends AppCompatActivity {

    private ListView listView;
    private PessoaDAO dao;
    private List<Pessoa> pessoas;
    private List<Pessoa> pessoasFiltradas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pessoas);

        listView = findViewById(R.id.lista_pessoas);
        dao = new PessoaDAO(this);
        pessoas = dao.obterTodos();
        pessoasFiltradas.addAll(pessoas);
        //adaptador de pessoa
        ArrayAdapter<Pessoa> adaptador = new ArrayAdapter<Pessoa>(this, android.R.layout.simple_list_item_1, pessoasFiltradas);

        listView.setAdapter(adaptador);
        registerForContextMenu(listView); // Quando o list view for pressionado vai abrir o context Menu
    }

    //Aplicar o menu principal na lista pessoas
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal, menu);

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
                return false;
            }
        });
        return true;
    }

    //Buscar os valores digitados
    public void procurarPessoas(String tipoVacina){
        pessoasFiltradas.clear();
        for(Pessoa p : pessoas){
            if (p.getNome().toLowerCase().contains(tipoVacina.toLowerCase())){
                pessoasFiltradas.add(p);
            }

        }
        listView.invalidateViews();
    }
    //chamada de menu context para excluir e editar
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v,menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);
    }

    public void excluir(MenuItem item){
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
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();
    }

    //clicar no icone "+" chama a tela de cadastro
    public void cadastrar(MenuItem item){
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }

    // atualizar a lista de pessoas cadastradas quando voltar

    @Override
    public void onResume(){
        super.onResume();
        pessoas = dao.obterTodos();
        pessoasFiltradas.clear();
        pessoasFiltradas.addAll(pessoas);
        listView.invalidateViews();
    }

}
