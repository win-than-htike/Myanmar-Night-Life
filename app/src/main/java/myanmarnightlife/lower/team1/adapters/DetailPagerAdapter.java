package myanmarnightlife.lower.team1.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by winthanhtike on 10/12/16.
 */
public class DetailPagerAdapter extends FragmentStatePagerAdapter {

    public List<Fragment> list = new ArrayList<>();

    public void add(Fragment fragment) {
        list.add(fragment);
    }

    public DetailPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
