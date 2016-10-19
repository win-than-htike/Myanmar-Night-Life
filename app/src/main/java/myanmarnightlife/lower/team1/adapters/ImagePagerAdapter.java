package myanmarnightlife.lower.team1.adapters;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import myanmarnightlife.lower.team1.MyanmarNightLifeApp;
import myanmarnightlife.lower.team1.R;

/**
 * Created by winthanhtike on 10/5/16.
 */
public class ImagePagerAdapter extends PagerAdapter {

    private List<String> mImages;
    private LayoutInflater inflater;

    public ImagePagerAdapter(String[] images) {

        if (images == null){
            mImages = new ArrayList<>();
        }else{
            mImages = new ArrayList<>(Arrays.asList(images));
        }

        inflater = LayoutInflater.from(MyanmarNightLifeApp.getContext());
    }

    @Override
    public int getCount() {
        return mImages.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ImageView imageView = (ImageView) inflater.inflate(R.layout.view_item_shop_images,container,false);

        String imageUrl = mImages.get(position);

        Glide.with(imageView.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.night)
                .error(R.mipmap.ic_launcher)
                .into(imageView);

        ((ViewPager) container).addView(imageView);

        return imageView;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }
}
