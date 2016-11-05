package qianfeng.com.phoenixnews.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import qianfeng.com.phoenixnews.bean.Classify;

/**
 * Created by Administrator on 2016/10/31 0031.
 */

public class MyFgAdapter extends FragmentPagerAdapter {
    List<Fragment> mList;
    List<Classify> mTitles;

    public MyFgAdapter(FragmentManager fm, List<Fragment> list, List<Classify> titles) {
        super(fm);
        mList = list;
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position).getName();
    }
}
