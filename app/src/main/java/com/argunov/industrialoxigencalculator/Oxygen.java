package com.argunov.industrialoxigencalculator;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import android.view.View;

import static java.lang.String.format;


class Oxygen {
    //double oxyInAir=getIntent().getDoubleExtra("oxyInAir",20.7);
    //double oxyPurity=getIntent().getDoubleExtra("oxyPur",99.5);
    private double oxyConc;
    private double oxyFlow;
    private double oxyInAir;
    private double oxyPurity;
    private double airFlow;
    private double furnaceOxyConc;
    Oxygen() {
        this.airFlow=0;
        this.oxyConc=0;
        this.oxyFlow=0;
        this.oxyInAir=0;
        this.oxyPurity=0;
        this.furnaceOxyConc=0;
    }
    Oxygen(double oxyConc,double oxyFlow,double oxyInAir, double oxyPurity,
           double airFlow, double furnaceOxyConc){
        this.airFlow=airFlow;
        this.oxyConc=oxyConc;
        this.oxyFlow=oxyFlow;
        this.oxyInAir=oxyInAir;
        this.oxyPurity=oxyPurity;
        this.furnaceOxyConc=furnaceOxyConc;
    }

    double calcOxyFlow(EditText oxyConcEtTxt, EditText airFlowEtTxt) {
        oxyConc=setParam(oxyConcEtTxt);
        airFlow=setParam(airFlowEtTxt);
        return (airFlow*(oxyConc-oxyInAir))/(oxyPurity-oxyInAir);
    }

    double calcOxyConc(EditText oxyFlowEtTxt, EditText airFlowEtTxt) {
        oxyFlow=setParam(oxyFlowEtTxt);
        airFlow=setParam(airFlowEtTxt);
        double oxygenInAir=airFlow*oxyInAir/100;
        double oxygenInOxygen=oxyFlow*oxyPurity/100;
        return (oxygenInAir+oxygenInOxygen)/(airFlow+oxyFlow)*100;
    }

    double calcAirDissipation (EditText furnaceOxyConcEtTxt) {
        furnaceOxyConc=setParam(furnaceOxyConcEtTxt);
        return oxyFlow*(oxyPurity-oxyInAir)/(furnaceOxyConc-oxyInAir)-airFlow;
    }

    double setParam(EditText editText) {
        return isEmpty(editText) ? 0 :Double.valueOf(editText.getText().toString()) ;
    }

    boolean isEmpty(EditText editText) {
        if (editText.getText().toString().trim().length() > 0) {
            return false;
        } else {
            dispToast();
            return true;
        }
    }

    void dispToast() {
        //Toast.makeText(this, getString(R.string.message), Toast.LENGTH_LONG ).show();
    }

    void printParam(TextView textView, String formattedMessage, double param){
        hideKeyboard();
        String message=format(Locale.US,formattedMessage, param);
        textView.setText(message);
    }

    void hideKeyboard() {
//        View view = this.getCurrentFocus();
//        if (view != null) {
//            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//        }
    }
}