package dri.example.com.colordetector2;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by takuyamorimatsu on 2017/10/10.
 */

public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    private Context context;
    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;

    public MyUncaughtExceptionHandler(Context context) {

        this.context = context;

        //デフォルトの例外ハンドラを退避させる
        uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();

    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.JAPAN);
        String e_date = sdf.format(date);




        //スタックトレースを文字にする
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        String stackTrace = stringWriter.toString();

        //スタックトレースをファイルに保存
        writeFile(context, stackTrace, e_date + "ErrorStackTrace.txt");

        uncaughtExceptionHandler.uncaughtException(t, e);
    }



    public void writeFile(Context context, String msg, String filename){

        SharedPreferences preferences = context.getSharedPreferences("error", Context.MODE_PRIVATE);
        preferences.edit().putString("error", msg).commit();
    }
}
