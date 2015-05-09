package edu.csumb.mattw.speedread;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends ActionBarActivity {

    private EditText userNameView;
    private EditText passwordView;
    Database database = Database.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up the login form.
        userNameView = (EditText) findViewById(R.id.email);
        passwordView = (EditText) findViewById(R.id.password);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

    }

    public void attemptLogin() {
        View focusView = null;
        boolean invalid = false;
        int numFails = 0;

        // Store values at the time of the login attempt.
        String email = userNameView.getText().toString();
        String password = passwordView.getText().toString();

        if (invalid == true) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }

        // Reset errors.
        userNameView.setError(null);
        passwordView.setError(null);

        // Check for a valid password, if the user entered one.
        if (!isPasswordValid(password)) {
            passwordView.setError(getString(R.string.error_invalid_password));
            focusView = passwordView;
            invalid = true;
        }

        if (!isEmailValid(email)) {
            userNameView.setError("Invalid Username");
            focusView = userNameView;
            invalid = true;
        }

        if (invalid == false) {
            database.userHashMap.put(email, password);
            Toast.makeText(getBaseContext(), "Account Created Successfully", Toast.LENGTH_LONG).show();
        }


    }

    private void returnToMain() {

    }

    private boolean isPasswordValid(String password) {
        int alpha = 0;
        int num = 0;
        int special = 0;
        char temp;

        for (int i = 0; i < password.length(); i++) {
            temp = password.charAt(i);
            if (temp >= 48 && temp <=57) {
                num++;
            }
            if (temp >= 33 && temp <= 47) {
                special++;
            }
            if ((temp >= 65 && temp <= 90) || (temp >= 97 && temp <= 122)) {
               alpha++;
            }
        }

        return ((alpha >= 3) && (num >=1) && (special >=1));
    }

    private boolean isEmailValid(String email) {
        if (database.userHashMap.containsKey(email)) {
            return false;
        }
        int alpha = 0;
        int num = 0;
        int special = 0;
        char temp;
        for (int i = 0; i < email.length(); i++) {
            temp = email.charAt(i);
            if (temp >= 48 && temp <=57) {
                num++;
            }
            if (temp >= 33 && temp <= 47) {
                special++;
            }
            if ((temp >= 65 && temp <= 90) || (temp >= 97 && temp <= 122)) {
                alpha++;
            }
        }
        return ((alpha >= 3) && (num >=1) && (special >=1));
    }
}
