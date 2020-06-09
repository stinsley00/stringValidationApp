# Program requirements:
Gradle 6.4.1
Java 11

Dependencies handled by gradle
junit is the only dependency. 

dependencies {
    testCompile group: 'junit', name: 'junit', version: '4.12'
}

# User Requirements:  
   1) String must consist of a mixture of letters and numerical digits only, with at least one of each.
   2) String must be between 5 and 12 characters in length.
   3) String must not contain any sequence of characters immediately followed by the same sequence.
   4) support internationalization
   5) have a test harness

# i18 Notes: This program supports input/output in spanish and english in its current implementation
this program uses gradle 6.4.1
parameters to be passed in: 
-L indicates language * currently english and spanish are the only implemented languages.  More can be added easily. 

    -L es would make the program write out in spanish

Example running the program passing in -L as a parameter
java -jar programJar.jar -L es "iddqd1" "ssqq123" "test1gw34"
-L en "3tr2ng1" "s4rst1" "sssstttt" "yuen1uyuy"

if -L is not used, the program defaults to en_US locale. 

# what this program does
    this program takes command line input strings and validates them against 3 criterion.  This program is multi threaded
    and evaluates the criterion regardless of one criteria passing for failing.  A reason for the criteria failure is provided as part of a 
    plain old java object data structure (POJO for short).  Each pojo contains a unique ID, and validation results which could be returned to 
    a frontend api, a backend database or message queue.  Dates and times could be added and internationalized as well. 
    
# Running the unit tests
    use the gradle wrapper to run 
    gw clean test 
    or
     ./gradlew clean test
 
    
------------------------------------------------------------
Gradle 6.4.1
------------------------------------------------------------

Version Reference: 
Kotlin:       1.3.71
Groovy:       2.5.10
Ant:          Apache Ant(TM) version 1.10.7 compiled on September 1 2019
JVM:          13.0.2 (Oracle Corporation 13.0.2+8)
OS:           Linux 5.6.15-200.fc31.x86_64 amd64
