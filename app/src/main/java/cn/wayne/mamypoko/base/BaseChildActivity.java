package cn.wayne.mamypoko.base;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cn.wayne.mamypoko.R;
import cn.wayne.mamypoko.ui.dialog.DialogCore;

/**
 * Created by Pollux on 2015/4/8.
 * //
 */
public abstract class BaseChildActivity extends CommBaseActivity {
    protected Toolbar mToolBar;
    private String saveDir = Environment.getExternalStorageDirectory()
            .getPath() + "/temp_image";
    private String capturePath;
    private static final int RESULT_LOAD_IMAGE = 0;
    private static final int RESULT_CAMERA_IMAGE = 1;

    @Override
    protected void initView() {
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        // toolbar.setLogo(R.drawable.ic_launcher);
        mToolBar.setTitle(setPageTitle());
        setSupportActionBar(mToolBar);
        // Navigation Icon 要.設定在 setSupoortActionBar 才有作用
        // 否則會出現 back bottom
        mToolBar.setNavigationIcon(R.drawable.ic_back);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseChildActivity.this.finish();
            }
        });
        setOverflowButtonAlwaysShow();
        supportInvalidateOptionsMenu();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_child_content, menu);

        if (isMoreShow()) {
            menu.findItem(R.id.menu_more_share).setVisible(true);
            menu.findItem(R.id.menu_more_collect).setVisible(true);
            menu.findItem(R.id.menu_more_report).setVisible(true);
        } else {
            menu.findItem(R.id.menu_more_share).setVisible(false);
            menu.findItem(R.id.menu_more_collect).setVisible(false);
            menu.findItem(R.id.menu_more_report).setVisible(false);
        }

        if (isRightShow()) {
            menu.findItem(R.id.menu_right_btn).setVisible(true);
            menu.findItem(R.id.menu_right_btn).setIcon(setRightBtnIcon());
        } else {
            menu.findItem(R.id.menu_right_btn).setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    private void setOverflowButtonAlwaysShow() {

        try {
            ViewConfiguration cfg = ViewConfiguration.get(this);
            Field mMenuKey = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            mMenuKey.setAccessible(true);
            mMenuKey.setBoolean(cfg, false);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    protected boolean isRightShow() {
        return true;
    }

    protected boolean isMoreShow() {
        return true;
    }

    protected abstract int setRightBtnIcon();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_right_btn:
                onRightButtonClick();
                break;
            case R.id.menu_more_share:
                onShareButtonClick();
                break;
            case R.id.menu_more_collect:
                onCollectButtonClick();
                break;
            case R.id.menu_more_report:
                onReportButtonClick();
                break;
        }
        return true;
    }

    protected void onReportButtonClick() {
    }

    protected void onCollectButtonClick() {
    }

    protected void onShareButtonClick() {
    }

    protected void onRightButtonClick() {
    }

    protected abstract String setPageTitle();


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_MENU) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected final AlertDialog showPhotoTakeDialog() {
        return DialogCore.showPhotoItemDialog(this, mOnAlbumClick, mOnCarmeraClick);
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
                Toast.makeText(BaseChildActivity.this, "请确认已经插入SD卡",
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
            onPhotoGetSuccessed(picturePath);
        }

        if (requestCode == RESULT_CAMERA_IMAGE && resultCode == RESULT_OK) {
            Bitmap photoBitmap = null;
            File f = new File(capturePath);
            if (f != null && f.exists()) {
                onPhotoGetSuccessed(capturePath);
            } else {
                Toast.makeText(this, "图片加载失败", Toast.LENGTH_SHORT).show();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 当需要拍照的时候重写此方法，可以通过FileUtil中的getImage(path)多图片进行处理也可以自行解决
     *
     * @param picturePath
     */
    protected void onPhotoGetSuccessed(String picturePath) {
    }


    protected void setPageTitle(String title) {
        mToolBar.setTitle(title);
    }
}
