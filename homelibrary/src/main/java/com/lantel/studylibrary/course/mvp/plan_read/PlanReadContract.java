package com.lantel.studylibrary.course.mvp.plan_read;

import com.lantel.studylibrary.course.list.model.CourseCardModel;
import com.xiao360.baselibrary.base.BaseFragmentPresenter;
import com.xiao360.baselibrary.mvp.BaseView;
import java.util.ArrayList;

public interface PlanReadContract {
    interface View extends BaseView {
        void refreshData(ArrayList<CourseCardModel> menu);
        void setLoadMoreData(ArrayList<CourseCardModel> menu);
    }

    abstract class Presenter extends BaseFragmentPresenter<View, PlanReadModel> {

    }
}
