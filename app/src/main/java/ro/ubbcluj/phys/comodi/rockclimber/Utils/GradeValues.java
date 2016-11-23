package ro.ubbcluj.phys.comodi.rockclimber.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sboda on 11/12/16.
 */
public class GradeValues {

    String[] grade_YDS = {"3-4", "5", "5.1", "5.2", "5.3", "5.4", "5.5", "5.6", "5.7", "5.8", "5.9", "5.10a", "5.10b", "5.10c", "5.10d", "5.11a", "6c+", "5.11b", "5.11c", "5.11d", "5.12a", "5.12b", "5.12c", "5.12d", "5.13a", "5.13b", "5.13c", "5.13d", "5.14a", "5.14b", "5.14c", "5.14d", "5.15a", "5.15b", "5.15c"};
    String[] grade_British_Tech = {"1", "2", "3", "4a", "4b", "4c", "5a", "5b", "5c", "6a", "6b", "6c", "7a", "7b"};
    String[] grade_British_Adj = {"M", "D", "VD", "S", "HS", "VS", "HVS", "E1", "E2", "E3", "VIIIc", "E4", "E5", "E6", "E7", "E8", "E9", "E10", "E11"};
    String[] grade_French = {"1", "2", "3", "4a", "4b", "4c", "5a", "5b", "5c", "6a", "6a+", "6b", "6b+", "6c", "7a", "7a+", "7b", "7b+", "7c", "7c+", "8a", "8a+", "8b", "8b+", "8c", "8c+", "9a", "9a+", "9b", "9b+"};
    String[] grade_UIAA = {"I", "II", "III", "IV", "IV+", "V", "V+", "VI-", "VI", "VI+", "VII-", "VII", "VII+", "VIII-", "VIII", "VIII+", "IX−", "IX", "IX+", "X−", "X", "X+", "XI−", "XI", "XI+", "XI+/XII−", "XII-"};
    String[] grade_Saxon = {"I", "II", "III", "IV", "V", "VI", "VIIa", "VIIb", "VIIc", "VIIIa", "VIIIb", "VIIIc", "IXa", "IXb", "IXc", "Xa", "Xb", "Xc", "XIa", "XIb", "XIc"};
    String[] grade_Ewbank = {"1-2", "3-4", "5-6", "7-8", "8-9", "10-11", "11-12", "13", "14-15", "15-16", "17", "18", "19", "20", "21", "22", "23", "24", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38"};
    String[] grade_Nordic = {"1", "2", "3", "4", "-5", "5", "5", "6−", "6", "-7", "7", "7", "7", "8−", "8", "8", "9−", "9", "9", "10−", "10", "10", "11−", "11"};
    String[] grade_Brazil = {"I", "I sup", "II", "II sup", "III", "III sup", "IV", "IV sup", "V", "VI", "VI sup", "8a", "8b", "8c", "9a", "9b", "9c", "10a", "10b", "10c", "11a", "11b", "11c", "12a", "12b", "12c"};

    public ArrayList<String> returngrades(String grading_system) {
        ArrayList<String> stringList = new ArrayList<String>();
        if (grading_system.contains("UIAA")) {
            stringList = new ArrayList<String>(Arrays.asList(grade_UIAA));
        }
        if (grading_system.contains("YDS")) {
            stringList = new ArrayList<String>(Arrays.asList(grade_YDS));
        }
        if (grading_system.contains("British_Tech")) {
            stringList = new ArrayList<String>(Arrays.asList(grade_British_Tech));
        }
        if (grading_system.contains("British_Adj")) {
            stringList = new ArrayList<String>(Arrays.asList(grade_British_Adj));
        }if (grading_system.contains("French")) {
            stringList = new ArrayList<String>(Arrays.asList(grade_French));
        }if (grading_system.contains("Saxon")) {
            stringList = new ArrayList<String>(Arrays.asList(grade_Saxon));
        }if (grading_system.contains("Ewbank")) {
            stringList = new ArrayList<String>(Arrays.asList(grade_Ewbank));
        }if (grading_system.contains("Nordic")) {
            stringList = new ArrayList<String>(Arrays.asList(grade_Nordic));
        }
        if (grading_system.contains("Brazil")) {
            stringList = new ArrayList<String>(Arrays.asList(grade_Brazil));
        }





        //TODO implement for the remaining gradeing system the proper return
        return stringList;

    }
}
