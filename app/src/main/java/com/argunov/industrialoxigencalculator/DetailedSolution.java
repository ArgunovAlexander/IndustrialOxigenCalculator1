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

public class DetailedSolution extends AppCompatActivity {
    private double FurnaceOxyConc;
    private double airFlow;
    private double oxyFlow;
    private double oxyConc;
    private double airDissipation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_solution);
    }
    public void onCalcDissipation(View view) {
        FurnaceOxyConc=getFurnaceOxyConc();
        airFlow=getIntent().getDoubleExtra("airFlow",100);
        oxyConc=getIntent().getDoubleExtra("oxyConc",25);
        oxyFlow=getIntent().getDoubleExtra("oxyFlow",15);
        airDissipation=CalcOxy.calcDissipation(airFlow,oxyFlow,FurnaceOxyConc,
                CalcOxy.OXYGEN_IN_AIR_CONC_BY_VOL,CalcOxy.OXYGEN_PURITY);
        printOxygenConcentration();

    }

    private double getFurnaceOxyConc() {
        EditText furnaceOxyConc=findViewById(R.id.inputOxyConc);
        hideKeyboard();
        return isEmpty(furnaceOxyConc)?0:Double.valueOf(furnaceOxyConc.getText().toString());
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

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void printOxygenConcentration(){
        String message="Итак:\n";
            message+=format("Расчитанное содержание кислорода (если бы не было потерь воздуха)"+
                            "\n%.1f\n", oxyConc);
            message+=format("Расход воздуха заданный"+"\n%.0f\n",airFlow);
            message+=format("Фактическое содержание кислорода (на ДП)"+"\n%.1f\n",FurnaceOxyConc);
            message+=format("Расход кислорода на ТВД(ТК)"+"\n%.1f\n",oxyFlow);
            message+=format("Потери воздуха"+"\n%.1f\n",airDissipation);
        TextView textView=findViewById(R.id.outputData);
        textView.setText(message);
    }
}
