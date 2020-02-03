package br.fabrica.projeto.carteiravacinadigital.models;

import java.io.Serializable;

public class Vacina implements Serializable {
    private Integer id;
    private String tipoVacina;
    private String data;
    private String lote;
    private String validade;
    private String responsavel;
    private String unidade;
    private String vacinaPessoaId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoVacina() {
        return tipoVacina;
    }

    public void setTipoVacina(String tipoVacina) {
        this.tipoVacina = tipoVacina;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getVacinaPessoaId() {
        return vacinaPessoaId;
    }

    public void setVacinaPessoaId(String vacinaPessoaId) {
        this.vacinaPessoaId = vacinaPessoaId;
    }

    @Override
    public String toString(){
        return tipoVacina;
    }
}
