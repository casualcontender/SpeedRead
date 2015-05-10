package edu.csumb.mattw.speedread;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ManageLog extends ActionBarActivity {

    Database database = Database.getInstance();
    private TextView logTextView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_log);
        logTextView = (TextView)findViewById(R.id.log_text);
        logTextView.setMovementMethod(new ScrollingMovementMethod());

        StringBuilder sb = new StringBuilder();

        for (UserLog u: database.log) {
            sb.append("Transaction Type: " + u.getEvent() + "\n");
            sb.append("Customer's username: " + u.getUserName() + "\n");
            sb.append("Transaction date: " + u.getDateStamp() + "\n");
            sb.append("Transaction time: " + u.getTime() + "\n\n");
        }
        String logData = sb.toString();

        logTextView.setText(logData);

        Button okButton = (Button) findViewById(R.id.ok_button);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), ManageMain.class);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_manage_main, menu);
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
