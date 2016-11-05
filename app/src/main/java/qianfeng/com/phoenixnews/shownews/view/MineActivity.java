package qianfeng.com.phoenixnews.shownews.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import qianfeng.com.phoenixnews.R;
import qianfeng.com.phoenixnews.adapter.MineLVAdapter;
import qianfeng.com.phoenixnews.bean.MineLVItemBean;

public class MineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        initView();
    }

    private void initView() {
        ListView listView = (ListView) findViewById(R.id.mineActivity_lv);
        bindData(listView);
    }

    private void bindData(ListView listView) {
        List<MineLVItemBean> list = initData();
        listView.setAdapter(new MineLVAdapter(this,list));
    }

    public List<MineLVItemBean> initData() {
        List<MineLVItemBean> list = new ArrayList<>();
        list.add(new MineLVItemBean(R.drawable.user_shopping_day,R.drawable.appwidget_next_btn_default,"商城"));
        list.add(new MineLVItemBean(R.drawable.user_finance_day,R.drawable.appwidget_next_btn_default,"理财"));
        list.add(new MineLVItemBean(R.drawable.game_icon,R.drawable.appwidget_next_btn_default,"游戏"));
        list.add(new MineLVItemBean(R.drawable.my_sub_day,R.drawable.appwidget_next_btn_default,"订阅"));
        list.add(new MineLVItemBean(R.drawable.night_mode_day,R.drawable.night_mode_switch_off,"夜间"));
        list.add(new MineLVItemBean(R.drawable.settings_day,R.drawable.appwidget_next_btn_default,"设置"));
        list.add(new MineLVItemBean(R.drawable.offline_download_day,R.drawable.appwidget_next_btn_default,"离线"));
        list.add(new MineLVItemBean(R.drawable.feedback_day,R.drawable.appwidget_next_btn_default,"反馈"));
        return list;
    }
}
