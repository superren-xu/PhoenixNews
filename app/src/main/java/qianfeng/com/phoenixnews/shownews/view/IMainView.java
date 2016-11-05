package qianfeng.com.phoenixnews.shownews.view;

import java.util.List;

import qianfeng.com.phoenixnews.bean.Classify;

/**
 * Created by Administrator on 2016/10/31 0031.
 */

public interface IMainView {
    void initTabLayoutData(List<Classify> list);

    void showError();
}
