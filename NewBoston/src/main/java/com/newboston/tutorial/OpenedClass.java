package com.newboston.tutorial;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by Chris on 17.09.13.
 */
public class OpenedClass extends Activity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    TextView question, test;
    RadioGroup selectionList;
    Button returnData;
    String gotBread,setData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send);
        init();

        SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String et = getData.getString("name","Travis is..");
        String values = getData.getString("list", "4");
        if(values.contentEquals("1"))
            question.setText(et);

        //Bundle gotBasket = getIntent().getExtras();
        //gotBread = gotBasket.getString("key");
        //question.setText(gotBread);

    }

    private void init() {
        question = (TextView) findViewById(R.id.tvQuestion);
        test = (TextView) findViewById(R.id.tvText);
        selectionList = (RadioGroup) findViewById(R.id.rgAnswers);
        returnData = (Button) findViewById(R.id.bReturn);
        returnData.setOnClickListener(this);
        selectionList.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent person = new Intent();
        Bundle backpack = new Bundle();
        backpack.putString("answer",setData);
        person.putExtras(backpack);
        setResult(RESULT_OK, person);
        finish();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch(checkedId){
            case R.id.rCrazy:
                setData = "Probably right!";
                break;
            case R.id.rMad:
                setData = "right";
                break;
            case R.id.rGenius:
                setData = ":P";
                break;
        }
        test.setText(setData);
    }
}
