package com.iessaladillo.lolo.mgc_pr02_exchange;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private EditText txtAmount;
    private RadioGroup rgFrom;
    private RadioGroup rgTo;
    private RadioButton rbFromDollar, rbToDollar;
    private RadioButton rbFromEuro, rbToEuro;
    private RadioButton rbFromPound, rbToPound;
    private ImageView imgFrom, imgTo;
    // DEFINE COMO LOCALES LAS QUE SÓLO VAYAS A USAR EN UN MÉTODO.
    private Button btnExchange;
    private double initialAmount, newAmount;
    private char stSymbol, ndSymbol;
    private final double EUR_DOL = 1.17;
    private final double EUR_POU = 0.89;
    private final double DOL_EUR = 0.86;
    private final double DOL_POU = 0.77;
    private final double POU_EUR = 1.12;
    private final double POU_DOL = 1.3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        txtAmount = findViewById(R.id.txtAmount);
        rgFrom = findViewById(R.id.rgFrom);
        rgTo = findViewById(R.id.rgTo);
        rbFromDollar = findViewById(R.id.rbFromDollar);
        rbFromEuro = findViewById(R.id.rbFromEuro);
        rbFromPound = findViewById(R.id.rbFromPound);
        rbToDollar = findViewById(R.id.rbToDollar);
        rbToEuro = findViewById(R.id.rbToEuro);
        rbToPound = findViewById(R.id.rbToPound);
        btnExchange = findViewById(R.id.btnExchange);
        imgFrom = findViewById(R.id.imgFrom);
        imgTo = findViewById(R.id.imgTo);
        rgFrom.setOnCheckedChangeListener(this);
        rgTo.setOnCheckedChangeListener(this);
        btnExchange.setOnClickListener(v -> exchange());
        // AGREGO ESTAS LÍNEAS PARA QUE PASE ALGUNOS TESTS.
        // TE HE CAMBIADO LOS NOMBRES DE LOS ARCHIVOS DRAWABLE PARA QUE PASE LOS TESTS.
        imgFrom.setTag(R.drawable.ic_euro);
        imgTo.setTag(R.drawable.ic_dollar);
    }

    private void exchange() {
        if (txtAmount.getText().toString().equals("") || txtAmount.getText().toString().equals("."))
            txtAmount.setText("0.00");
        else {
            initialAmount = Double.parseDouble(String.valueOf(txtAmount.getText()));
            if (rgFrom.getCheckedRadioButtonId() == rbFromDollar.getId()) {
                stSymbol = '$';
                if (rgTo.getCheckedRadioButtonId() == rbToEuro.getId()) {
                    newAmount = initialAmount * DOL_EUR;
                    ndSymbol = '€';
                } else {
                    newAmount = initialAmount * DOL_POU;
                    // DEFINE UNA CONSTANTE EN CUALQUIER CASO. NO USES NÚMEROS MÁGICOS
                    ndSymbol = 163;
                }
            } else if (rgFrom.getCheckedRadioButtonId() == rbFromEuro.getId()) {
                stSymbol = '€';
                if (rgTo.getCheckedRadioButtonId() == rbToDollar.getId()) {
                    newAmount = initialAmount * EUR_DOL;
                    ndSymbol = '$';
                } else {
                    newAmount = initialAmount * EUR_POU;
                    ndSymbol = 163;
                }
            } else {
                stSymbol = 163;
                if (rgTo.getCheckedRadioButtonId() == rbToDollar.getId()) {
                    newAmount = initialAmount * POU_DOL;
                    ndSymbol = '$';
                } else {
                    newAmount = initialAmount * POU_EUR;
                    ndSymbol = '€';
                }
            }
            // EN VEZ DE String.format DEFINE RECURSOS DE CADENA CON PARÁMETROS.
            Toast.makeText(this, String.format("%.2f %c = %.2f %c", initialAmount, stSymbol, newAmount, ndSymbol), Toast.LENGTH_SHORT).
                    show();
        }
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (group == rgFrom) {
            if (checkedId == rbFromDollar.getId()) {
                rbToDollar.setEnabled(false);
                rbToEuro.setEnabled(true);
                rbToPound.setEnabled(true);
                imgFrom.setImageResource(R.drawable.ic_dollar);
                // AGREGO ESTA LÍNEA PARA QUE PASE TESTS. FÍJATE
                imgFrom.setTag(R.drawable.ic_dollar);
            } else if (checkedId == rbFromEuro.getId()) {
                rbToEuro.setEnabled(false);
                rbToPound.setEnabled(true);
                rbToDollar.setEnabled(true);
                imgFrom.setImageResource(R.drawable.ic_euro);
                // AGREGO ESTA LÍNEA PARA QUE PASE TESTS. FÍJATE
                imgFrom.setTag(R.drawable.ic_euro);
            } else {
                rbToPound.setEnabled(false);
                rbToDollar.setEnabled(true);
                rbToEuro.setEnabled(true);
                imgFrom.setImageResource(R.drawable.ic_pound);
                // AGREGO ESTA LÍNEA PARA QUE PASE TESTS. FÍJATE
                imgFrom.setTag(R.drawable.ic_pound);
            }
        } else {
            if (checkedId == rbToDollar.getId()) {
                rbFromDollar.setEnabled(false);
                rbFromEuro.setEnabled(true);
                rbFromPound.setEnabled(true);
                imgTo.setImageResource(R.drawable.ic_dollar);
                // AGREGO ESTA LÍNEA PARA QUE PASE TESTS. FÍJATE
                imgTo.setTag(R.drawable.ic_dollar);
            } else if (checkedId == rbToEuro.getId()) {
                rbFromEuro.setEnabled(false);
                rbFromPound.setEnabled(true);
                rbFromDollar.setEnabled(true);
                imgTo.setImageResource(R.drawable.ic_euro);
                // AGREGO ESTA LÍNEA PARA QUE PASE TESTS. FÍJATE
                imgTo.setTag(R.drawable.ic_euro);
            } else {
                rbFromPound.setEnabled(false);
                rbFromDollar.setEnabled(true);
                rbFromEuro.setEnabled(true);
                imgTo.setImageResource(R.drawable.ic_pound);
                // AGREGO ESTA LÍNEA PARA QUE PASE TESTS. FÍJATE
                imgTo.setTag(R.drawable.ic_pound);
            }
        }
    }
}
