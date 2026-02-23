@echo off
for /f "tokens=5" %%a in ('netstat -ano ^| findstr :8085') do taskkill /PID %%a /F >nul 2>&1
for /f "tokens=5" %%a in ('netstat -ano ^| findstr :8090') do taskkill /PID %%a /F >nul 2>&1
mvn spring-boot:run
