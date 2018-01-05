package com.argunov.industrialoxigencalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DetailedSolution extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_solution);
    }
    public void onCalcDissipation(View view) {
        double airFlow=getIntent().getDoubleExtra("airFlow",0);
        double oxyFlow=getIntent().getDoubleExtra("oxyFlow",0);
        double oxyConc=getIntent().getDoubleExtra("oxyConc",0);

    }
}
