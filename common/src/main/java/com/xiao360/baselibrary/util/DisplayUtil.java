package com.xiao360.baselibrary.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.xiao360.baselibrary.base.BaseApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DisplayUtil {
    private DisplayUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }


    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param pxValue
     *            （DisplayMetrics类中属性density）
     * @return
     */
    public static int px2dip(float pxValue) {
        final float scale = BaseApplication.getAppContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     *            （DisplayMetrics类中属性density）
     * @return
     */
    public static int dip2px( float dipValue) {
        final float scale = BaseApplication.getAppContext().getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(float pxValue) {
        final float fontScale = BaseApplication.getAppContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(float spValue) {
        final float fontScale = BaseApplication.getAppContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 直接获取控件的宽、高
     * @param view
     * @return int[]
     */
    public static int[] getWidgetWH(final View view){
        ViewTreeObserver vto2 = view.getViewTreeObserver();
        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
        return new int[]{view.getWidth(),view.getHeight()};
    }

    /**
     * 直接获取控件的宽、高
     * @param view
     * @return int[]
     */
    public static int getViewHeight(final View view){
        ViewTreeObserver vto2 = view.getViewTreeObserver();
        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
        return view.getHeight();
    }

    /**
     * 直接获取控件的宽、高
     * @param view
     * @return int[]
     */
    public static int getViewWidth(final View view){
        ViewTreeObserver vto2 = view.getViewTreeObserver();
        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
        return view.getWidth();
    }

    /**
     * 获得屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * 获得状态栏的高度
     * 注意：该方法只能在Activity类中使用，在测试模式下失败
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 获取控件的宽
     * @param view
     * @return
     */
    public static int getWidgetWidth(View view){
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);//先度量
        int width = view.getMeasuredWidth();
        return width;
    }
    /**
     * 获取控件的高
     * @param view
     * @return
     */
    public static int getWidgetHeight(View view){
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);//先度量
        int height = view.getMeasuredHeight();
        return height;
    }
    /**
     * 设置控件宽
     * @param view
     * @param width
     */
    public static void setWidgetWidth(View view, int width){
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
        params.width = width;
        view.setLayoutParams(params);
    }
    /**
     * 设置控件高
     * @param view
     * @param height
     */
    public static void setWidgetHeight(View view, int height){
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
        params.height = height;
        view.setLayoutParams(params);
    }


    //----------------------------------------------

    /**
     * 获取当前屏幕截图，包含状态栏（这个方法没测试通过）
     *
     * @param activity
     * @return Bitmap
     */
    public static Bitmap snapShotWithStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
        view.destroyDrawingCache();
        return bp;
    }

    /**
     * 获取当前屏幕截图，不包含状态栏（这个方法没测试通过）
     *
     * @param activity
     * @return Bitmap
     */
    public static Bitmap snapShotWithoutStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height
                - statusBarHeight);
        view.destroyDrawingCache();
        return bp;
    }

    public static String getDateString(String date) {
        String year = date.substring(0,4);
        String month = date.substring(4,6);
        String day = date.substring(6);
        StringBuilder builder = new StringBuilder();
        builder.append(year).append("-").append(month).append("-").append(day);
        return builder.toString();
    }

    //由出生日期获得年龄
    public static int getAge(Date birthDay, Context context) {
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) {
            Toast.makeText(context, "出生日期不能在当今日期之后！", Toast.LENGTH_SHORT).show();
            return -1;
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;
            }else{
                age--;
            }
        }
        if(age<0)
            age = 0;
        return age;
    }

    public static String getTimeString(String time) {
        String hour = "";
        String minute = "";
        if(time.length() == 3){
             hour = time.substring(0,1);
             minute = time.substring(1);

        }else {
             hour = time.substring(0,2);
             minute = time.substring(2);
        }
        StringBuilder builder = new StringBuilder();
        builder.append(hour).append(":").append(minute);
        return builder.toString();
    }

    /**
     * 根据提供的年月日获取该月份的第一天
     * @Description: (这里用一句话描述这个方法的作用)
     * @Author: gyz
     * @Since: 2017-1-9下午2:26:57
     * @param index 0,第一天，1，最后一天
     * @return
     */
    public static Date getSupportBeginDayofMonth(Date date ,int index) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);

       if(index == 1)
           cal.roll(Calendar.DATE, -1);
        return cal.getTime();
    }

    public static Date formatIntDay(String format,String intDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(intDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String praseformatIntDay(String format,Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static String getFilePath(String url) {
        return /*Environment.getExternalStorageDirectory().getPath() + "//360_parentCRM/Download/"+*/url.substring(url.lastIndexOf("/")+1);
    }

    public static String formatNum(String str){
        String newStr = "";
        int count = 0;

        if(str.indexOf(".")==-1){
            for(int i=str.length()-1;i>=0;i--){
                if(count % 3 == 0 && count != 0){
                    newStr = str.charAt(i) + "," + newStr;
                }else{
                    newStr = str.charAt(i) + newStr;
                }
                count++;
            }
            str = newStr + ".00"; //自动补小数点后两位
        }
        else
        {
            for(int i = str.indexOf(".")-1;i>=0;i--){
                if(count % 3 == 0 && count != 0){
                    newStr = str.charAt(i) + "," + newStr;  //碰到3的倍数则加上“,”号
                }else{
                    newStr = str.charAt(i) + newStr; //逐个字符相接起来
                }
                count++;
            }

            str = newStr +str.substring(str.indexOf("."));
        }
        return str;
    }

    //将字节数转化为MB
    public static String byteToMB(long size){
        long kb = 1024;
        long mb = kb*1024;
        long gb = mb*1024;
        if (size >= gb){
            return String.format("%.1f G",(float)size/gb);
        }else if (size >= mb){
            float f = (float) size/mb;
            return String.format(f > 100 ?"%.0f M":"%.1f M",f);
        }else if (size > kb){
            float f = (float) size / kb;
            return String.format(f>100?"%.0f K":"%.1f K",f);
        }else {
            return String.format("%d B",size);
        }
    }

    /**
     * 开启软键盘
     */
    public static void showSoft(final Activity context) {
        Handler handle =new Handler();
        handle.postDelayed(new Runnable() {
        @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                }
         }, 100);
    }

    /**
     * 关闭软键盘
     *
     * @param dialog
     */
    public static void dismissSofo(final Activity context, Dialog dialog) {
        //针对dialog隐藏做一个监听  当dialog隐藏的时候 就关闭
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
        @Override
        public void onDismiss(DialogInterface dialog) {
            InputMethodManager inputMgr = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            View v = context.getWindow().peekDecorView();
            if (null != v) {
                inputMgr.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }

        }
        });
    }

    public static String getFormateStr(String fromForMate,String toForMate,String str) {
        Date date = formatIntDay(fromForMate,str);
        return praseformatIntDay(toForMate,date);
    }
}
