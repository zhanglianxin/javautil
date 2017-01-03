@ echo off
title enginx switch by @zhanglianxin
color 0a
echo.
rem 加括号帮助理解，并不影响实际执行效果
(taskkill /f /IM nginx.exe 1>nul 2>nul && echo nginx stopping...) || (start nginx.exe && echo nginx starting...)
ping 127.0.0.1 >nul 2>nul
