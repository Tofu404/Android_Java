@echo off
copy /y D:\Android_Java\ShanHuReport\config\1.dat D:\Android_Java\ShanHuReport\MyDemo\app\src\main\assets\40805.dat
call gradlew app:assembleMyApp1
adb install -r D:\Android_Java\ShanHuReport\MyDemo\app\build\outputs\apk\myApp1\debug\app-myApp1-debug.apk
adb shell am start -n nihao.com/create.by.tianze.MainActivity
:loop1
echo.
echo.
echo ################################
echo.
echo.
echo input "1" to reinstall the app
echo.
echo.
echo input "2" to go on
echo.
echo.
echo ################################
echo.
echo.
set /p num=
if %num% == 1 (
	adb uninstall nihao.com
	adb install -r D:\Android_Java\ShanHuReport\MyDemo\app\build\outputs\apk\myApp1\debug\app-myApp1-debug.apk
	adb shell am start -n nihao.com/create.by.tianze.MainActivity
	set /a num=0
	goto loop1
)
adb uninstall nihao.com

