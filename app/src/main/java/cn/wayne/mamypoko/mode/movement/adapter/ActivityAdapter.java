package cn.wayne.mamypoko.mode.movement.adapter;

import android.content.Context;
import android.net.Uri;
import android.text.Layout;
import android.util.TimeUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import cn.wayne.mamypoko.R;
import cn.wayne.mamypoko.mode.movement.entity.ActMode;
import cn.wayne.mamypoko.mode.movement.entity.DataEntity;

/**
 * Created by Lumia on 2015/4/6.
 */
public class ActivityAdapter extends ArrayAdapter<DataEntity> {
   private List<DataEntity> list;
   private Context mContext;
   private LayoutInflater inflater;
    public ActivityAdapter(Context mContext, List<DataEntity> list) {
        super(mContext,R.layout.item_activity,list);
        this.list = list;
        this.mContext = mContext;
        inflater = LayoutInflater.from(this.mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DataEntity item = list.get(position);
        ViewHolder holder = null;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_activity,parent,false);
            holder.pic = (SimpleDraweeView) convertView.findViewById(R.id.drawView_act_pic);
            holder.jps = (TextView) convertView.findViewById(R.id.text_act_jps);
            holder.endTime = (TextView) convertView.findViewById(R.id.text_act_endtime);
            holder.state = (TextView) convertView.findViewById(R.id.text_act_state);
            holder.ll_time_container = (LinearLayout) convertView.findViewById(R.id.ll_act_se_time_container);
            holder.over = (ImageView) convertView.findViewById(R.id.image_act_over);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.pic.setImageURI(Uri.parse(item.getPic()));
        holder.jps.setText(item.getPrize_num());
        holder.endTime.setText(item.getTime_str()+"");
        if(item.getTime_type() == 2) {
            holder.ll_time_container.setVisibility(View.GONE);
            holder.state.setVisibility(View.VISIBLE);
            holder.over.setVisibility(View.VISIBLE);
        }else {
            holder.ll_time_container.setVisibility(View.VISIBLE);
            holder.state.setVisibility(View.GONE);
            holder.over.setVisibility(View.GONE);
        }
        return convertView;
    }


    private static final class ViewHolder {
        public SimpleDraweeView pic;
        public ImageView over;
        public TextView jps;
        public TextView endTime;
        public TextView state;
        public LinearLayout ll_time_container;
    }
}
