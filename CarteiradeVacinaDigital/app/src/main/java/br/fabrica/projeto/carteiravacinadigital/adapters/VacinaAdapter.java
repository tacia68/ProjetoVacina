package br.fabrica.projeto.carteiravacinadigital.adapters;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.fabrica.projeto.carteiravacinadigital.R;
import br.fabrica.projeto.carteiravacinadigital.models.Vacina;


public class VacinaAdapter extends RecyclerView.Adapter<VacinaAdapter.ViewHolder> {
    private List<Vacina> vacina;
    private Context mContext;

    //Construtor da classe
    public VacinaAdapter(List<Vacina> vacina, Context mContext){
        super();
        this.vacina = vacina;
        this.mContext = mContext;
    }

    //Declaração do Layout do fragmento junto com view Holder, o suporte
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adapter_vacina, parent, false);
        return new ViewHolder(view);
    }

    //insere as variáveis nos textos do XML capturando os dados da lista da Programacao
    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        //Caputra a estrutura get() e set() da classe primitiva
        //Deaclaração dos parâmetros que são buscados no banco de dados e inseridos em uma lista
       Vacina v = vacina.get(position);
        holder.nome.setText(v.getTipoVacina());
        holder.nomePessoa.setText(v.getVacinaPessoaId());
        holder.data.setText(v.getData());
        holder.validade.setText(v.getValidade());
        holder.lote.setText(v.getLote());
        holder.responsavel.setText(v.getResponsavel());
        holder.unidade.setText(v.getUnidade());
        holder.btMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(mContext,holder.btMenu);
                popupMenu.inflate(R.menu.option_crud);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){

                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return vacina.size();
    }

    //Declaração das variáveis e busca no XML pelo ID para o ViewHolder
    class ViewHolder extends RecyclerView.ViewHolder{
        CardView card;
        ImageButton btMenu;
        TextView nome, nomePessoa, data, validade, lote, responsavel, unidade;
        ViewHolder (View view){
            super(view);

            //Busca pelo ID do card view onde será exibida a a programação
            card = view.findViewById(R.id.card_view);
            //Textos do XML para exibição através do ID
            btMenu = view.findViewById(R.id.ib_popup_menu);

            nomePessoa = view.findViewById(R.id.textViewNomePessoa);
            nome = view.findViewById(R.id.textViewNome);
            data = view.findViewById(R.id.textViewData);
            validade = view.findViewById(R.id.textViewValidade);
            lote = view.findViewById(R.id.textViewLote);
            responsavel = view.findViewById(R.id.textViewResp);
            unidade = view.findViewById(R.id.textViewUnidade);
        }
    }
}
