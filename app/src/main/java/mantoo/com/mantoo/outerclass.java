package mantoo.com.mantoo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by MOHIT AGARWAL on 12-07-2017.
 */

public class outerclass {

    insertdata insertdataObj;

    outerclass(Context context){
        insertdataObj=new insertdata(context);
    }

    public long insertsData(String name,String password,Double amount){
        SQLiteDatabase db=insertdataObj.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
       /* contentValues.put("name",name);
        contentValues.put("password",password);
        contentValues.put("Salary",amount);*/

        contentValues.put("id","id");
        contentValues.put("mantooId","mantooId");
        contentValues.put("partyId","partyId");
        contentValues.put("amount",10.5);
        contentValues.put("dueDate",123);
        contentValues.put("referenceId","referenceId");
        contentValues.put("balance",20.6);
        contentValues.put("createdAt",12);
        contentValues.put("updatedAt",45);




        //long id=db.insert("mohit",null,contentValues);

        long id=db.insert("duePayments",null,contentValues);
        return id;
    }

    public long insertPartydata(ContentValues contentValues){
        SQLiteDatabase db=insertdataObj.getWritableDatabase();

        long id=db.insert("parties",null,contentValues);
        return id;
    }


    public String getData(){
        SQLiteDatabase db=insertdataObj.getWritableDatabase();

        //String[] columns={"_id","name","password","Salary"};
        String[] columns={"id","mantooId","partyId","amount","dueDate"};

        // Cursor cursor=db.query("mohit",columns,null,null,null,null,null);
        Cursor cursor=db.query("duePayments",columns,null,null,null,null,null);

        StringBuffer buffer=new StringBuffer();
        while(cursor.moveToNext()){
            String custId=cursor.getString(0);
            String name=cursor.getString(1);
            String password=cursor.getString(2);
            Double amount=cursor.getDouble(3);
            String numb=cursor.getString(4);
            buffer.append(custId+"\t"+name+"\t"+password+"\t"+amount+"\t"+numb+"\n");
        }

        return buffer.toString();
    }

    public String getPartyData(){

        SQLiteDatabase db=insertdataObj.getWritableDatabase();

        String[] columns={"id","mantooId","name","address","phoneNumber","dueAmount","createdAt","updatedAt"};
        Cursor cursor=db.query("parties",columns,null,null,null,null,null);

        StringBuffer buffer=new StringBuffer();
        while(cursor.moveToNext()){
            long milli=cursor.getInt(6);
            String name=cursor.getString(2);
            String partyId=cursor.getString(0);
            String mantooId=cursor.getString(1);
            Date obj=new Date(milli);
            buffer.append(partyId+"\t"+mantooId+"\t"+name+"\t"+obj.toString());
        }


        return buffer.toString();

    }

    public long insertPartydatasss(){
        SQLiteDatabase db=insertdataObj.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.beginTransaction();

        try {
            db.setTransactionSuccessful();

            }

         finally{
            db.endTransaction();
        }



        long id=db.insert("parties",null,values);
        return id;
    }

    public ArrayList<String> getPartyComb(){
        SQLiteDatabase db=insertdataObj.getWritableDatabase();
        ArrayList<String> alParty=new ArrayList<String>();

        String[] columns={"id","mantooId","name","address","phoneNumber","dueAmount","createdAt","updatedAt"};
        Cursor cursor=db.query("parties",columns,null,null,null,null,null);

        StringBuffer buffer=new StringBuffer();
        while(cursor.moveToNext()){
            long milli=cursor.getInt(6);
            String name=cursor.getString(2);
            String partyId=cursor.getString(0);
            String mantooId=cursor.getString(1);
            Date obj=new Date(milli);
           // buffer.append(partyId+"\t"+mantooId+"\t"+name+"\t"+obj.toString());
            //buffer.append(name);
            alParty.add(name);
        }

        return alParty;
    }
}
