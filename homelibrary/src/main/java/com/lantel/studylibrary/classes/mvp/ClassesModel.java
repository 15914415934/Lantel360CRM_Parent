package com.lantel.studylibrary.classes.mvp;

import com.httpsdk.http.Http;
import com.httpsdk.http.RxHelper;
import com.lantel.studylibrary.classes.api.ClassBean;
import com.lantel.studylibrary.classes.api.ClassService;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;

public class ClassesModel extends ViewModel {

    public Observable<ClassBean> loadData(String page,String pageSize) {
        ClassService service = Http.getInstance().createRequest(ClassService.class);
        return service.getClassData(page,pageSize).compose(RxHelper.io_main());
    }
}
