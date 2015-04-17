package cn.wayne.mamypoko.mode.circle.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import cn.wayne.mamypoko.R;
import cn.wayne.mamypoko.mode.circle.entity.CircleModel;

/**
 * Created by Pollux on 2015/4/2.
 * //
 */
public class CircleAdapter extends ArrayAdapter<CircleModel.DataEntity> {
    List<CircleModel.DataEntity> objects;
    LayoutInflater inflater;
    Context mContext;

    public CircleAdapter(Context context, int resource, List<CircleModel.DataEntity> objects) {
        super(context, resource, objects);
        inflater = LayoutInflater.from(context);
        this.objects = objects;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        CircleModel.DataEntity item = getItem(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_circle, parent, false);
            holder.logo = (SimpleDraweeView) convertView.findViewById(R.id.image_item_circle);
            holder.type = (TextView) convertView.findViewById(R.id.text_item_circle_type);
            holder.title = (TextView) convertView.findViewById(R.id.text_item_circle_small_title);
            holder.container = (LinearLayout) convertView.findViewById(R.id.ll_circle_container);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.logo.setImageURI(Uri.parse(item.getPic()));
        holder.type.setText(item.getName());
        holder.title.setText(item.getNew_post());
        for (int i = 0; i < (item.getAvatar().size()>5?5:item.getAvatar().size()); i++) {
            SimpleDraweeView draweeView = new SimpleDraweeView(mContext);
            LinearLayout.LayoutParams lp =  new LinearLayout.LayoutParams((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,30f,mContext.getResources().getDisplayMetrics()),
                    (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,30f,mContext.getResources().getDisplayMetrics()));
            lp.setMargins(10,2,0,0);
            lp.gravity = Gravity.BOTTOM;
            draweeView.setLayoutParams(lp);
            draweeView.setImageURI(Uri.parse(item.getAvatar().get(i)));
            draweeView.getHierarchy().setPlaceholderImage(R.drawable.wel_head);
            draweeView.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP);

            holder.container.addView(draweeView);
        }
        return convertView;
    }

    /**
     * <com.facebook.drawee.view.SimpleDraweeView
     * android:id="@+id/image_circle_small_1"
     * android:layout_width="30dp"
     * android:layout_height="30dp"
     * app:placeholderImage="@drawable/wel_head"
     * app:actualImageScaleType="fitXY"
     * />
     */
    private static final class ViewHolder {
        public SimpleDraweeView logo;
        public TextView type;
        public TextView title;
        public LinearLayout container;
    }

}
