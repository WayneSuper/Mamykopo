package cn.wayne.mamypoko.mode.diary;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import cn.wayne.mamypoko.R;
import cn.wayne.mamypoko.app.AppCore;
import cn.wayne.mamypoko.base.BaseChildActivity;
import cn.wayne.mamypoko.ui.dialog.DialogCore;
import cn.wayne.mamypoko.utils.AppUtil;
import cn.wayne.mamypoko.utils.FileUtil;

public class DiaryActivity extends BaseChildActivity {
    private EditText mEditContent;
    private View mDivider;
    private TextView mTextDate;
    private ImageButton mIBtakephoto;
    private ImageView mImagePhoto;

    @Override
    protected int setRightBtnIcon() {
        return R.drawable.ic_release;
    }

    @Override
    protected String setPageTitle() {
        return getString(R.string.my_add_diary);
    }

    @Override
    protected int setContentViewID() {
        return R.layout.activity_diary;
    }

    @Override
    protected void initEvent() {
        mIBtakephoto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AppUtil.hideKeyBoard(DiaryActivity.this);
                showPhotoTakeDialog();
            }
        });
    }

    @Override
    protected void initData() {
        mTextDate.setText(AppUtil.getDataTime("MM月dd日"));
    }

    @Override
    protected void initView() {
        super.initView();
        mEditContent = getView(R.id.edit_content);
        mDivider = getView(R.id.divide_line);
        mTextDate = getView(R.id.text_date);
        mIBtakephoto = getView(R.id.ib_takephoto);
        mImagePhoto = getView(R.id.image_photo);
    }

    @Override
    protected void onRightButtonClick() {
        AppUtil.hideKeyBoard(this);
        if(TextUtils.isEmpty(mEditContent.getText())){
            Toast.makeText(this,"请输入文字内容",Toast.LENGTH_SHORT).show();
            return;
        }

    }





    private void showImageContainer(int flag) {
        mDivider.setVisibility(flag);
        mImagePhoto.setVisibility(flag);
    }

    @Override
    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);
    }

    @Override
    protected boolean isRightShow() {
        return true;
    }

    @Override
    protected boolean isMoreShow() {
        return false;
    }

    @Override
    protected void onPhotoGetSuccessed(String picturePath) {
        mImagePhoto.setImageBitmap(FileUtil.getimage(picturePath));
        showImageContainer(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
