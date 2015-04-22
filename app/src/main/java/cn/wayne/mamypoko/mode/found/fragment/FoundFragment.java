package cn.wayne.mamypoko.mode.found.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.wayne.mamypoko.R;
import cn.wayne.mamypoko.base.CommBaseFragment;
import cn.wayne.mamypoko.mode.found.LocalMusicActivity;
import cn.wayne.mamypoko.ui.FindItem;

public class FoundFragment extends CommBaseFragment implements View.OnClickListener {

    private FindItem itemMusic;
    @Override
    protected int setRootLayoutID() {
        return R.layout.fragment_found;
    }

    @Override
    protected void initView(View view) {
        itemMusic = (FindItem) view.findViewById(R.id.fi_music);
    }

    @Override
    protected void initData(View view) {

    }

    @Override
    protected void initEvent(View view) {
        itemMusic.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fi_music:
                startActivity(new Intent(mContext, LocalMusicActivity.class));
                break;
        }
    }
}
