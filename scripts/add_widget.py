import os, shutil
base = "android/app/src/main"
java_dir = base + "/java/com/mycalendar/app"
for d in (java_dir, base + "/res/layout", base + "/res/xml"):
    os.makedirs(d, exist_ok=True)
shutil.copy("widget/CalWidget.java", java_dir + "/CalWidget.java")
shutil.copy("widget/widget_week.xml", base + "/res/layout/widget_week.xml")
shutil.copy("widget/widget_info.xml", base + "/res/xml/widget_info.xml")
mf = base + "/AndroidManifest.xml"
s = open(mf).read()
rec = '''
        <receiver android:name=".CalWidget" android:exported="true">
            <intent-filter><action android:name="android.appwidget.action.APPWIDGET_UPDATE"/></intent-filter>
            <meta-data android:name="android.appwidget.provider" android:resource="@xml/widget_info"/>
        </receiver>
'''
if "CalWidget" not in s:
    s = s.replace("</application>", rec + "    </application>", 1)
    open(mf, "w").write(s)
print("Widget added")
