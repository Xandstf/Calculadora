package br.edu.ifsp.scl.ads.pdm.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Intent;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

// Calculadora tela principal
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Visores
    private final String VALUE_TV_RESULT = "VALOR_TV_RESULT";
    private final String VALUE_TV_VISOR = "VALOR_TV_VISOR";

    //Tipo calculadora
    public static int CALCULADORA_CIENTIFICA = 0;

    //Objetos
    private TextView valueTextView, valueVisor;
    private final StringBuilder digitScreen = new StringBuilder();
    private final StringBuilder digitScreenAll = new StringBuilder();
    private char operation = 'a';
    private Double value1=0.0;

    // ============
    // IGNIÇÃO DO MOTOR
    // ============
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        valueTextView = findViewById(R.id.resultado_tv);
        valueVisor = findViewById(R.id.visor_tv);
        Objects.requireNonNull(getSupportActionBar()).setSubtitle("Modo Básico");
    }

    // ============
    // SAVE RESTORE INSTANCE
    // ============
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(VALUE_TV_RESULT, valueTextView.getText().toString());
        outState.putString(VALUE_TV_VISOR, valueVisor.getText().toString());
    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        valueTextView.setText(savedInstanceState.getString(VALUE_TV_RESULT, "0"));
        valueVisor.setText(savedInstanceState.getString(VALUE_TV_VISOR, ""));

    }

    // ============
    // LIGA E CONFIGURA MENU
    // ============
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.configuracoesMi) {
            Intent configuracoesIntent = new Intent(this, ConfigurationsActivity.class);
            startActivityForResult(configuracoesIntent, CALCULADORA_CIENTIFICA);
            return true;
        }
        return false;
    }

    // ============
    // ATIVA / DESATIVA MODO AVANÇADO
    // ============
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (CALCULADORA_CIENTIFICA == 1){
                findViewById(R.id.raizquadrada_bt).setVisibility(View.VISIBLE);
                findViewById(R.id.potencia_bt).setVisibility(View.VISIBLE);
                findViewById(R.id.modulo_bt).setVisibility(View.VISIBLE);
                Objects.requireNonNull(getSupportActionBar()).setSubtitle("Modo Avançado");
        }else{
            findViewById(R.id.raizquadrada_bt).setVisibility(View.GONE);
            findViewById(R.id.potencia_bt).setVisibility(View.GONE);
            findViewById(R.id.modulo_bt).setVisibility(View.GONE);
            Objects.requireNonNull(getSupportActionBar()).setSubtitle("Modo Básico");
        }
    }

    // ============
    // MAPEIA / IDENTIFICA BOTÕES
    // ============
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zero_bt:
                digitScreen.append("0");
                digitScreenAll.append("0");
                break;
            case R.id.um_bt:
                digitScreen.append("1");
                digitScreenAll.append("1");
                break;
            case R.id.dois_bt:
                digitScreen.append("2");
                digitScreenAll.append("2");
                break;
            case R.id.tres_bt:
                digitScreen.append("3");
                digitScreenAll.append("3");
                break;
            case R.id.quatro_bt:
                digitScreen.append("4");
                digitScreenAll.append("4");
                break;
            case R.id.cinco_bt:
                digitScreen.append("5");
                digitScreenAll.append("5");
                break;
            case R.id.seis_bt:
                digitScreen.append("6");
                digitScreenAll.append("6");
                break;
            case R.id.sete_bt:
                digitScreen.append("7");
                digitScreenAll.append("7");
                break;
            case R.id.oito_bt:
                digitScreen.append("8");
                digitScreenAll.append("8");
                break;
            case R.id.nove_bt:
                digitScreen.append("9");
                digitScreenAll.append("9");
                break;
            case R.id.limpar_bt:
                digitScreen.setLength(0);
                digitScreenAll.setLength(0);
                setTextViewInEmpty();
                value1 =  0.0;
                break;
            case R.id.multi_bt:
                if (digitScreen.length() > 0) {
                    selectOperation('*');
                    digitScreenAll.append('*');
                }
                break;
            case R.id.divide_bt:
                if (digitScreen.length() > 0) {
                    selectOperation('/');
                    digitScreenAll.append('/');
                }
                break;
            case R.id.menos_bt:
                if(digitScreen.length() == 0){
                    digitScreen.append("-");
                }else{
                    selectOperation('-');
                }
                digitScreenAll.append('-');
                break;
            case R.id.mais_bt:
                if (digitScreen.length() > 0) {
                    selectOperation('+');
                    digitScreenAll.append('+');
                }
                break;
            case R.id.ponto_bt:
                if (digitScreen.length() > 0){
                    if (digitScreen.indexOf(".") == -1){
                        digitScreen.append('.');
                        digitScreenAll.append('.');
                    }
                }
                break;
            case R.id.modulo_bt:
                if (digitScreen.length() > 0) {
                    selectOperation('%');
                    digitScreenAll.append('/');
                }
                break;
            case R.id.raizquadrada_bt:
                if (digitScreen.length() > 0) {
                    try{
                        value1 = Double.parseDouble(String.valueOf(valueTextView.getText()));
                        digitScreen.setLength(0);
                        digitScreenAll.setLength(0);
                        digitScreenAll.append(Math.sqrt(value1));
                        digitScreen.append(Math.sqrt(value1));
                    }catch (NumberFormatException e){
                        Toast.makeText(this, "Digite um número válido...", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.potencia_bt:
                if (digitScreen.length() > 0) {
                    selectOperation('^');
                    digitScreenAll.append('^');
                }
                break;
            case R.id.backspace_bt:
                clearDigit();
                break;
            case R.id.igual_bt:
                if (value1 != 0.0 && operation != 'a'){
                    doMath();
                }
                break;
            default:
                Toast.makeText(this, "OPERAÇÃO NÃO IDENTIFICADA!", Toast.LENGTH_SHORT).show();
        }
        valueTextView.setText(digitScreen);
        valueVisor.setText(digitScreenAll);
    }

    // ============
    // OPERAÇÕES
    // ============
    private void doMath() {
        Double value2 = Double.parseDouble(String.valueOf(valueTextView.getText()));
        double result = 0.0;
        digitScreenAll.setLength(0);
        digitScreen.setLength(0);
        switch (operation){
            case '+':
                result = value1 + value2;
                break;
            case '-':
                result = value1 - value2;
                break;
            case '*':
                result = value1 * value2;
                break;
            case '/':
                result = value1 / value2;
                break;
            case '%':
                result = value1 % value2;
                break;
            case '^':
                result = Math.pow(value1, value2);
                break;
            default:
                Toast.makeText(this, "Sinto muito, esse app falhou", Toast.LENGTH_SHORT).show();
        }
        digitScreen.append(result);
        digitScreenAll.append(result);
        valueTextView.setText(digitScreen);
        valueVisor.setText(digitScreenAll);
    }

    // ============
    // LIMPA VISOR
    // ============
    private void deleteStringBuilder(int start, int end, StringBuilder str){
        str.delete(start, end);
    }
    private void clearDigit() {
        if (digitScreen.length() > 1){
            deleteStringBuilder(digitScreen.length()-1, digitScreen.length(), digitScreen) ;
            deleteStringBuilder(digitScreenAll.length()-1, digitScreenAll.length(), digitScreenAll) ;
        }
        else if(digitScreen.length() == 1){
            deleteStringBuilder(0, digitScreen.length(), digitScreen);
            deleteStringBuilder(0, digitScreenAll.length(), digitScreenAll);
            digitScreen.append("");
            digitScreenAll.append("");
        }
        setTextViewInEmpty();
        valueTextView.setText(digitScreen);
        valueVisor.setText(digitScreenAll);
    }
    private void setTextViewInEmpty(){
        valueVisor.setText("");
        valueTextView.setText("");
    }

    // ============
    // SELECIONAR OPERAÇÃO
    // ============
    private void selectOperation(char c){
        operation = c;
        if (valueTextView.toString().equals("")){
            Toast.makeText(this, "Nenhum número digitado...", Toast.LENGTH_SHORT).show();
        }
        else{
            try {
                value1 = Double.parseDouble(String.valueOf(valueTextView.getText()));
                digitScreen.delete(0, digitScreen.length());
                valueTextView.setText(digitScreen);
            }catch (NumberFormatException e){
                Toast.makeText(this, "Número inválido...", Toast.LENGTH_SHORT).show();
            }
        }
    }
}