@echo off

copy /y C:\Users\Hello\Desktop\ShanHuReport\config\1.dat C:\Users\Hello\Desktop\ShanHuReport\MyDemo\app\src\main\assets\40805.dat
call gradlew app:assembleMyApp1
adb install -r C:\Users\Hello\Desktop\ShanHuReport\MyDemo\apk_output_path\[myApp1]_debug.apk
adb shell am start -n nihao.com/com.alltheway.forward.MainActivity
:loop1
echo.
echo.
echo ################################
echo.
echo.
echo input “1” to reinstall the app
echo.
echo.
echo input “2” to go on
echo.
echo.
echo ################################
echo.
echo.
set /p num=
if %num% == 1 (
	adb uninstall nihao.com
	adb install -r C:\Users\Hello\Desktop\ShanHuReport\MyDemo\apk_output_path\[myApp1]_debug.apk
	adb shell am start -n nihao.com/com.alltheway.forward.MainActivity
	set /a num=0
	goto loop1
)
adb uninstall nihao.com