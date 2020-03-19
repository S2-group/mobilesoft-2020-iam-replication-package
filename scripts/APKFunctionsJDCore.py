import os
import csv
from shutil import copyfile,move
from jnius import autoclass
import errno
os.environ['CLASSPATH'] = "jd-core-java/build/libs/jd-core-java-1.2.jar"

with open('installedApplication.csv', mode='w') as csvWriterFile:
	fields = ['Category','App Name','APK','APK Found on Androzoo','Function','In File']
	writer = csv.DictWriter(csvWriterFile,fieldnames=fields)
	writer.writeheader()
	for root,dirs, files in os.walk("."):
		for dir in dirs:
			for root,dirs,files in os.walk(dir):
				if "installedApplications.csv" in files and dir != "api-study":
					with open('%s/installedApplications.csv'%dir, mode='r') as csv_file:
						fieldnames = ['App Name', 'APK', 'Found']
						reader = csv.DictReader(csv_file, fieldnames=fieldnames)
						next(reader, None)
						for row in reader:
							currentAPK = row['APK']
							#passedOverDirs = ["News & Magazines","Brain Games","Music & Audio","Beauty","Finance","Health & Fitness","Creativity","Entertainment","Action & Adventure","Medical","Maps & Navigation","Social","Personalization","Comics"]
							#skipAPKs = ["com.glitchVHS.glitch3d.vhs.camcorder.effect.glitch","com.iosclip.moviemaker.movieapple.videostar.imovie","alpaga.cascadeClassifier","com.rokiababy.atfalsighar","com.dgcodes.apps.twwarhammer"]
							#goForDir = ["Family"]
							#and dir  in goForDir and currentAPK not in skipAPKs:
							if row['Found'] == "True" and os.path.isfile("%s/%s.apk"%(dir,currentAPK)): 
								functionsNotFound = True
								os.system('AndroidDecompiler/dex2jar/d2j-dex2jar.sh \"%s/%s.apk\"'%(dir,currentAPK))
								Decompiler = autoclass('jd.core.Decompiler')
								decompiler = Decompiler()
								if os.path.isfile('%s-dex2jar.jar'%currentAPK):
									try:
										classesDict = decompiler.decompile('%s-dex2jar.jar'%currentAPK)
										classNamesArray = classesDict.keySet().toArray()
										for className in classNamesArray:
											code = classesDict.get(className)
											if code and ("getInstalledApplications" in code or "getInstalledPackages" in code):
												file = None
												try:
													file = open("JavaFilesWithFunctions/%s-%s"%(currentAPK,className.replace("/","-")),"w")
												except OSError as e:
													if e.errno == errno.ENAMETOOLONG:
														file = open("JavaFilesWithFunctions/%s-%s"%(currentAPK,os.path.basename(className)),"w")
													else:
														raise
												file.write(code)
												file.close()
												functionsNotFound = False
												if "getInstalledApplications" in code:
													writer.writerow({'Category': dir, 'App Name': row['App Name'],'APK':currentAPK,'APK Found on Androzoo':'True','Function':'getInstalledApplications','In File':'%s%s'%("",className)})
												if "getInstalledPackages" in code: 
													writer.writerow({'Category': dir, 'App Name': row['App Name'],'APK':currentAPK,'APK Found on Androzoo':'True','Function':'getInstalledPackages','In File':'%s%s'%("",className)})
										if functionsNotFound:
											writer.writerow({'Category': dir, 'App Name': row['App Name'],'APK':currentAPK,'APK Found on Androzoo':'True','Function':'Not Found','In File':'Not Found'})
									except Exception as e:
										writer.writerow({'Category': dir, 'App Name': row['App Name'],'APK':currentAPK,'APK Found on Androzoo':'True','Function':'JD Core Failed','In File':'JD CORE FAILED'})	
									os.remove('%s-dex2jar.jar'%currentAPK)
								else:
									writer.writerow({'Category': dir, 'App Name': row['App Name'],'APK':currentAPK,'APK Found on Androzoo':'True','Function':'DEX2JAR FAILED','In File':'DEX2JAR FAILED'})
								if os.path.isfile('%s-error.zip'%currentAPK):
									move('%s-error.zip'%currentAPK,'Dex2JarErrors/%s-error.zip'%currentAPK)

							else:
								writer.writerow({'Category': dir, 'App Name': row['App Name'],'APK':currentAPK,'APK Found on Androzoo':'False','Function':'Not Found','In File':'Not Found'})
		break