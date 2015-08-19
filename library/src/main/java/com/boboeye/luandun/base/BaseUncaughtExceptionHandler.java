package com.boboeye.luandun.base;

import android.util.Log;
import android.widget.Toast;

import org.kymjs.kjframe.utils.FileUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * Created by libo_591 on 15/8/17.
 */
public class BaseUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "BaseUncaughtExp";

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        Log.e(TAG, thread.getName() + "--" + ex.getMessage());
        ex.printStackTrace();
        if(FileUtils.checkSDcard()){
            String filepath = FileUtils.getSDCardPath()+"/LUANDUN/exception.txt";
            try {
                FileOutputStream fios = new FileOutputStream(filepath);
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(fios));
                pw.print(thread.getName() + "--" + ex.getMessage());
                pw.flush();
                pw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        System.exit(0);
    }
}
