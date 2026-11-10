@echo off
title Arret des Microservices Spring Cloud
echo ========================================================
echo   Arret des microservices sur les ports 8761, 8088, 8089, 8888
echo ========================================================

powershell -Command "$ports = @(8761, 8088, 8089, 8888); foreach ($p in $ports) { $conns = Get-NetTCPConnection -LocalPort $p -ErrorAction SilentlyContinue; if ($conns) { $pids = $conns | Select-Object -ExpandProperty OwningProcess -Unique; foreach ($procId in $pids) { if ($procId -gt 0) { Stop-Process -Id $procId -Force -ErrorAction SilentlyContinue; Write-Host ('Port ' + $p + ' libere (Process ' + $procId + ' arrete).') } } } else { Write-Host ('Port ' + $p + ' est deja libre.') } }"

echo.
echo Operation terminee.
pause

