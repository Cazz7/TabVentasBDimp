package com.projects.cristianzapata.tagventas;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by cristian.zapata on 23-05-2017.
 */

public class pagerAdapter extends FragmentStatePagerAdapter {

    int mNumTabs;
    public pagerAdapter(FragmentManager fm, int NumTabs) {
        super(fm);
        this.mNumTabs = NumTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                tabFrutas tab1 = new tabFrutas();
                return tab1;
            case 1:
                tabLacteos tab2 = new tabLacteos();
                return tab2;
            case 2:
                tabCarnicos tab3 = new tabCarnicos();
                return tab3;
            case 3:
                tabAseo tab4 = new tabAseo();
                return tab4;
            case 4:
                tabDespensa tab5 = new tabDespensa();
                return tab5;
            case 5:
                tabLicores tab6 = new tabLicores();
                return tab6;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumTabs;
    }
}
