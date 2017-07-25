package mantoo.com.mantoo;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class MainActivity extends Activity implements OnItemClickListener {

    private DrawerLayout drawerLayout;
    private ListView listView;
    private ActionBarDrawerToggle drawerToggle;
    private Mycustomadapter adapterCustom;

    ArrayList<String> alPartyData;

    ArrayList<String> drawerMenu=new ArrayList<String>();

    EditText ev1,ev2;
    insertdata insertdataObj;
    outerclass outerclassObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       ev1=(EditText) findViewById(R.id.sqlliteinsertuname);
       ev2=(EditText) findViewById(R.id.sqlliteinsertpass);

        drawerMenu.add("Parties");
        drawerMenu.add("xyz");

        outerclassObj=new outerclass(this);

        alPartyData=new ArrayList<String>();
        alPartyData=outerclassObj.getPartyComb();



        for(String Data:alPartyData){
           // Message.message(this,Data);
        }

        listView=(ListView) findViewById(R.id.appdrawerlistview);
        // listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,weeks));
        adapterCustom=new Mycustomadapter(this);
        listView.setAdapter(adapterCustom);
        listView.setOnItemClickListener(this);



        drawerLayout = (DrawerLayout) findViewById(R.id.appdrawer_drawerLayout);
        drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.drawable.dr,R.string.drawer_open,R.string.drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                // Toast.makeText(MainActivity.this,"Drawer Opened",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
               // Toast.makeText(MainActivity.this,"Drawer Closed",Toast.LENGTH_SHORT).show();
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);






       insertdataObj=new insertdata(this);
        SQLiteDatabase db=insertdataObj.getWritableDatabase();






    }


  /*  public void insertpartyData(View view){

           ContentValues contentValues=new ContentValues();
        UUID partyId = UUID.randomUUID();
        UUID mantooId = UUID.randomUUID();

        Date today=new Date();
        long millisecond=System.currentTimeMillis();

        contentValues.put("id",partyId.toString());
        contentValues.put("mantooId",mantooId.toString());
        contentValues.put("name","Mohit(8981871984)");
        contentValues.put("address","Pune");
        contentValues.put("phoneNumber","+918981871984");
        contentValues.put("dueAmount",15000.5);
        contentValues.put("createdAt",millisecond);
        contentValues.put("updatedAt",millisecond);

        long id=outerclassObj.insertPartydata(contentValues);
        if(id>0){
            mantoo.com.mantoo.Message.message(this,"Successfull");
        }else{
            mantoo.com.mantoo.Message.message(this,"Un-Successfull");
        }

    }*/

    public void sqlliteinsertadduser(View view){

        String username=ev1.getText().toString();
        String password=ev2.getText().toString();
        double amnt=10.5;

        long id= outerclassObj.insertsData(username,password,amnt);
        if(id>0){
            mantoo.com.mantoo.Message.message(this,"Successfull");
        }else{
            mantoo.com.mantoo.Message.message(this,"Un-Successfull");
        }
    }

  public void sqlliteinsertshowdetails(View view){

       /* String data=outerclassObj.getData();
        if(data.equals(" ")){
            Message.message(this,"No Data");
        }else{
            Message.message(this,data);
        }*/

       Intent in=new Intent(this,partiesdata.class);
        startActivity(in);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = null, chooser = null;
        switch (i) {
            case 0:
                intent = new Intent(android.content.Intent.ACTION_VIEW);
                intent.setData(Uri
                        .parse("geo:22.630906200000000000,88.436148000000000000"));
                startActivity(intent);

                drawerLayout.closeDrawers();
                break;
        }
    }

    private void selectItem(int i) {
        listView.setItemChecked(i,true);
    }



    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState)//3line image
    {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }
}


class Mycustomadapter extends BaseAdapter {

    private Context context;
    String[] week;
    int[] images={R.drawable.dr,R.drawable.dr,R.drawable.dr,R.drawable.dr,R.drawable.dr,R.drawable.dr,R.drawable.dr};;
    MainActivity obj=new MainActivity();

    //ArrayList<String> week;
    public Mycustomadapter(Context context){
        this.context=context;
        week=context.getResources().getStringArray(R.array.Week);
      //this.week=obj;

    }
    @Override
    public int getCount() {
        return week.length;
    }

    @Override
    public Object getItem(int i) {
        return week[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        View row=null;

        if(view == null)
        {
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.appdrawer_custom_listviewdesign_items,viewGroup,false);

        }else{
            row=view;
        }
        TextView tv=(TextView) row.findViewById(R.id.appdrawer_custom_listViewdesign_textview);
        ImageView iv=(ImageView) row.findViewById(R.id.appdrawer_custom_listViewdesign_imageView);


        tv.setText(week[i]);
        iv.setImageResource(images[i]);
        return row;
    }
}
