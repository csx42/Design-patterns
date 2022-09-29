package studentCoursesBackup.driver;
import studentCoursesBackup.util.FileDisplayInterface;
import studentCoursesBackup.util.Results;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Course {
  private int capacity;
  private int classTime;
  private char courseName;

  public Course(int capacity, int classTime, char courseName) {
    this.capacity = capacity;
    this.classTime = classTime;
    this.courseName = courseName;
  }

  public int getCapacity() {
    return capacity;
  }


  public int getClassTime() {
    return classTime;
  }

  public char getCourseName() {
    return courseName;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }


  @Override
  public String toString() {
    return "Course{" +
                "capacity=" + capacity +
                ", classTimings=" + classTime +
                ", courseName='" + courseName +
                '}';
  }

}

class Student {
  private int id;
  private char[] preferences;
  private ArrayList<String> my_courses;
  private ArrayList<Integer> my_timings;
  private int satisfaction;

  public Student(int id, char[] preferences) {
    this.id = id;
    this.preferences = preferences;
    this.my_courses = new ArrayList<String>();
    this.my_timings = new ArrayList<Integer>();
    this.satisfaction = 0;
  }

  public int getId() {
    return id;
  }

  public char[] getPreferences() {
    return preferences;
  }

  public ArrayList<String> getCourses() {
    return my_courses;
  }

  public ArrayList<Integer> getTimings() {
    return my_timings;
  }

  public int getSatisfaction() {
    return satisfaction;
  }

  public void setCourses(ArrayList<String> courses) {
    this.my_courses = courses;
  }

  public void setTimings(ArrayList<Integer> time) {
    this.my_timings = time;
  }

  public void setSatisfaction(int satisfaction) {
    this.satisfaction = satisfaction;
  }
  
  
  @Override
  public String toString() {
    return "student{" +
                "id=" + id +
                ", pref=" + String.valueOf(preferences) +
                '}';
  }

}

interface main {
  void readCourseInfo(String read);
  void readCoursePrefs(String prefs);
  void assignCourses(String conflicts,String errors);
  void writeRegResults(String write);
}

public class Driver implements main {
  public Course[] courses;
  public Student[] students;

  public static void main(String[] args) {
    if (args.length != 5 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}")
				|| args[3].equals("${arg3}") || args[4].equals("${arg4}")) {

			System.err.println("Error: Incorrect number of arguments. Program accepts 5 arguments.");
			System.exit(0);
		}

    Driver driver = new Driver();
    driver.readCourseInfo(args[1]);
    driver.readCoursePrefs(args[0]);
    driver.assignCourses(args[3],args[4]);
    driver.writeRegResults(args[2]);
  }

  public void readCourseInfo(String CourseInfo_args) {
    courses = new Course[9];
    try{
      Scanner s = new Scanner(new File(CourseInfo_args));
      for (int i = 0; i < 9; i++) {
        String line = s.nextLine();
        String[] parts = line.split(":");
        char courseName = parts[0].charAt(0);
        int capacity = Integer.parseInt(parts[1]);
        int classTime = Integer.parseInt(parts[2]);
        courses[i] = new Course(capacity, classTime, courseName);
      }
      s.close();
    } 
    catch (FileNotFoundException e) {
      System.err.println("File not found " + e);
      System.exit(0);
    }
    finally{}
  }

  public void readCoursePrefs(String coursePrefs_args) {
    students = new Student[1000];
    try {
      Scanner s = new Scanner(new File(coursePrefs_args));
      int j=0;
      while (s.hasNextLine()) {
        String line = s.nextLine();
        String[] parts = line.split(" ");
        int id = Integer.parseInt(parts[0]);
        char[] preferences = new char[9];
        for (int i = 0; i < 9; i++) {
          preferences[i] = parts[i + 1].charAt(0);
        }
        students[j] = new Student(id, preferences);
        j=j+1;
      }
      s.close();
    } 
    catch (FileNotFoundException e) {
      System.err.println("File not found " + e);
      System.exit(0);
    }
    finally{}
  }

  public void assignCourses(String reg_conflicts_args, String err_args) {
    String c = "";
    String d = "";
    for(Student student : students){
        if(student != null){
            for(int i =0; i < 9; i++){
                char coursename =  student.getPreferences()[i];
                int satisfactionRating = 9 - i;
                ArrayList<String> coursesreg = student.getCourses();
                if(coursesreg.size()<3){ 
                  for(Course course : courses){
                      if (course.getCourseName() == coursename){
                          int capacityleft = course.getCapacity();
                          if(capacityleft > 0 ){
                              ArrayList<Integer> timings = student.getTimings();
                              Boolean flag = false;
                              int time = course.getClassTime();
                              for(int mytime : timings){
                                if(mytime== time){
                                  flag = true;
                                  int index = timings.indexOf(mytime);
                                  String courseconflict = coursesreg.get(index);
                                  c = c + "Student "+ student.getId() + " didnt get course "+ coursename + " due to the Time conflict with course " + courseconflict + "\n";
                                }
                              }
                              if(flag == false){
                                timings.add(time);
                                student.setTimings(timings);
                                String s = Character.toString(coursename);
                                coursesreg.add(s);
                                student.setCourses(coursesreg);
                                student.setSatisfaction(student.getSatisfaction()+satisfactionRating);
                                course.setCapacity(capacityleft-1);
                              }
                          }
                          else{
                            d= d + "Student " + student.getId() + " didnt get course "+ coursename + " due to the course capacity" + "\n";
                          } 
                      }
                  }
                }
                Results display = new Results();
                display.write_regconflicts(c,reg_conflicts_args);
                display.write_error(d,err_args);
            }
        }
    }   
  }

  public void writeRegResults(String reg_results_args) {
    try {
      int number_of_students = 0;
      java.io.PrintWriter writer = new java.io.PrintWriter(reg_results_args);
      float totalSatisfactionRating = 0;
      for(Student student : students) {
        if(student!=null){
          float f = student.getSatisfaction();
          writer.printf(student.getId() + ": " + student.getCourses() + " :: SatisfactionRating=%.2f ", f/3 );
          writer.printf("\n");
          totalSatisfactionRating += f/3;
          number_of_students+=1;
        }
      }
      writer.printf("Average SatisfactionRating=%.2f ", totalSatisfactionRating/number_of_students);
      writer.close();
    } 
    catch (FileNotFoundException e) {
      System.err.println("File not found " + e);
      System.exit(0);
    }
    finally{}
  }
}
