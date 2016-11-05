package qianfeng.com.phoenixnews.adapter;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import qianfeng.com.phoenixnews.R;
import qianfeng.com.phoenixnews.utils.Constant;

import java.util.List;

import qianfeng.com.phoenixnews.bean.LVItemBean;

/**
 * Created by Administrator on 2016/11/1 0001.
 */

public class MyLVAdapter extends BaseAdapter {

    private Context mContext;
    private List<LVItemBean> mList;
    private LayoutInflater mInflater;

    public MyLVAdapter(Context context, List<LVItemBean> list) {
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
        PTViewHolder ptViewHolder = null;
        PhoViewHolder phoViewHolder = null;
        TextViewHolder textViewHolder = null;
        IVLLViewHolder ivllViewHolder = null;
        TVLLViewHolder tvllViewHolder = null;
        int itemViewType = getItemViewType(position);
        if (convertView == null) {
            switch (itemViewType) {
                case Constant.PHOTO_TEXT:
                    ptViewHolder = new PTViewHolder();
                    convertView = mInflater.inflate(R.layout.photo_text_layout, parent, false);
                    ptViewHolder.thumbnail = (ImageView) convertView.findViewById(R.id.iv_thumbnail);
                    ptViewHolder.title = (TextView) convertView.findViewById(R.id.tv_title);
                    ptViewHolder.updateTime = (TextView) convertView.findViewById(R.id.tv_updatetime);
                    ptViewHolder.commentAll = (TextView) convertView.findViewById(R.id.tv_commentsall);
                    convertView.setTag(ptViewHolder);
                    break;
                case Constant.PHOTO:
                    phoViewHolder = new PhoViewHolder();
                    convertView = mInflater.inflate(R.layout.photo_layout, parent, false);
                    phoViewHolder.thumbnail1 = (ImageView) convertView.findViewById(R.id.iv_thumbnail1);
                    phoViewHolder.thumbnail2 = (ImageView) convertView.findViewById(R.id.iv_thumbnail2);
                    phoViewHolder.thumbnail3 = (ImageView) convertView.findViewById(R.id.iv_thumbnail3);
                    phoViewHolder.commentAll = (TextView) convertView.findViewById(R.id.tv_commentsall);
                    phoViewHolder.slideCount = (TextView) convertView.findViewById(R.id.tv_slidecount);
                    phoViewHolder.title = (TextView) convertView.findViewById(R.id.tv_title);
                    phoViewHolder.updateTime = (TextView) convertView.findViewById(R.id.tv_updatetime);
                    convertView.setTag(phoViewHolder);
                    break;
                case Constant.TEXT:
                    textViewHolder = new TextViewHolder();
                    convertView = mInflater.inflate(R.layout.text_layout, parent, false);
                    textViewHolder.commentAll = (TextView) convertView.findViewById(R.id.tv_commentsall);
                    textViewHolder.title = (TextView) convertView.findViewById(R.id.tv_title);
                    textViewHolder.updateTime = (TextView) convertView.findViewById(R.id.tv_updatetime);
                    convertView.setTag(textViewHolder);
                    break;
                case Constant.IMAGE_LL:
                    ivllViewHolder = new IVLLViewHolder();
                    convertView = mInflater.inflate(R.layout.imageview_linearlayout, parent, false);
                    ivllViewHolder.picture = (ImageView) convertView.findViewById(R.id.iv_picture);
                    ivllViewHolder.commentAll = (TextView) convertView.findViewById(R.id.tv_commentsall);
                    ivllViewHolder.likes = (TextView) convertView.findViewById(R.id.tv_likes);
                    convertView.setTag(ivllViewHolder);
                    break;
                case Constant.TEXTVIEW_LL:
                    tvllViewHolder = new TVLLViewHolder();
                    convertView = mInflater.inflate(R.layout.textview_linearlayout, parent, false);
                    tvllViewHolder.commentAll = (TextView) convertView.findViewById(R.id.tv_commentsall);
                    tvllViewHolder.content = (TextView) convertView.findViewById(R.id.tv_content);
                    tvllViewHolder.likes = (TextView) convertView.findViewById(R.id.tv_likes);
                    convertView.setTag(tvllViewHolder);
                    break;
            }
        } else {
            switch (itemViewType) {
                case Constant.PHOTO_TEXT:
                    ptViewHolder = (PTViewHolder) convertView.getTag();
                    break;
                case Constant.PHOTO:
                    phoViewHolder = (PhoViewHolder) convertView.getTag();
                    break;
                case Constant.TEXT:
                    textViewHolder = (TextViewHolder) convertView.getTag();
                    break;
                case Constant.IMAGE_LL:
                    ivllViewHolder = (IVLLViewHolder) convertView.getTag();
                    break;
                case Constant.TEXTVIEW_LL:
                    tvllViewHolder = (TVLLViewHolder) convertView.getTag();
                    break;
            }
        }
        LVItemBean lvItemBean = mList.get(position);
        switch (itemViewType) {
            case Constant.PHOTO_TEXT:
                String thumbnail = lvItemBean.getThumbnail();
                if (!thumbnail.isEmpty()) {
                    Picasso.with(mContext).load(thumbnail).into(ptViewHolder.thumbnail);
                }
                ptViewHolder.commentAll.setText(lvItemBean.getCommentsall());
                ptViewHolder.updateTime.setText(lvItemBean.getUpdateTime());
                ptViewHolder.title.setText(lvItemBean.getTitle());
                break;
            case Constant.PHOTO:
                String[] imgs = lvItemBean.getImgs();
                Picasso.with(mContext).load(imgs[0]).into(phoViewHolder.thumbnail1);
                Picasso.with(mContext).load(imgs[1]).into(phoViewHolder.thumbnail2);
                Picasso.with(mContext).load(imgs[2]).into(phoViewHolder.thumbnail3);
                phoViewHolder.updateTime.setText(lvItemBean.getUpdateTime());
                phoViewHolder.title.setText(lvItemBean.getTitle());
                phoViewHolder.slideCount.setText(lvItemBean.getSlideCount()+"");
                phoViewHolder.commentAll.setText(lvItemBean.getCommentsall());
                break;
            case Constant.TEXT:
                textViewHolder.updateTime.setText(lvItemBean.getUpdateTime());
                textViewHolder.title.setText(lvItemBean.getTitle());
                textViewHolder.commentAll.setText(lvItemBean.getCommentsall());
                break;
            case Constant.TEXTVIEW_LL:
                tvllViewHolder.likes.setText(lvItemBean.getLikes()+"");
                tvllViewHolder.content.setText(lvItemBean.getContent());
                tvllViewHolder.commentAll.setText(lvItemBean.getCommentsall());
                break;
            case Constant.IMAGE_LL:
                String width = lvItemBean.getWidth();
                String height = lvItemBean.getHeight();
                ViewGroup.LayoutParams layoutParams = ivllViewHolder.picture.getLayoutParams();
                layoutParams.width = Integer.parseInt(width);
                layoutParams.height = Integer.parseInt(height);
                ivllViewHolder.picture.setLayoutParams(layoutParams);
                String thumbnail1 = lvItemBean.getThumbnail();
                if (!thumbnail1.isEmpty()) {
                    Picasso.with(mContext).load(thumbnail1).into(ivllViewHolder.picture);
                }
                ivllViewHolder.likes.setText(lvItemBean.getLikes()+"");
                ivllViewHolder.commentAll.setText(lvItemBean.getCommentsall());
                break;
        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getType();
    }

    @Override
    public int getViewTypeCount() {
        return 5;
    }

    class PTViewHolder {
        ImageView thumbnail;
        TextView title,updateTime, commentAll;
    }

    class PhoViewHolder{
        TextView title,updateTime,slideCount,commentAll;
        ImageView thumbnail1,thumbnail2,thumbnail3;
    }

    class TextViewHolder{
        TextView title,updateTime, commentAll;
    }

    class IVLLViewHolder{
        ImageView picture;
        TextView likes, commentAll;
    }

    class TVLLViewHolder{
        TextView content,likes, commentAll;
    }
}
