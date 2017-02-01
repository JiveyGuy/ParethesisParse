

@echo off 
rem This is a comment!
rem Author: Jason Ivey,.bat file to run in command line.
mode con: cols=1080 lines=720
title ParenthesisParse

color 02

echo[

rem echo[ prints a blank line


echo Where is Jason Ivey's Lab Folder? Find it in file explorer then copy the path here
echo[

SET /P X=Type the directory:

rem Set gets input


cd %X%

rem changing directory 

:begin


rem Label for spaghetti code 

for /l %%x in (1, 1, 4) do (echo[)


javac Lab06.bat

rem compiles Lab06.bat!


java Lab06

rem Runs Lab06


echo[

echo You have just run my lab! Awesome, would you like to run it again? 

echo[

SET /P X=Type 'y' or 'n' or "starwars":

echo[

echo %X%
 

if /i %X% == y (goto :begin) else (

	
	rem If statements that will either run the program again, exit, or play starwars Episode IV.
 

	if /i %X% == starwars (

		pkgmgr /iu:"TelnetClient"

		Telnet Towel.blinkenlights.nl
	
	) else (goto :eof) 
) 