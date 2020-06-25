@echo off
call gradlew app:clean
copy /y C:\Users\Hello\Desktop\ShanHuReport\config\1.dat C:\Users\Hello\Desktop\ShanHuReport\MyDemo\app\src\main\assets\40805.dat
call gradlew app:assembleMyApp1
adb install -r C:\Users\Hello\Desktop\ShanHuReport\MyDemo\app\build\outputs\apk\myApp1\debug\app-myApp1-debug.apk
adb shell am start -n com.dcxs100.star/create.by.tianze.MainActivity
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
	adb uninstall com.dcxs100.star
	adb install -r C:\Users\Hello\Desktop\ShanHuReport\MyDemo\app\build\outputs\apk\myApp1\debug\app-myApp1-debug.apk
	adb shell am start -n com.dcxs100.star/create.by.tianze.MainActivity
	set /a num=0
	goto loop1
)
adb uninstall com.dcxs100.star

copy /y C:\Users\Hello\Desktop\ShanHuReport\config\2.dat C:\Users\Hello\Desktop\ShanHuReport\MyDemo\app\src\main\assets\40805.dat
call gradlew app:assembleMyApp2
adb install -r C:\Users\Hello\Desktop\ShanHuReport\MyDemo\app\build\outputs\apk\myApp2\debug\app-myApp2-debug.apk
adb shell am start -n com.zhl.fuckvideo/create.by.tianze.MainActivity
:loop2
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
	adb uninstall com.zhl.fuckvideo
	adb install -r C:\Users\Hello\Desktop\ShanHuReport\MyDemo\app\build\outputs\apk\myApp2\debug\app-myApp2-debug.apk
	adb shell am start -n com.zhl.fuckvideo/create.by.tianze.MainActivity
	set /a num=0
	goto loop2
)
adb uninstall com.zhl.fuckvideo

copy /y C:\Users\Hello\Desktop\ShanHuReport\config\3.dat C:\Users\Hello\Desktop\ShanHuReport\MyDemo\app\src\main\assets\40805.dat
call gradlew app:assembleMyApp3
adb install -r C:\Users\Hello\Desktop\ShanHuReport\MyDemo\app\build\outputs\apk\myApp3\debug\app-myApp3-debug.apk
adb shell am start -n com.gzyike.painting/create.by.tianze.MainActivity
:loop3
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
	adb uninstall com.gzyike.painting
	adb install -r C:\Users\Hello\Desktop\ShanHuReport\MyDemo\app\build\outputs\apk\myApp3\debug\app-myApp3-debug.apk
	adb shell am start -n com.gzyike.painting/create.by.tianze.MainActivity
	set /a num=0
	goto loop3
)
adb uninstall com.gzyike.painting

copy /y C:\Users\Hello\Desktop\ShanHuReport\config\4.dat C:\Users\Hello\Desktop\ShanHuReport\MyDemo\app\src\main\assets\40805.dat
call gradlew app:assembleMyApp4
adb install -r C:\Users\Hello\Desktop\ShanHuReport\MyDemo\app\build\outputs\apk\myApp4\debug\app-myApp4-debug.apk
adb shell am start -n com.gzyike.color.by.number/create.by.tianze.MainActivity
:loop4
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
	adb uninstall com.gzyike.color.by.number
	adb install -r C:\Users\Hello\Desktop\ShanHuReport\MyDemo\app\build\outputs\apk\myApp4\debug\app-myApp4-debug.apk
	adb shell am start -n com.gzyike.color.by.number/create.by.tianze.MainActivity
	set /a num=0
	goto loop4
)
adb uninstall com.gzyike.color.by.number

