package ananda.if4b.tulis_aja;

import android.content.Context;
import android.content.SharedPreferences;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Utility {
    private static final String PREFERENCE_FINAL_KEY = Utility.class.getPackage().getName();
    private static final String BASE_URL = "https://tulisaja-restapi-amber.vercel.app/";

    public static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static void setValue(Context context, String sPref, String sValue) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_FINAL_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(sPref, sValue);
        editor.commit();
    }

    public static String getValue(Context context, String sPref) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_FINAL_KEY, Context.MODE_PRIVATE);
        String sValue = sp.getString(sPref, null);
        return sValue;
    }

    public static boolean checkValue(Context context, String sPref) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_FINAL_KEY, Context.MODE_PRIVATE);
        String sValue = sp.getString(sPref, null);
        return sValue != null;
    }

    public static void cleanUser(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_FINAL_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("xUserId", null);
        editor.apply();
    }
}
