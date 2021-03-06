package br.fabrica.projeto.carteiravacinadigital.adapters;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.fabrica.projeto.carteiravacinadigital.ListarVacinasActivity;
import br.fabrica.projeto.carteiravacinadigital.models.Pessoa;
import br.fabrica.projeto.carteiravacinadigital.R;


public class PessoaAdapter extends RecyclerView.Adapter<PessoaAdapter.ViewHolder> {
    private List<Pessoa> pessoa;
    private  Context mContext;

    //Construtor da classe
    public PessoaAdapter(List<Pessoa> pessoa, Context mContext){
        super();
        this.pessoa = pessoa;
        this.mContext = mContext;
    }

    //Declaração do Layout do fragmento junto com view Holder, o suporte
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adapter_pessoa, parent, false);
        return new ViewHolder(view);
    }

    //insere as variáveis nos textos do XML capturando os dados da lista da Programacao
    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        //Caputra a estrutura get() e set() da classe primitiva
        Pessoa p = pessoa.get(position);

        holder.nome.setText(p.getNome());
        holder.TipoSanguineo.setText(p.getTiposanguineo());
        holder.sus.setText(p.getSus());
        holder.parentesco.setText(p.getParentesco());

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, ListarVacinasActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return pessoa.size();
    }

    //Declaração das variáveis e busca no XML pelo ID para o ViewHolder
    class ViewHolder extends RecyclerView.ViewHolder{
        CardView card;
        TextView nome, TipoSanguineo, sus, parentesco;
        ViewHolder (View view){
            super(view);

            //Busca pelo ID do card view onde será exibida a a programação
            card = view.findViewById(R.id.card_view);
            //Textos do XML para exibição através do ID
            nome = view.findViewById(R.id.textViewNome);
            TipoSanguineo = view.findViewById(R.id.textViewSangue);
            sus = view.findViewById(R.id.textViewSus);
            parentesco = view.findViewById(R.id.textViewParentesco);
        }
    }

}