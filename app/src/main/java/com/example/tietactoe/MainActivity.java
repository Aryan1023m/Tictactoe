package com.example.tietactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tietactoe.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private final List<int[]> combinationList = new ArrayList<>();
    private int[] boxposition = {0,0,0,0,0,0,0,0,0};
    private int playerTurn = 1;
    private int totalSelectedBoxes =1;

    TextView p1points;
    TextView P2points;
    int P1 = 0, P2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        p1points = findViewById(R.id.p1points);
        P2points = findViewById(R.id.p2points);





        combinationList.add(new int[]{0,1,2});
        combinationList.add(new int[]{3,4,5});
        combinationList.add(new int[]{6,7,8});
        combinationList.add(new int[]{0,3,6});
        combinationList.add(new int[]{1,4,7});
        combinationList.add(new int[]{2,5,8});
        combinationList.add(new int[]{2,4,6});
        combinationList.add(new int[]{0,4,8});

        String getPlayerOneName = getIntent().getStringExtra("PLAYERONE");
        String getPlayerTwoName = getIntent().getStringExtra("PLAYERTWO");



        binding.PlayerOneName.setText(getPlayerOneName);
        binding.Player2Name.setText(getPlayerTwoName);

        binding.image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if (isBoxSelectable(0)){
                     performAction((ImageView) v, 0 );
                 }
            }
        });
        binding.image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBoxSelectable(1)){
                    performAction((ImageView) v, 1);
                }
            }
        });
        binding.image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBoxSelectable(2)){
                    performAction((ImageView) v, 2);
                }
            }
        });
        binding.image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBoxSelectable(3)){
                    performAction((ImageView) v, 3 );
                }
            }
        });
        binding.image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBoxSelectable(4)){
                    performAction((ImageView) v, 4 );
                }
            }
        });
        binding.image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBoxSelectable(5)){
                    performAction((ImageView) v, 5 );
                }
            }
        });
        binding.image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBoxSelectable(6)){
                    performAction((ImageView) v, 6 );
                }
            }
        });
        binding.image8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBoxSelectable(7)){
                    performAction((ImageView) v, 7 );
                }
            }
        });
        binding.image9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBoxSelectable(8)){
                    performAction((ImageView) v, 8 );
                }
            }
        });

    }
    private void performAction(ImageView imageView, int selectedBoxPosition) {
        boxposition[selectedBoxPosition] = playerTurn;
        if (playerTurn == 1) {
            imageView.setImageResource(R.drawable.cross3);
            if (checkResult()) {
                    ResultDIalog resultDIalog = new ResultDIalog(MainActivity.this, binding.PlayerOneName.getText().toString() + " is a Winner!", MainActivity.this);
                    resultDIalog.setCancelable(false);
                    resultDIalog.show();
                    P1++;
                    p1points.setText(String.valueOf(P1));



            } else if (totalSelectedBoxes == 9) {
                ResultDIalog resultDIalog = new ResultDIalog(MainActivity.this, "Match Draw!", MainActivity.this);
                resultDIalog.setCancelable(false);
                resultDIalog.show();
            } else {
                changePlayerTurn(2);
                totalSelectedBoxes++;
            }
            }else {
            imageView.setImageResource(R.drawable.zero2);
            if (checkResult()) {
                    ResultDIalog resultDIalog = new ResultDIalog(MainActivity.this, binding.Player2Name.getText().toString() + " is a Winner!", MainActivity.this);
                    resultDIalog.setCancelable(false);
                    resultDIalog.show();
                    P2++;
                    P2points.setText(String.valueOf(P2));


            } else if (totalSelectedBoxes == 9) {
                ResultDIalog resultDIalog = new ResultDIalog(MainActivity.this, "Match Draw!", MainActivity.this);
                resultDIalog.setCancelable(false);
                resultDIalog.show();
            } else {
                changePlayerTurn(1);
                totalSelectedBoxes++;
            }
        }
    }
    private void changePlayerTurn(int currentPlayerTurn){
        playerTurn = currentPlayerTurn;
        if(playerTurn == 1){
            binding.playerOneLayout.setBackgroundResource(R.drawable.black_border);
            binding.playerTwoLayout.setBackgroundResource(R.drawable.white_box);
        }
        else{
            binding.playerTwoLayout.setBackgroundResource(R.drawable.black_border);
            binding.playerOneLayout.setBackgroundResource(R.drawable.white_box);
        }
    }
    private boolean checkResult(){
        boolean response = false;
        for(int i = 0; i < combinationList.size(); i++){
            final int[] combination = combinationList.get(i);

            if(boxposition[combination[0]] == playerTurn && boxposition[combination[1]] == playerTurn && boxposition[combination[2]] == playerTurn){
                response = true; 
            }
        }
        return response;
    }

    private boolean isBoxSelectable(int boxPosition){
        boolean response = false;
        if(boxposition[boxPosition] == 0){
            response = true;
        }
        return response;
    }

    void restartMatch(){
        boxposition = new int[] {0,0,0,0,0,0,0,0,0};
        playerTurn = 1;
        totalSelectedBoxes = 1;


        binding.image1.setImageResource(R.drawable.white_box);
        binding.image2.setImageResource(R.drawable.white_box);
        binding.image3.setImageResource(R.drawable.white_box);
        binding.image4.setImageResource(R.drawable.white_box);
        binding.image5.setImageResource(R.drawable.white_box);
        binding.image6.setImageResource(R.drawable.white_box);
        binding.image7.setImageResource(R.drawable.white_box);
        binding.image8.setImageResource(R.drawable.white_box);
        binding.image9.setImageResource(R.drawable.white_box);

    }
}