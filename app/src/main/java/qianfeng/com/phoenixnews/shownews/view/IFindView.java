package qianfeng.com.phoenixnews.shownews.view;

import java.util.List;

import qianfeng.com.phoenixnews.bean.FindLVItemBean;

/**
 * Created by superren on 2016/11/6.
 */

public interface IFindView {
    void initListView(List<FindLVItemBean> list,int refreshMode);

    void showError();
}
