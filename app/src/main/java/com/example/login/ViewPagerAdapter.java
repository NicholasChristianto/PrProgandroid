package com.example.login;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class ViewPagerAdapter extends FragmentPagerAdapter {
    int tabs;
    private Fragment[] frag;

    public ViewPagerAdapter(FragmentManager fm,int tabs) {
        super(fm);
        this.tabs = tabs;
        frag = new Fragment[]{new Left(), new Right()};
    }
    @Override

    public Fragment getItem(int position) {

        return frag[position];

    }
    @Override
    public int getCount(){
        return tabs;
    }
    @Override
    public CharSequence getPageTitle(int position){
        String title ;
        if(position==0){
            title="Biodata";
        }else{
            title="About";
        }
        return title;
    }
}
