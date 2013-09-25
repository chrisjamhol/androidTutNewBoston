package com.newboston.tutorial;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Region;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Chris on 17.09.13.
 */
public class Data extends Activity implements View.OnClickListener {
    EditText sendET;
    Button start, startFor;
    TextView gotAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get);
        init();
    }

    private void init() {
        sendET = (EditText) findViewById(R.id.etSend);
        start = (Button) findViewById(R.id.bSA);
        startFor = (Button) findViewById(R.id.bSAFR);
        gotAnswer = (TextView) findViewById(R.id.tvGot);

        start.setOnClickListener(this);
        startFor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bSA:
                onBSAClicked();
                break;
            case R.id.bSAFR:
                onBSAFRClicked();
                break;
        }
    }

    private void onBSAFRClicked() {
        startActivityForResult(new Intent(Data.this,OpenedClass.class),0);
    }

    private void onBSAClicked() {
        String bread = sendET.getText().toString();
        Bundle basket = new Bundle();
        basket.putString("key",bread);
        Intent a = new Intent(Data.this, OpenedClass.class);
        a.putExtras(basket);
        startActivity(a);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Bundle basket = data.getExtras();
            String s = basket.getString("answer");
            gotAnswer.setText(s);
        }
    }
}
