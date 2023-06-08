package ananda.if4b.tulis_aja;

import com.google.gson.annotations.SerializedName;

public class ValueNoData {
    @SerializedName("success")
    private int success;

    @SerializedName("message")
    private String message;

    public int getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
