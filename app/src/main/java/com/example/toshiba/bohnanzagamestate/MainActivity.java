package com.example.toshiba.bohnanzagamestate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView testTextEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testTextEdit = (TextView)findViewById(R.id.testText);
    }


    @Override
    public void onClick(View v) {
        Card myCard = new Card(blueBean);
        Button runTest = (Button)v;
        String buttonLabel = (String)runTest.getText();

        if (buttonLabel.equalsIgnoreCase("Run Test"))
        {
            testTextEdit.clearComposingText();

            BohnanzaState firstInstance  = new BohnanzaState();

            BohnanzaState secondInstance = new BohnanzaState(firstInstance, 0);

            firstInstance.buyThirdField(0);
            //firstInstance.plantBean(0, 1, firstInstance.playerList, neew a deck here)
            //firstInstance.harvestField(0, need deck here);
            firstInstance.turn2Cards(0);
            firstInstance.


        }

    }
}
