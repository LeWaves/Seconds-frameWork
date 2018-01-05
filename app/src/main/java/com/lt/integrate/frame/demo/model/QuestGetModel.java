package com.lt.integrate.frame.demo.model;

import android.content.Context;
import com.lt.integrate.frame.demo.listener.HttpsListenerInterface;

/**
 * Created by iclick on 2017/9/26.
 */

public interface QuestGetModel {
    void loadQuestGetData(Context context, String url, HttpsListenerInterface listener);
}
