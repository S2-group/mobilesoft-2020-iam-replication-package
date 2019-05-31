import json
import os

with open('data.json') as json_file:
	data = json.load(json_file)
	allAppsString = ""
	for app in data["table"]["rows"]:
		appPackageName = app[1][0]["url"].replace("/apps/google-play/app/","").replace("/details/","")
		allAppsString += appPackageName + ","
	os.system("az -m play.google.com -k 4965b196b91b76dcc111379d848ae2261e2656d47ee0905e8910830a3d60bcb0 -i ~/Documents/APKs/allFiles/latest.csv -pn %s"%allAppsString[:-1])
	
