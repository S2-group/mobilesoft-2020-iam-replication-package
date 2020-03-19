import csv
import pdb
import random

class App:

	def __init__ (self,category,name,apkName,apkFoundOnAndrozoo,appDescription):
		self.category = category
		self.name = name
		self.apkName = apkName
		self.apkFoundOnAndrozoo = apkFoundOnAndrozoo
		self.appDescription = appDescription
		self.functions = []

class Function:

	def __init__ (self,name,file,packageName,className,classExtends,classImplements,methodName,fields,permissions):
		self.name = name
		self.file = file
		self.packageName = packageName
		self.className = className
		self.classExtends = classExtends
		self.classImplements = classImplements
		self.methodName = methodName
		self.fields = fields
		self.permissions = permissions

def fillFunctions(app,row):
	if row['Function'] != "Not Found" and row['Function'] != "DEX2JAR FAILED" and row['Function'] != "CORRUPTED":
		function = Function(row['Function'],row['In File'],row['Package Name'],row['Class Name'],row['Class Extends'],row['Class Implements'],row['Method Name'],row['Function Fields'],row['Permissions'])
		app.functions.append(function)
		'''if row['Function'] in app.functions:
			app.functions[row['Function']].append(row['In File'])
		else:
			arrayOfFiles = [row['In File']]
			app.functions[row['Function']] = arrayOfFiles'''

def countFunctionsFound(list):
	counter = 0
	foundApps = 0
	for app in list:
		if len(app.functions) != 0:
			counter += 1
		if app.apkFoundOnAndrozoo == "TRUE":
			foundApps += 1
	return counter/foundApps

def findTotalAppsUsingApi(sortedList):
	foundOnAndrozooCounter = 0
	appsUsingAPisCounter = 0
	for categoryList in sortedList:
		for app in categoryList:
			if app.apkFoundOnAndrozoo == "TRUE":
				foundOnAndrozooCounter += 1
			if len(app.functions) != 0:
				appsUsingAPisCounter += 1
	print("%s apps out of %s apps use the functions"%(appsUsingAPisCounter,foundOnAndrozooCounter))

def sortedPackages(sortedList):
	packageDict = {}
	localFileDict = {}
	for categoryList in sortedList:
		for app in categoryList:
			for function in app.functions:
				if app.apkName+function.packageName not in localFileDict:
					localFileDict[app.apkName+function.packageName] = True
					#packageSplit = function.packageName.split(".")
					if function.packageName in packageDict :
						packageDict[function.packageName] += 1
					else:
						if  function.packageName != "CORRUPTED":
							packageDict[function.packageName] = 1
							
	sortedPackageList = sorted(packageDict.items(),key=lambda x: x[1])
	sortedPackageList.reverse()
	return sortedPackageList

def findMostPackageNameUsed(sortedList):
	
	packageDict = {}
	localFileDict = {}
	for categoryList in sortedList:
		for app in categoryList:
			for function in app.functions:
				if app.apkName+function.packageName not in localFileDict:
					localFileDict[app.apkName+function.packageName] = True
					packageSplit = function.packageName.split(".")
					if len(packageSplit) > 1:
						splitted = packageSplit[0]+"."+packageSplit[1]
						if splitted in packageDict :
							packageDict[splitted] += 1
						else:
							packageDict[splitted] = 1
							
					else:
						if function.packageName in packageDict :
							packageDict[function.packageName] += 1
						else:
							if  function.packageName != "CORRUPTED":
								packageDict[function.packageName] = 1
							
	sortedList = sorted(packageDict.items(),key=lambda x: x[1])
	sortedList.reverse()
	counter = 0
	countPackages = 0
	for package in sortedList:
		#package[1]
		if package[1] > 20:
			countPackages += 1
			counter += package[1]
			print(package[0])
	print("here%d"%countPackages)
	print(counter)
	print(len(localFileDict))

def findMostPackageNameUsedOriginal(sortedList):
	
	packageDict = {}
	localFileDict = {}
	for categoryList in sortedList:
		for app in categoryList:
			for function in app.functions:
				if app.apkName+function.packageName not in localFileDict:
					localFileDict[app.apkName+function.packageName] = True
					packageSplit = function.packageName.split(".")
					if function.packageName in packageDict :
						packageDict[function.packageName] += 1
					else:
						if  function.packageName != "CORRUPTED":
							packageDict[function.packageName] = 1
							
	sortedList = sorted(packageDict.items(),key=lambda x: x[1])
	sortedList.reverse()
	counter = 0
	countPackages = 0
	for package in sortedList:
		if package[1] > 5:
			countPackages += 1
			counter += package[1]
			print(package[1])
	print("here%d"%countPackages)
	print(counter)
	print(len(localFileDict))

def searchAdFilesForMatches(sortedList):
	counter = 0
	libraryDict = {}
	with open ("ad_1050.txt","r") as file:
		for line in file:
			line = line.rstrip('\n')
			for package in sortedPackages(sortedList):
				if line in package[0]:
					if line not in libraryDict:
						libraryDict[line] = True
						print(line)
						counter += 1
		print(counter)
		file.close()

def findAppsOrApplications(sortedList):
	for categoryList in sortedList:
		for app in categoryList:
			if "apps" in app.appDescription or "applications" in app.appDescription:
				print(app.appDescription)

def findHowManyApisAreLibraries(sortedList):
	countLocal = 0
	countAll = 0
	uniquePackageAPK = {}
	for categoryList in sortedList:
		for app in categoryList:
			if len(app.functions) > 0:
				for function in app.functions:
					if app.apkName+function.packageName not in uniquePackageAPK:
						uniquePackageAPK[app.apkName+function.packageName] = True
						countAll += 1
						apkSplit = app.apkName.split(".")
						packageSplit = function.packageName.split(".")
						if (len(packageSplit) > 1 and apkSplit[0]+"."+apkSplit[1] == packageSplit[0]+"."+packageSplit[1]) or len(packageSplit) <= 1:
							countLocal += 1
	print("There are %.2f %% APIs used locally and %.2f %% used in third party libraries %d %d %d"%(100*countLocal/countAll,100*(countAll- countLocal)/countAll,countLocal,countAll-countLocal,countAll))
	print(len(uniquePackageAPK)) 

def findHowManyApisAreLibrariesForEachCategory(sortedList):
	countLocal = 0
	countAll = 0
	uniquePackageAPK = {}
	for categoryList in sortedList:
		for app in categoryList:
			if len(app.functions) > 0:
				for function in app.functions:
					if app.apkName+function.packageName not in uniquePackageAPK:
						uniquePackageAPK[app.apkName+function.packageName] = True
						countAll += 1
						apkSplit = app.apkName.split(".")
						packageSplit = function.packageName.split(".")
						if (len(packageSplit) > 1 and apkSplit[0]+"."+apkSplit[1] == packageSplit[0]+"."+packageSplit[1]) or len(packageSplit) <= 1:
							countLocal += 1
		print(countAll-countLocal)
		countLocal = 0
		countAll = 0 
def searchDescriptions(sortedList):
	arrayofAppsUsingApis = []
	count = 0
	for categoryList in sortedList:
		for app in categoryList:
			if len(app.functions) > 0:
				arrayofAppsUsingApis.append(app)
				count += 1
	newArrayofThreeFiveThree = []
	
	for i in range(353):
		chooseIndex = random.randint(0,len(arrayofAppsUsingApis)-1)
		newArrayofThreeFiveThree.append(arrayofAppsUsingApis[chooseIndex])
		arrayofAppsUsingApis.pop(chooseIndex)
		print(chooseIndex)


	with open ("installedAppsWithApis.txt", mode="w") as file:
		for app in newArrayofThreeFiveThree:
			file.write(app.name+"\n\n"+app.appDescription+"\n_________________________________________________\n")
		file.close()

	with open('installedAppsWithApis.csv', mode='w') as csv_file:
		fieldnames = ['Category','App Name','APK','Mentions API Usage','Usage Description']
		writer = csv.DictWriter(csv_file, fieldnames=fieldnames)
		writer.writeheader()
		for app in newArrayofThreeFiveThree:
			writer.writerow({'Category':app.category,'App Name':app.name,'APK':app.apkName,'Mentions API Usage':"",'Usage Description':""})

def searchForMostFieldsMostPermissionsUsed(sortedList):
	fields = {}
	permissions = {}
	for categoryList in sortedList:
		for app in categoryList:
			for function in app.functions:
				if function.permissions != "":
					permissionSplitted = function.permissions.split(", ")
					for permission in permissionSplitted:
						if permission in permissions:
							permissions[permission] += 1
						else:
							permissions[permission] = 1
				if function.fields != "":
					fieldsSplitted = function.fields.split(", ")
					for field in fieldsSplitted:
						if field in fields:
							fields[field] += 1
						else:
							fields[field] = 1
	sortedFields = sorted(fields.items(),key = lambda x:x[1])
	sortedFields.reverse()
	countFields = 0
	for field in sortedFields:
		countFields += field[1]
		print(field[1])
	print("total fields %d"%countFields)
	sortedPermissions = sorted(permissions.items(),key = lambda x:x[1])
	sortedPermissions.reverse()
	countPermissions = 0
	for permission in sortedPermissions:
		countPermissions += permission[1]
		print(permission[1])
	print("total permissions %d"%countPermissions)

		


'''Read CSV and extract Data'''
arrayOfApps = []
with open('installedAppsFunctionsRevised.csv', mode='r') as csv_file:
	fieldnames = ['Category','App Name','APK','APK Found on Androzoo','Function','In File','Package Name','Class Name','Class Extends','Class Implements','Method Name','Function Fields','Permissions','App Description']
	reader = csv.DictReader(csv_file, fieldnames=fieldnames)
	next(reader, None)
	familyCategory = ["Action & Adventure","Ages 5 & Under","Ages 6-8","Ages 9 & Up","Brain Games","Creativity","Education","Music & Video","Pretend Play"]
	categoryArray = []
	#previousCategory = ""
	currentCategory = ""
	currentAPK = ""
	previousAPP = None
	
	for row in reader:
		previousAPK = currentAPK
		previousCategory = currentCategory
		currentCategory = row['Category'] if row['Category'] not in familyCategory else "Family" 
		currentAPK = row['APK']
		if currentAPK != previousAPK:
			app = App(currentCategory,row['App Name'],row['APK'],row['APK Found on Androzoo'],row['App Description'])
			fillFunctions(app,row)
			previousAPP = app
			if currentCategory == previousCategory:
				categoryArray.append(app)
			else:
				if len(categoryArray) > 0 :
					arrayOfApps.append(categoryArray)
				categoryArray = []
				categoryArray.append(app)
		else:
			fillFunctions(previousAPP,row)
	arrayOfApps.append(categoryArray)
	'''Merge Family Categories'''
	familyCat = None
	familyCategoryFound = False
	i = 0
	while i < len(arrayOfApps):
		category = arrayOfApps[i]
		if not familyCategoryFound:
			if category[0].category == "Family" :
				familyCategoryFound = True
				familyCat = category
		else:
			if category[0].category == "Family":
				familyCat.extend(category)
				arrayOfApps.pop(i)
				i -= 1 
		i += 1




'''Sort the data by most functions used''' 
sortedArray = []

for categoryArr in arrayOfApps:
	counter = 0
	foundOnAndrozoo = 0
	for app in categoryArr:
		if len(app.functions) != 0:
			counter += 1
		if app.apkFoundOnAndrozoo == "TRUE":
			foundOnAndrozoo += 1
	if len(sortedArray) == 0:
		sortedArray.append(categoryArr)
	else: 
		for i in range(len(sortedArray)):
			if (counter/foundOnAndrozoo) < countFunctionsFound(sortedArray[i]):
				sortedArray.insert(i,categoryArr)
				break
			elif i == len(sortedArray) - 1:
				sortedArray.append(categoryArr)
sortedArray.reverse()

'''Print Data'''
getInstalledApplicationsCounter = 0
getInstalledPackagesCounter = 0
getInstalledPackages = "getInstalledPackages"
getInstalledApplications = "getInstalledApplications"
filesDict = {}
sumApps = 0
repititions = 0
print("-------")
for arr in sortedArray:
	for app in arr:
		for function in app.functions:
			if getInstalledPackages == function.name:
				getInstalledPackagesCounter += 1
				file = function.file
				if file in filesDict:
					repititions += 1
				else:
					filesDict[file] = "True"
			if getInstalledApplications == function.name:
				getInstalledApplicationsCounter += 1
				file = function.file
				if file in filesDict:
					repititions += 1
				else:
					filesDict[file] = "True"
	countFunctionsFound(arr)

print("%s: %d   -   %s: %d"%(getInstalledPackages,getInstalledPackagesCounter,getInstalledApplications,getInstalledApplicationsCounter))
print(len(filesDict))
print(repititions)
findTotalAppsUsingApi(sortedArray)
findMostPackageNameUsedOriginal(sortedArray)
findHowManyApisAreLibraries(sortedArray)
searchAdFilesForMatches(sortedArray)
findHowManyApisAreLibrariesForEachCategory(sortedArray)
findAppsOrApplications(sortedArray)
searchDescriptions(sortedArray)
searchForMostFieldsMostPermissionsUsed(sortedArray)


		
