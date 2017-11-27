@ echo off
title mysql switch by @zhanglianxin
color 0a
echo.
REM Place it in the mysql directory
(taskkill /f /IM mysqld.exe 1>nul 2>nul && echo mysql stopping...) || (start mysqld.exe && echo mysql starting...)
ping 127.0.0.1 >nul 2>nul
