package br.fabrica.projeto.carteiravacinadigital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ProgressBar;


public class Apresentacao extends AppCompatActivity {

    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apresentacao);

       //getSupportActionBar().hide();
       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        progressBar = findViewById(R.id.barra_progresso);

        progressBar.setMax(300);
        progressBar.setScaleY(3f);

       progressoAnimacao();

        //Delay para poder destruir a activity splash, pois a função que faz a chamada da activity main está em uma class java, o que impossibilita destruir a tela splash após sua execução
         new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Após os 4 segundo inicia uma nova atividade
                startActivity(new Intent(Apresentacao.this, LoginActivity.class));
                finish();
            }
        },3000);
    }

     public void progressoAnimacao(){
        BarraProgresso anim = new BarraProgresso(progressBar,0f,300f);
        anim.setDuration(3000);
        progressBar.setAnimation(anim);
    }
}

