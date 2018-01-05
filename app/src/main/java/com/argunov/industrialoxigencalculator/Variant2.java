//enriched air oxygen concentration

package com.argunov.industrialoxigencalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.String.format;

public class Variant2 extends AppCompatActivity {
    private double airFlowIv;
    private double oxyFlowIv;
    private double oxyConcIv;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variant2);
        findViewById(R.id.calcMore).setEnabled(false);
        findViewById(R.id.variant2).setEnabled(true);

    }

    public void onCalcOxyConc(View view) {
        double air=getAirFlow();
        double oxygen=getOxygenFlow();
        double oxygenConcentration=CalcOxy.calcEnrichAirOxyConc(air,oxygen,CalcOxy.OXYGEN_IN_AIR_CONC_BY_VOL,CalcOxy.OXYGEN_PURITY);
        printOxygenConcentration(oxygenConcentration,air,oxygen);
        airFlowIv=air;
        oxyFlowIv=oxygen;
        oxyConcIv=oxygenConcentration;
     }

    private void printOxygenConcentration(double oxygenConcentration, double airFlow , double oxygenFlow){
        String message=format(getString(R.string.enrich_air_oxy_conc)+"\n%.0f\n",oxygenConcentration);
        message+=format(getString(R.string.air_flow)+"\n%.0f\n",airFlow);
        message+=format(getString(R.string.oxy_flow)+"\n%.0f\n",oxygenFlow);
        TextView textView=findViewById(R.id.outputData);
        textView.setText(message);
        findViewById(R.id.calcMore).setEnabled(true);
        findViewById(R.id.variant2).setEnabled(false);

    }

    private double getOxygenFlow() {
        EditText oxygenFlow=findViewById(R.id.inputOxygenFlow);
        return isEmpty(oxygenFlow)?0:Double.valueOf(oxygenFlow.getText().toString());
        }

    private double getAirFlow() {
        EditText FurnaceAirFlow=findViewById(R.id.inputBlastFurnaceAirFlow);
        return isEmpty(FurnaceAirFlow)?0:Double.valueOf(FurnaceAirFlow.getText().toString());
    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0) {
            return false;
        } else {
            dispToast();
            return true;
        }
    }

    private void dispToast() {
        Toast.makeText(this, getString(R.string.message), Toast.LENGTH_LONG ).show();
    }

    public void onCalcMore(View view) {
        Intent intent = new Intent(this, DetailedSolution.class);
        intent.putExtra("airFlow",airFlowIv)
              .putExtra("oxyFlow",oxyFlowIv)
              .putExtra("oxyConc",oxyConcIv);
        startActivity(intent);
    }
}
