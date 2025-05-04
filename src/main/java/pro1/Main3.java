package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.ActionsList;
import pro1.apiDataModel.TeachersList;

import java.util.Comparator;

public class Main3 {

    public static void main(String[] args) {
        System.out.println(emailOfBestTeacher("KIKM",2024));
    }

    public static String emailOfBestTeacher(String department, int year)
    {
        // TODO 3.2:

        //  - Stáhni seznam učitelů na katedře - Hotovo
        String jsonTeachers = Api.getTeachersByDepartment(department);
        TeachersList teachersList = new Gson().fromJson(jsonTeachers, TeachersList.class);

        //  - Stáhni seznam akcí na katedře - Hotovo
        String jsonActions = Api.getActionsByDepartment(department,year);
        ActionsList actionsList = new Gson().fromJson(jsonActions, ActionsList.class);

        //  - Najdi učitele s nejvyšším "score" a vrať jeho e-mail - Hotobo
        return teachersList.items.stream()
                .max(Comparator.comparingLong(teacher -> TeacherScore(teacher.id, actionsList)))
                .get().email;
    }

    public static long TeacherScore(long teacherId, ActionsList departmentSchedule)
    {
        // TODO 3.1: Doplň pomocnou metodu - součet všech přihlášených studentů na akcích daného učitele - Hotovo snad
        return departmentSchedule.items.stream()
                .filter(action -> action.teacherId == teacherId)  // Akce daného učitele
                .mapToLong(action -> action.personsCount)        // Počet studentů na akci
                .sum();
    }
}