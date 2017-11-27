@ echo off
title php-cgi switch by @zhanglianxin
color 0a
echo.
REM referer: [PHP-FastCGI on Windows | NGINX](https://www.nginx.com/resources/wiki/start/topics/examples/phpfastcgionwindows/)
(taskkill /f /IM php-cgi.exe 1>nul 2>nul && echo php-cgi stopping...) || (bin\RunHiddenConsole.exe php\php-cgi.exe -b 127.0.0.1:9000 && echo php-cgi starting...)
ping 127.0.0.1 >nul 2>nul
@exit
