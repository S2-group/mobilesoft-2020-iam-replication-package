# MobileSoft 2020 – Replication package


This repository contains the replication package and dataset of the paper published at MobileSoft 2020 with the title **Leave my Apps Alone! A Study on how Android Developers Access Installed Apps on User’s Device**.

This study has been designed, developed, and reported by the following investigators:

- Gian Luca Scoccia (DISIM, University of L’Aquila)
- Ibrahim Kanj (Vrije Universiteit Amsterdam)
- [Ivano Malavolta](https://www.ivanomalavolta.com) (Vrije Universiteit Amsterdam)
- Kaveh Razavi (Vrije Universiteit Amsterdam)

For any information, interested researchers can contact us by sending an email to any of the investigators listed above.
The full dataset including raw data, mining scripts, and analysis scripts produced during the study are available below.

## How to cite the dataset
If the dataset is helping your research, consider to cite it is as follows, thanks!

```
@inproceedings{MobileSoft2020,
  title={{Leave my Apps Alone! A Study on how Android Developers Access Installed Apps on User’s Device}},
  author={Gian Luca Scoccia and Ibrahim Kanj and Ivano Malavolta and Kaveh Razavi},
  booktitle = {Proceedings of the 7th IEEE/ACM International Conference on Mobile Software Engineering and Systems},
  year = {2020},
  pages = {to appear},
  numpages = {12}
}
```

### Overview of the replication package
---

This replication package is structured as follows:

```
    .
    |--- scripts/       		The scripts that have been used to collect the Android apps and to identify and extract IAMs calls from them.
    |
    |--- dataset/             	The full dataset used in the paper, including the raw files with IAMs calls, the questionnaire responses and the aggregated data by app category.
    |
    |--- libraries_classification/   	The results of the manual classification procedure performed for the Top 20 IAM using libraries.
    |
    |--- MobileSoft_2020.pdf             A copy of the paper in pdf format
```

Each of the folders listed above are described in details in the remaining of this readme.

### Dataset
---
```
dataset
    .
    |--- Top APKs Java Files   The raw java files found using IAMs extracted from apps mined from Google Play Store.  
    |--- OS Java Files         The raw java files found using IAMs extracted from Open Source apps in AndroidTimeMachine.
    |--- javaFilesSrcMl.xml    The java files in the folders above converted in a traversable xml format by means of Src.ML
    |--- questionnaire         The raw responses to our developer questionnaire.
    |--- Extracted_data.ods    Spreadsheet with collected data, aggregated by category and analysis.

``` 

### Scripts
---
Usage of the scripts is explained in [README](https://github.com/S2-group/mobilesoft-2020-iam-replication-package/blob/master/scripts/README.md) file inside the scripts folder.

### Libraries classification
---
```
libraries_classification
    .
    |--- classification.csv                      Results of the manual classification process of libraries. The csv includes the original purpose as classified indipendently by the two researchers as well as the final one after disagreements were solved.
```


## License

This software is licensed under the MIT License.
