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
import cn.wayne.mamypoko.base.BaseChildActivity;
import cn.wayne.mamypoko.ui.dialog.DialogCore;
import cn.wayne.mamypoko.utils.FileUtil;

public class DiaryActivity extends BaseChildActivity {


    private static final int RESULT_LOAD_IMAGE = 0;
    private static final int RESULT_CAMERA_IMAGE = 1;

    private EditText mEditContent;
    private View mDivider;
    private TextView mTextDate;
    private ImageButton mIBtakephoto;
    private ImageView mImagePhoto;
    private String saveDir = Environment.getExternalStorageDirectory()
            .getPath() + "/temp_image";
    private Bitmap photo;

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

    }

    @Override
    protected void initData() {
        mEditContent = getView(R.id.edit_content);
        mDivider = getView(R.id.divide_line);
        mTextDate = getView(R.id.text_date);
        mIBtakephoto = getView(R.id.ib_takephoto);
        mImagePhoto = getView(R.id.image_photo);

    }

    @Override
    protected void onRightButtonClick() {
        DialogCore.showPhotoItemDialog(this, mOnAlbumClick, mOnCarmeraClick);
    }

    private View.OnClickListener mOnAlbumClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, RESULT_LOAD_IMAGE);
        }
    };

    private File mPhotoFile;
    private String localTempImgDir = "temp_image";
    private String localTempImgFileName = "temp.jpg";
    private String capturePath;
    private View.OnClickListener mOnCarmeraClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String state = Environment.getExternalStorageState();
            if (state.equals(Environment.MEDIA_MOUNTED)) {
                Intent getImageByCamera = new Intent(
                        "android.media.action.IMAGE_CAPTURE");
                String out_file_path = saveDir;
                File dir = new File(out_file_path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                capturePath = saveDir
                        + "/headImg.jpg";
                getImageByCamera.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(new File(capturePath)));
                getImageByCamera.putExtra(
                        MediaStore.EXTRA_VIDEO_QUALITY, 1);
                startActivityForResult(getImageByCamera, RESULT_CAMERA_IMAGE);
            } else {
                Toast.makeText(DiaryActivity.this, "请确认已经插入SD卡",
                        Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            mImagePhoto.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            showImageContainer(View.VISIBLE);
        }

        if (requestCode == RESULT_CAMERA_IMAGE && resultCode == RESULT_OK) {
            Bitmap photoBitmap = null;
            File f = new File(capturePath);
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            Bitmap bitmap = BitmapFactory.decodeFile(capturePath);
            photoBitmap = FileUtil.getBitmapFromFile(f,bitmap.getWidth()/2,bitmap.getHeight()/2);
            mImagePhoto.setImageBitmap(photoBitmap);
            showImageContainer(View.VISIBLE);
        }
        super.onActivityResult(requestCode, resultCode, data);
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

    private Bitmap getBitmapFromUri(Bitmap bitmap, Uri uri) {
        try {
            bitmap = MediaStore.Images.Media.getBitmap(
                    this.getContentResolver(), uri);
            return bitmap;
        } catch (Exception e) {
            return null;
        }
    }

    private int getExifOrientation(String filepath) {
        int degree = 0;
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(filepath);
        } catch (IOException ex) {
        }
        if (exif != null) {
            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, -1);
            if (orientation != -1) {
                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        degree = 90;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        degree = 180;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        degree = 270;
                        break;
                }
            }
        }
        return degree;
    }

    public String getRealPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null,
                null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    @Override
    protected void onDestroy() {
        destoryImage();
        super.onDestroy();
    }

    private void destoryImage() {
        if (photo != null) {
            photo.recycle();
            photo = null;
        }
    }
}
