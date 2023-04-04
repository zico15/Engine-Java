package com.engine.objects.interfaces;

import com.engine.graphics.Graphics;

public interface IObject {

    void onRender(Graphics gc);
    void onUpdate(double tpf);

    void onCreate();
    void onDestroy();
}
