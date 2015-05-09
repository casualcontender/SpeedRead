package edu.csumb.mattw.speedread;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Click listener for createAcctButton
        View createAcctButton = findViewById(R.id.createAcctButton);
        createAcctButton.setOnClickListener(this);

        //Click listener for createAcctButton
        View placeHoldButton = findViewById(R.id.placeHoldButton);
        placeHoldButton.setOnClickListener(this);

        //Click listener for createAcctButton
        View cancelHoldButton = findViewById(R.id.cancelHoldButton);
        cancelHoldButton.setOnClickListener(this);

        //Click listener for createAcctButton
        View manageSystemButton = findViewById(R.id.manageSystemButton);
        manageSystemButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.createAcctButton) {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }
        if (v.getId() == R.id.placeHoldButton) {
            Intent i = new Intent(this, HoldActivity.class);
            startActivity(i);
        }
        if (v.getId() == R.id.cancelHoldButton) {
            Intent i = new Intent(this, HoldActivity.class);
            startActivity(i);
        }
        if (v.getId() == R.id.manageSystemButton) {
            Intent i = new Intent(this, ManageActivity.class);
            startActivity(i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
