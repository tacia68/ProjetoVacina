package br.fabrica.projeto.carteiravacinadigital;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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
}
