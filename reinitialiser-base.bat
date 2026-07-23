@echo off
REM Reinitialise la base parc_info a partir du script sql\parcreset.sql
REM Chemin vers mysql.exe

set "MYSQL_EXE=D:\Program\xampp\mysql\bin\mysql.exe"
set "DB_USER=root"

echo Réinitialisation de la base parc_info...
"%MYSQL_EXE%" -u %DB_USER% < "sql\parcreset.sql"

if errorlevel 1 (
    echo Erreur : la réinitialisation a échoué.
    exit /b 1
)

echo Base parc_info réinitialisée avec succès.
pause