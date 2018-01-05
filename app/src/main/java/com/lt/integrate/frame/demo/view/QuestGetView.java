package com.lt.integrate.frame.demo.view;

import java.util.List;

/**
 * Created by iclick on 2017/9/26.
 */

public interface QuestGetView {
    void onHttpSucesss(List<Object> list, String data );
    void onHttpError(String error );
}
