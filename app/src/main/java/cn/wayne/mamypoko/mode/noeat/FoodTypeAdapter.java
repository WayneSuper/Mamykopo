package cn.wayne.mamypoko.mode.noeat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.imagepipeline.request.ImageRequest;

import cn.wayne.mamypoko.R;

/**
 * Created by Pollux on 2015/4/14.
 * //
 */
public class FoodTypeAdapter extends BaseAdapter {

    private int[] btms = new int[]{R.drawable.eat_1, R.drawable.eat_2, R.drawable.eat_3, R.drawable.eat_4,
            R.drawable.eat_5, R.drawable.eat_6, R.drawable.eat_7, R.drawable.eat_8, R.drawable.eat_9,
            R.drawable.eat_10, R.drawable.eat_11, R.drawable.eat_12};
    private String[] types = null;
    LayoutInflater inflater;

    public FoodTypeAdapter(Context context) {
        types = new String[]{context.getString(R.string.eat1), context.getString(R.string.eat2), context.getString(R.string.eat3), context.getString(R.string.eat4), context.getString(R.string.eat5), context.getString(R.string.eat6), context.getString(R.string.eat7),
                context.getString(R.string.eat8), context.getString(R.string.eat9), context.getString(R.string.eat10), context.getString(R.string.eat11), context.getString(R.string.eat12)};
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return types.length;
    }

    @Override
    public String getItem(int position) {
        return types[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_noeat_grid, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.llContainer = (LinearLayout) convertView.findViewById(R.id.ll_container);
            viewHolder.imageType = (ImageView) convertView.findViewById(R.id.image_type);
            viewHolder.textType = (TextView) convertView.findViewById(R.id.text_type);
            viewHolder.viewBottomLine = (ImageView) convertView.findViewById(R.id.view_bottom_line);
            viewHolder.viewRightLine = (ImageView) convertView.findViewById(R.id.view_right_line);
            convertView.setTag(viewHolder);
        }
        initializeViews(position, (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(int position, ViewHolder holder) {

        holder.imageType.setImageResource(btms[position]);
        holder.textType.setText(types[position]);
        if((position+1)%3==0) {
            holder.viewRightLine.setVisibility(View.GONE);
        }else {
            holder.viewRightLine.setVisibility(View.VISIBLE);
        }

        if(position == types.length-1 || position == types.length-2||position == types.length-3) {
            holder.viewBottomLine.setVisibility(View.GONE);
        }else {
            holder.viewBottomLine.setVisibility(View.VISIBLE);
        }

    }

    protected class ViewHolder {
        private LinearLayout llContainer;
        private ImageView imageType;
        private TextView textType;
        private ImageView viewBottomLine;
        private ImageView viewRightLine;
    }


}
