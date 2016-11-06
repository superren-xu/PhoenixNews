package qianfeng.com.phoenixnews.shownews.model;

import java.util.List;

import qianfeng.com.phoenixnews.bean.FindLVItemBean;

/**
 * Created by superren on 2016/11/6.
 */

public interface OnFindActvityDataLiatener {
    void onSuccess(List<FindLVItemBean> list);

    void onFail();
}
