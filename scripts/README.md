### extractApps.py

```
Make sure that you have [az](https://github.com/ArtemKushnerov/az) installed and that you change the path of the input file inside extractApps.py 
```
Each category contains two files (data.json and extractApps.py). Executing extractApps.py would allow az to download the APKs resepctive to the category.

### APKFunctionsJDCore.py

```
Make sure that you download [jd core java](https://github.com/nviennot/jd-core-java) and build it
Install jnius - as to call java methods from python : pip install jnius 
Make sure to set these paths in your command line:
export JAVA_HOME="/usr/lib/jvm/java-11-openjdk-amd64"
export CLASSPATH="jd-core-java/build/libs/jd-core-java-1.2.jar"
```
This script will check to see if the APIs exist in the APKs and it will save all the java files where the APIs were found into a new directory "JavaFilesWithFunctions". It also exports "installedApplication.csv" which contains 

### javaToXML.py

```
Make sure that you have [srcml](https://www.srcml.org/#download) installed  
```
This script turns all the java files resulting from the previous script into xml files and save them into a directory "xmlFiles" inside "JavaFilesWithFunctions"

### findClassesMethods.py

```
Make sure that you have these installed
lxml      - pip install lxml
requests  - pip install requests  
[google play api](https://github.com/facundoolano/google-play-api) and call npm install, npm start
```
This script searches for the methods, classes where the APIs where called. It checks what the classes extend and implement. It also gets the app's description from google play. All this info will be saved into "installedAppsFunctionsRevised.csv"

### installedAppsStats.py

This script reads "installedAppsFunctionsRevised.csv" and prints different statistics concerning the apps and their APIs usage. 


