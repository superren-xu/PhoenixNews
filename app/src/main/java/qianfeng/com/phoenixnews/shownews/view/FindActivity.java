package qianfeng.com.phoenixnews.shownews.view;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import qianfeng.com.phoenixnews.BaseActivity;
import qianfeng.com.phoenixnews.R;
import qianfeng.com.phoenixnews.adapter.FindLVAdapter;
import qianfeng.com.phoenixnews.bean.FindLVItemBean;
import qianfeng.com.phoenixnews.shownews.presenter.FindPresenter;
import qianfeng.com.phoenixnews.utils.Constant;

public class FindActivity extends BaseActivity implements IFindView {
    private FindPresenter findPresenter = new FindPresenter(this);
    private ListView listView;
    private List<FindLVItemBean> mList;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int firstVisiblePosition = 0;
    private int firstVisibleItemTop = 0;
    private FindLVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        mList = new ArrayList<>();
        initView();
    }

    private void initView() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout);
        listView = (ListView) findViewById(R.id.findActivity_lv);
        findPresenter.loadData(Constant.FIND_URL, Constant.DOWN_REFRESH);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                findPresenter.loadData(Constant.FIND_URL, Constant.DOWN_REFRESH);
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount) {
                    firstVisiblePosition = view.getFirstVisiblePosition();
//                    firstVisibleItemTop = view.getChildAt(0).getTop();
                    findPresenter.loadData(Constant.FIND_URL, Constant.UP_REFRESH);
                }
            }
        });
    }

    @Override
    public void initListView(List<FindLVItemBean> list, int refreshMode) {
        if (Constant.DOWN_REFRESH == refreshMode) {
            mList = new ArrayList<>();
            mList.addAll(list);
            swipeRefreshLayout.setRefreshing(false);
        } else {
            mList.addAll(list);
        }
        adapter = new FindLVAdapter(this, list);
        listView.setAdapter(adapter);
//        listView.setSelectionFromTop(firstVisiblePosition, firstVisibleItemTop);
//        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {

    }
}
