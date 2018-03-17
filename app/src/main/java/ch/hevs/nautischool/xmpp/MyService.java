package ch.hevs.nautischool.xmpp;

/**
 * Created by GCI on 14.02.2018.
 */
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.IBinder;


public class MyService extends Service {

    public static ConnectivityManager cm;
    public static MyXMPP xmpp;


    @Override
    public IBinder onBind(final Intent intent) {
        return new LocalBinder<MyService>(this);
    }

    /**
     * Fonction
     */
    @Override
    public void onCreate() {
        super.onCreate();
        cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // read connexion settings from SharedPreferences
        SettingsPrefs settings = new SettingsPrefs(this);
        String server = settings.getServer();
        String user = settings.getUser();
        String password = settings.getPassword();

        xmpp = MyXMPP.getInstance(MyService.this,server, user,password);
        xmpp.connect("onCreate");

    }

    @Override
    public int onStartCommand(final Intent intent, final int flags,
                              final int startId) {
        return Service.START_NOT_STICKY;
    }

    @Override
    public boolean onUnbind(final Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyXMPP.connection.disconnect();
    }
}