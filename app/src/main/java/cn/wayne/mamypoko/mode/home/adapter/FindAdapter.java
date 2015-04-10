package cn.wayne.mamypoko.mode.home.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import cn.wayne.mamypoko.R;
import cn.wayne.mamypoko.mode.home.entity.Beauty;

/**
 * Created by Lumia on 2015/3/30.
 */
public class FindAdapter extends BaseAdapter {


    private Context mContext;
    private List<Beauty.BeautyEntity> mList;
    private LayoutInflater inflater;

    public FindAdapter(Context mContext, List<Beauty.BeautyEntity> mList) {
        this.mContext = mContext;
        this.mList = mList;
        this.inflater = LayoutInflater.from(this.mContext);
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_home_find_bty, parent, false);
            holder.displayImage = (SimpleDraweeView) convertView.findViewById(R.id.image_home_find);
            holder.title = (TextView) convertView.findViewById(R.id.text_home_find_name);
            holder.header = (SimpleDraweeView) convertView.findViewById(R.id.image_home_find_header);
            holder.articleType = (TextView) convertView.findViewById(R.id.text_home_find_type);
            holder.pinglunNum = (TextView) convertView.findViewById(R.id.text_home_find_num);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Beauty.BeautyEntity item = mList.get(position);
        holder.displayImage.setImageURI(Uri.parse(item.getUrl()));
        holder.title.setText(item.getTitle());
       holder.header.setImageURI(Uri.parse(item.getAvatar()));
        holder.articleType.setText(item.getFrom());
        holder.pinglunNum.setText(item.getRe_num() + "");
        return convertView;
    }


    private static final class ViewHolder {
        public SimpleDraweeView displayImage;
        public TextView title;
        public SimpleDraweeView header;
        public TextView articleType;
        public TextView pinglunNum;
    }
}
