package com.example.toshiba.bohnanzagamestate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity  {
    TextView testTextEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testTextEdit = (TextView)findViewById(R.id.testText);
        Button runTestButton = (Button)findViewById(R.id.testButton);
        runTestButton.setOnClickListener(new buttonListener());
    }


    private class buttonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Card myCard = new Card("blueBean");
            Button runTest = (Button) v;
            String buttonLabel = (String) runTest.getText();

            if (buttonLabel.equalsIgnoreCase("Run Test")) {
                testTextEdit.setText("hello");

                BohnanzaState firstInstance = new BohnanzaState();
                BohnanzaPlayerState[] firstInstancePL = firstInstance.getPlayerList();
                BohnanzaState secondInstance = new BohnanzaState(firstInstance, 0);

                firstInstancePL[0].setCoins(10);
                firstInstance.buyThirdField(0);

                firstInstance.plantBean(0, 1, myCard, firstInstancePL[0].getHand());

                firstInstance.harvestField(0, firstInstancePL[0].getField(0));

                firstInstance.turn2Cards(0);

                firstInstance.startTrading(0);

                firstInstance.makeOffer(0);

                firstInstance.abstainFromTrading(0);

                firstInstance.acceptOffer(0);

                firstInstance.draw3Cards(0);

                testTextEdit.append(firstInstance.toString());


            }

        }
    }
}

