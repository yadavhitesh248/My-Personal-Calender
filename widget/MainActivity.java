package com.mycalendar.app;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {
    public static class WidgetJs {
        private final Context ctx;
        WidgetJs(Context c) { ctx = c; }

        @JavascriptInterface
        public void save(String json) {
            ctx.getSharedPreferences("CapacitorStorage", Context.MODE_PRIVATE)
               .edit().putString("mycal_widget", json).apply();
            AppWidgetManager m = AppWidgetManager.getInstance(ctx);
            int[] ids = m.getAppWidgetIds(new ComponentName(ctx, CalWidget.class));
            if (ids.length > 0) new CalWidget().onUpdate(ctx, m, ids);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBridge().getWebView().addJavascriptInterface(new WidgetJs(getApplicationContext()), "AndroidWidget");
    }
}
