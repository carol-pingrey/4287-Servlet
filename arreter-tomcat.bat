@echo off
set "JAVA_HOME=D:\Program Files\Java\jdk-1.8"
set "CATALINA_HOME=D:\Program Files\apache-tomcat-9.0.113"
set "TOMCAT_BIN=%CATALINA_HOME%\bin"

echo Arret de Tomcat...
call "%TOMCAT_BIN%\shutdown.bat"

echo.
echo Tomcat a ete arrete.
pause