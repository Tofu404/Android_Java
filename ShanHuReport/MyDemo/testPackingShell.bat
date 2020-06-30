@echo off

if exist D:\Android_Java\ShanHuReport\MyDemo\app\src\main\assets\40805.dat (
	del D:\Android_Java\ShanHuReport\MyDemo\app\src\main\assets\40805.dat
)
copy /y D:\Android_Java\ShanHuReport\config\40805.dat D:\Android_Java\ShanHuReport\MyDemo\app\src\main\assets\40805.dat
call gradlew app:assembleDebug
adb install -r D:\Android_Java\ShanHuReport\MyDemo\app\build\outputs\apk\myApp1\debug\app-myApp1-debug.apk
adb shell am start -n com.hh.beikemm/create.by.tianze.MainActivity
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
	adb uninstall com.hh.beikemm
	adb install -r D:\Android_Java\ShanHuReport\MyDemo\app\build\outputs\apk\myApp1\debug\app-myApp1-debug.apk
	adb shell am start -n com.hh.beikemm/create.by.tianze.MainActivity
	set /a num=0
	goto loop1
)
adb uninstall com.hh.beikemm

adb install -r D:\Android_Java\ShanHuReport\MyDemo\app\build\outputs\apk\myApp2\debug\app-myApp2-debug.apk
adb shell am start -n com.boshide.kingbeans/create.by.tianze.MainActivity
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
	adb uninstall com.boshide.kingbeans
	adb install -r D:\Android_Java\ShanHuReport\MyDemo\app\build\outputs\apk\myApp2\debug\app-myApp2-debug.apk
	adb shell am start -n com.boshide.kingbeans/create.by.tianze.MainActivity
	set /a num=0
	goto loop2
)
adb uninstall com.boshide.kingbeans

adb install -r D:\Android_Java\ShanHuReport\MyDemo\app\build\outputs\apk\myApp3\debug\app-myApp3-debug.apk
adb shell am start -n com.bcxgame.lw.dzfhd/create.by.tianze.MainActivity
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
	adb uninstall com.bcxgame.lw.dzfhd
	adb install -r D:\Android_Java\ShanHuReport\MyDemo\app\build\outputs\apk\myApp3\debug\app-myApp3-debug.apk
	adb shell am start -n com.bcxgame.lw.dzfhd/create.by.tianze.MainActivity
	set /a num=0
	goto loop3
)
adb uninstall com.bcxgame.lw.dzfhd

adb install -r D:\Android_Java\ShanHuReport\MyDemo\app\build\outputs\apk\myApp4\debug\app-myApp4-debug.apk
adb shell am start -n com.tyrell.game.zt.cygs2/create.by.tianze.MainActivity
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
	adb uninstall com.tyrell.game.zt.cygs2
	adb install -r D:\Android_Java\ShanHuReport\MyDemo\app\build\outputs\apk\myApp4\debug\app-myApp4-debug.apk
	adb shell am start -n com.tyrell.game.zt.cygs2/create.by.tianze.MainActivity
	set /a num=0
	goto loop4
)
adb uninstall com.tyrell.game.zt.cygs2

adb install -r D:\Android_Java\ShanHuReport\MyDemo\app\build\outputs\apk\myApp5\debug\app-myApp5-debug.apk
adb shell am start -n com.walk.moneycome.bodyhealth/create.by.tianze.MainActivity
:loop5
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
	adb uninstall com.walk.moneycome.bodyhealth
	adb install -r D:\Android_Java\ShanHuReport\MyDemo\app\build\outputs\apk\myApp5\debug\app-myApp5-debug.apk
	adb shell am start -n com.walk.moneycome.bodyhealth/create.by.tianze.MainActivity
	set /a num=0
	goto loop5
)
adb uninstall com.walk.moneycome.bodyhealth

