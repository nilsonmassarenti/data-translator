# Data Translator

Data translator is a example of read, transform and write files. 

# New Features!
  - Data transformation using thread (Producer and Consumer)

### Tech
* Java 8

### Installation

Data transformation requires [Maven](https://maven.apache.org/) to run 

```sh
$ cd data-translator
$ mvn exec:java -Dexec.args="D:\dev\test-files\ 1-vendor.txt 2-configuration.txt 3-id.txt"
```
Where 
* arg[0] - Directory of data (D:\dev\test-files\)
* arg[1] - Vendor file (1-vendor.txt)
* arg[2] - Configuration file (Transform Columns Vendor -> Your Columns) (2-configuration.txt)
* arg[3] - ID converters (VENDOR ID -> Your ID) (3-id.txt)
