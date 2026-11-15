package com.mycalendar.app;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.widget.RemoteViews;
import org.json.JSONArray;
import org.json.JSONObject;

public class CalWidget extends AppWidgetProvider {
    @Override
    public void onUpdate(Context ctx, AppWidgetManager mgr, int[] ids) {
        SharedPreferences sp = ctx.getSharedPreferences("CapacitorStorage", Context.MODE_PRIVATE);
        JSONArray arr;
        try { arr = new JSONArray(sp.getString("mycal_widget", "[]")); } catch (Exception e) { arr = new JSONArray(); }
        int[] rows = {R.id.r0, R.id.r1, R.id.r2, R.id.r3, R.id.r4, R.id.r5, R.id.r6};
        for (int id : ids) {
            RemoteViews v = new RemoteViews(ctx.getPackageName(), R.layout.widget_week);
            for (int i = 0; i < rows.length && i < arr.length(); i++) {
                try {
                    JSONObject o = arr.getJSONObject(i);
                    String t = o.getString("text");
                    if (t.isEmpty()) t = "\u2014";
                    v.setTextViewText(rows[i], o.getString("label") + "   " + t);
                    v.setInt(rows[i], "setBackgroundColor", Color.parseColor(o.getString("color")));
                    v.setTextColor(rows[i], Color.parseColor(o.getString("fg")));
                } catch (Exception e) { }
            }
            Intent open = ctx.getPackageManager().getLaunchIntentForPackage(ctx.getPackageName());
            PendingIntent pi = PendingIntent.getActivity(ctx, 0, open, PendingIntent.FLAG_IMMUTABLE);
            v.setOnClickPendingIntent(R.id.root, pi);
            mgr.updateAppWidget(id, v);
        }
    }
}
