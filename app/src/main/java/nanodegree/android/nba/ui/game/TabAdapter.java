package nanodegree.android.nba.ui.game;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    // Force a refresh of the page when a different fragment is displayed
    @Override
    public int getItemPosition(Object object) {
        int position = mFragmentList.indexOf(object);
        if (position >= 0) {
            return position;
        } else {
            return POSITION_NONE;
        }
    }

    public void replaceFragment(Fragment oldFragment, Fragment newFragment) {
        mFragmentList.set(mFragmentList.indexOf(oldFragment), newFragment);
        notifyDataSetChanged();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}