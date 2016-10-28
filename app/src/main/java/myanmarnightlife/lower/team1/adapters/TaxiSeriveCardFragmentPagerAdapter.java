package myanmarnightlife.lower.team1.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.CardView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import myanmarnightlife.lower.team1.fragments.TaxiServiceCardFragment;

/**
 * Created by winthanhtike on 10/27/16.
 */
public class TaxiSeriveCardFragmentPagerAdapter extends FragmentStatePagerAdapter implements TaxiCardInterface{

    private List<TaxiServiceCardFragment> mFragments;
    private float mBaseElevation;

    public TaxiSeriveCardFragmentPagerAdapter(FragmentManager fm, float baseElevation) {
        super(fm);
        mFragments = new ArrayList<>();
        mBaseElevation = baseElevation;

        for(int i = 0; i< 3; i++){
            addCardFragment(new TaxiServiceCardFragment());
        }

    }

    @Override
    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mFragments.get(position).getCardView();
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return TaxiServiceCardFragment.getInstance(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object fragment = super.instantiateItem(container, position);

        mFragments.set(position, (TaxiServiceCardFragment) fragment);
        return fragment;
    }

    public void addCardFragment(TaxiServiceCardFragment fragment) {
        mFragments.add(fragment);
    }

}
