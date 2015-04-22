package cn.wayne.mamypoko.mode.found.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import cn.wayne.mamypoko.R;
import cn.wayne.mamypoko.mode.found.model.Audio;

/**
 * Created by Pollux on 2015/4/22.
 * //
 */
public class SingleAdapter extends ArrayAdapter<Audio> {
    private Context mContext;
    private LayoutInflater mInflater;

    public SingleAdapter(Context context, List<Audio> objects) {
        super(context, 0, objects);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            convertView = mInflater.inflate(R.layout.item_music_single,parent,false);
            holder = new ViewHolder();
            holder.mTName = (TextView) convertView.findViewById(R.id.text_name);
            holder.mTArtistName = (TextView) convertView.findViewById(R.id.text_nikename);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        holder.mTName.setText(getItem(position).getTitle());
        holder.mTArtistName.setText(getItem(position).getArtist());
        return convertView;
    }



    private final class ViewHolder {
        private TextView mTName;
        private TextView mTArtistName;
    }

    /**
     * 根据ListView的当前位置获取分类的首字母的char ascii值
     */
    public int getSectionForPosition(int position) {
        return getItem(position).getSortLetters().charAt(0);
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = getItem(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }

        return -1;
    }


    public Object[] getSections() {
        return null;
    }
}
