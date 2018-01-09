//find oxygen flow

package com.argunov.industrialoxigencalculator;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import static java.lang.String.format;

public class Variant1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variant1);
    }

    public void onCalcOxyFlow(View view) {
        double air=getAirFlow();
        double oxyConc=getOxyConc();
        double oxyFlow=CalcOxy.calculateOxygenFlow(air,oxyConc,CalcOxy.OXYGEN_IN_AIR_CONC_BY_VOL,CalcOxy.OXYGEN_PURITY);
        printOxyFlow(oxyConc,air,oxyFlow);
    }

    private void printOxyFlow(double oxygenConcentration, double airFlow , double oxygenFlow){
        String message=format(getString(R.string.oxy_flow)+"\n%.1f\n",oxygenFlow);
        message+=format(getString(R.string.air_flow)+"\n%.0f\n",airFlow);
        message+=format(getString(R.string.enrich_air_oxy_conc)+"\n%.1f\n",oxygenConcentration);
        TextView textView=findViewById(R.id.outputData);
        textView.setText(message);
    }

    private double getOxyConc() {
        hideKeyboard();
        EditText oxyConc = findViewById(R.id.inputEnrichAirOxyConc);
        return isEmpty(oxyConc) ? 0 :Double.valueOf(oxyConc.getText().toString()) ;
    }

    private double getAirFlow() {
        EditText FurnaceAirFlow=findViewById(R.id.inputBlastFurnaceAirFlow);
        return isEmpty(FurnaceAirFlow)? 0 :Double.valueOf(FurnaceAirFlow.getText().toString());
    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0) {
            return false;
        } else {
            dispToast();
            return true;
        }
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void dispToast() {
        Toast.makeText(this, getString(R.string.message), Toast.LENGTH_LONG ).show();
    }

}
