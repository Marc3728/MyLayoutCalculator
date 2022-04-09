package com.example.mylayoutcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContentInfo;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private  Button button0;
    private  Button button1;
    private  Button button2;
    private  Button button3;
    private  Button button4;
    private  Button button5;
    private  Button button6;
    private  Button button7;
    private  Button button8;
    private  Button button9;
    private  Button buttonsum;
    private  Button buttonig;
    private  Button buttoncl;
    private  Button buttonres;
    private  Button buttondiv;
    private  Button buttonmult;
    private Button buttonpunto;
    private RadioGroup radiogroup;
    private RadioButton radiosuma;
    private RadioButton radioresta;
    private RadioButton radiomult;
    private RadioButton radiodiv;
    private CheckBox checkBoxOp;
    private TextView diplay;
    private int aux;
    private Button auxb;
    private Button buttonselect;
    private boolean bol;
    private boolean primeraop;
    private String numero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculatorcl);
        button0 = findViewById(R.id.button0cl);
        button1 = findViewById(R.id.button1cl);
        button2 = findViewById(R.id.button2cl);
        button3 = findViewById(R.id.button3cl);
        button4 = findViewById(R.id.button4cl);
        button5 = findViewById(R.id.button5cl);
        button6 = findViewById(R.id.button6cl);
        button7 = findViewById(R.id.button7cl);
        button8 = findViewById(R.id.button8cl);
        button9 = findViewById(R.id.button9cl);
        buttonsum = findViewById(R.id.buttonsumcl);
        buttonig = findViewById(R.id.buttonigcl);
        buttoncl = findViewById(R.id.buttonccl);
        buttonres = findViewById(R.id.buttonres);
        buttondiv = findViewById(R.id.buttondiv);
        buttonmult = findViewById(R.id.buttonmult);
        buttonpunto = findViewById(R.id.punto);
        radiogroup = findViewById(R.id.radioGroupOperaciones);
        radiosuma = findViewById(R.id.radioButtonSuma);
        radioresta = findViewById(R.id.radioButtonResta);
        radiomult = findViewById(R.id.radioButtonMultiplicacion);
        radiodiv = findViewById(R.id.radioButtonDivision);
        checkBoxOp = findViewById(R.id.checkBoxOperaciones);
        diplay = findViewById(R.id.dispalyc);
        auxb = null;
        buttonselect = null;
        numero = "";
        primeraop = true;
        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        buttonsum.setOnClickListener(this);
        buttonig.setOnClickListener(this);
        buttoncl.setOnClickListener(this);
        buttondiv.setOnClickListener(this);
        buttonmult.setOnClickListener(this);
        buttonres.setOnClickListener(this);
        buttonpunto.setOnClickListener(this);
        radiogroup.setVisibility(View.GONE);

        checkBoxOp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBoxOp.isChecked()){
                    radiogroup.setVisibility(View.VISIBLE);
                } else {
                    radiogroup.clearCheck();
                    radiogroup.setVisibility(View.GONE);
                    buttonsum.setEnabled(true);
                    buttonres.setEnabled(true);
                    buttonmult.setEnabled(true);
                    buttondiv.setEnabled(true);
                }
            }
        });

        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radioButtonSuma:
                        buttonsum.setEnabled(false);
                        buttonres.setEnabled(true);
                        buttonmult.setEnabled(true);
                        buttondiv.setEnabled(true);
                        break;
                    case R.id.radioButtonResta:
                        buttonsum.setEnabled(true);
                        buttonres.setEnabled(false);
                        buttonmult.setEnabled(true);
                        buttondiv.setEnabled(true);
                        break;
                    case R.id.radioButtonMultiplicacion:
                        buttonsum.setEnabled(true);
                        buttonres.setEnabled(true);
                        buttonmult.setEnabled(false);
                        buttondiv.setEnabled(true);
                        break;
                    case R.id.radioButtonDivision:
                        buttonsum.setEnabled(true);
                        buttonres.setEnabled(true);
                        buttonmult.setEnabled(true);
                        buttondiv.setEnabled(false);
                }
            }
        });


    }


    @Override
    public void onClick(View view) {
        if (view instanceof Button){
            Button b = (Button) view;
            if (b == buttonig && buttonselect!=null && !diplay.getText().toString().equals("")){
                resultadoOperacion();
                numero = diplay.getText().toString();
                buttonselect = null;
            } else if (b== buttoncl) {
                diplay.setText("0");
                numero = null;
                buttonselect = null;
            } else if (b== buttonsum || b== buttonres || b==buttondiv || b == buttonmult){
                if (!diplay.getText().equals("0")) {
                    if (buttonselect != null) {
                        resultadoOperacion();
                    }
                    numero = diplay.getText().toString();
                    buttonselect = b;
                    bol = true;
                }
            } else {
                if (bol){
                    diplay.setText("");
                    bol = false;
                }
                if (diplay.getText().equals("0"))
                    diplay.setText(b.getTag().toString());
                else
                    diplay.setText(diplay.getText()+b.getTag().toString());

            }
        }
    }

    public boolean resultadoOperacion(){
        if (buttonselect!=null) {
            if (contienePunto(diplay.getText().toString()) || contienePunto(numero)){
                float numf = Float.parseFloat(numero);
                float dspf = Float.parseFloat(diplay.getText().toString());
                if (buttonselect==buttonsum) {
                    diplay.setText(String.valueOf(numf + dspf));
                } else if (buttonselect==buttonres){
                    diplay.setText(String.valueOf(numf - dspf));
                } else if (buttonselect==buttondiv){
                    diplay.setText(String.valueOf(numf / dspf));
                } else {
                    diplay.setText(String.valueOf(numf * dspf));
                }
            } else {
                int numi = Integer.parseInt(numero);
                int dspi = Integer.parseInt(diplay.getText().toString());
                if (buttonselect==buttonsum) {
                    diplay.setText(String.valueOf(numi + dspi));
                } else if (buttonselect==buttonres){
                    diplay.setText(String.valueOf(numi - dspi));
                } else if (buttonselect==buttondiv){
                    diplay.setText(String.valueOf(numi / dspi));
                } else {
                    diplay.setText(String.valueOf(numi * dspi));
                }
            }
            return true;
        }
        return false;
    }

    public boolean contienePunto(String numerod){
        for (int i = 0;i<numerod.length();i++){
            if (numerod.charAt(i)=='.'){
                return true;
            }
        }
        return false;
    }
}