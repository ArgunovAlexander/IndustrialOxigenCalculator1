//find oxygen flow

package com.argunov.industrialoxigencalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;
import static java.lang.String.format;

import java.util.Locale;



public class Variant1 extends AppCompatActivity {

    EditText inputOxyConc = null;
    EditText inputAirFlow = null;
    TextView onCalcOxyFlow = null;
    TextView outputData = null;
    Oxygen o=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variant1);

        TextView incrOxyPer=findViewById(R.id.incrOxyPer);
        TextView decrOxyPer =  findViewById(R.id.decrOxyPer);
        TextView incrAirFlow =  findViewById(R.id.incrAirFlow);
        TextView decrAirFlow =  findViewById(R.id.decrAirFlow);

        inputOxyConc =  findViewById(R.id.inputOxyConc);
        inputAirFlow =  findViewById(R.id.inputAirFlow);
        onCalcOxyFlow =  findViewById(R.id.onCalcOxyFlow);
        outputData =  findViewById(R.id.outputData);

        incrOxyPer.setOnClickListener(new StepperInputListener(inputOxyConc, 0.1d, "%.1f"));
        decrOxyPer.setOnClickListener(new StepperInputListener(inputOxyConc, -0.1d, "%.1f"));
        incrAirFlow.setOnClickListener(new StepperInputListener(inputAirFlow, 5, "%.0f"));
        decrAirFlow.setOnClickListener(new StepperInputListener(inputAirFlow, -5, "%.0f"));

        onCalcOxyFlow.setOnClickListener(new OnClickListener() {
        public void onClick (View view){
            double oxyInAir = getIntent().getDoubleExtra("oxyInAir", 20.7);
            double oxyPurity = getIntent().getDoubleExtra("oxyPur", 99.5);
            double oxyConc = Oxygen.setParam(inputOxyConc);
            double airFlow = Oxygen.setParam(inputAirFlow);
            Oxygen o = new Oxygen.Builder()
                    .oxyPurity(oxyPurity)
                    .oxyInAir(oxyInAir)
                    .oxyConc(oxyConc)
                    .airFlow(airFlow)
                    .build();
            o.printParam(outputData, "Расход кислорода = %.1f", o.calcOxyFlow());
        }
    });
}
    class StepperInputListener implements OnClickListener {
        EditText editText;
        double step;
        String format;
        StepperInputListener (EditText editText, double step, String format) {
            this.editText=editText;
            this.step=step;
            this.format=format;
        }
        public void onClick(View view){
            //o.hideKeyboard();
            incrementView();
        }
        private void incrementView() {
            double value=Double.valueOf(editText.getText().toString());
            value+=step;
            editText.setText(format(Locale.US,format,value));
        }
    }
}