package com.example.gnomerock.mytemplate1;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {


     String[] TAB_TITLES={"HOME","PROFILE","FIND","Dummy","Dummy","Dummy","Dummy"};
     TabLayout tabLayout;
     ViewPager viewPager;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);


        tabLayout = (TabLayout) view.findViewById(R.id.main_tab_layout);
        viewPager = (ViewPager) view.findViewById(R.id.main_view_pager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //set fragment pager adapter into pager,
        //and set pager into tab
        loadPager();
        final int[] ICONS = new int[] {
                R.drawable.ic_action_home,
                R.drawable.ic_action_user,
                R.drawable.ic_action_search,
                R.drawable.ic_launcher,
        };



        return view;
    }

    public void loadPager() {
        if (viewPager.getAdapter() == null)
            viewPager.setAdapter(new MainFragmentAdapter(getFragmentManager()));
        else
            viewPager.getAdapter().notifyDataSetChanged();
        tabLayout.setupWithViewPager(viewPager);
    }

    private class MainFragmentAdapter extends FragmentPagerAdapter {

        public MainFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            if(position == 1)
                return FacebookLoginFragment.newInstance();
            if(position == 2)
                return FindScientistFragment.newInstance();
            else
                return DummyFragment.newInstance();
        }

        @Override
        public int getCount() {
            return TAB_TITLES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TAB_TITLES[position];
        }
    }
}
