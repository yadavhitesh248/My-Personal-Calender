BUILD THE APK WITHOUT INSTALLING ANYTHING (GitHub Actions, free)
1. Create a free account at github.com and make a new repository (e.g. mycalendar).
2. Upload ALL files from this folder (including the hidden .github folder) to the repo.
   Easiest: "Add file > Upload files", drag the unzipped folder contents. If .github is skipped,
   use "Add file > Create new file", name it .github/workflows/build-apk.yml and paste the file's text.
3. Open the repo's Actions tab > "Build APK" > Run workflow. Wait about 5 minutes.
4. Open the finished run, download the "MyCalendar-APK" artifact (zip), unzip to get app-debug.apk.
5. Copy to your phone, allow "install unknown apps", install.
6. Open the app once, then long-press the home screen > Widgets > My Calendar.

BUILD ON YOUR OWN PC (needs Node 18+, JDK 17, Android SDK)
  npm install && npx cap add android && python3 scripts/add_widget.py && npx cap sync android
  cd android && ./gradlew assembleDebug
  APK: android/app/build/outputs/apk/debug/app-debug.apk
