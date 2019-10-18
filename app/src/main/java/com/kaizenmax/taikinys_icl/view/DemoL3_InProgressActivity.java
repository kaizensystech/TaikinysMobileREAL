package com.kaizenmax.taikinys_icl.view;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.kaizenmax.taikinys_icl.R;
import com.kaizenmax.taikinys_icl.presenter.DemoL3ActivityPresenter;
import com.kaizenmax.taikinys_icl.presenter.DemoL3ActivityPresenterInterface;
import com.kaizenmax.taikinys_icl.util.DemoL3ListItem;
import com.kaizenmax.taikinys_icl.util.MyAdapter_ForDemoL3IdList;

import java.util.ArrayList;
import java.util.List;

public class DemoL3_InProgressActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private  static DemoL3_InProgressActivity instance;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    int count =0;

    private List<DemoL3ListItem> listItems;

    DemoL3ActivityPresenterInterface demoL3ActivityPresenterInterface ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_l3__in_progress);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show(); */


               Intent intent = new Intent(DemoL3_InProgressActivity.this, DemoL3Activity.class);
               startActivity(intent);
               finish();

            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        instance = this;


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView. setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        instance = this;

        listItems = new ArrayList<>();


        demoL3ActivityPresenterInterface = new DemoL3ActivityPresenter();


       // Cursor cursor = demoL3ActivityPresenterInterface.getDemoL3DataFromID();


        Cursor cursor = null;
        try {
            listItems = demoL3ActivityPresenterInterface.getAll_Incompleted_DemoL3Data();
        } catch (Exception e) {
            e.printStackTrace();
        }





/*        Integer id = 3000;

        Integer permanentId = 5000;





        for(int i = 0; i <=20; i++){

            id++;
            permanentId++;
            String tempId = "TEMP"+id;

            String permanentId_String = permanentId.toString();

            DemoL3ListItem listItem = new DemoL3ListItem( null,  null,  tempId,
                    permanentId_String,  null,  null);

            listItems.add(listItem);

        }  */

        adapter = new MyAdapter_ForDemoL3IdList(listItems, this);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL));

        recyclerView.setAdapter(adapter);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.demo_l3__in_progress, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
    /*    if (id == R.id.action_settings) {
            return true;
        } */

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.ifc) {
            // IFC Activity clicked by user


            Intent intent = new Intent(DemoL3_InProgressActivity.this, IndividualFarmerContactMainActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();


        } else if (id == R.id.fm) {
            Intent intent = new Intent(DemoL3_InProgressActivity.this, FarmerMeetingActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();

        } else if (id == R.id.fd) {

            Intent intent = new Intent(DemoL3_InProgressActivity.this, FieldDayActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();

        } else if (id == R.id.dv) {
            Intent intent = new Intent(DemoL3_InProgressActivity.this, DiagnosticVisitActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();

        }

        else if(id==R.id.mc){
            Intent intent = new Intent(DemoL3_InProgressActivity.this, MandiCampaignActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();
        }

        else if (id == R.id.demol3) {

            Intent intent = new Intent(DemoL3_InProgressActivity.this, DemoL3Activity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();
        }


        else if (id == R.id.demol3_progress) {

            Intent intent = new Intent(DemoL3_InProgressActivity.this, DemoL3_InProgressActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static DemoL3_InProgressActivity getInstance()
    {
        return instance;
    }


    public  void protocol_Intent(String tempId) {

        Intent intent = new Intent(DemoL3_InProgressActivity.getInstance(), DemoL3Activity_Protocol.class);
        intent.putExtra("DEMO_L3_SERIAL_ID", tempId);

        startActivity(intent);

    }




    public void execution_Intent(String tempId) {
        Intent intent = new Intent(DemoL3_InProgressActivity.getInstance(), DemoL3Activity_Execution.class);
        intent.putExtra("DEMO_L3_SERIAL_ID", tempId);
        startActivity(intent);
    }

    public void interim_Intent(String tempId) {
        Intent intent = new Intent(DemoL3_InProgressActivity.getInstance(), DemoL3_InterimResult.class);
        intent.putExtra("DEMO_L3_SERIAL_ID", tempId);
        startActivity(intent);
    }

    public void yield_Intent(String tempId) {
        Intent intent = new Intent(DemoL3_InProgressActivity.getInstance(), DemoL3Activity_YieldResult.class);
        intent.putExtra("DEMO_L3_SERIAL_ID", tempId);
        startActivity(intent);
    }
}
