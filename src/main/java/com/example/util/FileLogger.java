package com.example.util;

import java.io.*;
import java.util.Calendar;

/**
 * Created by taozhiheng on 16-8-10.
 *
 * Log instance helper
 *
 */
public class FileLogger {


    private PrintWriter printWriter;

    private Calendar calendar = Calendar.getInstance();

    private boolean isDebug = true;

    public FileLogger(String fileName)
    {
        try {
            setOutput(new FileOutputStream(fileName, true));
        } catch (FileNotFoundException e) {
//            e.printStackTrace();
            File file = new File(fileName);
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            //setDebug(false);
        }
    }

    public PrintWriter getPrintWriter()
    {
        return printWriter;
    }

    public void setOutput(OutputStream out)
    {
        if(printWriter != null)
            printWriter.close();
        printWriter = new PrintWriter(out);
    }

    public void setDebug(boolean debug)
    {
        isDebug = debug;
    }

    public boolean isDebug()
    {
        return isDebug;
    }

    public static String getMethodName() {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[3];
        String ret = "["+e.getClassName()+":"+e.getMethodName()+" "+e.getLineNumber()+"] ";
        return ret;
    }

    public void log(String tag, String msg)
    {
        if(printWriter != null && isDebug)
        {
            calendar.setTimeInMillis(System.currentTimeMillis());
            //print date
            printWriter.print(String.format("%04d-%02d-%02d~~", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DATE)));
            //print time
            printWriter.print(String.format("%02d:%02d:%02d~~", calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND)));
            printWriter.print(getMethodName());
            printWriter.println("[" + tag + "]: " + msg);
            printWriter.flush();
        }
    }

    public  void close()
    {
        printWriter.close();
    }

}
