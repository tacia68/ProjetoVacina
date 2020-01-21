package br.fabrica.projeto.carteiravacinadigital;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;

public class BarraProgresso extends Animation {
    //Variáveis para exibição da Barra de animação
    private ProgressBar progressBar;
    private float inicio;
    private float fim;
    //Construtor que está recebendo essas variáveis
    BarraProgresso(ProgressBar progressBar, float inicio, float fim){
        this.progressBar = progressBar;
        this.inicio = inicio;
        this.fim = fim;
    }
    //Classe protect que faz o cálculo e exbição da barra de progresso
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float valor = inicio + (fim - inicio) * interpolatedTime;
        progressBar.setProgress((int)valor);
    }
}
