package mantoo.com.mantoo;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by MOHIT AGARWAL on 12-07-2017.
 */

public class Message {
    public static void message(Context c, String m){
        Toast.makeText(c,m,Toast.LENGTH_SHORT).show();
    }
}
