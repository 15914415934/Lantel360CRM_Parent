package com.lantel.setting.bindfile.bindstudent.mvp;

import android.os.Bundle;

import com.lantel.setting.bindfile.bindstudent.api.BindStudentBean;
import com.lantel.setting.bindfile.bindstudent.list.model.BindStudentListModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class BindStudentPresenter extends BindStudentContract.Presenter<BindStudentBean,BindStudentListModel>{

    @Override
    public void onCrete(Bundle savedInstanceState) {

    }

    //onActivityCreated
    @Override
    public void onCrete() {

    }

    @Override
    public void onStart() {

        refreshData(null);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    protected Observable<BindStudentBean> getObserver(boolean isMore) {
        return mModel.loadData();
    }

    @Override
    protected void showLoading() {
        mView.showLoading();
    }

    @Override
    protected void ViewSetLoadMoreData(ArrayList<BindStudentListModel> list) {

    }

    @Override
    protected void ViewRefreshData(ArrayList<BindStudentListModel> list) {
        mView.refreshData(list);
    }

    @Override
    protected void showEmpty() {
        mView.showEmpty();
    }

    @Override
    protected int getTotal(BindStudentBean data) {
        if(null == data.getData())
            return 0;
        return data.getData().getList().size();
    }

    @Override
    protected int getErrorCode(BindStudentBean data) {
        return data.getError();
    }

    @Override
    protected void setUpData(ArrayList<BindStudentListModel> list, BindStudentBean data) {
        BindStudentBean.DataBean dataBean = data.getData();
        if(null != dataBean){
            List<BindStudentBean.DataBean.ListBean> listBeans = dataBean.getList();
            if(null != listBeans){
                /*for(){

                }*/
            }
        }
    }

    @Override
    protected String getErrorMessage(BindStudentBean data) {
        return data.getMessage();
    }
}
