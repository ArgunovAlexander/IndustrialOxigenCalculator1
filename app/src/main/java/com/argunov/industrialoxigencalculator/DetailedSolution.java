package com.argunov.industrialoxigencalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static java.lang.String.format;

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
        printOxygenConcentration(oxyConc,airFlow,oxyFlow);

    }
    private void printOxygenConcentration(double oxygenConcentration, double airFlow , double oxygenFlow){
        String message="Рассчитанные данные";
            message+=format(getString(R.string.enrich_air_oxy_conc)+"\n%.0f\n",oxygenConcentration);
            message+=format(getString(R.string.air_flow)+"\n%.0f\n",airFlow);
            message+=format(getString(R.string.oxy_flow)+"\n%.0f\n",oxygenFlow);
        TextView textView=findViewById(R.id.calcMore);
        textView.setText(message);
    }
}
