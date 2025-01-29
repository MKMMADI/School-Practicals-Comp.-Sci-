@echo on

SET JAVA_HOME=C:\jdk-21
SET PATH=%JAVA_HOME%\bin;%PATH%

-javac version

cd ..

SET PRAC_SRC=.\src
SET PRAC_DOC=.\docs
SET PRAC_BIN=.\bin

javac -sourcepath %PRAC_SRC% %PRAC_SRC%\Main.java -d %PRAC_BIN%

java -cp %PRAC_BIN% Main

pause