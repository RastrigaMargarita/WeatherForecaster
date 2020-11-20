package com.margretcraft.weatherforecaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.margretcraft.weatherforecaster.hours.DaysFragment;
import com.margretcraft.weatherforecaster.towns.TownActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    String[] days;
    private Button buttonTiming;
    private String AppName = ApplicationClass.getInstance().getPackageName();
    private TownClass currentTown;
    private boolean windmes;
    private boolean tempmes;

    private DaysFragment daysFragment;
    private MainFragment mainFragment;

    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLifecycle().addObserver(new ActivityObserver());
        SharedPreferences sharedPref = getSharedPreferences(AppName, MODE_PRIVATE);
        if (sharedPref.getBoolean("theme", true)) {
            setTheme(R.style.AppTheme);
        } else {
            setTheme(R.style.AppThemeGreen);
        }
        setContentView(R.layout.activity_main);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            buttonTiming = findViewById(R.id.buttonTiming);
            buttonTiming.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (getSupportFragmentManager().findFragmentByTag("hours") != null) {
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragmentPlace, mainFragment);
                        fragmentTransaction.commit();
                    } else {
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragmentPlace, daysFragment, "hours");
                        fragmentTransaction.commit();
                    }

                }
            });
        }



        if (savedInstanceState == null) {
            currentTown = new TownClass(sharedPref.getString("townName", getString(R.string.Moscow)),
                    sharedPref.getString("townPoint", getString(R.string.MoscowPoint)),
                    sharedPref.getString("townZone", "UTF+3"));
            windmes = sharedPref.getBoolean("wind", true);
            tempmes = sharedPref.getBoolean("temp", true);

        } else {
            currentTown = savedInstanceState.getParcelable("Town");
            windmes = savedInstanceState.getBoolean("wind");
            tempmes = savedInstanceState.getBoolean("temp");

        }

        Date showDay = new Date();

        SimpleDateFormat sdfOneDay = new SimpleDateFormat("d", Locale.getDefault());
        days = new String[5];

        for (int i = 0; i < 5; i++) {
            days[i] = "" + sdfOneDay.format(new Date(showDay.getTime() + i * 24 * 60 * 60 * 1000));
        }

        setFragments();
    }


    @Override
    @NonNull
    protected void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        saveInstanceState.putParcelable("Town", currentTown);
        saveInstanceState.putBoolean("wind", windmes);
        saveInstanceState.putBoolean("temp", tempmes);
    }

    private void setFragments() {
        daysFragment = new DaysFragment();
        Bundle bd = new Bundle();
        bd.putStringArray("days", days);
        daysFragment.setArguments(bd);
        mainFragment = new MainFragment();

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentPlace, mainFragment);
            fragmentTransaction.commit();
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentMainPlace, mainFragment);
            fragmentTransaction.replace(R.id.fragmentPlace, daysFragment);
            fragmentTransaction.commit();
        }
    }

    public void changeTown() {
        Intent intent = new Intent(getApplicationContext(), TownActivity.class);
        startActivityForResult(intent, 1);
    }

    public void changeConf() {
        Intent intent = new Intent(getApplicationContext(), CastActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
   protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            SharedPreferences sharedPref = getSharedPreferences(AppName, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            switch (requestCode) {

                case 1:
                    currentTown = data.getParcelableExtra("town");
                    editor.putString("townName", currentTown.getName());
                    editor.putString("townPoint", currentTown.getPoint());
                    editor.putString("townZone", currentTown.getTimeZone());
                    break;
//                case 2:
//                    windmes = data.getBooleanExtra("windmes", true);
//                    tempmes = data.getBooleanExtra("tempmes", true);
//                    editor.putBoolean("windmes", windmes);
//                    editor.putBoolean("tempmes", tempmes);
//                    break;
           }
            editor.apply();
        }
        setFragments();
    }

    public void goWebSite() {
        StringBuilder sb = new StringBuilder();
        sb.append(getString(R.string.map_upl)).append(currentTown.getPoint()).append(getString(R.string.map_size)).append(getString(R.string.map_pres));
        Uri uri = Uri.parse(sb.toString());

        Intent browser = new Intent(Intent.ACTION_VIEW, uri);
        Intent chooser = Intent.createChooser(browser, getString(R.string.choose_app));
        if (browser.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        }
    }

    public TownClass getCurrentTown() {
        return currentTown;
    }

    public boolean isWindmes() {
        return windmes;
    }

    public boolean isTempmes() {
        return tempmes;
    }

}