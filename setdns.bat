@echo off
color 0a
title set dns by @zhanglianxin
echo.
echo 设置 DNS 服务器地址
echo.
set /p adapter=请输入网络连接名称：
echo.
netsh interface ip set dns "%adapter%" static 123.207.137.88
netsh interface ip add dns "%adapter%" 8.8.8.8
echo.
echo 设置成功
ping 127.0.0.1 >nul
