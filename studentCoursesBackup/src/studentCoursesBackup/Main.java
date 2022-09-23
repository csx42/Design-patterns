/*
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CourseRegistration {
 
    private int capacity;
    private int classTimings;
    private String courseName;
    private int satisfactionRating;
 
    public CourseRegistration() {
    }
 
    public CourseRegistration(int capacity, int classTimings, String courseName) {
        this.capacity = capacity;
        this.classTimings = classTimings;
        this.courseName = courseName;
    }
 
    public int getCapacity() {
        return capacity;
    }
 
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
 
    public int getClassTimings() {
        return classTimings;
    }
 
    public void setClassTimings(int classTimings) {
        this.classTimings = classTimings;
    }
 
    public String getCourseName() {
        return courseName;
    }
 
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
 
    public int getSatisfactionRating() {
        return satisfactionRating;
    }
 
    public void setSatisfactionRating(int satisfactionRating) {
        this.satisfactionRating = satisfactionRating;
    }
 
    @Override
    public String toString() {
        return "CourseRegistration{" +
                "capacity=" + capacity +
                ", classTimings=" + classTimings +
                ", courseName='" + courseName + '\'' +
                ", satisfactionRating=" + satisfactionRating +
                '}';
    }
}

public class Main {
 
    public static void main(String[] args) {
 
        //Reading the coursePrefs.txt file
        File file1 = new File("/home/sandila1/DP/cs542-fall-22-assign1-shreevaraandila1/studentCoursesBackup/src/studentCoursesBackup/coursePrefs.txt");
        BufferedReader br1 = null;
        int lineNumber1 = 0;
        Map<Integer, List<String>> coursePrefs = new HashMap<>();
 
        try {
            br1 = new BufferedReader(new FileReader(file1));
            String line1;
            while ((line1 = br1.readLine()) != null) {
                //System.out.println("line1 = " + line1);
                String data[] = line1.split("\\s+");
                //System.out.println("data = " + Arrays.toString(data));
                List<String> prefs = new ArrayList<>();
                int key = Integer.parseInt(data[0]);
                for (int i = 1; i < data.length; i++) {
                    prefs.add(data[i]);
                }
                coursePrefs.put(key, prefs);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br1 != null)
                    br1.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("coursePrefs = " + coursePrefs);

        File file2 = new File("/home/sandila1/DP/cs542-fall-22-assign1-shreevaraandila1/studentCoursesBackup/src/studentCoursesBackup/courseInfo.txt");
        BufferedReader br2 = null;
        int lineNumber2 = 0;
        Map<String, CourseRegistration> courseInfo = new HashMap<>();
 
        try {
            br2 = new BufferedReader(new FileReader(file2));
            String line2;
            while ((line2 = br2.readLine()) != null) {
                //System.out.println("line2 = " + line2);
                String data[] = line2.split(":");
                //System.out.println("data = " + Arrays.toString(data));
                CourseRegistration courseRegistration = new CourseRegistration();
                courseRegistration.setCourseName(data[0]);
                courseRegistration.setCapacity(Integer.parseInt(data[1]));
                courseRegistration.setClassTimings(Integer.parseInt(data[2]));
                courseInfo.put(data[0], courseRegistration);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br2 != null)
                    br2.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
 
        System.out.println("courseInfo = " + courseInfo);



    }
}

*/


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

public class Main {
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