@echo off
set "JAVA_HOME=D:\Program Files\Java\jdk-1.8"
set "CATALINA_HOME=D:\Program Files\apache-tomcat-9.0.113"
set "TOMCAT_BIN=%CATALINA_HOME%\bin"

echo Demarrage de Tomcat...
call "%TOMCAT_BIN%\startup.bat"

echo.
echo Tomcat est en cours de demarrage.
echo Verifie dans la fenetre de log ou sur http://localhost:8080/
pause