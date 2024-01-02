package com.example.todoapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todoapp.Model.UserModel;
import com.example.todoapp.Utils.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameEditText = findViewById(R.id.editTextUsername);
        passwordEditText = findViewById(R.id.editTextPassword);
        databaseHelper = new DatabaseHelper(this);

        Button registerButton = findViewById(R.id.buttonRegister);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performRegistration();
            }
        });
    }

    private void performRegistration() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString();

        // Validate username and password (add your validation logic here)

        // Check if the username is already taken
//        if (databaseHelper.isUsernameTaken(username)) {
//            Toast.makeText(this, "Username is already taken. Choose a different one.", Toast.LENGTH_SHORT).show();
//            return;
//        }

        // Validate username and password
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if the username is already taken
        if (databaseHelper.isUsernameTaken(username)) {
            Toast.makeText(this, "Username is already taken. Choose a different one.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a UserModel instance and insert into the database
        UserModel user = new UserModel();
        user.setUsername(username);
        user.setPassword(password);

        databaseHelper.insertUser(user);

        // Provide feedback to the user (e.g., show a Toast)
        Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();

        // Optionally, navigate to the login screen or main activity
        // For simplicity, let's finish the registration activity
        finish();
    }
}
