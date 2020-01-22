package br.fabrica.projeto.carteiravacinadigital;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class VacinaDAO {

    private Conexao conexao;
    private SQLiteDatabase teste3;

    public VacinaDAO(Context context){
        conexao = new Conexao(context);
        teste3 = conexao.getWritableDatabase();
    }



    // devolve o id do numero que foi inserido
    public long addVacina(Vacina vacina){
        ContentValues values = new ContentValues();
        values.put("tipo", vacina.getTipoVacina());
        values.put("data", vacina.getData());
        values.put("lote", vacina.getLote());
        values.put("validade", vacina.getValidade());
        values.put("responsavel", vacina.getResponsavel());
        values.put("unidade", vacina.getUnidade());
        values.put("vacina_pessoa_id", vacina.getVacinaPessoaId());
        return teste3.insert("vacina", null, values);
    }
    /* public static final String TBL_VACINA = "vacina";
    public static final String VACINA_ID = "id";
    public static final String VACINA_TIPO = "tipo";
    public static final String VACINA_DATA = "data";
    public static final String VACINA_LOTE = "lote";
    public static final String VACINA_VALIDADE = "validade";
    public static final String VACINA_RESPONSAVEL = "responsavel";
    public static final String VACINA_UNIDADE = "unidade";
    //chave estrangeira
    public static final String VACINA_PESSOA_ID = "vacina_pessoa_id";*/

    public List<Vacina> obterTodos(){
        List<Vacina> vacina = new ArrayList<>();
        Cursor cursor = teste3.query("vacina", new String[]{"id","tipo","data","lote", "validade", "responsavel", "unidade", "vacina_pessoa_id"},
                null, null, null, null, null);

        while(cursor.moveToNext()){

            Vacina v = new Vacina();
            v.setId(cursor.getInt(0));
            v.setTipoVacina(cursor.getString(1));
            v.setData(cursor.getString(2));
            v.setLote(cursor.getString(3));
            v.setValidade(cursor.getString(4));
            v.setResponsavel(cursor.getString(5));
            v.setUnidade(cursor.getString(6));
            v.setVacinaPessoaId(cursor.getString(7));

            vacina.add(v);
        }
        return vacina;
    }
}
