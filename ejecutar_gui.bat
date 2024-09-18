@echo off

if not exist .\bin call compilar.bat 

if {%1}=={} (set/p parametro= ¿Deseas ejecutarlo con partidas con con jugadas? ) else ^
set parametro= %1

java -cp .\bin;.\lib\* ^
quantik.gui.Quantik