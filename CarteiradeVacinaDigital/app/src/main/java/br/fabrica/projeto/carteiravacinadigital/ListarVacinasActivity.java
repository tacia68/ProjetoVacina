package br.fabrica.projeto.carteiravacinadigital;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.ImgTemplate;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.fabrica.projeto.carteiravacinadigital.DAO.VacinaDAO;
import br.fabrica.projeto.carteiravacinadigital.adapters.PessoaAdapter;
import br.fabrica.projeto.carteiravacinadigital.adapters.VacinaAdapter;
import br.fabrica.projeto.carteiravacinadigital.models.Vacina;

public class ListarVacinasActivity extends AppCompatActivity {
    private RecyclerView rv;
    VacinaAdapter adaptador;
    private VacinaDAO dao;
    private List<Vacina> vacinas;
    private List<Vacina> vacinasFiltradas = new ArrayList<>();

    private static final int STORAGE_CODE = 1000;
    private static final int READ_REQUEST_CODE = 10;


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

        dao = new VacinaDAO(this);
        vacinas = dao.obterTodos();
        vacinasFiltradas.addAll(vacinas);
        //adaptador de vacina
        adaptador = new VacinaAdapter(vacinasFiltradas, this);

        rv.setAdapter(adaptador);
        registerForContextMenu(rv);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }
        });
        cadastrar();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_print) {
            if (Build.VERSION.SDK_INT> Build.VERSION_CODES.M){

                if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){

                    String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    requestPermissions(permissions, STORAGE_CODE);
                }
                else {
                    savePdf();
                }

            }
            else {
                savePdf();
            }
        }
        if (id == R.id.action_web_view) {
            startActivity(new Intent(this, MyWebView.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.painel_pessoa, menu);

        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            //verifica as letras que o usuario digitar
            @Override
            public boolean onQueryTextChange(String s) {
                procurarVacina(s);
                return false;
            }
        });
        return true;
    }

    //Buscar os valores digitados
    public void procurarVacina(String tipoVacina){
        vacinasFiltradas.clear();
        for(Vacina v : vacinas){
            if (v.getTipoVacina().toLowerCase().contains(tipoVacina.toLowerCase())){
                vacinasFiltradas.add(v);
            }

        }
        rv.invalidate();
    }

    //clicar no icone "+" chama a tela de cadastro de vacina
    public void cadastrar(){
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListarVacinasActivity.this, CadastroVacina.class));
            }
        });
    }

    private void savePdf(){

        Document mDoc = new Document();

        String mFileName = new SimpleDateFormat("yyyuMMdd_HHmmss", Locale.getDefault()).format(System.currentTimeMillis());

        String mFilePath = Environment.getExternalStorageDirectory() + "/" + mFileName + ".pdf";
        //String nome = null, tipoVacina = null, data = null, lote = null, validade = null, responsavel = null, unidade = null;
        try {
            PdfWriter.getInstance(mDoc, new FileOutputStream(mFilePath));
            mDoc.open();
            for (Vacina v : vacinas) {
                mDoc.add(new Paragraph("\n"));
                mDoc.add(new Paragraph(v.getVacinaPessoaId()));
                mDoc.add(new Paragraph("\n"));
                mDoc.add(new Paragraph(v.getTipoVacina()));
                mDoc.add(new Paragraph(v.getData()));
                mDoc.add(new Paragraph(v.getLote()));
                mDoc.add(new Paragraph(v.getValidade()));
                mDoc.add(new Paragraph(v.getUnidade()));
                mDoc.add(new Paragraph("\n\n"));
            }
            mDoc.close();
            Toast.makeText(this, mFileName+".pdf\nDocumento salvo\n" + mFileName, Toast.LENGTH_LONG).show();

        }
        catch (Exception e){

            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case STORAGE_CODE:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    savePdf();
                }
            }
        }
    }

    // atualizar a lista de vacinas cadastradas quando voltar

    @Override
    public void onResume(){
        super.onResume();
        vacinas = dao.obterTodos();
        vacinasFiltradas.clear();
        vacinasFiltradas.addAll(vacinas);
        rv.invalidate();
    }
}
