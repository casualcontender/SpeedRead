package edu.csumb.mattw.speedread;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.Timestamp;
import java.util.Calendar;


public class RegisterActivity extends ActionBarActivity  {

    DataHelperAdapter dataHelperAdapter;
    private EditText userNameView;
    private EditText passwordView;
    Database database = Database.getInstance();
    boolean pWordInvalid = false;
    boolean userInvalid = false;
    int numFails = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dataHelperAdapter = new DataHelperAdapter(this);

        //createDatabase();

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

    public void createDatabase() {
        addUser("a@lice5", "@csit100");
        addUser("$brian7", "123abc##");
        addUser("!chris12!", "CHRIS12!!");

    }

    public void attemptLogin() {
        View focusView = null;

        String newAcct = "New Account";
        String success = "Account Created Successfully";
        String fail = "Failed Account Creation";

        //Alert for Account creation success
        AlertDialog.Builder createAcctBuilder = new AlertDialog.Builder(RegisterActivity.this);
        createAcctBuilder.setMessage(R.string.success_message);
        createAcctBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                returnToMain();
            }
        });
        AlertDialog createAcctDialog = createAcctBuilder.create();

        //Alert for invalid username
        AlertDialog.Builder invUserNameBuilder = new AlertDialog.Builder(RegisterActivity.this);
        invUserNameBuilder.setMessage(R.string.invalid_username_message);
        invUserNameBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //finish();
            }
        });
        AlertDialog invUsernameDialog = invUserNameBuilder.create();

        //Alert for taken username
        AlertDialog.Builder takenUsernameBuilder = new AlertDialog.Builder(RegisterActivity.this);
        takenUsernameBuilder.setMessage(R.string.username_taken_message);
        takenUsernameBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //finish();
            }
        });
        AlertDialog takenUsernameDialog = takenUsernameBuilder.create();

        //Alert for invalid password
        AlertDialog.Builder invPasswordBuilder = new AlertDialog.Builder(RegisterActivity.this);
        invPasswordBuilder.setMessage(R.string.invalid_password_message);
        invPasswordBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //finish();
            }
        });
        AlertDialog invPasswordDialog = invPasswordBuilder.create();


        // Store values at the time of the login attempt.
        String email = userNameView.getText().toString();
        String password = passwordView.getText().toString();

        // Reset errors.
        userNameView.setError(null);
        passwordView.setError(null);

        // Check for a valid password, if the user entered one.
        if (!isPasswordValid(password)) {
            pWordInvalid = true;
            invPasswordDialog.show();
        }

        boolean emailTaken = emailTaken(email);
        boolean emailValid = isEmailValid(email);

        if (emailTaken || !emailValid) {
            userInvalid = true;
            if (emailTaken) {
                takenUsernameDialog.show();
            } else {
                invPasswordBuilder.show();
            }
        }


        if (pWordInvalid == false && userInvalid == false) {
            //addUser(email, password);
            database.userHashMap.put(email,password);
            addToLog(newAcct);
            createAcctDialog.show();
        }

        if (pWordInvalid == true || userInvalid == true) {
            numFails++;
            pWordInvalid = userInvalid = false;
        }

        if (numFails ==2) {
            Toast.makeText(getBaseContext(), fail, Toast.LENGTH_LONG).show();
            returnToMain();
        }
    }

    private void returnToMain() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
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

    private boolean emailTaken(String email) {
        if (database.userHashMap.containsKey(email)) {
            return true;
        } return false;
    }

    private boolean isEmailValid(String email) {

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

    public void addUser(String user, String password) {
        //String user = userNameView.getText().toString();
        //String password = passwordView.getText().toString();

        long id = dataHelperAdapter.insertData(user, password);
        if(id<0) {
            Message.message(this, "");
        } else {
            Message.message(this, "Successfully Inserted a Row");
        }
    }

    public void viewDetails(View view) {
        String data = dataHelperAdapter.getAllData();
        Message.message(this, data);
    }

    public void addToLog(String event) {
        Calendar calendar = Calendar.getInstance();
        java.sql.Date currentTimestamp = new java.sql.Date(calendar.getTime().getTime());
        database.log.add(event + currentTimestamp);
    }
}
