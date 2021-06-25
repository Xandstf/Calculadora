package br.edu.ifsp.scl.ads.pdm.calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import br.edu.ifsp.scl.ads.pdm.calculadora.databinding.ActivityConfiguracoesBinding;

public class ConfigurationsActivity extends AppCompatActivity {
    private ActivityConfiguracoesBinding activityConfiguracoesBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityConfiguracoesBinding = activityConfiguracoesBinding.inflate(getLayoutInflater());
        setContentView(activityConfiguracoesBinding.getRoot());
        if(MainActivity.CALCULADORA_CIENTIFICA == 0){
            activityConfiguracoesBinding.tipoCalculadoraRg.check( activityConfiguracoesBinding.normalRb.getId());
        }else{
            activityConfiguracoesBinding.tipoCalculadoraRg.check( activityConfiguracoesBinding.avancadoRb.getId());
        }
    }

    public void onClick(View view){
        if(activityConfiguracoesBinding.tipoCalculadoraRg.getCheckedRadioButtonId() == activityConfiguracoesBinding.normalRb.getId()){
            MainActivity.CALCULADORA_CIENTIFICA = 0;
        }else{
            MainActivity.CALCULADORA_CIENTIFICA = 1;
        }
        finish();
    }
}
