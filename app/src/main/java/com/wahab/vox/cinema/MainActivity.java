package com.wahab.vox.cinema;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,Tab1.OnFragmentInteractionListener,Tab2.OnFragmentInteractionListener,Tab3.OnFragmentInteractionListener, Tab4.OnFragmentInteractionListener, Tab5.OnFragmentInteractionListener {



    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        spinner = (Spinner) findViewById(R.id.spinner1);
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            // Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(spinner);

            // Set popupWindow height to 1000px
            popupWindow.setHeight(1000);
        }
        catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            // silently fail...
        }

        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this,R.array.theatres,android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter1);
        spinner.setOnItemSelectedListener(this);




        TabLayout tabLayout = (TabLayout)findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Today"));
        tabLayout.addTab(tabLayout.newTab().setText("Wed 15 Aug"));
        tabLayout.addTab(tabLayout.newTab().setText("Thu 16 Aug"));
        tabLayout.addTab(tabLayout.newTab().setText("Fri 17 Aug"));
        tabLayout.addTab(tabLayout.newTab().setText("Sat 18 Aug"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        final ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));



        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        });
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView myText = (TextView) view;
        Toast.makeText(this,"You Selected " +myText.getText(),Toast.LENGTH_SHORT).show();
        ((TextView) adapterView.getChildAt(0)).setGravity(Gravity.CENTER);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

