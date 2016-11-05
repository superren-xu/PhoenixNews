package qianfeng.com.phoenixnews.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Administrator on 2016/11/1 0001.
 */

public class HeadVPAdapter extends PagerAdapter {

    private Context mContext;
    private String[] imgs;

    public HeadVPAdapter(Context context, String[] imgs) {
        mContext = context;
        this.imgs = imgs;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Picasso.with(mContext).load(imgs[position]).into(imageView);
        container.addView(imageView);
        return imageView;
    }
}
