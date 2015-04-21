package cn.wayne.mamypoko.mode.home.adapter;

/**
 * Created by Lumia on 2015/4/18.
 */

import android.content.Context;
import android.net.Uri;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import cn.wayne.mamypoko.R;
import cn.wayne.mamypoko.mode.home.entity.FindBtyEntity;

public class FindBtyAdapter extends BaseAdapter {

    private List<FindBtyEntity.DataEntity> objects ;

    private Context context;
    private LayoutInflater layoutInflater;

    public FindBtyAdapter(Context context, List<FindBtyEntity.DataEntity> objects) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public FindBtyEntity.DataEntity getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_find_bty, parent,false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.draweeIcon = (SimpleDraweeView) convertView.findViewById(R.id.drawee_icon);
            viewHolder.textName = (TextView) convertView.findViewById(R.id.text_name);
            viewHolder.textAgeStr = (TextView) convertView.findViewById(R.id.text_age_str);
            viewHolder.textShowTime = (TextView) convertView.findViewById(R.id.text_show_time);
            viewHolder.textArticalTitle = (TextView) convertView.findViewById(R.id.text_artical_title);
            viewHolder.ll_small_image_container = (LinearLayout) convertView.findViewById(R.id.ll_small_image_container);
            viewHolder.textType = (TextView) convertView.findViewById(R.id.text_type);
            viewHolder.textReplysNum = (TextView) convertView.findViewById(R.id.text_replys_num);

            convertView.setTag(viewHolder);
        }
        initializeViews(getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(FindBtyEntity.DataEntity object, ViewHolder holder) {

        holder.draweeIcon.setImageURI(Uri.parse(object.getAvatar()));
        holder.textName.setText(object.getNickname());
        holder.textAgeStr.setText(object.getAge_str());
        holder.textShowTime.setText(object.getShowdated());
        holder.textArticalTitle.setText(object.getTitle());
        List<String> list = object.getPic_small();
        int size = list.size();
        holder.ll_small_image_container.removeAllViews();
        for(int i=0;i< (size <= 3 ? size:3);i++) {
            String url=  list.get(i);
            SimpleDraweeView drawee = new SimpleDraweeView(context);
            drawee.setImageURI(Uri.parse(url));
            drawee.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FOCUS_CROP);
            drawee.getHierarchy().setPlaceholderImage(R.drawable.default_home_list_bg);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    0
                    ,(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,80,context.getResources().getDisplayMetrics())
            );
            lp.weight = 1;
            lp.leftMargin = 10;
            drawee.setLayoutParams(lp);
            holder.ll_small_image_container.addView(drawee);
        }

        holder.textType.setText(object.getNickname());
        holder.textReplysNum.setText(object.getRe_num());
    }

    protected class ViewHolder {
        private SimpleDraweeView draweeIcon;
        private TextView textName;
        private TextView textAgeStr;
        private TextView textShowTime;
        private TextView textArticalTitle;
        private LinearLayout ll_small_image_container;
        private TextView textType;
        private TextView textReplysNum;
    }
}
