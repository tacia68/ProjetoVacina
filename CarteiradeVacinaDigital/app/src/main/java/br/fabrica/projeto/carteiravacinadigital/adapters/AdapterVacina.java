package br.fabrica.projeto.carteiravacinadigital.adapters;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.List;

import br.fabrica.projeto.carteiravacinadigital.R;
import br.fabrica.projeto.carteiravacinadigital.models.Pessoa;
import br.fabrica.projeto.carteiravacinadigital.models.Vacina;


public class AdapterVacina extends RecyclerView.Adapter<AdapterVacina.ViewHolder> {
    private List<Vacina> vacina;
    private Context mContext;

    //Construtor da classe
    public AdapterVacina(List<Vacina> vacina, Context mContext){
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
        Vacina v = vacina.get(position);
        holder.btMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(mContext,holder.btMenu);
                popupMenu.inflate(R.menu.option_crud);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.menu_add:
                                Toast.makeText(mContext, "Editar", Toast.LENGTH_SHORT).show();
                            case R.id.menu_remove:
                                Toast.makeText(mContext, "Deletar", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
        //Deaclaração dos parâmetros que são buscados no banco de dados e inseridos em uma lista
       /* holder.textData.setText("Dia "+p.getDataEvento());
        holder.textHora.setText(p.getHora()+" horas");
        holder.textTipoMinicurso.setText("Categoria: "+p.getTipoMinicurso());
        holder.textTitulo.setText(p.getTitulo());
        holder.textLocal.setText("Local: "+ p.getLocalizacao());
        holder.textResp.setText("Responsável: "+p.getResponsavel());*/

    }

    @Override
    public int getItemCount() {
        return vacina.size();
    }

    //Declaração das variáveis e busca no XML pelo ID para o ViewHolder
    class ViewHolder extends RecyclerView.ViewHolder{
        CardView card;
        ImageButton btMenu;
        // TextView textTipoMinicurso, textTitulo, textHora, textLocal, textResp, etiqueta, textData;
        ViewHolder (View view){
            super(view);

            //Busca pelo ID do card view onde será exibida a a programação
            card = view.findViewById(R.id.card_view);
            //Textos do XML para exibição através do ID
            btMenu = view.findViewById(R.id.ib_popup_menu);
          /*  textTipoMinicurso = view.findViewById(R.id.tTipoMinicurso);
            textTitulo = view.findViewById(R.id.tTitulo);
            textHora = view.findViewById(R.id.tHora);
            textLocal = view.findViewById(R.id.tLocal);
            textResp = view.findViewById(R.id.txtResp);
            etiqueta = view.findViewById(R.id.etiqueta);
            textData = view.findViewById(R.id.tData);*/
        }
    }
}
