package com.base.base.baseui.widget.album;

import android.Manifest;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import com.base.base.ImageConst;
import com.base.base.utilcode.util.AppUtils;
import com.base.base.utilcode.util.ToastUtils;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.List;


/**
 * Created by zhangyinlei on 2018/5/19 13:35
 */
public class AlbumUtil {

    public static void ChooseAlbum(final Activity activity, final int maxSelect) {
        AndPermission.with(activity)
                .runtime()
                .permission(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        Matisse.from(activity)
                                .choose(MimeType.allOf())
                                .countable(true)
                                .addFilter(new MaxSizeFilter(1, 1, 1))
                                .capture(false)
                                .maxSelectable(maxSelect)
                                .captureStrategy(new CaptureStrategy(true, AppUtils.getAppInfo().getPackageName() + ".fileprovider"))
                                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                                .thumbnailScale(0.85f)
                                .imageEngine(new GlideEngine())
                                .forResult(ImageConst.REQUEST_CODE_CHOOSE);
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        ToastUtils.showShort("请打开相关权限！");
                    }
                })
                .start();
    }

    public static void ChooseAlbum(final Fragment fragment, final int maxSelect) {
        AndPermission.with(fragment)
                .runtime()
                .permission(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        Matisse.from(fragment)
                                .choose(MimeType.allOf())
                                .addFilter(new MaxSizeFilter(1, 1, 1))
                                .countable(true)
                                .capture(false)
                                .maxSelectable(maxSelect)
                                .captureStrategy(new CaptureStrategy(true, AppUtils.getAppInfo().getPackageName() + ".fileprovider"))
                                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                                .thumbnailScale(0.85f)
                                .imageEngine(new GlideEngine())
                                .forResult(ImageConst.REQUEST_CODE_CHOOSE);
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        ToastUtils.showShort("请打开相关权限！");
                    }
                })
                .start();
    }

}
