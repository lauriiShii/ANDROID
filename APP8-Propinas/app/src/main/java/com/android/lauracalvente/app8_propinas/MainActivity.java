package com.android.lauracalvente.app8_propinas;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnOkAccount)
    Button btnOkAccount;
    @BindView(R.id.btnRoundOutAccount)
    Button btnRoundOutAccount;
    @BindView(R.id.txtAccount)
    EditText txtAccount;
    @BindView(R.id.txtTotal)
    EditText txtTotal;
    @BindView(R.id.txtTipPercentage)
    EditText txtTipPercentage;
    @BindView(R.id.txtTip)
    EditText txtTip;
    @BindView(R.id.txtDiners)
    EditText txtDiners;
    @BindView(R.id.txtNumDiners)
    TextInputLayout txtNumDiners;
    @BindView(R.id.txtTotalDiner)
    EditText txtTotalDiner;
    @BindView(R.id.btnOkDiners)
    Button btnOkDiners;
    @BindView(R.id.btnRoundOutDiners)
    Button btnRoundOutDiners;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnOkAccount)
    public void onClickTotalAccount() {
        accountCalculate();
    }

    @OnClick(R.id.btnRoundOutAccount)
    public void onClickRoundAccount() {
        accountRound();
    }

    @OnClick(R.id.btnOkDiners)
    public void onClickTotalDiner() {
        totalDinerCalculate();
    }

    @OnClick(R.id.btnRoundOutDiners)
    public void onClickRoundDiners() {
        totalDinerRound();
    }

    @OnTextChanged(R.id.txtAccount)
    void onTextChangedAccount() {
        accountCalculate();
    }

    @OnTextChanged(R.id.txtTipPercentage)
    void onTextChangedPorcentage() {
        accountCalculate();
    }

    @OnTextChanged(R.id.txtDiners)
    void onTextChangedDiner() {
        totalDinerCalculate();
    }

    //this method rounds the total value of the account
    public void accountRound() {
        //Ceil round to the top
        txtTotal.setText(replace(String.valueOf(Math.ceil(Float.valueOf(txtTotal.getText().toString())))));
        totalDinerRound();
        txtDiners.setText("1");
    }

    //this method rounds the total value of the diner
    public void totalDinerRound() {
        //Ceil round to the top
        txtTotalDiner.setText(replace(String.valueOf(Math.ceil(Float.valueOf(txtTotalDiner.getText().toString())))));
    }

    //this method calculate the total value of the account
    public void accountCalculate() {

        //Check txt in cardView account
        if (!TextUtils.isEmpty(txtAccount.getText().toString()) && !TextUtils.isEmpty(txtTipPercentage.getText().toString())) {
            //We calculate the tip and total, we control that are only two decimals and calculate cardView diners
            porcentageCalculate();
            txtTotal.setText(replace(String.format("%.2f", (Float.parseFloat(txtAccount.getText().toString()) + Float.parseFloat(txtTip.getText().toString())))));
            totalDinerCalculate();
            btnRoundOutAccount.setEnabled(true);
        } else
            btnRoundOutAccount.setEnabled(false);
    }

    //this method calculate the total value of the diner
    public void totalDinerCalculate() {

        //We check the empty txt
        if (!TextUtils.isEmpty(txtAccount.getText().toString()) && !TextUtils.isEmpty(txtTipPercentage.getText().toString()) && !TextUtils.isEmpty(txtDiners.getText().toString())) {
            //Calculate total for diner
            txtTotalDiner.setText(replace(String.format("%.2f", (Float.parseFloat(txtTotal.getText().toString()) / Integer.parseInt(txtDiners.getText().toString())))));
            btnRoundOutDiners.setEnabled(true);
        } else
            btnRoundOutDiners.setEnabled(false);
    }

    public void porcentageCalculate() {
        txtTip.setText(replace(String.format("%.2f", (Float.parseFloat(txtAccount.getText().toString()) * Float.parseFloat(txtTipPercentage.getText().toString()) / 100))));
    }

    //replace them , for .
    @NonNull
    private String replace(String valor) {
        return valor.replace(',', '.');
    }

}
