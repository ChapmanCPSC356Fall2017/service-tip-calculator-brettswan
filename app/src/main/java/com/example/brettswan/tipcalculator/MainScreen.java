package com.example.brettswan.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainScreen extends AppCompatActivity {

    private EditText totalEditText;
    private SeekBar ratingSeekBar;
    private TextView recommendedTipTextView;
    private TextView ratingTextView;
    private TextView finalTotalTextView;
    private int rating;
    private double tipValue;
    private double totalAmount;
    private String strippedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        //reference objects on the screen
        this.totalEditText = (EditText) findViewById(R.id.total_ET);
        this.ratingSeekBar = (SeekBar) findViewById(R.id.rating_SB);
        this.recommendedTipTextView = (TextView) findViewById(R.id.recommendedTip);
        this.ratingTextView = (TextView) findViewById(R.id.ratingView);
        this.finalTotalTextView = (TextView) findViewById(R.id.finalTotal);

        ratingTextView.setText(Integer.toString(ratingSeekBar.getProgress())+"/10");

        this.totalEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                rating = ratingSeekBar.getProgress();
                strippedText = charSequence.toString().replaceAll("$","");
                if (!strippedText.equals("") && !strippedText.equals(".")) {
                    totalAmount = Double.parseDouble(strippedText);
                    //                1<=score<=3 yields a 10% tip
                    //                4<=score<=5 yields a 13% tip
                    //                6<=score<=7 yields a 15% tip
                    //                8<=score<=9 yields a 20% tip
                    //                score==10 yields a 25% tip

                    if (rating < 4) {
                        tipValue = totalAmount * .1;
                    }

                    if ((rating > 3) && (rating < 6)) {
                        tipValue = totalAmount * .13;
                    }

                    if ((rating > 5) && (rating < 8)) {
                        tipValue = totalAmount * .15;
                    }

                    if ((rating > 7) && (rating < 10)) {
                        tipValue = totalAmount * .2;
                    }
                    if (rating == 10) {
                        tipValue = totalAmount * .25;
                    }

                    recommendedTipTextView.setText("Recommended tip amount is: $" + Double.toString((double) Math.round(tipValue * 100) / 100));
                    finalTotalTextView.setText("Your total bill is: $" + (Double.parseDouble(totalEditText.getText().toString()) + (double) Math.round(tipValue * 100) / 100));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
//                totalEditText.setText(setTexttotalEditText.getText().toString());
            }
        });
        this.ratingSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                rating = i;
                ratingTextView.setText(Integer.toString(i)+"/10");
                strippedText = totalEditText.getText().toString().replaceAll("$","");
                if (!strippedText.equals("") && !strippedText.equals(".")) {
                    totalAmount = Double.parseDouble(strippedText);
                    //                1<=score<=3 yields a 10% tip
                    //                4<=score<=5 yields a 13% tip
                    //                6<=score<=7 yields a 15% tip
                    //                8<=score<=9 yields a 20% tip
                    //                score==10 yields a 25% tip

                    if (rating < 4) {
                        tipValue = totalAmount * .1;
                    }

                    if ((rating > 3) && (rating < 6)) {
                        tipValue = totalAmount * .13;
                    }

                    if ((rating > 5) && (rating < 8)) {
                        tipValue = totalAmount * .15;
                    }

                    if ((rating > 7) && (rating < 10)) {
                        tipValue = totalAmount * .2;
                    }
                    if (rating == 10) {
                        tipValue = totalAmount * .25;
                    }

                    recommendedTipTextView.setText("Recommended tip amount is: $" + Double.toString((double) Math.round(tipValue * 100) / 100));
                    finalTotalTextView.setText("Your total bill is: $" + (Double.toString((double) Math.round((Double.parseDouble(totalEditText.getText().toString()) + tipValue) * 100) / 100)));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
