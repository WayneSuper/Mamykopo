package cn.wayne.mamypoko.ui.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import cn.wayne.mamypoko.R;
import cn.wayne.mamypoko.utils.AppUtil;

/**
 * Created by Lumia on 2015/4/12.
 */
public class DialogCore {

    public static AlertDialog showPhotoItemDialog(Context context, final View.OnClickListener onAlbumItemClick, final View.OnClickListener onCameraItemClick) {
           final AlertDialog dialog = new AlertDialog.Builder(context).create();
           dialog.show();
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_photo_item,null);
        Button btnAlbum = (Button) view.findViewById(R.id.btn_select_from_album);
        Button btnCarmare = (Button) view.findViewById(R.id.btn_select_from_carmare);
        dialog.getWindow().setContentView(view);
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = 3*AppUtil.getScreenWidth(context)/4;
        dialog.getWindow().setAttributes(lp);
        btnAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialog!=null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                if(onAlbumItemClick!=null) {
                    onAlbumItemClick.onClick(v);
                }
            }
        });
        btnCarmare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialog!=null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                if(onCameraItemClick!=null) {
                    onCameraItemClick.onClick(v);
                }
            }
        });
        return dialog;
    }


}
