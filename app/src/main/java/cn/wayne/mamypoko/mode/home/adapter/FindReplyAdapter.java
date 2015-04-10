package cn.wayne.mamypoko.mode.home.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Spannable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import cn.wayne.mamypoko.R;
import cn.wayne.mamypoko.mode.home.entity.FindReplyEntity;
import cn.wayne.mamypoko.ui.PasteEditText;
import cn.wayne.mamypoko.utils.SmileUtils;

/**
 * Created by Pollux on 2015/4/10.
 * //
 */
public class FindReplyAdapter extends ArrayAdapter<FindReplyEntity> {
    private LayoutInflater inflater;
    private Context mContext;
    public FindReplyAdapter(Context context, int resource, List<FindReplyEntity> objects) {
        super(context, resource, objects);
        inflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_repy_content, parent, false);
            holder.draweeIcon = (SimpleDraweeView) convertView.findViewById(R.id.drawee_icon);
            holder.textRepyName = (TextView) convertView.findViewById(R.id.text_repy_name);
            holder.textRepyDate = (TextView) convertView.findViewById(R.id.text_repy_date);
            holder.textRepyLocation = (TextView) convertView.findViewById(R.id.text_repy_location);
            holder.textRepyFloor = (TextView) convertView.findViewById(R.id.text_repy_floor);
            holder.textReplyContent = (PasteEditText) convertView.findViewById(R.id.text_reply_content);
            holder.textRepyTime = (TextView) convertView.findViewById(R.id.text_repy_time);

            convertView.setTag(holder);
        }
        initializeViews(getItem(position), (ViewHolder) convertView.getTag(),position+1);
        return convertView;
    }

    private void initializeViews(FindReplyEntity object, ViewHolder holder,int position) {
        String image = object.getAvatar();
        if(image!=null && image.length()>10) {
            holder.draweeIcon.setImageURI(Uri.parse(image));
        }else {
            holder.draweeIcon.getHierarchy().setPlaceholderImage(R.drawable.wel_head);
        }


        holder.textRepyName.setText(object.getNickname());
        holder.textRepyDate.setText(object.getAge_str());
        holder.textRepyLocation.setText(object.getCity());
        holder.textRepyFloor.setText(position+"");
        Spannable span = SmileUtils.getSmiledText(mContext, object.getContent());
        holder.textReplyContent.setText(span , TextView.BufferType.SPANNABLE);
        holder.textRepyTime.setText(object.getShow_date());
    }

    private static final class ViewHolder {
        private TextView textRepyName;
        private TextView textRepyDate;
        private TextView textRepyLocation;
        private TextView textRepyFloor;
        private PasteEditText textReplyContent;
        private TextView textRepyTime;
        private SimpleDraweeView draweeIcon;
    }


}
