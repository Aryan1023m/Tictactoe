package com.example.tietactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Addplayers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_addplayers);
        EditText PLAYERONE = findViewById(R.id.PLAYERONE);
        EditText PLAYERTWO = findViewById(R.id.PLAYERTWO);
        EditText rounds = findViewById(R.id.rounds);
        Button btnst = findViewById(R.id.btnst);

        btnst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getPLAYERONEname = PLAYERONE.getText().toString();
                String getPLAYERTWOname = PLAYERTWO.getText().toString();
                int getrounds = Integer.parseInt(rounds.getText().toString());


                if(getPLAYERONEname.isEmpty() || getPLAYERTWOname.isEmpty()){
                    Toast.makeText(Addplayers.this , "Please enter player name and rounds", Toast.LENGTH_SHORT ).show();
                }
                else {
                    Intent intent = new Intent(Addplayers.this, MainActivity.class);
                    intent.putExtra("PLAYERONE",getPLAYERONEname) ;
                    intent.putExtra("PLAYERTWO", getPLAYERTWOname);
                    intent.putExtra("Rounds",getrounds);
                    startActivity(intent);
                }
            }
        });
    }
}