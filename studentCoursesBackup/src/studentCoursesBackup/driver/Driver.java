package studentCoursesBackup.driver;
import studentCoursesBackup.util.FileDisplayInterface;
import studentCoursesBackup.util.FileProcessor;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
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
    return "CourseRegistration{" +
                "capacity=" + capacity +
                ", classTimings=" + classTime +
                ", courseName='" + courseName +
                '}';
  }
}


class Student {
  private int id;
  private char[] preferences;
  private char[] courses;
  private int satisfaction;

  public Student(int id, char[] preferences) {
    this.id = id;
    this.preferences = preferences;
    this.courses = new char[3];
    this.satisfaction = 0;
  }

  public int getId() {
    return id;
  }

  public char[] getPreferences() {
    return preferences;
  }

  public char[] getCourses() {
    return courses;
  }

  public int getSatisfaction() {
    return satisfaction;
  }

  public void setPreferences(char[] preferences) {
    this.preferences = preferences;
  }

  public void setCourses(char[] courses) {
    this.courses = courses;
  }

  public void setSatisfaction(int satisfaction) {
    this.satisfaction = satisfaction;
  }
  @Override
  public String toString() {
    return "CoursePrefs{" +
                "id=" + id +
                ", preferences=" + preferences +
                '}';
  }
}

public class Driver {
  private static Course[] courses;
  private static Student[] students;

  public static void main(String[] args) {
    readCourseInfo();
    readCoursePrefs();

  }

  private static void readCourseInfo() {
    courses = new Course[9];
    try {
      Scanner s = new Scanner(new File("/home/sandila1/DP/cs542-fall-22-assign1-shreevaraandila1/studentCoursesBackup/src/studentCoursesBackup/courseInfo.txt"));
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
      e.printStackTrace();
    }
    System.out.println(Arrays.toString(courses));
  }

  private static void readCoursePrefs() {
    students = new Student[1000];
    try {
      Scanner s = new Scanner(new File("/home/sandila1/DP/cs542-fall-22-assign1-shreevaraandila1/studentCoursesBackup/src/studentCoursesBackup/coursePrefs.txt"));
      while (s.hasNextLine()) {
        String line = s.nextLine();
        String[] parts = line.split(" ");
        int id = Integer.
        parseInt(parts[0]);
        char[] preferences = new char[9];
        for (int i = 0; i < 9; i++) {
          preferences[i] = parts[i + 1].charAt(0);
        }
        students[id] = new Student(id, preferences);
      }
      s.close();
    } 
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
 


 
}
