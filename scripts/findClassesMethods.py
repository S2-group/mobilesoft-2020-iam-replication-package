
import glob
import csv
#import xml.etree.ElementTree as ET
from lxml import etree
import pdb
#from bond import make_bond
import requests
import json
import time
#import google-play-scraper


namespace = {'mw':'http://www.srcML.org/srcML/src'}
nms = "{http://www.srcML.org/srcML/src}"%namespace

def getTextFrom(item):
	returnedText = ""
	if item.text is None:
		for item in item.getchildren():
			returnedText += getTextFrom(item)
	elif item.tag == nms+"argument_list":
		returnedText += "<"
		for item in item.getchildren():
			returnedText += getTextFrom(item)
		returnedText += ">"
	else:
		returnedText = item.text
	return returnedText

def getApplicationOrPackageFields(method,tree):
	fieldsFound = {}
	packageFields = ["CREATOR","activities","applicationInfo","baseRevisionCode","configPreferences","featureGroups","firstInstallTime","gids","installLocation","instrumentation","lastUpdateTime","packageName","permissions","providers","receivers","reqFeatures","requestedPermissions","requestedpermissionsFlags","services","sharedUserId","sharedUserLabel","signatures","signingInfo","splitNames","splitRevisionCodes","versionCode","versionName"]
	applicationFields = ["CREATOR","appComponentFactory","backupAgentName","category","className","compatibleWidthLimitDp","dataDir","descriptionRes","deviceProtectedDataDir","enabled","flags","largestWidthLimitDp","manageSpaceActivityName","minSdkVersion","nativeLibraryDir","permission","processName","publicSourceDir","requiresSmallestWidthDp","sharedLibraryFiles","sourceDir","splitNames","splitPublicSourceDirs","splitSourceDirs","storageUuid","targetSdkVersion","taskAffinity","theme","uiOptions","uid"]
	allFields = [packageFields,applicationFields]
	permissions = ""
	for name in method.iterfind('.//mw:name',namespaces=namespace):

		for fields in allFields:
			previousSibling = name.getprevious()
			if name.text in fields and previousSibling is not None and previousSibling.tag == nms + "operator" and previousSibling.text == ".":
				fieldsFound[name.text] = True

		if name.text == "checkPermission":
			parentOfParent = name.getparent().getparent()
			argumentLists = parentOfParent.iterfind('.//mw:argument_list',namespaces=namespace)
			for argumentList in argumentLists:
				argument = argumentList.getchildren()[0] if len(argumentList.getchildren()) > 0 else None
				expression = argument.getchildren()[0] if argument is not None else None
				currentPermission = getTextFrom(expression) if expression is not None else ""
				if currentPermission != "":
					permissions += currentPermission + ", "
	theFields = ""
	for field in fieldsFound:
		theFields += field + ", "
	return (theFields[:-2],permissions[:-2])

with open('installedAppsFunctionsRevised.csv', mode='w') as csv_file:
	fieldnames = ['Category','App Name','APK','APK Found on Androzoo','Function','In File','Package Name','Class Name','Class Extends','Class Implements','Method Name','Function Fields','Permissions','App Description']
	writer = csv.DictWriter(csv_file, fieldnames=fieldnames)
	writer.writeheader()

	with open('installedApplications.csv', mode='r') as csv_file:
		fieldnames = ['Category','App Name','APK','APK Found on Androzoo','Function','In File']
		reader = csv.DictReader(csv_file, fieldnames=fieldnames)
		next(reader, None)
		appDict = {}
		
		for row in reader:
			if row["In File"] == "Not Found" or row["In File"] == "DEX2JAR FAILED":
				pass
			elif  row["APK"]+"-"+row["In File"] not in appDict:
				appDict[row["APK"]+"-"+row["In File"]] = True
			else:
				continue 
			if row["In File"] != "Not Found" and row["In File"] != "DEX2JAR FAILED":
				
				#fix the names for two files with long names
				file = "JavaFilesWithFunctions/xmlFiles/%s-%s"%(row["APK"],row["In File"].replace("java","xml").replace("/","-"))
				if file == "JavaFilesWithFunctions/xmlFiles/com.medicsshield.bloodpressure.checker.diary.bp.info.chart.log.tracker.history.evaluation.control.calculator.journal-com-medicsshield-bloodpressure-checker-diary-bp-info-chart-log-tracker-history-evaluation-control-calculator-journal-activites-MainActivity.xml":
					file = "JavaFilesWithFunctions/xmlFiles/com.medicsshield.bloodpressure.checker.diary.bp.info.chart.log.tracker.history.evaluation.control.calculator.journal-MainActivity.xml"
				elif file == "JavaFilesWithFunctions/xmlFiles/com.workouthealthmedical.BloodPressure.checker.diary.app.bp.info.tracker.chart.log.calculator.evaluation.hypertension-com-workouthealthmedical-BloodPressure-checker-diary-app-bp-info-tracker-chart-log-calculator-evaluation-hypertension-activites-MainActivity.xml":
					file = "JavaFilesWithFunctions/xmlFiles/com.workouthealthmedical.BloodPressure.checker.diary.app.bp.info.tracker.chart.log.calculator.evaluation.hypertension-MainActivity.xml"
				print(file)
				tree = etree.parse(file)
				root = tree.getroot()
				foundFunction = False
				currentFunction = ""
				packageName = ""

				appDetails = requests.get('http://localhost:3000/api/apps/%s'%row['APK'])
				if appDetails.status_code == 200:
					appDescription = json.loads(appDetails.content)['description']

				package = tree.find('.//mw:package',namespaces=namespace)
				if package is not None:
					packageName = getTextFrom(package.getchildren()[0])
				print("package name: "+ packageName)

				for name in tree.iterfind('.//mw:name',namespaces=namespace):
					methodName = ""
					className = ""
					extends = ""
					implements = ""
					functionFields = ("","")
					if (name.text == "getInstalledApplications" or name.text == "getInstalledPackages"):
						foundFunction = True
						currentFunction = name.text
						namesParent = name.getparent()
						
						while (namesParent is not None and namesParent.tag != nms+"function" and namesParent.tag != nms+"constructor"):
							namesParent = namesParent.getparent()
						if namesParent is not None:
							methodName = getTextFrom(namesParent.find(nms+"name"))
							functionFields = getApplicationOrPackageFields(namesParent,tree)
							print("method name: "+getTextFrom(namesParent.find(nms+"name")))
							namesParent = name.getparent()
							classNotFound = False
							while (True):
								if namesParent is not None: 
									if namesParent.tag == nms+"class" and namesParent.find(nms+"specifier") is not None:
										break
									else:
										namesParent = namesParent.getparent()
								else:
									classNotFound = True
									break
									
							if not classNotFound:	
								className = getTextFrom(namesParent.find(nms+"name"))
								print("class name: "+getTextFrom(namesParent.find(nms+"name")))
								if namesParent.find(nms+"super") is not None:
									if namesParent.find(nms+"super").find(nms+"extends") is not None:
										extClasses = "Extends "
										for extendClasses in namesParent.find(nms+"super").find(nms+"extends").getchildren():
											if len(extendClasses) == 0:
												extClasses += extendClasses.text + ", "
											else:
												
												for madeUpClasses in extendClasses:
													extClasses += getTextFrom(madeUpClasses) #madeUpClasses.text
												extClasses += ", "
										extends = extClasses[:-2]
										print(extClasses[:-2])
										impClasses = "Implements "
									if namesParent.find(nms+"super").find(nms+"implements") is not None:
										for implementsClasses in namesParent.find(nms+"super").find(nms+"implements").getchildren():

											if len(implementsClasses) == 0:
												impClasses += implementsClasses.text + ", "
											else:
												for madeUpClasses in implementsClasses:
													impClasses += getTextFrom(madeUpClasses) #madeUpClasses.text
												impClasses += ", "
										implements = impClasses[:-2]
										print(impClasses[:-2])
								writer.writerow({'Category':row['Category'],'App Name':row['App Name'],'APK':row['APK'],'APK Found on Androzoo':row['APK Found on Androzoo'],'Function':currentFunction,'In File':row["In File"],'Package Name':packageName,'Class Name':className,'Class Extends':extends,'Class Implements':implements,'Method Name':methodName,'Function Fields':functionFields[0],'Permissions':functionFields[1],'App Description':appDescription})
							else:
								writer.writerow({'Category':row['Category'],'App Name':row['App Name'],'APK':row['APK'],'APK Found on Androzoo':row['APK Found on Androzoo'],'Function':currentFunction,'In File':row["In File"],'Package Name':packageName,'Class Name':"",'Class Extends':"",'Class Implements':"",'Method Name':methodName,'Function Fields':functionFields[0],'Permissions':functionFields[1],'App Description':appDescription})
								print("class not found")
						else:
							writer.writerow({'Category':row['Category'],'App Name':row['App Name'],'APK':row['APK'],'APK Found on Androzoo':row['APK Found on Androzoo'],'Function':"CORRUPTED",'In File':"CORRUPTED",'Package Name':'CORRUPTED','Class Name':"CORRUPTED",'Class Extends':"CORRUPTED",'Class Implements':"CORRUPTED",'Method Name':"CORRUPTED",'Function Fields':"CORRUPTED",'Permissions':"CORRUPTED",'App Description':""})
							print("Not Well Formatted - method not found, class not found")
				if not foundFunction: 
					writer.writerow({'Category':row['Category'],'App Name':row['App Name'],'APK':row['APK'],'APK Found on Androzoo':row['APK Found on Androzoo'],'Function':"CORRUPTED",'In File':"CORRUPTED",'Package Name':'CORRUPTED','Class Name':"CORRUPTED",'Class Extends':"CORRUPTED",'Class Implements':"CORRUPTED",'Method Name':"CORRUPTED",'Function Fields':"CORRUPTED",'Permissions':"CORRUPTED",'App Description':""})
					print("no function was found")
				print("______________________________________________________________________")
			else:
				writer.writerow({'Category':row['Category'],'App Name':row['App Name'],'APK':row['APK'],'APK Found on Androzoo':row['APK Found on Androzoo'],'Function':row["Function"],'In File':row["In File"],'Package Name':"",'Class Name':"",'Class Extends':"",'Class Implements':"",'Method Name':"",'Function Fields':"",'Permissions':"",'App Description':""})
		print(len(appDict))


