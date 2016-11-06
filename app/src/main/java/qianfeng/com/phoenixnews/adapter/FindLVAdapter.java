package qianfeng.com.phoenixnews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import qianfeng.com.phoenixnews.R;
import qianfeng.com.phoenixnews.bean.FindLVItemBean;
import qianfeng.com.phoenixnews.utils.Constant;

/**
 * Created by superren on 2016/11/6.
 */

public class FindLVAdapter extends BaseAdapter {

    private Context context;
    private List<FindLVItemBean> list;
    private LayoutInflater inflater;

    public FindLVAdapter(Context context, List<FindLVItemBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PTViewHolder ptViewHolder = null;
        PhotoViewHolder photoViewHolder = null;
        int itemViewType = getItemViewType(position);
        if (convertView == null) {
            switch (itemViewType) {
                case Constant.FIND_PHOTO_TEXT:
                    convertView = inflater.inflate(R.layout.find_photo_text, parent, false);
                    ptViewHolder = new PTViewHolder();
                    ptViewHolder.thumbnail = (ImageView) convertView.findViewById(R.id.iv_thumbnail);
                    ptViewHolder.title = (TextView) convertView.findViewById(R.id.tv_title);
                    ptViewHolder.name = (TextView) convertView.findViewById(R.id.tv_name);
                    ptViewHolder.commentsall = (TextView) convertView.findViewById(R.id.tv_commentsall);
                    convertView.setTag(ptViewHolder);
                    break;
                case Constant.FIND_PHOTO:
                    convertView = inflater.inflate(R.layout.find_photo, parent, false);
                    photoViewHolder = new PhotoViewHolder();
                    photoViewHolder.thumbnail1 = (ImageView) convertView.findViewById(R.id.iv_thumbnail1);
                    photoViewHolder.thumbnail2 = (ImageView) convertView.findViewById(R.id.iv_thumbnail2);
                    photoViewHolder.thumbnail3 = (ImageView) convertView.findViewById(R.id.iv_thumbnail3);
                    photoViewHolder.title = (TextView) convertView.findViewById(R.id.tv_title);
                    photoViewHolder.name = (TextView) convertView.findViewById(R.id.tv_name);
                    photoViewHolder.slideCount = (TextView) convertView.findViewById(R.id.tv_slidecount);
                    photoViewHolder.commentsall = (TextView) convertView.findViewById(R.id.tv_commentsall);
                    convertView.setTag(photoViewHolder);
                    break;
            }
        } else {
            switch (itemViewType) {
                case Constant.FIND_PHOTO_TEXT:
                    ptViewHolder = (PTViewHolder) convertView.getTag();
                    break;
                case Constant.FIND_PHOTO:
                    photoViewHolder = (PhotoViewHolder) convertView.getTag();
                    break;
            }
        }
        FindLVItemBean findLVItemBean = list.get(position);
        switch (itemViewType) {
            case Constant.FIND_PHOTO_TEXT:
                String thumbnail = findLVItemBean.getThumbnail();
                if (!thumbnail.isEmpty()) {
                    Picasso.with(context).load(thumbnail).into(ptViewHolder.thumbnail);
                }
                ptViewHolder.title.setText(findLVItemBean.getTitle());
                ptViewHolder.name.setText(findLVItemBean.getName());
                ptViewHolder.commentsall.setText(findLVItemBean.getCommentsall());
                break;
            case Constant.FIND_PHOTO:
                String[] imgs = findLVItemBean.getImgs();
                if (imgs != null && imgs.length > 2) {
                    Picasso.with(context).load(imgs[0]).into(photoViewHolder.thumbnail1);
                    Picasso.with(context).load(imgs[1]).into(photoViewHolder.thumbnail2);
                    Picasso.with(context).load(imgs[2]).into(photoViewHolder.thumbnail3);
                }
                photoViewHolder.title.setText(findLVItemBean.getTitle());
                photoViewHolder.name.setText(findLVItemBean.getName());
                photoViewHolder.slideCount.setText(findLVItemBean.getSlideCount() + "");
                photoViewHolder.commentsall.setText(findLVItemBean.getCommentsall());
                break;
        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    class PTViewHolder {
        ImageView thumbnail;
        TextView title, name, commentsall;
    }

    class PhotoViewHolder {
        ImageView thumbnail1, thumbnail2, thumbnail3;
        TextView title, name, slideCount, commentsall;
    }
}
