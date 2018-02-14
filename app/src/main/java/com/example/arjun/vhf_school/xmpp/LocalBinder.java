package com.example.arjun.vhf_school.xmpp;

/**
 * Created by GCI on 14.02.2018.
 */


import java.lang.ref.WeakReference;
import android.os.Binder;

public class LocalBinder<S> extends Binder {
    private final WeakReference<S> mService;

    public LocalBinder(final S service) {
        mService = new WeakReference<S>(service);
    }

    public S getService() {
        return mService.get();
    }

}