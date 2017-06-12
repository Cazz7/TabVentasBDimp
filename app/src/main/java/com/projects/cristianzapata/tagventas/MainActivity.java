package com.projects.cristianzapata.tagventas;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        //custom view for icon
        View viewFruta = getLayoutInflater().inflate(R.layout.custom_tab_view, null);
        viewFruta.findViewById(R.id.icon).setBackgroundResource(R.mipmap.frutas_icon);
        tabLayout.addTab(tabLayout.newTab().setCustomView(viewFruta));

        //Custom view for icon
        View viewLacteos = getLayoutInflater().inflate(R.layout.custom_tab_view, null);
        viewLacteos.findViewById(R.id.icon).setBackgroundResource(R.mipmap.lacteos_icon);
        tabLayout.addTab(tabLayout.newTab().setCustomView(viewLacteos));

        //Custom view for icon
        View viewCarnes = getLayoutInflater().inflate(R.layout.custom_tab_view, null);
        viewCarnes.findViewById(R.id.icon).setBackgroundResource(R.mipmap.carne_icon);
        tabLayout.addTab(tabLayout.newTab().setCustomView(viewCarnes));

        //Custom view for icon
        View viewAseo = getLayoutInflater().inflate(R.layout.custom_tab_view, null);
        viewAseo.findViewById(R.id.icon).setBackgroundResource(R.mipmap.aseo_icon);
        tabLayout.addTab(tabLayout.newTab().setCustomView(viewAseo));

        //Custom view for icon
        View viewDespensa = getLayoutInflater().inflate(R.layout.custom_tab_view, null);
        viewDespensa.findViewById(R.id.icon).setBackgroundResource(R.mipmap.despensa_icon);
        tabLayout.addTab(tabLayout.newTab().setCustomView(viewDespensa));

        //Custom view for icon
        View viewLicores = getLayoutInflater().inflate(R.layout.custom_tab_view, null);
        viewLicores.findViewById(R.id.icon).setBackgroundResource(R.mipmap.licores_icon);
        tabLayout.addTab(tabLayout.newTab().setCustomView(viewLicores));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        final pagerAdapter pAdapter = new pagerAdapter
                (getSupportFragmentManager(),tabLayout.getTabCount());

        viewPager.setAdapter(pAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener( new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        } );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_buy) {
            // lo ideal aquí sería hacer un intent para abrir una nueva clase como lo siguiente
            Intent compras = new Intent(getApplicationContext(), listaCompra.class);
            startActivity(compras);
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "compras", null, 1);
//        SQLiteDatabase bd = admin.getWritableDatabase();
//        int cant = bd.delete("listacompras", null, null);
//        bd.close();
    }
}
