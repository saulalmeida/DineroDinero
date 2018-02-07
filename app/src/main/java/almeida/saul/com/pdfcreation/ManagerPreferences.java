package almeida.saul.com.pdfcreation;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Elite desk 700g1 on 22/01/2018.
 */

public class ManagerPreferences {

    private static final String PREF_NAME = "Prefence saved";
    private static SharedPreferences settings;
    private static SharedPreferences.Editor editor;

    public ManagerPreferences(Context context){
        if (settings == null){
            settings = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        }

        editor = settings.edit();
    }
}
