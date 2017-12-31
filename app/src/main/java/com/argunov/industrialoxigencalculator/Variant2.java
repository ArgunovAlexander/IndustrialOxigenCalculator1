//enriched air oxygen concentration

package com.argunov.industrialoxigencalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
        String message=format(R.string.enrich_air_oxy_conc+"%1$.0f"+R.string.air_flow+"%2$.0f"+R.string.oxy_flow+"%3$.0f",oxygenConcentration,airFlow,oxygenFlow);
        TextView textView=findViewById(R.id.outputData);
        textView.setText(message);
    }
    private double getOxygenFlow() {
        EditText oxygenFlow=findViewById(R.id.inputOxygenFlow);
        return Double.valueOf(oxygenFlow.getText().toString());
        }
    private double getAirFlow() {
        EditText FurnaceAirFlow=findViewById(R.id.inputBlastFurnaceAirFlow);
        return Double.valueOf(FurnaceAirFlow.getText().toString());
    }
}
