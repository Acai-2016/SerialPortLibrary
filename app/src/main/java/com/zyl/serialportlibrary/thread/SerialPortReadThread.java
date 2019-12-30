package com.zyl.serialportlibrary.thread;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

/**
 * 串口读取线程
 */
public abstract class SerialPortReadThread extends Thread {

    private String TAG = SerialPortReadThread.class.getName();
    private InputStream mInputStream;
    private byte[] mReadBuffer;

    public SerialPortReadThread(InputStream inputStream) {
        mInputStream = inputStream;
        mReadBuffer =new byte[1024];
    }

    @Override
    public void run() {
        super.run();

        while (!isInterrupted()) {
            try {
                if (null == mInputStream) {
                    return;
                }

                Log.i(TAG,"run:");
                //读取输入流中的字节到缓冲区
                int size = mInputStream.read(mReadBuffer);

                if(-1== size || 0 >= size) {
                    return;
                }
                //定义一个字节数组用来存放读取到的字节
                byte[] readBytes = new byte[size];
                //拷贝
                System.arraycopy(mReadBuffer,0,readBytes,0,size);

                Log.i(TAG,"run: readBytes = " + new String(readBytes));
                onDataReceived(readBytes);

            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public abstract void onDataReceived(byte[] readBytes);

    /**
     * 关闭线程 释放资源
     */
    public  void release() {
        interrupt();

        if (null !=mInputStream) {
            try {
                mInputStream.close();
                mInputStream = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

