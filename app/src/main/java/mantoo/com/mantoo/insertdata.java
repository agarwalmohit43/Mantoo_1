package mantoo.com.mantoo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by MOHIT AGARWAL on 12-07-2017.
 */

public class insertdata extends SQLiteOpenHelper {

    private Context context;

    private static final String DATABASE_NAME="mantoo";
    private static final int DATABASE_VERSION=4;

    private static final String CREATE_TABLE="CREATE TABLE parties (id TEXT PRIMARY KEY NOT NULL,mantooId TEXT NOT NULL,name TEXT NOT NULL UNIQUE,address TEXT,phoneNumber TEXT,dueAmount DECIMAL(10,5) NOT NULL DEFAULT 0,createdAt INTEGER,updatedAt INTEGER);";
    private static final String DROP_TABLE="DROP TABLE  IF EXISTS parties";

    insertdata(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
        Toast.makeText(context,"Constructor Called of insertdata",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try{
            sqLiteDatabase.execSQL(CREATE_TABLE);
            Message.message(context,"onCreate Called");
        }catch (Exception e){
            //e.printStackTrace();
            Message.message(context,""+e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try{
            Message.message(context,"onUpgrade Called");
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);

        }catch (Exception e){
            Message.message(context,""+e);
        }
    }
}
