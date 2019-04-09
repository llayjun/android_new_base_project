package com.base.base.baseui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.base.base.ImageConst;
import com.base.base.R;
import com.base.base.RouterCommonPath;
import com.base.base.baseui.base.BaseActivity;
import com.base.base.baseui.base.BasePresenter;
import com.base.base.baseui.dialog.ConfirmDialog;
import com.base.base.baseui.listener.OnPositiveClickListener;
import com.base.base.baseui.message.MPreviewImageDelete;
import com.base.base.baseui.widget.photoview.PhotoView;
import com.base.base.utilcode.util.EventBusUtils;
import com.base.base.utilcode.util.GlideUtils;
import com.base.base.utilcode.util.ScreenUtils;

import java.util.List;

/**
 * 预览图片，可只查看，可删除，默认查看
 */
@Route(path = RouterCommonPath.ROUTER_COMMON_PREVIEW_IMAGE)
public class ImagePreviewActivity extends BaseActivity implements View.OnClickListener {
    private ViewPager viewpager;
    private TextView tv_current, tv_total;
    private ImageView mDeleteIv;

    private List<String> pathList;
    private int position = 0;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenUtils.setFullScreen(this);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_image_preview;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        viewpager = findViewById(R.id.viewpager);
        tv_current = findViewById(R.id.tv_current);
        tv_total = findViewById(R.id.tv_total);
        mDeleteIv = findViewById(R.id.iv_delete);
        mDeleteIv.setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);
    }

    @Override
    protected void loadData() {
        if (null == getIntent()) return;
        pathList = getIntent().getStringArrayListExtra(ImageConst.EXTRA_COMMON_PREVIEW_IMAGE_LIST);
        position = getIntent().getIntExtra(ImageConst.EXTRA_COMMON_PREVIEW_IMAGE_POSITION, position);
        boolean canDelete = getIntent().getBooleanExtra(ImageConst.EXTRA_COMMON_PREVIEW_IMAGE_LIST_CAN_DELETE, false);
        if (canDelete) {
            mDeleteIv.setVisibility(View.VISIBLE);
        }
        if (pathList != null && pathList.size() > 0) {
            tv_current.setText(position + 1 + "");
            tv_total.setText(pathList.size() + "");
            viewpager.setAdapter(new MyPagerAdapter());
            viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    tv_current.setText(position + 1 + "");
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            viewpager.setCurrentItem(position);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_back) {
            finish();
        } else if (view.getId() == R.id.iv_delete) {
            new ConfirmDialog.Builder(this)
                    .setTitle("确定删除该图片")
                    .setCancelClick("取消", null)
                    .setConfirmClick("确定", new OnPositiveClickListener() {
                        @Override
                        public void onPositiveClick(View view, String content) {
                            EventBusUtils.post(new MPreviewImageDelete(viewpager.getCurrentItem()));
                            finish();
                        }
                    }).create().show();
        }
    }

    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return pathList.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            GlideUtils.loadImageView(ImagePreviewActivity.this, pathList.get(position), photoView);
            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }
}
