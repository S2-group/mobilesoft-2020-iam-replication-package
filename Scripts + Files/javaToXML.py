import glob
import os

#os.mkdir("JavaFilesWithFunctions/xmlFiles")
with open("JavaFilesWithFunctions/FilesCompleted.txt","w") as progressFile:
	for file in glob.iglob("JavaFilesWithFunctions/**/*.java",recursive=True):
		trimmedDirectoryFromFile = file.replace("JavaFilesWithFunctions/","")
		if not os.path.isfile("JavaFilesWithFunctions/xmlFiles/%s"%trimmedDirectoryFromFile.replace("java","xml")):
			progressFile.write(trimmedDirectoryFromFile)
			os.system("sudo srcml %s -o JavaFilesWithFunctions/xmlFiles/%s"%(file,trimmedDirectoryFromFile.replace("java","xml")))
	
	progressFile.close()
		#print(file)
