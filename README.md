# CSX42: Assignment 1
## Name: SHREEVARA ANDILA

-----------------------------------------------------------------------
### 1) Compiling and Running Commands:
-----------------------------------------------------------------------
Compilation: ant -buildfile studentCoursesBackup/src/build.xml all <br>
Running the code: ant -buildfile studentCoursesBackup/src/build.xml run -Darg0=coursePrefs.txt -Darg1=courseInfo.txt -Darg2=regResults.txt -Darg3=regConflicts.txt -Darg4=errorLog.txt 

### **NOTE**:

While running the program note that:     <br>
1st argument: -Darg0, must be course preference file.      <br>
2nd argument: -Darg1, must be course info file.         <br>
3rd argument: -Darg2, is the file where results are written.       <br>
4th argument: -Darg3, is the file where time conflicts are written.        <br>
4th argument: -Darg3, is the file where error in registration due to capacity are written.      <br>

CoursePrefs and courseInfo file must be on the same level of src.<br>
There is no need for regResult, errorlog and regConflicts files to be present while running. It will be created in the runtime.<br>

-----------------------------------------------------------------------
### 2) Input Format Specification:
-----------------------------------------------------------------------

a.) courseInfo.txt 

- This will have the following format, 
	<course_name>:<capacity>:<class_time> 
- No duplicate course name i.e., nine lines in total.

b.) coursePrefs.txt

- This will have the following format,
	<student_id> <PREF_1> <PREF_2> <PREF_3> <PREF_4> <PREF_5> <PREF_6> <PREF_7> <PREF_8> <PREF_9>; 
- Each student will be having exactly 9 preferences given. Even though if a student has opted same course twice, it will be logged in reg_conflicts.
- There can be a max of 1000 students in total. No restriction in Id number of a student.

c.) regResults.txt

- This will have the following format,<br>
	<student1_id>:[<course_1>,<course_2>,<course_3>]::SatisfactionRating=<value>  <br>
	<student2_id>:[<course_1>,<course_2>,<course_3>]::SatisfactionRating=<value>  <br>
	<student3_id>:[<course_1>,<course_2>,<course_3>]::SatisfactionRating=<value>  <br>
	...<br>
	AverageSatisfactionRating=<value><br>

d.) regConflicts.txt

- This file contains the list of students who didnt get the courses due to the time conflicts with the previously preffered courses.

e.) errorlog.txt

- This file contains the list of students who didnt get the courses due to the course capacity.

-----------------------------------------------------------------------
### 3)  Choice of data structures and clarifications:
-----------------------------------------------------------------------

- I have created a class called Course with data members capacity, classTime, coursename.
As I read courseinfo.txt, each course data is stores in this class object. So the course is an array with Course object.
I chose to store in Course object because it allows me to get capacticy by just calling get capacity() method declared inside
Course class. Similarly there are other methods name getClassTime(), getCoursename() to return time and course name respectively.
Also this class has methods to set value such as setCapacity() to decrease the capacity as a student gets registered.

- Similarly like class Course I have delcared another class called Student to store student preference details.
As mentioned above I chose to store student data in array of Student objects because it allows me to getId, preferences just by calling particular method delared here.
I have declare 2 Array list inside Student namely my_courses and my_timings.
These are the time courses and time slot of a particular student.
I chose array list mainly because of inbuilt add() function which allows me to append the course/time to the list when student registers for a course.

-Why havent I wrote file processing in FileProcessor.java?
Since I declared the Course class and Student class inside Driver.java, and as the input is read I sore it in Student object and Course object for courseprefs and courseinfo respectively. Since tnose classes are defailt in nature I cannot call it from another package where FileProcessor is reciding.

### Note: 
- I have wrote the code in results.java to write to errorlog and regconflicts file, in which results class is implemented from FileDisplayInterface exactly as how you needed.

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating I will have to sign an
official form that I have cheated and that this form will be stored in
my official university record. I also understand that I will receive a
grade of 0 for the involved assignment for my first offense and that I
will receive a grade of F for the course for any additional
offense."

Date: 29-09-2022


