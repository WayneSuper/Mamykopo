package cn.wayne.mamypoko.mode.bbs;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import cn.wayne.mamypoko.R;
import cn.wayne.mamypoko.mode.home.entity.Beauty;

/**
 * Created by Pollux on 2015/4/16.
 * //
 */
public class BbsAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Beauty.BeautyEntity> mList;

    public BbsAdapter(Context context, List<Beauty.BeautyEntity> data) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.mList = data;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Beauty.BeautyEntity getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_hot_bbs, parent,false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.draweeIcon = (SimpleDraweeView) convertView.findViewById(R.id.drawee_icon);
            viewHolder.textNikename = (TextView) convertView.findViewById(R.id.text_nikename);
            viewHolder.imageJing = (ImageView) convertView.findViewById(R.id.image_jing);
            viewHolder.textArticalTitle = (TextView) convertView.findViewById(R.id.text_artical_title);
            viewHolder.imageHaspic = (ImageView) convertView.findViewById(R.id.image_haspic);
            viewHolder.textAgeStr = (TextView) convertView.findViewById(R.id.text_age_str);
            viewHolder.textArticalReplys = (TextView) convertView.findViewById(R.id.text_artical_replys);
            viewHolder.textShowTime = (TextView) convertView.findViewById(R.id.text_show_time);
            convertView.setTag(viewHolder);
        }
        initializeViews(getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(Beauty.BeautyEntity object, ViewHolder holder) {
        holder.draweeIcon.setImageURI(Uri.parse(object.getAvatar()));
        holder.textNikename.setText(object.getNickname());
        if(object.getGold_added()==1) {
            holder.imageJing.setVisibility(View.VISIBLE);
        }else {
            holder.imageJing.setVisibility(View.GONE);
        }


        holder.textArticalTitle.setText(object.getTitle());
        if(!"0".equals(object.getPic())) {
            holder.imageHaspic.setVisibility(View.VISIBLE);
        }else {
            holder.imageHaspic.setVisibility(View.GONE);
        }
        holder.textAgeStr.setText(object.getAge_str());
        holder.textArticalReplys.setText(object.getRe_num());
        holder.textShowTime.setText(object.getShowdated());
    }

    protected class ViewHolder {
        private SimpleDraweeView draweeIcon;
        private TextView textNikename;
        private ImageView imageJing;
        private TextView textArticalTitle;
        private ImageView imageHaspic;
        private TextView textAgeStr;
        private TextView textArticalReplys;
        private TextView textShowTime;

    }
}
