package cn.wayne.mamypoko.mode.diary;

/**
 * Created by Lumia on 2015/4/12.
 */
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.View;

import cn.wayne.mamypoko.R;
import cn.wayne.mamypoko.ui.view.TimeLineCircle;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

public class DiaryAdapter extends BaseAdapter {

    private List<Diary> mList;
    private Context context;
    private LayoutInflater layoutInflater;

    public DiaryAdapter(Context context,List<Diary> mList) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Diary getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_diary_timeline, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.draweee_image = (SimpleDraweeView) convertView.findViewById(R.id.draweee_image);
            viewHolder.linecircleTimeLineLeft = (TimeLineCircle) convertView.findViewById(R.id.linecircle_time_line_left);
            viewHolder.image1 = (ImageView) convertView.findViewById(R.id.image1);
            viewHolder.textTimeLineTime = (TextView) convertView.findViewById(R.id.text_time_line_time);
            viewHolder.textTimeLineContent = (TextView) convertView.findViewById(R.id.text_time_line_content);
            convertView.setTag(viewHolder);
        }
        initializeViews(mList.get(position), (ViewHolder) convertView.getTag(),position);
        return convertView;
    }

    private void initializeViews(Diary object, ViewHolder holder,int position) {
        if(object.getIsfirst().equals("N")){
            holder.linecircleTimeLineLeft.setVisibility(View.GONE);
        }else {
            holder.linecircleTimeLineLeft.setVisibility(View.VISIBLE);
        }

        holder.textTimeLineTime.setText(object.getTime());
        holder.textTimeLineContent.setText(object.getContent());
        if(object.getPic()!=null && object.getPic().length()>4) {
            holder.draweee_image.setVisibility(View.VISIBLE);
            holder.draweee_image.setImageURI(Uri.parse(object.getPic()));
        }else {
            holder.draweee_image.setVisibility(View.GONE);
        }

        holder.linecircleTimeLineLeft.setMoth(object.getMonth()+"月");

        holder.linecircleTimeLineLeft.setDay(object.getDay()+"日");


    }

    protected class ViewHolder {
        private TimeLineCircle linecircleTimeLineLeft;
        private ImageView image1;
        private TextView textTimeLineTime;
        private TextView textTimeLineContent;
        private SimpleDraweeView draweee_image;
    }
}
