package com.argunov.industrialoxigencalculator;
        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;

public class MenuActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
//find oxygen flow
    public void onFindOxyFlow(View view) {
        Intent intent = new Intent(this, Variant1.class);
        startActivity(intent);
    }
//find
    public void onFindOxyConc(View view) {
        Intent intent = new Intent(this, Variant2.class);
        startActivity(intent);
    }
}