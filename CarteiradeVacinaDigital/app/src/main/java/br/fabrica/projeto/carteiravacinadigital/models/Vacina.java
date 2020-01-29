package br.fabrica.projeto.carteiravacinadigital.models;

import java.io.Serializable;

public class Vacina implements Serializable {
    private Integer id;
    private String tipoVacina;
    private String dataVacina;
    private Integer lote;
    private String validade;
    private String responsavel;
    private String unidade;

    public Integer getIdVacina() {
        return id;
    }
    public void setIdVacina(Integer id) {
        this.id = id;
    }

    public String getTipoVacina() { return tipoVacina;    }

    public void setTipoVacina(String tipoVacina) {
        this.tipoVacina =tipoVacina;
    }

    public String getDataVacina() {
        return dataVacina;
    }

    public void setDataVacina(String dataVacina) {
        this.dataVacina = dataVacina;
    }

    public Integer getLote() {
        return lote;
    }

    public void setSus(Integer lote) {
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
}
