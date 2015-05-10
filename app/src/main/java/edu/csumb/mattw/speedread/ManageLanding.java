package edu.csumb.mattw.speedread;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class ManageLanding extends ActionBarActivity {
    private EditText userNameView;
    private EditText passwordView;
    int attempt = 0;
    private static Context context;
    Database database = Database.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_landing);

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
        // Store values at the time of the login attempt.
        String userName = userNameView.getText().toString();
        String password = passwordView.getText().toString();
        Activity getActivity;

        //Alert for wrong username/password
        AlertDialog.Builder wrongLoginBuilder = new AlertDialog.Builder(context);
        wrongLoginBuilder.setMessage(R.string.wrong_username_message);
        wrongLoginBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //finish();
            }
        });
        AlertDialog takenUsernameDialog = wrongLoginBuilder.create();


        if (database.adminHashMap.containsKey(userName) && database.adminHashMap.get(userName).equals(password)) {
            Intent i = new Intent(this, ManageMain.class);
            startActivity(i);
        } else if (attempt < 1) {
            attempt++;
            wrongLoginBuilder.show();
        } else {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_manage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
