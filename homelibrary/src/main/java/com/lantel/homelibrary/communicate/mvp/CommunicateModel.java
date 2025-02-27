package com.lantel.homelibrary.communicate.mvp;

import com.httpsdk.http.Http;
import com.httpsdk.http.RxHelper;
import com.lantel.common.HeaderUtil;
import com.lantel.homelibrary.communicate.api.CommitChatReq;
import com.lantel.homelibrary.communicate.api.CommunicateBean;
import com.lantel.homelibrary.communicate.api.CommunicateService;
import com.lantel.setting.bindAccount.api.BindAccountBean;

import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;

public class CommunicateModel extends ViewModel {
    public Observable<CommunicateBean> loadData(String create_time) {
        CommunicateService service = Http.getInstance().createRequest(CommunicateService.class);
        return service.getChatData(HeaderUtil.getJsonHeaderMap(),create_time).compose(RxHelper.io_main());
    }

    public Observable<BindAccountBean> commitData(CommitChatReq req) {
        CommunicateService service = Http.getInstance().createRequest(CommunicateService.class);
        return service.commitChatData(HeaderUtil.getJsonHeaderMap(),req).compose(RxHelper.io_main());
    }
}
