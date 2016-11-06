package qianfeng.com.phoenixnews.shownews.presenter;

import java.util.List;

import qianfeng.com.phoenixnews.bean.FindLVItemBean;
import qianfeng.com.phoenixnews.shownews.model.IFindData;
import qianfeng.com.phoenixnews.shownews.model.IFindDataImpl;
import qianfeng.com.phoenixnews.shownews.model.OnFindActvityDataLiatener;
import qianfeng.com.phoenixnews.shownews.view.IFindView;

/**
 * Created by superren on 2016/11/6.
 */

public class FindPresenter {
    private IFindData mIFindData;
    private IFindView mIFindView;

    public FindPresenter(IFindView mIFindView) {
        this.mIFindView = mIFindView;
        mIFindData = new IFindDataImpl();
    }

    public void loadData(String url, final int refreshMode) {
        mIFindData.downloadData(url, new OnFindActvityDataLiatener() {
            @Override
            public void onSuccess(List<FindLVItemBean> list) {
                mIFindView.initListView(list,refreshMode);
            }

            @Override
            public void onFail() {
                mIFindView.showError();
            }
        });
    }
}
