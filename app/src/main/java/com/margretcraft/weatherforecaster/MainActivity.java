package com.margretcraft.weatherforecaster;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.margretcraft.weatherforecaster.hours.HoursFragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button buttonleft;
    private Button buttonright;
    private ImageButton buttonTiming;

    private boolean isDebug = false;
    private final String LOGTAG = "ActivityState";

    private TownClass currentTown;
    private boolean windmes;
    private boolean tempmes;

    private HoursFragment hoursFragment;
    private MainFragment mainFragment;

    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isDebug) {
            Log.i(LOGTAG, "onCreate");
            Toast.makeText(getApplicationContext(), R.string.onCreate, Toast.LENGTH_LONG).show();
        }

        buttonleft = findViewById(R.id.button);
        buttonright = findViewById(R.id.button2);
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
                        fragmentTransaction.replace(R.id.fragmentPlace, hoursFragment, "hours");
                        fragmentTransaction.commit();
                    }

                }
            });
        }
        if (savedInstanceState == null) {
            //Hardcode временный (sharedpreferences? SQLite?)
            currentTown = new TownClass(getString(R.string.Moscow), getString(R.string.MoscowPoint));
            windmes = true;
            tempmes = true;
        } else {
            currentTown = savedInstanceState.getParcelable("Town");
            windmes = savedInstanceState.getBoolean("wind");
            tempmes = savedInstanceState.getBoolean("temp");
        }

        Date showDay = new Date();
        Date showDayPlus = new Date(showDay.getTime() + 24 * 60 * 60 * 1000);
        Date showDayMinus = new Date(showDay.getTime() - 24 * 60 * 60 * 1000);

        SimpleDateFormat sdfOneDay = new SimpleDateFormat("d", Locale.getDefault());
        buttonleft.setText(sdfOneDay.format(showDayMinus));
        buttonright.setText(sdfOneDay.format(showDayPlus));

        setFragments();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isDebug) {
            Log.i(LOGTAG, "onStart");
            Toast.makeText(getApplicationContext(), R.string.onStart, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isDebug) {
            Log.i(LOGTAG, "onStop");
            Toast.makeText(getApplicationContext(), R.string.onStop, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isDebug) {
            Log.i(LOGTAG, "onDestroy");
            Toast.makeText(getApplicationContext(), R.string.onDestroy, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isDebug) {
            Log.i(LOGTAG, "onPause");
            Toast.makeText(getApplicationContext(), R.string.onPause, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isDebug) {
            Log.i(LOGTAG, "onResume");
            Toast.makeText(getApplicationContext(), R.string.onResume, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (isDebug) {
            Log.i(LOGTAG, "onRestart");
            Toast.makeText(getApplicationContext(), R.string.onRestart, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState) {

        super.onRestoreInstanceState(saveInstanceState);

        if (isDebug) {
            Log.i(LOGTAG, "onRestoreInstanceState");
            Toast.makeText(getApplicationContext(), R.string.onRestoreInstanceState, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    @NonNull
    protected void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        if (isDebug) {
            Log.i(LOGTAG, "onSaveInstanceState");
            Toast.makeText(getApplicationContext(), R.string.onSaveInstanceState, Toast.LENGTH_LONG).show();
        }
        saveInstanceState.putParcelable("Town", currentTown);
        saveInstanceState.putBoolean("wind", windmes);
        saveInstanceState.putBoolean("temp", tempmes);
    }

    private void setFragments() {
        hoursFragment = new HoursFragment();
        mainFragment = new MainFragment();

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentPlace, mainFragment);
            fragmentTransaction.commit();
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentMainPlace, mainFragment);
            fragmentTransaction.replace(R.id.fragmentPlace, hoursFragment);
            fragmentTransaction.commit();
        }
    }

    public void changeTown() {
        Intent intent = new Intent(getApplicationContext(), TownActivity.class);
        startActivityForResult(intent, 1);
    }

    public void changeConf() {
        Intent intent = new Intent(getApplicationContext(), CastActivity.class);
        intent.putExtra("windmes", windmes);
        intent.putExtra("tempmes", tempmes);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case 1:
                    currentTown = data.getParcelableExtra("town");
                    break;
                case 2:
                    windmes = data.getBooleanExtra("windmes", true);
                    tempmes = data.getBooleanExtra("tempmes", true);
                    break;

            }
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