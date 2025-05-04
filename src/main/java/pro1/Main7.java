package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.SpecializationsList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class Main7 {
    public static void main(String[] args) {
        System.out.println(specializationDeadlines(2025));
    }

    public static String specializationDeadlines(int year) {
        String json = Api.getSpecializations(year);
        SpecializationsList specializations = new Gson().fromJson(json, SpecializationsList.class);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");

        String res = specializations.items.stream()
                .map(i -> i.deadline != null ? i.deadline.value : null)
                .filter(s -> s != null && !s.equals("null"))
                .map(s -> LocalDate.parse(s.trim(), formatter))
                .distinct()
                .sorted()
                .map(d -> d.format(formatter))
                .collect(Collectors.joining(","));

        return res;

    }
}
