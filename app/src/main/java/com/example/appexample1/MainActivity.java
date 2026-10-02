package com.example.appexample1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * MainActivity is the login screen of the app.
 * It is the entry point declared in AndroidManifest.xml with the MAIN/LAUNCHER intent filter,
 * meaning this is the first screen the user sees when the app is opened.
 */
public class MainActivity extends AppCompatActivity {
    TextView openSignUpPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        // Enable Edge-to-Edge display so the app content draws behind the system bars
        // (status bar and navigation bar), giving a modern full-screen look.
        EdgeToEdge.enable(this);

        // Inflate and set the login screen layout (activity_main.xml) as the UI for this activity.
        setContentView(R.layout.activity_main);

        // Apply padding to the root view equal to the system bar heights.
        // This prevents the login form content from being hidden behind
        // the status bar at the top or the navigation bar at the bottom.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        openSignUpPage = findViewById(R.id.textToSignUp);

        openSignUpPage.setOnClickListener(
                view ->{
                    Intent openSignUpPageIntent = new Intent(MainActivity.this, signup.class);
                    startActivity(openSignUpPageIntent);
                }
        );
    }
}