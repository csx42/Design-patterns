package studentCoursesBackup.driver;
import studentCoursesBackup.util.FileDisplayInterface;
import studentCoursesBackup.util.FileProcessor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

class Course {
  private int capacity;
  private String classTime;
  private char courseName;
  public Course(int capacity, String classTime, char courseName) {
    this.capacity = capacity;
    this.classTime = classTime;
    this.courseName = courseName;
  }

  public int getCapacity() {
    return capacity;
  }


  public String getClassTime() {
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
  private ArrayList<String> my_timings;
  private int satisfaction;

  public Student(int id, char[] preferences) {
    this.id = id;
    this.preferences = preferences;
    this.my_courses = new ArrayList<String>();
    this.my_timings = new ArrayList<String>();
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

  public ArrayList<String> getTimings() {
    return my_timings;
  }

  public int getSatisfaction() {
    return satisfaction;
  }

  public void setPreferences(char[] preferences) {
    this.preferences = preferences;
  }

  public void setCourses(ArrayList<String> courses) {
    this.my_courses = courses;
  }

  public void setTimings(ArrayList<String> time) {
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

public class Driver {
  private static Course[] courses;
  private static Student[] students;

  public static void main(String[] args) {
    readCourseInfo();
    readCoursePrefs();
    assignCourses();
  }

  private static void readCourseInfo() {
    courses = new Course[9];
    try {
      Scanner s = new Scanner(new File("/home/sandila1/DP/cs542-fall-22-assign1-shreevaraandila1/studentCoursesBackup/src/studentCoursesBackup/driver/courseInfo.txt"));
      for (int i = 0; i < 9; i++) {
        String line = s.nextLine();
        String[] parts = line.split(":");
        char courseName = parts[0].charAt(0);
        int capacity = Integer.parseInt(parts[1]);
        String classTime = parts[2];
        courses[i] = new Course(capacity, classTime, courseName);
      }
      s.close();
    } 
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    System.out.println(Arrays.toString(courses));
  }

  private static void readCoursePrefs() {
    students = new Student[10];
    try {
      Scanner s = new Scanner(new File("/home/sandila1/DP/cs542-fall-22-assign1-shreevaraandila1/studentCoursesBackup/src/studentCoursesBackup/driver/coursePrefs.txt"));
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
      e.printStackTrace();
    }
    System.out.println(Arrays.toString(students));
  }

  public static void assignCourses() {
    for(Student student : students){
        if(student != null){
            for(int i =0; i < 9; i++){
                char coursename =  student.getPreferences()[i];
                int satisfactionRating = 9 - i;
                for(Course course : courses){
                    if (course.getCourseName() == coursename){
                        String time = course.getClassTime();
                        int capacityleft = course.getCapacity();
                        ArrayList<String> coursesreg = student.getCourses();
                        if(capacityleft > 0 ){
                            ArrayList<String> timings = student.getTimings();
                            for(String mytime : timings){
                                if(time==mytime){
                                    System.out.println("Time conflict");
                                }
                                else{
                                    timings.add(time);
                                }
                            }
                        String s = Character.toString(coursename);
                        coursesreg.add(s);
                        }
                    }

                }
            }
        }
    }

      
    }
}



 
