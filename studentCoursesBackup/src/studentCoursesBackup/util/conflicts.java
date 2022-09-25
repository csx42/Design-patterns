package studentCoursesBackup.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class conflicts{
    public static void write_regconflicts(String regc, String reg_conflicts_args){
        try {
            FileWriter file = new FileWriter(reg_conflicts_args);
            BufferedWriter output = new BufferedWriter(file);
            output.write(regc);
            output.close();
        }
        catch (IOException e) {
            System.out.println("exception occurred" + e);
        }
    }
}
