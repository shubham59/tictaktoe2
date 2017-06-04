package com.example.shubhamtripathi.tictaktoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.transition.Slide;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {




    int activeplayer = 0; //0 for zero and 1 for cross

    int[] gamestate ={2,2,2,2,2,2,2,2,2};

    int counterchances = 0;

    int winnerstatus =0;

    int[][] winningpositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,6,8},{2,4,6}};

    public void dropIn(View view){



        ImageView counter = (ImageView) view;

        int tappedcounter = Integer.parseInt(counter.getTag().toString());

        if(gamestate[tappedcounter]==2) {

            gamestate[tappedcounter] = activeplayer;

            counter.setTranslationY(-1000f);

            if (activeplayer == 0) {

                counter.setImageResource(R.drawable.zero);
                counter.animate().translationYBy(1000f).setDuration(300);
                counterchances++;

                activeplayer = 1;
            } else

            {

                counter.setImageResource(R.drawable.cross1);
                counter.animate().translationYBy(1000f).setDuration(300);
                activeplayer = 0;
                counterchances++;
            }

            for(int[] winningposition : winningpositions){





                if(gamestate[winningposition[0]]== gamestate[winningposition[1]]
                && gamestate[winningposition[1]]== gamestate[winningposition[2]] && gamestate[winningposition[0]]!=2){

                    Toast.makeText(this, "Wow!! You are Smart", Toast.LENGTH_SHORT).show();
                     String winner = "Cross";
                     winnerstatus = 1;

                    if(gamestate[winningposition[0]]==0){
                        winner = "Zero";

                    }


                    TextView winnermessage = (TextView) findViewById(R.id.textView);

                    winnermessage.setText("Player with " + winner +" won");

                    LinearLayout layout = (LinearLayout) findViewById(R.id.linearlayout);

                    layout.setVisibility(View.VISIBLE);
                    layout.animate().rotation(360f).setDuration(1000);

                    


                }

            }

        }

        if(counterchances==9 && winnerstatus==0)
        {
            System.out.println("None has won");

            TextView noone =(TextView) findViewById(R.id.textView);
            noone.setText("Noone has won");
            LinearLayout layoutlosser =(LinearLayout) findViewById(R.id.linearlayout);
            layoutlosser.setVisibility(View.VISIBLE);
            layoutlosser.animate().rotation(360f).setDuration(1000);

        }

    }

    public void playagain(View view){

        counterchances = 0;

        winnerstatus =0;

        LinearLayout layout = (LinearLayout) findViewById(R.id.linearlayout);

        layout.setVisibility(View.INVISIBLE);

        activeplayer = 0;

        for(int i=0;i<gamestate.length;i++)
        {
            gamestate[i]=2;
        }

        GridLayout gridlayout = (GridLayout) findViewById(R.id.gridlayout);

        for(int j=0;j<gridlayout.getChildCount();j++)
        {
            ((ImageView) gridlayout.getChildAt(j)).setImageResource(0);
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
