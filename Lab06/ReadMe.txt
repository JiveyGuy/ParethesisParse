Hello Mr Servin!
This is Jason Ivey speaking...
I wrote a quick bat file so that you could execute
my lab in the CMD terminal or console. 
It was designed in the style of the old 80's Comand
Line programs and looks cooler in the bat file. 



Conditions to run the .bat or BATCH file:
-You must have the java PATH: C:\ProgramData\Oracle\Java\javapath\java.exe
-You must have my lab's name as Lab06.java
-You must find the directory in which my lab is stored, 
	example: C:\Users\Jason Ivey\Downloads\lab06





Incase you were wondering what is in the Lab06.bat file 
here is the text in it:

@echo off
title ParenthesisParse
color 02
echo[
echo  Where is Jason Ivey's Lab Folder? Find it in file explorer then copy the path here
SET /P X=Type the directory: 
cd %X%
:begin
for /l %%x in (1, 1, 4) do (echo[)
java Lab06
PAUSE
echo[
echo You have just run my lab! Awesome, would you like to run it again? 
echo[
SET /P X=Type 'y' or 'n' or "starwars":
echo[
echo %X% 
if /i %X% == y (goto :begin) else (
	if /i %X% == starwars (
		pkgmgr /iu:"TelnetClient"
		Telnet Towel.blinkenlights.nl
	) else (goto :eof) 
) 



