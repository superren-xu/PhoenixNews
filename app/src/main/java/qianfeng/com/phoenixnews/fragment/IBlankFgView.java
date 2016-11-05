package qianfeng.com.phoenixnews.fragment;

import java.util.List;

import qianfeng.com.phoenixnews.bean.LVItemBean;

/**
 * Created by Administrator on 2016/11/1 0001.
 */

public interface IBlankFgView {
    void initListView(List<LVItemBean> list, int refreshMode);

    void setHeaderView(List<LVItemBean> list);
    void showError();
}
