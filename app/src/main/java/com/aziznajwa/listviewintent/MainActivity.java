package com.aziznajwa.listviewintent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;

import androidx.appcompat.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    ListView listView;
    Intent i1;
    int code;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });
        listView=(ListView)findViewById(R.id.listView);
        final ArrayList<HashMap<String,String>> listitems =new ArrayList<>();
        HashMap<String,String> map =new HashMap<>();
        map.put("titre","Word");
        map.put("description","Editeur de Text");
        map.put("img",String.valueOf(R.drawable.word));
        listitems.add(map);

        map =new HashMap<>();
        map.put("titre","Excel");
        map.put("description","Tableur");
        map.put("img",String.valueOf(R.drawable.excel));
        listitems.add(map);

        map =new HashMap<>();
        map.put("titre","Power Point");
        map.put("description","Logiciel de Presentation");
        map.put("img",String.valueOf(R.drawable.powerpoint));
        listitems.add(map);

        map =new HashMap<>();
        map.put("titre","Outlook");
        map.put("description","Client de courrir electronique");
        map.put("img",String.valueOf(R.drawable.outlook));
        listitems.add(map);

        final SimpleAdapter adapter = new SimpleAdapter(this.getBaseContext(),
                listitems,
                R.layout.items,
                new String[]{"img","titre","description"},
                new int[]{R.id.image_view,R.id.text_view1,R.id.textView2}
        );
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String,String> map = (HashMap<String,String>) listView.getItemAtPosition(position);
                Bundle b= new Bundle();
                b.putString("titre",map.get("titre"));
                b.putString("desc",map.get("description"));
                Intent i = new Intent(getApplicationContext(), QuestionActivity.class);
                i.putExtras(b);
                code=1;
                startActivityForResult(i,code);
            }
        });
        i1=getIntent();
        if(i1!=null) {
            Bundle b=i1.getBundleExtra("bundel");
            String title=b.getString("title");
            String rep=b.getString("rep");
            final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Votre Reponse");
            if(rep=="oui")
            {
                builder.setMessage("vous utilise " +title);
            }
            else
            {
                builder.setMessage("vous n'utilise pas " +title);
            }
            builder.setPositiveButton("ok", null);
            builder.show();
        }


    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==code)
        {
            
        }
    }
}