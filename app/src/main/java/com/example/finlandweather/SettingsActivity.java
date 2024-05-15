package com.example.finlandweather;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    Button btnAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        btnAlert = (Button) findViewById(R.id.btnAlert);
        btnAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialogue();
            }
        });


        ImageButton homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(view -> {

            Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
            startActivity(intent);
        });



        ImageButton quizButton = findViewById(R.id.quizbutton);

        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });
    }

    public void showToast(View view) {
        if (view.getId() == R.id.locationLabel || view.getId() == R.id.languageLabel || view.getId() == R.id.DegreeLabel || view.getId() == R.id.feedback) {
            Toast.makeText(this, "Tap On Info!", Toast.LENGTH_SHORT).show();
        }
    }


    private void showAlertDialogue() {
        AlertDialog.Builder builder =  new AlertDialog.Builder(this);
        builder.setTitle("Important😢");
        builder.setCancelable(false);
        builder.setMessage("Unfortunately, " +
                "It is not possible to change anything in the settings " +
                "and everything is set to default. However, this will change in upcoming update.");


        builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
