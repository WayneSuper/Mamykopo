package cn.wayne.mamypoko.mode.noeat;

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
import cn.wayne.mamypoko.ui.view.StateImageText;

/**
 * Created by Pollux on 2015/4/15.
 * //
 */
public class ResultAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<ResultModel.DataEntity> dataSource;
    public ResultAdapter(Context context,List<ResultModel.DataEntity> data) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.dataSource = data;
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public ResultModel.DataEntity getItem(int position) {
        return dataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_noeat_layout, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.draweeIcon = (SimpleDraweeView) convertView.findViewById(R.id.drawee_icon);
            viewHolder.textName = (TextView) convertView.findViewById(R.id.text_name);
            viewHolder.textNikename = (TextView) convertView.findViewById(R.id.text_nikename);
            viewHolder.stateMm = (StateImageText) convertView.findViewById(R.id.state_mm);
            viewHolder.stateYz = (StateImageText) convertView.findViewById(R.id.state_yz);
            viewHolder.stateBr = (StateImageText) convertView.findViewById(R.id.state_br);
            viewHolder.stateYe = (StateImageText) convertView.findViewById(R.id.state_ye);
            convertView.setTag(viewHolder);
        }
        initializeViews(getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(ResultModel.DataEntity item, ViewHolder holder) {
        holder.draweeIcon.setImageURI(Uri.parse(item.getImg()));
        holder.textName.setText(item.getName());
        holder.textNikename.setText(item.getNick());
        holder.stateMm.setState(Integer.parseInt(item.getStage1()));
        holder.stateYz.setState(Integer.parseInt(item.getStage2()));
        holder.stateBr.setState(Integer.parseInt(item.getStage3()));
        holder.stateYe.setState(Integer.parseInt(item.getStage4()));
    }

    protected class ViewHolder {
        private SimpleDraweeView draweeIcon;
        private TextView textName;
        private TextView textNikename;
        private StateImageText stateMm;
        private StateImageText stateYz;
        private StateImageText stateBr;
        private StateImageText stateYe;
    }
}
