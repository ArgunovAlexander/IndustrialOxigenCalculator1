//enriched air oxygen concentration

package com.argunov.industrialoxigencalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.String.format;

public class Variant2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variant2);
    }

    public void onCalculateVariant2(View view) {
        double air=getAirFlow();
        double oxygen=getOxygenFlow();
        double oxygenConcentration=CalcOxy.calcEnrichAirOxyConc(air,oxygen,CalcOxy.OXYGEN_IN_AIR_CONC_BY_VOL,CalcOxy.OXYGEN_PURITY);
        printOxygenConcentration(oxygenConcentration,air,oxygen);
     }

    private void printOxygenConcentration(double oxygenConcentration, double airFlow , double oxygenFlow){
        String message=format(getString(R.string.enrich_air_oxy_conc)+"\n%.0f\n",oxygenConcentration);
        message+=format(getString(R.string.air_flow)+"\n%.0f\n",airFlow);
        message+=format(getString(R.string.oxy_flow)+"\n%.0f\n",oxygenFlow);
        TextView textView=findViewById(R.id.outputData);
        textView.setText(message);
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
}
