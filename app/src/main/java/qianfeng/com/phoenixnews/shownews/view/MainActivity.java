package qianfeng.com.phoenixnews.shownews.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import qianfeng.com.phoenixnews.BaseActivity;
import qianfeng.com.phoenixnews.R;
import qianfeng.com.phoenixnews.adapter.MyFgAdapter;
import qianfeng.com.phoenixnews.bean.Classify;
import qianfeng.com.phoenixnews.fragment.BlankFragment;
import qianfeng.com.phoenixnews.shownews.presenter.MainPresenter;
import qianfeng.com.phoenixnews.utils.Constant;

public class MainActivity extends BaseActivity implements IMainView {

    private ImageView mIV_News;
    private ImageView mIV_Video;
    private ImageView mIV_Find;
    private ImageView mIV_Mine;
    private TextView mTV_News;
    private TextView mTV_Video;
    private TextView mTV_Find;
    private TextView mTV_Mine;
    MainPresenter mMainPresenter = new MainPresenter(this);
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private int page = 1;
    private int size = 20;
    private String action = "down";
    private BlankFragment mBlankFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mMainPresenter.loadTabLayoutData(Constant.API_URL);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mIV_News.setImageResource(R.drawable.tab_menu_item_news_press);
        mTV_News.setTextColor(Color.RED);
        setBottomStyle(Constant.NEWS);
    }

    private void initView() {
        mIV_News = ((ImageView) findViewById(R.id.iv_news));
        mIV_Video = ((ImageView) findViewById(R.id.iv_video));
        mIV_Find = ((ImageView) findViewById(R.id.iv_find));
        mIV_Mine = ((ImageView) findViewById(R.id.iv_mine));
        mTV_News = ((TextView) findViewById(R.id.tv_news));
        mTV_Video = ((TextView) findViewById(R.id.tv_video));
        mTV_Find = ((TextView) findViewById(R.id.tv_find));
        mTV_Mine = ((TextView) findViewById(R.id.tv_mine));
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mIV_News.setImageResource(R.drawable.tab_menu_item_news_press);
        mTV_News.setTextColor(Color.RED);
    }


    private void setBottomStyle(int select) {
        switch (select) {
            case Constant.NEWS:
                mIV_Video.setImageResource(R.drawable.tab_menu_item_video_default);
                mTV_Video.setTextColor(Color.GRAY);
                mIV_Find.setImageResource(R.drawable.tab_menu_item_read_default);
                mTV_Find.setTextColor(Color.GRAY);
                mIV_Mine.setImageResource(R.drawable.tab_menu_item_account_default);
                mTV_Mine.setTextColor(Color.GRAY);
                break;
            case Constant.VIDEO:
                mIV_News.setImageResource(R.drawable.tab_menu_item_news_default);
                mTV_News.setTextColor(Color.GRAY);
                mIV_Find.setImageResource(R.drawable.tab_menu_item_read_default);
                mTV_Find.setTextColor(Color.GRAY);
                mIV_Mine.setImageResource(R.drawable.tab_menu_item_account_default);
                mTV_Mine.setTextColor(Color.GRAY);
                break;
            case Constant.FIND:
                mIV_Video.setImageResource(R.drawable.tab_menu_item_video_default);
                mTV_Video.setTextColor(Color.GRAY);
                mIV_News.setImageResource(R.drawable.tab_menu_item_news_default);
                mTV_News.setTextColor(Color.GRAY);
                mIV_Mine.setImageResource(R.drawable.tab_menu_item_account_default);
                mTV_Mine.setTextColor(Color.GRAY);
                break;
            case Constant.MINE:
                mIV_News.setImageResource(R.drawable.tab_menu_item_news_default);
                mTV_News.setTextColor(Color.GRAY);
                mIV_Find.setImageResource(R.drawable.tab_menu_item_read_default);
                mTV_Find.setTextColor(Color.GRAY);
                mIV_Video.setImageResource(R.drawable.tab_menu_item_video_default);
                mTV_Video.setTextColor(Color.GRAY);
                break;
        }
    }

    public void news(View view) {
        mIV_News.setImageResource(R.drawable.tab_menu_item_news_press);
        mTV_News.setTextColor(Color.RED);
        setBottomStyle(Constant.NEWS);
    }

    public void video(View view) {
        mIV_Video.setImageResource(R.drawable.tab_menu_item_video_press);
        mTV_Video.setTextColor(Color.RED);
        setBottomStyle(Constant.VIDEO);
    }

    public void find(View view) {
        mIV_Find.setImageResource(R.drawable.tab_menu_item_read_press);
        mTV_Find.setTextColor(Color.RED);
        setBottomStyle(Constant.FIND);
    }

    public void mine(View view) {
        mIV_Mine.setImageResource(R.drawable.tab_menu_item_account_press);
        mTV_Mine.setTextColor(Color.RED);
        setBottomStyle(Constant.MINE);
        startActivity(new Intent(this, MineActivity.class));
    }


    @Override
    public void initTabLayoutData(List<Classify> list) {
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Classify classify = list.get(i);
            switch (classify.getName()) {
                case "头条":
                    mBlankFragment = BlankFragment.getInstance(classify, Constant.GEN_PARSE, String.format(classify.getApi(), page));
                    fragments.add(mBlankFragment);
                    break;
                case "推荐":
                    mBlankFragment = BlankFragment.getInstance(classify, Constant.RECOMMEND, classify.getApi());
                    fragments.add(mBlankFragment);
                    break;
                case "娱乐":
                    mBlankFragment = BlankFragment.getInstance(classify, Constant.GEN_PARSE, String.format(classify.getApi(), page));
                    fragments.add(mBlankFragment);
                    break;
                case "本地":
                    mBlankFragment = BlankFragment.getInstance(classify, Constant.LOCAL, String.format(classify.getApi(), Constant.PROVINCE, Constant.CITY, page));
                    fragments.add(mBlankFragment);
                    break;
                case "财经":
                    mBlankFragment = BlankFragment.getInstance(classify, Constant.GEN_PARSE, String.format(classify.getApi(), page));
                    fragments.add(mBlankFragment);
                    break;
                case "凤凰号":
                    mBlankFragment = BlankFragment.getInstance(classify, Constant.PHEONIXNUM, String.format(classify.getApi(), page));
                    fragments.add(mBlankFragment);
                    break;
                case "凤凰卫视":
                    mBlankFragment = BlankFragment.getInstance(classify, Constant.PHEONIXVIDEO, String.format(classify.getApi(), size));
                    fragments.add(mBlankFragment);
                    break;
                case "社会":
                    mBlankFragment = BlankFragment.getInstance(classify, Constant.GEN_PARSE, String.format(classify.getApi(), action));
                    fragments.add(mBlankFragment);
                    break;
                case "科技":
                    mBlankFragment = BlankFragment.getInstance(classify, Constant.GEN_PARSE, String.format(classify.getApi(), page));
                    fragments.add(mBlankFragment);
                    break;
                case "美女":
                    mBlankFragment = BlankFragment.getInstance(classify, Constant.BEAUTY, String.format(classify.getApi(), page));
                    fragments.add(mBlankFragment);
                    break;
                case "军事":
                    mBlankFragment = BlankFragment.getInstance(classify, Constant.GEN_PARSE, String.format(classify.getApi(), action));
                    fragments.add(mBlankFragment);
                    break;
                case "体育":
                    mBlankFragment = BlankFragment.getInstance(classify, Constant.GEN_PARSE, String.format(classify.getApi(), page));
                    fragments.add(mBlankFragment);
                    break;
                case "历史":
                    mBlankFragment = BlankFragment.getInstance(classify, Constant.GEN_PARSE, String.format(classify.getApi(), action));
                    fragments.add(mBlankFragment);
                    break;
                case "汽车":
                    mBlankFragment = BlankFragment.getInstance(classify, Constant.GEN_PARSE, String.format(classify.getApi(), page));
                    fragments.add(mBlankFragment);
                    break;
                case "时尚":
                    mBlankFragment = BlankFragment.getInstance(classify, Constant.GEN_PARSE, String.format(classify.getApi(), page));
                    fragments.add(mBlankFragment);
                    break;
                case "房产":
                    mBlankFragment = BlankFragment.getInstance(classify, Constant.GEN_PARSE, String.format(classify.getApi(), page));
                    fragments.add(mBlankFragment);
                    break;
                case "FUN来了":
                    mBlankFragment = BlankFragment.getInstance(classify, Constant.GEN_PARSE, String.format(classify.getApi(), page));
                    fragments.add(mBlankFragment);
                    break;
                case "段子":
                    mBlankFragment = BlankFragment.getInstance(classify, Constant.JOKES, String.format(classify.getApi(), page));
                    fragments.add(mBlankFragment);
                    break;
                case "萌物":
                    mBlankFragment = BlankFragment.getInstance(classify, Constant.PET, String.format(classify.getApi(), page));
                    fragments.add(mBlankFragment);
                    break;
            }
        }
        MyFgAdapter myFgAdapter = new MyFgAdapter(getSupportFragmentManager(), fragments, list);
        mViewPager.setAdapter(myFgAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void showError() {
        Toast.makeText(MainActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
    }
}
