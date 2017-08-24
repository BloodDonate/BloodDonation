package com.example.jonathandorvilier.dondesang.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.astuetz.PagerSlidingTabStrip;
import com.example.jonathandorvilier.dondesang.R;
import com.example.jonathandorvilier.dondesang.fragment.DonationFragment;
import com.example.jonathandorvilier.dondesang.fragment.DemandeFragment;

/**
 * Created by Jonathan Dorvilier on 8/23/2017.
 */

public class BloodPageAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.IconTabProvider {

    private int tabIcons[] = {R.mipmap.ic_reservation, R.mipmap.ic_donation, R.mipmap.ic_donate};

    final int PAGE_COUNT =2;
    private String tabTitles [] ={"Reservation", "Donation"};

    public BloodPageAdapter (FragmentManager fragmentManager){
        super(fragmentManager);

    }

    @Override
    public Fragment getItem(int position) {
        if(position ==0){
            return new DemandeFragment();

        }else if(position==1) {
            return new DonationFragment();
        }else {

            return null;
        }
    }

    //return the table title
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }


    @Override
    public int getPageIconResId(int position) {
        return tabIcons[position];
    }
}