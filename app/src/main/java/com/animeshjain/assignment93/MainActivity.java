package com.animeshjain.assignment93;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    //    Declaring Arrays for names and phoneNumber
    String[] names=
            {"ABC","DEF","GHI","JKL","MNO","PQR","STU","VWX","YZ1"};
    String[] contactNo=
            {"123","234","345","456","567","678","789","890","012"};
    int x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=(ListView)findViewById(R.id.listView);
        CustomAdapter customAdapter= new CustomAdapter();
        listView.setAdapter(customAdapter);

//        Assigining ContextMenu to the listView
        registerForContextMenu(listView);

    }

//    Creating ContextMenu by using onCreateContextMenu:: then meu.add()
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

//        Setting a Title to for the ContextMenu
        menu.setHeaderTitle("Select The Action");
//        Adding MenuItems in the ContextMenu
//        using:  menu.add(groupId,itemId, itemPosition, itemName);
        menu.add(0,0,1,"SMS");
        menu.add(0,1,1,"Call");
    }

//    Setting actions when any ContextMenu Item is Selected using onContextMenuItemSelected
    @Override
    public boolean onContextItemSelected(MenuItem item) {

//        Getting position of the listItem on which ContextMenu is called/created
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
       int  position = info.position;

        switch (item.getItemId()){
            case 0:{

                Toast.makeText(this, "You selected the SMS Option", Toast.LENGTH_SHORT).show();
//                Making Intent to Open the Messaging Service and Setting the Recipient's ContactNumber
//                and a Hi Message to Selected listItem
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("sms:"));
                i.putExtra("address",contactNo[position]);
                i.putExtra("sms_body","HI!! " + names[position]);
                startActivity(i);
            }break;
            case 1:{
                Toast.makeText(this, "You Selected the Call Option", Toast.LENGTH_SHORT).show();
//                Making Intent to Open Dialer service with the Contact Number of selected listItem
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:"+contactNo[position]));
                startActivity(i);
            }break;
        }

        return super.onContextItemSelected(item);
    }

//    Creating SubClass to implement BaseAdapted
    class CustomAdapter extends BaseAdapter{


        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view=getLayoutInflater().inflate(R.layout.row, null);
            TextView nameRow= (TextView)view.findViewById(R.id.nameRow);
            TextView phoneRow= (TextView)view.findViewById(R.id.phoneRow);

//            Setting COntent to the Fields in CustomAdapter
            nameRow.setText(names[i]);
            phoneRow.setText(contactNo[i]);

            return view;
        }
    }
}
