@echo off
color 0a
title set dns by @zhanglianxin
echo.
echo ���� DNS ��������ַ
echo.
set /p adapter=�����������������ƣ�
echo.
netsh interface ip set dns "%adapter%" static 123.207.137.88
netsh interface ip add dns "%adapter%" 8.8.8.8
echo.
echo ���óɹ�
ping 127.0.0.1 >nul
