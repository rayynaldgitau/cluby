package com.example.appexample1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;

/**
 * signup is the registration screen of the app.
 * It collects new user details (email, password, phone number, gender)
 * and is navigated to from MainActivity when the user taps "Don't have an account? Sign up".
 *
 * Note: By Android convention, Activity class names should be PascalCase (e.g. SignupActivity).
 */
public class signup extends AppCompatActivity {
    EditText signupEmail, signupPassword;
    Button signupButton;
    FirebaseAuth signupFirebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable Edge-to-Edge display so the signup form draws behind the system bars,
        // giving a modern full-screen look consistent with MainActivity.
        EdgeToEdge.enable(this);

        // Inflate and set the signup screen layout (activity_sign_up.xml) as the UI.
        setContentView(R.layout.activity_sign_up);

        // Apply padding to the root view equal to the system bar heights.
        // This ensures the form fields and buttons are not obscured by the
        // status bar at the top or the navigation bar at the bottom.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Link textToLogin: tapping "Already have an account? Login" finishes this
        // Activity and returns the user to the login screen (MainActivity).
        TextView textToLogin = findViewById(R.id.textToLogin);
        textToLogin.setOnClickListener(v -> finish());

        //initialize the login
        signupEmail = findViewById(R.id.emailSignup);
        signupPassword = findViewById(R.id.signupPassword);
        signupButton = findViewById(R.id.buttonLogin);
        signupFirebase = FirebaseAuth.getInstance();
        signupButton.setOnClickListener(view -> signUp());
    }
    private void signUp(){
        String email = signupEmail.getText().toString().trim();
        String password = signupPassword.getText().toString().trim();
        signupFirebase.createUserWithEmailAndPassword( email, password ).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                Intent openLoginIntent = new Intent(signup.this, MainActivity.class);
                startActivity(openLoginIntent);

                Toast.makeText(this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                signupEmail.setText("");
                signupPassword.setText("");
            }
        });

    }
}