package com.margretcraft.weatherforecaster;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

public class ActivityObserver implements LifecycleObserver {
    private boolean isDebug = false;

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    void onAny(LifecycleOwner source, Lifecycle.Event event) {
        if (isDebug) {
            Log.i(source.getClass().getSimpleName(), event.toString());
            Toast.makeText(ApplicationClass.getInstance().getApplicationContext(),
                    source.getClass().getSimpleName() + " " + event, Toast.LENGTH_LONG).show();
        }
    }
}
