package com.xiao360.baselibrary.base;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.baselibrary.R;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 *   抽取的列表上下拉刷新共同基类
 * */
public abstract class NormalListFragment<T extends BaseFragmentPresenter, E extends ViewModel> extends ToolBarStateFragment<T, E> {
    protected RecyclerView.Adapter mAdapter;
    protected RecyclerView recyclerView;
    protected boolean hasLoadMore = false;

    public void resetLoadMore() {
        stateLayout.refreshLayout.setEnableLoadMore(false);
        hasLoadMore = false;
    }

    //状态容器布局
    @Override
    protected int getContainerLayoutID() {
        return R.layout.common_container;
    }

    //失败状态容器布局
    @Override
    protected int getFailViewId() {
        return R.id.fail;
    }

    //加载状态容器布局
    @Override
    protected int getLoadingViewId() {
        return R.id.loading;
    }

    //空状态容器布局
    @Override
    protected int getEmptyViewId() {
        return R.id.empty;
    }

    //toolbar布局
    @Override
    protected int getToolBarLayoutID() {
        return R.layout.common_toolbar;
    }

    //状态栏布局
    @Override
    protected int getStateBarviewID() {
        return R.id.statebarView;
    }

    @Override
    protected void initView() {
        initEmptyView();
        initLoadingView();
        initFailView();
        initToolBar();

        //进入默认显示内容
        stateLayout.showContentView();

        //初始化列表
        recyclerView = rootView.findViewById(getListView());
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        mAdapter = getAdapter();
        recyclerView.setAdapter(mAdapter);

        //设置上下拉
        stateLayout.refreshLayout.setEnableLoadMore(false);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisible = manager.findLastVisibleItemPosition();
                if(!hasLoadMore && lastVisible>=9){
                    stateLayout.refreshLayout.setEnableLoadMore(true);
                    hasLoadMore = true;
                }
            }
        });
        InitView();
    }

    protected abstract void InitView();

    protected abstract int getListView();

    protected void initFailView() {
        ImageView fail_img = rootView.findViewById(R.id.fail_img);
        TextView fail_text = rootView.findViewById(R.id.fail_text);
        fail_img.setImageResource(getFailImgID());
        fail_text.setText(getFailText());
    }

    private int getFailText() {
        return R.string.fail_str;
    }

    private int getFailImgID() {
        return R.mipmap.fail;
    }

    protected void initLoadingView() {
        ImageView loading_img = rootView.findViewById(R.id.loading_img);
        TextView loading_text = rootView.findViewById(R.id.loading_text);
        loading_img.setImageResource(getLoadingImgID());
        loading_text.setText(getLoadingText());
    }

    private int getLoadingText() {
        return R.string.loading;
    }

    private int getLoadingImgID() {
        return R.mipmap.loading;
    }

    protected void initEmptyView() {
        ImageView empty_img = rootView.findViewById(R.id.empty_img);
        TextView empty_text = rootView.findViewById(R.id.empty_text);
        empty_img.setImageResource(getEmptyImgID());
        empty_text.setText(getEmptyText());
    }

    private int getEmptyText() {
        return R.string.empty;
    }

    private int getEmptyImgID() {
        return R.mipmap.empty;
    }

    protected abstract RecyclerView.Adapter getAdapter();

    protected void initToolBar() {
        TextView title = rootView.findViewById(R.id.title);
        title.setText(getString(getToolbarTitle()));
        ImageView back = rootView.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }

    protected abstract int getToolbarTitle();
}
