package edu.csumb.mattw.speedread;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Matt on 5/9/2015.
 */
public class Message {
    public static void message(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
