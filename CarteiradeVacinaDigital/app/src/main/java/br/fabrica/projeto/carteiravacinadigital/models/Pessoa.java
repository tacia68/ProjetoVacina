package br.fabrica.projeto.carteiravacinadigital.models;

import android.widget.PopupMenu;

import java.io.Serializable;
import java.util.List;


public class Pessoa implements Serializable {

        private Integer id;
        private String nome;
        private String tiposanguineo;
        private String sus;
        private String parentesco;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getNome() { return nome;    }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getTiposanguineo() {
            return tiposanguineo;
        }

        public void setTiposanguineo(String tiposanguineo) {
            this.tiposanguineo = tiposanguineo;
        }

        public String getSus() {
            return sus;
        }

        public void setSus(String sus) {
            this.sus = sus;
        }

        public String getParentesco() {
            return parentesco;
        }

        public void setParentesco(String parentesco) {
            this.parentesco = parentesco;
        }

        @Override
        public String toString(){
            return nome;
        }


    }

