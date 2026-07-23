@echo off
setlocal enabledelayedexpansion

set "APP_NAME=ParcInformatique"
set "SRC_DIR=src"
set "WEB_DIR=web"
set "BUILD_DIR=build"
set "LIB_DIR=lib"
set "JAVA_HOME=D:\Program Files\Java\jdk-1.8"
set "CATALINA_HOME=D:\Program Files\apache-tomcat-9.0.113"
set "TOMCAT_WEBAPPS=%CATALINA_HOME%\webapps"
set "XAMPP_PHP_DIR=D:\Program\xampp\htdocs\ParcInformatique\php"
set "SERVLET_API_JAR=%LIB_DIR%\servlet-api.jar"
set "MYSQL_JAR=%LIB_DIR%\mysql-connector-j-9.7.0.jar"

if not exist "%MYSQL_JAR%" (
    echo ERREUR : driver MySQL introuvable a l'emplacement "%MYSQL_JAR%"
    echo Verifiez le nom exact du fichier dans le dossier lib avec : dir lib
    exit /b 1
)

if exist "%BUILD_DIR%" rmdir /s /q "%BUILD_DIR%"
mkdir "%BUILD_DIR%\WEB-INF\classes"
mkdir "%BUILD_DIR%\WEB-INF\lib"

set "SOURCES_LIST=sources_tmp.txt"
if exist "%SOURCES_LIST%" del "%SOURCES_LIST%"
for /r "%SRC_DIR%" %%f in (*.java) do (
    set "CHEMIN=%%f"
    set "CHEMIN=!CHEMIN:\=/!"
    echo "!CHEMIN!">> "%SOURCES_LIST%"
)

REM on met tous les jars de lib sur le classpath (servlet-api, mysql et jackson)
"%JAVA_HOME%\bin\javac" -cp "%LIB_DIR%\*" -d "%BUILD_DIR%\WEB-INF\classes" @"%SOURCES_LIST%"

if errorlevel 1 (
    echo Erreur de compilation, arret du script.
    del "%SOURCES_LIST%"
    exit /b 1
)

del "%SOURCES_LIST%"

xcopy "%WEB_DIR%" "%BUILD_DIR%" /E /I /Y

copy "%MYSQL_JAR%" "%BUILD_DIR%\WEB-INF\lib\"
copy "%LIB_DIR%\jackson-*.jar" "%BUILD_DIR%\WEB-INF\lib\"

pushd "%BUILD_DIR%"
"%JAVA_HOME%\bin\jar" -cvf "%APP_NAME%.war" *
popd

REM avant de coller le nouveau .war, on efface l'ancienne version dans Tomcat
REM (le dossier decompresse ParcInformatique et l'ancien ParcInformatique.war)
REM sinon Tomcat continue de servir l'ancienne version
if exist "%TOMCAT_WEBAPPS%\%APP_NAME%" rmdir /s /q "%TOMCAT_WEBAPPS%\%APP_NAME%"
if exist "%TOMCAT_WEBAPPS%\%APP_NAME%.war" del /q "%TOMCAT_WEBAPPS%\%APP_NAME%.war"

REM une fois l'ancien efface (ou s'il n'y avait rien), on colle le nouveau .war
copy "%BUILD_DIR%\%APP_NAME%.war" "%TOMCAT_WEBAPPS%"

REM le PHP tourne sous XAMPP, pas Tomcat : on le copie donc au bon endroit
if not exist "%XAMPP_PHP_DIR%" mkdir "%XAMPP_PHP_DIR%"
copy "%WEB_DIR%\php\ordi_etat.php" "%XAMPP_PHP_DIR%\"

echo Deploiement termine. Redemarrez Tomcat si necessaire.
endlocal