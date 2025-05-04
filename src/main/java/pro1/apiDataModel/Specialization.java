package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Specialization {
    @SerializedName("eprDeadlinePrihlaska")
    public DeadlineValue deadline;

    public static class DeadlineValue {
        @SerializedName("value")
        public String value;
    }
}
