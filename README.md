# Mapreduce Search Longest Word
-----------------------

## MapReduce

* Data processing layer in Hadoop. 
* Processing structured & Unstructured Data in Hadoop.

## Pros

* Best Performance 

## Cons 

* Hard to Extend 
* Lack of management tools
* Not suitable for real time processing
* very small community

## Input Directory

Input directory contains input files that will be processed by MapReduce to find longest word.

<img src="screenshots/input_directory.png"> <br/>

In the above screenshot, we can see an input directory (in) contains 2 input files (input.txt, input1.txt).

## Input.txt
 
<img src="screenshots/input.png"> <br/>

In the above screenshot, we can see the content of the first input file.

## Input1.txt

<img src="screenshots/input1.png"> <br/>
 
In the above screenshot, we can see the content of the second input file.

## Executing Hadoop Jar

<img src="screenshots/execute_hadoop_jar.png"> <br/>
 
In the above screenshot, we can see the command to find longest word. <br/>
<b>hadoop jar:</b> This is command used to execute Hadoop jar <br/>
<b>SearchLongestWord.jar:</b> Hadoop jar file name <br/>
<b>SearchLongestWord:</b> jar class name contains main(). <br/>
<b>/in:</b> Input directory contains file to process <br/>
<b>/out:</b> Ouput directory will be created after execution of this command <br/>

## Output Directory

Output directory will be automatically created contains output files.
 
<img src="screenshots/output_directory.png"> <br/>

## Read output file Content:

<img src="screenshots/read_output_file_content.png"> <br/>
 
In the above screenshot, we can see the output contains key 9 – represent the length of the word & value – maximum length word.

## Test Cases:

<img src="screenshots/test_cases.png"> <br/>
 
In the above screenshot, we can see that all 12 test cases have passed.

**Created by:** <br/>
**Name: Krishna Kumar Singh** <br/>
**Email: krishnaai265@gmail.com** <br/>
**Phone: +91-9368754996** 
