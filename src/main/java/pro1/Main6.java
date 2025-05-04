package pro1;

import pro1.apiDataModel.ActionsList;

import com.google.gson.Gson;

import java.util.Comparator;
import java.util.HashMap;


public class Main6 {

    public static void main(String[] args) {
        System.out.println(idOfBestTeacher("KIKM",2024));
    }

    public static long idOfBestTeacher(String department, int year)
    {
        // TODO 6.1 (navazuje na TODO 3):
        //  - Stáhni seznam akcí na katedře (jiná data nepoužívat) - HOTOVO

        String json = Api.getActionsByDepartment(department,year);
        ActionsList actions= new Gson().fromJson(json, ActionsList.class);

        //  - Najdi učitele s nejvyšším "score" a vrať jeho ID - HOTOVO

        HashMap<Long,Integer> h = new HashMap<>();
        for(var a :actions.items)
        {
            Integer v =h.getOrDefault(a.teacherId,0);
            h.put(a.teacherId, a.personsCount + v);
        }
        return h.entrySet().stream()
                .max(Comparator.comparing(p->p.getValue()))
                .get().getKey();
    }


}