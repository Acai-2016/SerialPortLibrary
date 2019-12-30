package com.zyl.serialportlibrary;

import android.util.Log;

public class ThreadDemo extends Thread {

    private String TAG = ThreadDemo.class.getSimpleName();

    @Override
    public void run() {
        super.run();
        while(!interrupted()) {
            System.out.println("......");
            for (int i = 0; i < 10; i++) {
                System.out.println("..." + i + "...");
                if (i == 5) {
                    return;
                }
            }
            System.out.println("111111");
        }
    }
}
