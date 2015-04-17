package cn.wayne.mamypoko.mode.noeat;

/**
 * Created by Pollux on 2015/4/15.
 * //
 */

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.wayne.mamypoko.R;

public class NoeatDetailAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<NoeatDetailModel.DetailImage.DataEntity> mData;

    public NoeatDetailAdapter(Context context, List<NoeatDetailModel.DetailImage.DataEntity> mData) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public NoeatDetailModel.DetailImage.DataEntity getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_noeat_detail, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.textName = (TextView) convertView.findViewById(R.id.text_name);
            viewHolder.imageType = (ImageView) convertView.findViewById(R.id.image_type);
            viewHolder.textType = (TextView) convertView.findViewById(R.id.text_type);
            viewHolder.textContent = (TextView) convertView.findViewById(R.id.text_content);

            convertView.setTag(viewHolder);
        }
        initializeViews(getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(NoeatDetailModel.DetailImage.DataEntity object, ViewHolder holder) {
        holder.textName.setText(object.getTitle());
        if ("1".equals(object.getCan_eat())) {
            holder.imageType.setImageResource(R.drawable.eat_yes);
            holder.textType.setTextColor(Color.parseColor("#79D646"));
            holder.textType.setText(context.getString(R.string.eat_yes));
        } else if ("2".equals(object.getCan_eat())) {
            holder.imageType.setImageResource(R.drawable.eat_danger);
            holder.textType.setTextColor(Color.parseColor("#FFBF00"));
            holder.textType.setText(context.getString(R.string.eat_danger));
        } else {
            holder.imageType.setImageResource(R.drawable.eat_no);
            holder.textType.setTextColor(Color.parseColor("#E33200"));
            holder.textType.setText(context.getString(R.string.eat_no));
        }

        holder.textContent.setText(object.getContent());
    }

    protected class ViewHolder {
        private TextView textName;
        private ImageView imageType;
        private TextView textType;
        private TextView textContent;
    }
}
