# Classifier
Classifier

This is a personal project to implement a text classifier using SVM technology.
Currently, I only have finished the function of feature selection by Chi-Squared algorithm. 
There are always a lot of work to implement this classifier.

Next step, I will be going to implement the trainer of SVM based on these feature.

The structure of current version would be explain below.

1. Folder structure
   The project mainly includes "Data", "lib", “TrainFile" and "src". 
   The Data folder includes the dictionary of sentence segment and stoplist. So people can 
update these file personaly.
   The lib folder includes some jar file neccessary.
   The TrainFile folder include the training files, and each category owns one folder named
by its categary.
   The src folder includes all source code. It includes the sentence segment source code of 
NLPIR named "org", "FileOperation" to implement the functong of file operation, such as read
file, write or append file, etc., "zdhlib.feature" contains the code of feature count to 
implement the function of feature selection and Chi-Squared calculation.

2. How to test
   The main test function is located in the folder of "test".
