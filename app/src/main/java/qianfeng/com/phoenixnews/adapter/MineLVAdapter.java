package qianfeng.com.phoenixnews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import qianfeng.com.phoenixnews.R;
import qianfeng.com.phoenixnews.bean.MineLVItemBean;

/**
 * Created by Administrator on 2016/11/5 0005.
 */

public class MineLVAdapter extends BaseAdapter {

    private Context mContext;
    private List<MineLVItemBean> mList;
    private LayoutInflater mInflater;

    public MineLVAdapter(Context context, List<MineLVItemBean> list) {
        mContext = context;
        mList = list;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.mineactivity_lv_item, parent, false);
            holder.leftImg = ((ImageView) convertView.findViewById(R.id.iv_left));
            holder.rightImg = (ImageView) convertView.findViewById(R.id.iv_right);
            holder.name = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        MineLVItemBean mineLVItemBean = mList.get(position);
        holder.leftImg.setImageResource(mineLVItemBean.getLeftImg());
        holder.rightImg.setImageResource(mineLVItemBean.getRightImg());
        holder.name.setText(mineLVItemBean.getName());
        return convertView;
    }

    class ViewHolder{
        ImageView leftImg, rightImg;
        TextView name;
    }
}
