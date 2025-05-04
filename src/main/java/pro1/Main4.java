package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.TeachersList;

import java.util.Comparator;

public class Main4 {

    public static void main(String[] args) {
         printShortestEmails("KIKM",5);
    }

    public static void printShortestEmails(String department, int count)
    {
        // TODO 4.1: Vypiš do konzole "count" nejkratších učitelských emailových adres - Hotovo
        String jsonTeachers = Api.getTeachersByDepartment(department);
        TeachersList teachersList = new Gson().fromJson(jsonTeachers, TeachersList.class);

        teachersList.items.stream()
                .filter(teacher -> teacher.email != null)
                .sorted(Comparator.comparing(teacher -> teacher.email.length()))
                .limit(count)
                .forEach(teacher -> System.out.println(teacher.email));
    }
}