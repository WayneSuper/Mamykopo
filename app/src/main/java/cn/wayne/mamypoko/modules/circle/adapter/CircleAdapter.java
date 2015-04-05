package cn.wayne.mamypoko.modules.circle.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import cn.wayne.mamypoko.R;
import cn.wayne.mamypoko.modules.circle.entity.CircleModel;

/**
 * Created by Pollux on 2015/4/2.
 * //
 */
public class CircleAdapter extends ArrayAdapter<CircleModel> {
    List<CircleModel> objects;
    LayoutInflater inflater;

    public CircleAdapter(Context context, int resource, List<CircleModel> objects) {
        super(context, resource, objects);
        inflater = LayoutInflater.from(context);
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        CircleModel item = getItem(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_circle, parent, false);
            holder.logo = (ImageView) convertView.findViewById(R.id.image_item_circle);
            holder.type = (TextView) convertView.findViewById(R.id.text_item_circle_type);
            holder.title = (TextView) convertView.findViewById(R.id.text_item_circle_small_title);
            holder.image1 = (SimpleDraweeView) convertView.findViewById(R.id.image_circle_small_1);
            holder.image2 = (SimpleDraweeView) convertView.findViewById(R.id.image_circle_small_2);
            holder.image3 = (SimpleDraweeView) convertView.findViewById(R.id.image_circle_small_3);
            holder.image4 = (SimpleDraweeView) convertView.findViewById(R.id.image_circle_small_4);
            holder.image5 = (SimpleDraweeView) convertView.findViewById(R.id.image_circle_small_5);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.logo.setImageResource(item.getLogo());
        holder.type.setText(item.getType());
        holder.title.setText(item.getTitle());
        int size = item.getImages().size();
        for (int i = 0; i < size; i++) {
            switch (i) {
                case 0:
                    holder.image1.setImageURI(Uri.parse(item.getImages().get(i)));
                    break;
                case 1:
                    holder.image2.setImageURI(Uri.parse(item.getImages().get(i)));
                    break;
                case 2:
                    holder.image3.setImageURI(Uri.parse(item.getImages().get(i)));
                    break;
                case 3:
                    holder.image4.setImageURI(Uri.parse(item.getImages().get(i)));
                    break;
                case 4:
                    holder.image5.setImageURI(Uri.parse(item.getImages().get(i)));
                    break;
            }
        }
        return convertView;
    }


    private static final class ViewHolder {
        public ImageView logo;
        public TextView type;
        public TextView title;
        public SimpleDraweeView image1;
        public SimpleDraweeView image2;
        public SimpleDraweeView image3;
        public SimpleDraweeView image4;
        public SimpleDraweeView image5;
    }

}
