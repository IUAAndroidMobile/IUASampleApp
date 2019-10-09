package com.nicolasfanin.IUASampleApp.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Esta clase gestiona todas las Preferencias de la app de un modo simplificado.
 */
public class PreferencesUtils {

    private Context context;
    private SharedPreferences sharedPreferences;

    //Declaramos los nombres de las propiedades que vamos a almacenar.
    private String USER_NAME = "user_name";

    private static final String PREFS_NAME ="com.nicolasfanin.IUASampleApp.prefs";

    public PreferencesUtils(Context context) {
        this.context = context;
        //sharedPreferences = this.context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setUserName(String userName) {
        sharedPreferences.edit().putString(USER_NAME, userName).apply();
    }

    public String getUserName() {
        return sharedPreferences.getString(USER_NAME, "");
    }
}
