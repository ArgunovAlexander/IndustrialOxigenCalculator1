package com.argunov.industrialoxigencalculator;
        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.view.inputmethod.InputMethodManager;
        import android.widget.EditText;
        import android.widget.TextView;
        import java.util.Locale;
        import static java.lang.String.format;

public class MenuActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        TextView incrOxyInAir=findViewById(R.id.incrOxyInAir);
        incrOxyInAir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increment((EditText) findViewById(R.id.inputOxyInAirPerc),0.1d,"%.1f");
            }
        });


        TextView decrOxyInAir=findViewById(R.id.decrOxyInAir);
        decrOxyInAir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increment((EditText) findViewById(R.id.inputOxyInAirPerc),-0.1d,"%.1f");
            }
        });

        TextView incrOxyPurity=findViewById(R.id.incrOxyPurity);
        incrOxyPurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increment((EditText) findViewById(R.id.inputOxyPurity),0.1d,"%.1f");
            }
        });


        TextView decrOxyPurity=findViewById(R.id.decrOxyPurity);
        decrOxyPurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increment((EditText) findViewById(R.id.inputOxyPurity),-0.1d,"%.1f");
            }
        });

    }

    public void onFindOxyFlow(View view) {
        EditText edit1=(EditText)findViewById(R.id.inputOxyPurity);
        EditText edit2=findViewById(R.id.inputOxyInAirPerc);
        double oxyPur=Double.valueOf(edit1.getText().toString());
        double oxyPer=Double.valueOf(edit2.getText().toString());
        Intent intent = new Intent(this, Variant1.class);
        intent.putExtra("oxyPurity", oxyPur)
                .putExtra("oxyInAir", oxyPer);
        startActivity(intent);
    }

    public void onFindOxyConc(View view) {
        EditText edit1=findViewById(R.id.inputOxyPurity);
        EditText edit2=findViewById(R.id.inputOxyInAirPerc);
        double oxyPur=Double.valueOf(edit1.getText().toString());
        double oxyPer=Double.valueOf(edit2.getText().toString());
        Intent intent = new Intent(this, Variant2.class);
        intent.putExtra("oxyPurity", oxyPur)
                .putExtra("oxyInAir", oxyPer);
        startActivity(intent);
    }

    private void increment(EditText editText, double step, String format) {
        hideKeyboard();
        double value=Double.valueOf(editText.getText().toString());
        value+=step;
        editText.setText(format(Locale.US,format,value));
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}