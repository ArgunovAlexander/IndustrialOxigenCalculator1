package com.argunov.industrialoxigencalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.String.format;

public class DetailedSolution extends AppCompatActivity {
    double FurnaceOxyConc;
    double airFlow;
    double oxyFlow;
    double oxyConc;
    double airDissipation;
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
        airDissipation=CalcOxy.calcDissipation(airFlow,oxyFlow,oxyConc,FurnaceOxyConc,
                CalcOxy.OXYGEN_IN_AIR_CONC_BY_VOL,CalcOxy.OXYGEN_PURITY);
        printOxygenConcentration();

    }

    private double getFurnaceOxyConc() {
        EditText FurnaceOxyConc=findViewById(R.id.inputOxyConc);
        return isEmpty(FurnaceOxyConc)?0:Double.valueOf(FurnaceOxyConc.getText().toString());
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

    private void printOxygenConcentration(){
        String message="Рассчитанные данные";
            message+=format("Расчитанное содержание кислорода (если бы не было потерь воздуха)"+
                            "\n%.1f\n", oxyConc);
            message+=format("Расход воздуха заданный"+"\n%.0f\n",airFlow);
            message+=format("Фактическое содержание кислорода (на ДП)"+"\n%.1f\n",FurnaceOxyConc);
            message+=format("Потери воздуха"+"\n%.1f\n",airDissipation);
        TextView textView=findViewById(R.id.calcMore);
        textView.setText(message);
    }
}
