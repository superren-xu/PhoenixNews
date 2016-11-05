package qianfeng.com.phoenixnews.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import qianfeng.com.phoenixnews.BaseFragment;
import qianfeng.com.phoenixnews.R;
import qianfeng.com.phoenixnews.adapter.HeadVPAdapter;
import qianfeng.com.phoenixnews.adapter.MyLVAdapter;
import qianfeng.com.phoenixnews.bean.Classify;
import qianfeng.com.phoenixnews.bean.LVItemBean;
import qianfeng.com.phoenixnews.shownews.presenter.BlankFgPresenter;
import qianfeng.com.phoenixnews.shownews.view.DetailActivity;
import qianfeng.com.phoenixnews.utils.Constant;

/**
 * Created by Administrator on 2016/10/31 0031.
 */

public class BlankFragment extends BaseFragment implements IBlankFgView {

    private BlankFgPresenter mBlankFgPresenter = new BlankFgPresenter(this);
    private ListView mListView;
    private View mItemView;
    private LayoutInflater mInflater;
    private List<LVItemBean> mList = new ArrayList<>();
    private static List<Classify> mClassifyList = new ArrayList<>();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private int firstVisablePosition = 0;
    private int firstVisablePositionTop = 0;
    private int page = 1;
    private int videoSize = 20;
    private List<LVItemBean> tempList = new ArrayList<>();
    private MyLVAdapter mMyLVAdapter;

    public static BlankFragment getInstance(Classify classify, int parseMode, String url) {
        mClassifyList.add(classify);
        BlankFragment blankFragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString("url", url);
        args.putInt("mode", parseMode);
        args.putString("fgName", classify.getName());
        blankFragment.setArguments(args);
        return blankFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflater = inflater;
        if (mItemView == null) {
            mItemView = inflater.inflate(R.layout.blankfragment_layout, container, false);
            initData();
        }
        return mItemView;
    }

    private void initData() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) mItemView.findViewById(R.id.swiperefreshlayout);
        mListView = (ListView) mItemView.findViewById(R.id.lv);
        Bundle bundle = getArguments();
        String url = bundle.getString("url");
        int mode = bundle.getInt("mode");
        mBlankFgPresenter.initData(mode, url);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                switch (getArguments().getString("fgName")) {
                    case "头条":
                        mBlankFgPresenter.loadData(Constant.GEN_PARSE, String.format(mClassifyList.get(0).getApi(), Constant.PAGE), Constant.DOWN_REFRESH);
                        break;
                    case "推荐":
                        mBlankFgPresenter.loadData(Constant.RECOMMEND, mClassifyList.get(1).getApi(), Constant.DOWN_REFRESH);
                        break;
                    case "娱乐":
                        mBlankFgPresenter.loadData(Constant.GEN_PARSE, String.format(mClassifyList.get(2).getApi(), Constant.PAGE), Constant.DOWN_REFRESH);
                        break;
                    case "本地":
                        mBlankFgPresenter.loadData(Constant.LOCAL, String.format(mClassifyList.get(3).getApi(), Constant.PROVINCE, Constant.CITY, Constant.PAGE), Constant.DOWN_REFRESH);
                        break;
                    case "财经":
                        mBlankFgPresenter.loadData(Constant.GEN_PARSE, String.format(mClassifyList.get(4).getApi(), Constant.PAGE), Constant.DOWN_REFRESH);
                        break;
                    case "凤凰号":
                        mBlankFgPresenter.loadData(Constant.PHEONIXNUM, String.format(mClassifyList.get(5).getApi(), Constant.PAGE), Constant.DOWN_REFRESH);
                        break;
                    case "凤凰卫视":
                        mBlankFgPresenter.loadData(Constant.PHEONIXVIDEO, String.format(mClassifyList.get(6).getApi(), Constant.VIDEOSIZE), Constant.DOWN_REFRESH);
                        break;
                    case "社会":
                        mBlankFgPresenter.loadData(Constant.GEN_PARSE, String.format(mClassifyList.get(7).getApi(), Constant.ACTION_DOWN), Constant.DOWN_REFRESH);
                        break;
                    case "科技":
                        mBlankFgPresenter.loadData(Constant.GEN_PARSE, String.format(mClassifyList.get(8).getApi(), Constant.PAGE), Constant.DOWN_REFRESH);
                        break;
                    case "美女":
                        mBlankFgPresenter.loadData(Constant.BEAUTY, String.format(mClassifyList.get(9).getApi(), Constant.PAGE), Constant.DOWN_REFRESH);
                        break;
                    case "军事":
                        mBlankFgPresenter.loadData(Constant.GEN_PARSE, String.format(mClassifyList.get(10).getApi(), Constant.ACTION_DOWN), Constant.DOWN_REFRESH);
                        break;
                    case "体育":
                        mBlankFgPresenter.loadData(Constant.GEN_PARSE, String.format(mClassifyList.get(11).getApi(), Constant.PAGE), Constant.DOWN_REFRESH);
                        break;
                    case "历史":
                        mBlankFgPresenter.loadData(Constant.GEN_PARSE, String.format(mClassifyList.get(12).getApi(), Constant.ACTION_DOWN), Constant.DOWN_REFRESH);
                        break;
                    case "汽车":
                        mBlankFgPresenter.loadData(Constant.GEN_PARSE, String.format(mClassifyList.get(13).getApi(), Constant.PAGE), Constant.DOWN_REFRESH);
                        break;
                    case "时尚":
                        mBlankFgPresenter.loadData(Constant.GEN_PARSE, String.format(mClassifyList.get(14).getApi(), Constant.PAGE), Constant.DOWN_REFRESH);
                        break;
                    case "房产":
                        mBlankFgPresenter.loadData(Constant.GEN_PARSE, String.format(mClassifyList.get(15).getApi(), Constant.PAGE), Constant.DOWN_REFRESH);
                        break;
                    case "FUN来了":
                        mBlankFgPresenter.loadData(Constant.GEN_PARSE, String.format(mClassifyList.get(16).getApi(), Constant.PAGE), Constant.DOWN_REFRESH);
                        break;
                    case "段子":
                        mBlankFgPresenter.loadData(Constant.JOKES, String.format(mClassifyList.get(17).getApi(), Constant.PAGE), Constant.DOWN_REFRESH);
                        break;
                    case "萌物":
                        mBlankFgPresenter.loadData(Constant.PET, String.format(mClassifyList.get(0).getApi(), Constant.PAGE), Constant.DOWN_REFRESH);
                        break;
                }
            }
        });
    }

    @Override
    public void initListView(List<LVItemBean> list, int refreshMode) {
        if (refreshMode == Constant.DOWN_REFRESH) {
            tempList = new ArrayList<>();
            tempList.addAll(list);
            mList = new ArrayList<>();
            if (Constant.HEAD.equals(list.get(0).getTag())) {
                mList.addAll(list.subList(1, list.size()));
            } else {
                mList.addAll(list);
            }
            mSwipeRefreshLayout.setRefreshing(false);
        } else {
            tempList.addAll(list);
            mList.addAll(list);
        }
        mMyLVAdapter.notifyDataSetChanged();
        mListView.setSelectionFromTop(firstVisablePosition, firstVisablePositionTop);

    }

    @Override
    public void setHeaderView(List<LVItemBean> list) {
        if (list != null && list.size() > 0) {
            tempList.addAll(list);
            mList.addAll(list);
            LVItemBean lvItemBean = list.get(0);
            if (Constant.HEAD.equals(lvItemBean.getTag())) {
                View view = mInflater.inflate(R.layout.header_layout, null);
                ViewPager viewPager = (ViewPager) view.findViewById(R.id.header_viewpager);
                final TextView textView = (TextView) view.findViewById(R.id.header_title);
                viewPager.setAdapter(new HeadVPAdapter(getActivity(), lvItemBean.getImgs()));
                final String[] titles = lvItemBean.getTitles();
                textView.setText(titles[0]);
                mListView.addHeaderView(view);
                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        textView.setText(titles[position]);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                mList = mList.subList(1, mList.size());
            }
            mMyLVAdapter = new MyLVAdapter(getActivity(), mList);
            mListView.setAdapter(mMyLVAdapter);
            mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {

                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    if (firstVisibleItem + visibleItemCount == totalItemCount) {
                        firstVisablePosition = view.getFirstVisiblePosition();
                        firstVisablePositionTop = view.getChildAt(0).getTop();
                        switch (getArguments().getString("fgName")) {
                            case "头条":
                                mBlankFgPresenter.loadData(Constant.GEN_PARSE, String.format(mClassifyList.get(0).getApi(), ++page), Constant.UP_REFRESH);
                                break;
                            case "推荐":
                                mBlankFgPresenter.loadData(Constant.RECOMMEND, mClassifyList.get(1).getApi(), Constant.UP_REFRESH);
                                break;
                            case "娱乐":
                                mBlankFgPresenter.loadData(Constant.GEN_PARSE, String.format(mClassifyList.get(2).getApi(), ++page), Constant.UP_REFRESH);
                                break;
                            case "本地":
                                mBlankFgPresenter.loadData(Constant.LOCAL, String.format(mClassifyList.get(3).getApi(), Constant.PROVINCE, Constant.CITY, ++page), Constant.UP_REFRESH);
                                break;
                            case "财经":
                                mBlankFgPresenter.loadData(Constant.GEN_PARSE, String.format(mClassifyList.get(4).getApi(), ++page), Constant.UP_REFRESH);
                                break;
                            case "凤凰号":
                                mBlankFgPresenter.loadData(Constant.PHEONIXNUM, String.format(mClassifyList.get(5).getApi(), ++page), Constant.UP_REFRESH);
                                break;
                            case "凤凰卫视":
                                //                            mBlankFgPresenter.loadData(Constant.PHEONIXVIDEO, String.format(mClassifyList.get(6).getApi(), videoSize += 20), Constant.UP_REFRESH);
                                break;
                            case "社会":
                                mBlankFgPresenter.loadData(Constant.GEN_PARSE, String.format(mClassifyList.get(7).getApi(), Constant.ACTION_UP), Constant.UP_REFRESH);
                                break;
                            case "科技":
                                mBlankFgPresenter.loadData(Constant.GEN_PARSE, String.format(mClassifyList.get(8).getApi(), ++page), Constant.UP_REFRESH);
                                break;
                            case "美女":
                                mBlankFgPresenter.loadData(Constant.BEAUTY, String.format(mClassifyList.get(9).getApi(), ++page), Constant.UP_REFRESH);
                                break;
                            case "军事":
                                mBlankFgPresenter.loadData(Constant.GEN_PARSE, String.format(mClassifyList.get(10).getApi(), Constant.ACTION_UP), Constant.UP_REFRESH);
                                break;
                            case "体育":
                                mBlankFgPresenter.loadData(Constant.GEN_PARSE, String.format(mClassifyList.get(11).getApi(), ++page), Constant.UP_REFRESH);
                                break;
                            case "历史":
                                mBlankFgPresenter.loadData(Constant.GEN_PARSE, String.format(mClassifyList.get(12).getApi(), Constant.ACTION_UP), Constant.UP_REFRESH);
                                break;
                            case "汽车":
                                mBlankFgPresenter.loadData(Constant.GEN_PARSE, String.format(mClassifyList.get(13).getApi(), ++page), Constant.UP_REFRESH);
                                break;
                            case "时尚":
                                mBlankFgPresenter.loadData(Constant.GEN_PARSE, String.format(mClassifyList.get(14).getApi(), ++page), Constant.UP_REFRESH);
                                break;
                            case "房产":
                                mBlankFgPresenter.loadData(Constant.GEN_PARSE, String.format(mClassifyList.get(15).getApi(), ++page), Constant.UP_REFRESH);
                                break;
                            case "FUN来了":
                                mBlankFgPresenter.loadData(Constant.GEN_PARSE, String.format(mClassifyList.get(16).getApi(), ++page), Constant.UP_REFRESH);
                                break;
                            case "段子":
                                mBlankFgPresenter.loadData(Constant.JOKES, String.format(mClassifyList.get(17).getApi(), ++page), Constant.UP_REFRESH);
                                break;
                            case "萌物":
                                mBlankFgPresenter.loadData(Constant.PET, String.format(mClassifyList.get(0).getApi(), ++page), Constant.UP_REFRESH);
                                break;
                        }
                    }
                }
            });
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    LVItemBean lvItemBean1 = null;
                    for (int i = 0; i < tempList.size(); i++) {
                        LVItemBean lvItemBean2 = tempList.get(i);
                        if (Constant.HEAD.equals(lvItemBean2.getTag())) {
                            lvItemBean1 = mList.get(position - 1);
                            break;
                        } else if (Constant.GENERAL.equals(lvItemBean2.getTag())) {
                            lvItemBean1 = mList.get(position);
                        }
                    }
                    Intent intent = new Intent(getActivity(), DetailActivity.class);
                    intent.putExtra("lvItemBean", lvItemBean1);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.enter, R.anim.exit);
                }
            });
        }
    }

    @Override
    public void showError() {
        mSwipeRefreshLayout.setRefreshing(false);
        Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
    }
}
