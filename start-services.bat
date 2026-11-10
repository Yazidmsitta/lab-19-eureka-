@echo off
title Demarrage Architecture Microservices Spring Cloud
echo ========================================================
echo   Demarrage de l'Architecture Microservices Spring Cloud
echo ========================================================
echo.

set JAVA_HOME=C:\Program Files\JetBrains\IntelliJ IDEA 2026.2.4\jbr
set PATH=%JAVA_HOME%\bin;%PATH%

echo 1. Lancement de Eureka Server (:8761)...
start "1 - EUREKA SERVER (8761)" cmd /k "cd eureka-server && "%JAVA_HOME%\bin\java.exe" -jar target\eureka-server-0.0.1-SNAPSHOT.jar"
echo    En attente de 10 secondes pour l'initialisation d'Eureka...
timeout /t 10 /nobreak > nul

echo 2. Lancement de SERVICE-CLIENT (:8088)...
start "2 - SERVICE-CLIENT (8088)" cmd /k "cd service-client && "%JAVA_HOME%\bin\java.exe" -jar target\service-client-0.0.1-SNAPSHOT.jar"
echo    En attente de 5 secondes...
timeout /t 5 /nobreak > nul

echo 3. Lancement de SERVICE-VOITURE (:8089)...
start "3 - SERVICE-VOITURE (8089)" cmd /k "cd service-voiture && "%JAVA_HOME%\bin\java.exe" -jar target\service-voiture-0.0.1-SNAPSHOT.jar"
echo    En attente de 5 secondes...
timeout /t 5 /nobreak > nul

echo 4. Lancement de GATEWAY-SERVICE (:8888)...
start "4 - GATEWAY-SERVICE (8888)" cmd /k "cd gateway-service && "%JAVA_HOME%\bin\java.exe" -jar target\gateway-service-0.0.1-SNAPSHOT.jar"

echo.
echo ========================================================
echo   Tous les services ont ete lances !
echo   - Eureka Dashboard : http://localhost:8761
echo   - Gateway API      : http://localhost:8888/clients
echo                        http://localhost:8888/voitures
echo ========================================================
pause

