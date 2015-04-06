package cn.wayne.mamypoko.mode.personal.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import cn.wayne.mamypoko.R;

public class PersonalFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personal, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SimpleDraweeView mImageView = (SimpleDraweeView) view.findViewById(R.id.civ_header_photo);

        mImageView.setImageURI(Uri.parse("http://www.qipaox.com/tupian/200810/20081051924582.jpg"));
    }
}
