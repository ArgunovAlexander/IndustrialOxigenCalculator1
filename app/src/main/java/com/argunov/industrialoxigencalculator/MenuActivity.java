package com.argunov.industrialoxigencalculator;
        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.view.inputmethod.InputMethodManager;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import java.util.Locale;
        import static java.lang.String.format;

public class MenuActivity extends Activity {
    EditText inputOxyPurity;
    EditText inputOxyInAirPerc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        inputOxyPurity=findViewById(R.id.inputOxyPurity);
        inputOxyInAirPerc=findViewById(R.id.inputOxyInAirPerc);

        TextView incrOxyInAir=findViewById(R.id.incrOxyInAir);
        TextView decrOxyInAir=findViewById(R.id.decrOxyInAir);
        TextView incrOxyPurity=findViewById(R.id.incrOxyPurity);
        TextView decrOxyPurity=findViewById(R.id.decrOxyPurity);

        TextView toDefaultOxyPurity=findViewById(R.id.toDefaultOxyPurity);
        TextView toDefaultOxyInAir=findViewById(R.id.toDefaultOxyInAir);

        TextView oxyFlow=findViewById(R.id.oxyFlow);
        TextView oxyConc=findViewById(R.id.oxyConc);

        incrOxyInAir.setOnClickListener(new StepperInputListener(inputOxyInAirPerc,0.1d,"%.1f"));
        decrOxyInAir.setOnClickListener(new StepperInputListener(inputOxyInAirPerc,-0.1d,"%.1f"));
        incrOxyPurity.setOnClickListener(new StepperInputListener(inputOxyPurity,0.1d,"%.1f"));
        decrOxyPurity.setOnClickListener(new StepperInputListener(inputOxyPurity,-0.1d,"%.1f"));

        toDefaultOxyPurity.setOnClickListener(new ToDefaultListener(inputOxyPurity,"99.5"));
        toDefaultOxyInAir.setOnClickListener(new ToDefaultListener(inputOxyInAirPerc,"20.7"));

        oxyFlow.setOnClickListener(new ButtonsListener(this, Variant1.class));
        oxyConc.setOnClickListener(new ButtonsListener(this, Variant2.class));
    }

    class StepperInputListener implements View.OnClickListener {
        EditText editText;
        double step;
        String format;
        StepperInputListener (EditText editText, double step, String format) {
            this.editText=editText;
            this.step=step;
            this.format=format;
        }
        public void onClick(View view){
            incrementView();
        }
        private void incrementView() {
            hideKeyboard();
            double value=Double.valueOf(editText.getText().toString());
            value+=step;
            editText.setText(format(Locale.US,format,value));
        }
    }

    class ButtonsListener implements View.OnClickListener {
        Class<?> cls;
        Context packageContext;
        ButtonsListener (Context packageContext,Class<?> cls) {
            this.cls=cls;
            this.packageContext=packageContext;
        }
        public void onClick(View view){
            double oxyPur=Double.valueOf(inputOxyPurity.getText().toString());
            double oxyPer=Double.valueOf(inputOxyInAirPerc.getText().toString());
            TextView warningOxyPurity=findViewById(R.id.warning_oxyPurity);
            TextView warningOxyInAir=findViewById(R.id.warning_oxyInAir);
            warningOxyInAir.setText("");
            warningOxyPurity.setText("");

             if (isCorrect(oxyPur,20,99.5f, warningOxyPurity)&&isCorrect(oxyPer,20,22, warningOxyInAir)) {
                Intent intent = new Intent(packageContext, cls);
                intent.putExtra("oxyPurity", oxyPur)
                        .putExtra("oxyInAir", oxyPer);
                startActivity(intent);
            }
        }
        private boolean isCorrect(double value, double min, double max, TextView textView) {
            if (value<min||value>max) {
                textView.setText(format(Locale.US,"Введите число между %1$.1f и %2$.1f ",min,max));
                return false;
            }
            return true;
        }
    }

    class ToDefaultListener implements View.OnClickListener{
        EditText editText=null;
        String value="";
        ToDefaultListener(EditText editText,String value) {
            this.editText=editText;
            this.value=value;
        }
        public void onClick(View view) {
            editText.setText(value);
        }
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}