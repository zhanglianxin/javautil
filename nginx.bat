@ echo off
title enginx switch by @zhanglianxin
color 0a
echo.
REM Place it in the nginx directory
(taskkill /f /IM nginx.exe 1>nul 2>nul && echo nginx stopping...) || (start nginx.exe && echo nginx starting...)
ping 127.0.0.1 >nul 2>nul
@exit
