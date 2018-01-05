package com.lt.integrate.frame.demo.presenter;

import android.content.Context;

import com.lt.integrate.frame.demo.listener.HttpsListenerInterface;
import com.lt.integrate.frame.demo.model.QuestGetModel;
import com.lt.integrate.frame.demo.model.QuestGetModelImpl;
import com.lt.integrate.frame.demo.view.QuestGetView;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by iclick on 2017/9/26.
 */

public class QuestGetPrestenerImpl implements QuestGetPrestener {
    private QuestGetView mGetView;
    private QuestGetModel mGetModel;
    private HttpsListenerInterface listenerInterface;

    public QuestGetPrestenerImpl(QuestGetView mView){
        this.mGetView = mView;
        this.mGetModel = new QuestGetModelImpl();
        this.listenerInterface = new OnInterfaceListener(this);
    }

    private  class  OnInterfaceListener implements HttpsListenerInterface{
        private WeakReference<QuestGetPrestenerImpl> presenter;

        public OnInterfaceListener(QuestGetPrestenerImpl presenter) {
            this.presenter = new WeakReference<>(presenter);
        }

        @Override
        public void onSucessHttps(List<Object> list, String data) {
            this.presenter.get().mGetView.onHttpSucesss(list,data);
        }

        @Override
        public void onErrorHttps(String error) {
            this.presenter.get().mGetView.onHttpError(error);
        }
    }


    @Override
    public void loadQuestGetPrestenerData(Context context, String url) {
        this.mGetModel.loadQuestGetData(context,url,listenerInterface);
    }
}
