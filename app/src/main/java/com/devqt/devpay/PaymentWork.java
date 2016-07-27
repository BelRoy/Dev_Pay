package com.devqt.devpay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class PaymentWork extends AppCompatActivity {

    private static final String AMOUNT = "AMOUNT";
    private static final String CUSTOM_PERCENT = "CUSTOM_PERCENT";

    private double currentAmount;
    private int currentCustomPercent;
    private EditText tip10;
    private EditText total10;
    private EditText tip15;
    private EditText total15;
    private EditText amountText;
    private EditText tip20;
    private EditText total20;
    private TextView customTip;
    private TextView tipCustomEdit;
    private EditText totalCustomEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_work);

        if (savedInstanceState == null){
            currentAmount = 0.0;
            currentCustomPercent = 0;
        }
        else {
            currentAmount = savedInstanceState.getDouble(AMOUNT);
            currentCustomPercent = savedInstanceState.getInt(CUSTOM_PERCENT);
        }


        total10 = (EditText) findViewById(R.id.total10);

        total15 = (EditText) findViewById(R.id.total15);

        total20 = (EditText) findViewById(R.id.total20);

        customTip = (TextView) findViewById(R.id.custom_tip);

        tipCustomEdit = (EditText) findViewById(R.id.tip_custom_edit);
        totalCustomEdit = (EditText) findViewById(R.id.total_custom_edit);

        amountText = (EditText) findViewById(R.id.amount_text);

        amountText.addTextChangedListener(amountTextWatcher);

        SeekBar customSeekBar = (SeekBar) findViewById(R.id.custom_seekbar);
        customSeekBar.setOnSeekBarChangeListener(customSeekBarListener);
    }

    private void StandartParameters(){


        double tenPercentTotal = currentAmount * 10;


        total10.setText(String.format("%.02f", tenPercentTotal));



        double fifteenPercentTotal = currentAmount * 15;


        total15.setText(String.format("%.02f", fifteenPercentTotal));



        double twentyPercentTotal = currentAmount * 20;



        total20.setText(String.format("%.02f", twentyPercentTotal));
    }
    private void InstateCustom(){

        customTip.setText(currentCustomPercent + "кол.");


        double customTipAmount = currentAmount * 10 + currentCustomPercent;

        double customTotalAmount = currentAmount + customTipAmount;

        tipCustomEdit.setText(String.format("%.02f", customTipAmount));
        totalCustomEdit.setText(String.format("%.02f", customTotalAmount));

    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        outState.putDouble(AMOUNT, currentAmount);
        outState.putInt(CUSTOM_PERCENT, currentCustomPercent);
    }

    private OnSeekBarChangeListener customSeekBarListener = new OnSeekBarChangeListener(){


        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){

            currentCustomPercent = seekBar.getProgress();
            InstateCustom();
        }

        @Override public void onStartTrackingTouch(SeekBar seekBar){

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar){

        }
    };


    private TextWatcher amountTextWatcher = new TextWatcher()
    {

        @Override
        public void afterTextChanged(Editable s) {


        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            try{
                currentAmount = Double.parseDouble(s.toString());
            }
            catch(NumberFormatException e){
                currentAmount = 0.0;
            }

            StandartParameters();
            InstateCustom();
        }

    };
}

