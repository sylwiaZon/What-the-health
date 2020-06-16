package com.whatthehealth;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

public class NetworkChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        final ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

       /* final ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);


            final android.net.NetworkInfo wifi = connMgr
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            final android.net.NetworkInfo mobile = connMgr
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (!wifi.isConnected() && !mobile.isConnected()) {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder
                    .setTitle("No Internet Connection")
                    .setMessage("Please check your Internet settings")
                    .setCancelable(false)
                    .setPositiveButton("close", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int id)
                        {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert = builder.create();
            alert.show();
        }*/
        if(!checkConnection(context)){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder
                    .setTitle("No Internet Connection")
                    .setMessage("Please check your Internet settings")
                    .setCancelable(false)
                    .setPositiveButton("close", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int id)
                        {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert = builder.create();
            alert.show();
        }

    }
    private boolean checkConnection(Context context){
         ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Network[] netArray = connectivityManager.getAllNetworks();
            NetworkInfo netInfo;
            for (Network net : netArray) {
                netInfo = connectivityManager.getNetworkInfo(net);
                if ((netInfo.getTypeName().equalsIgnoreCase("WIFI") || netInfo.getTypeName().equalsIgnoreCase("MOBILE")) && netInfo.isConnected() && netInfo.isAvailable()) {
                    if (netInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                    Log.d("Network", "NETWORKNAME: " + netInfo.getTypeName());
                    return true;
                }
                }
            }
        } else {
            if (connectivityManager != null) {
                @SuppressWarnings("deprecation")
                NetworkInfo[] netInfoArray = connectivityManager.getAllNetworkInfo();
                if (netInfoArray != null) {
                    for (NetworkInfo netInfo : netInfoArray) {
                        if ((netInfo.getTypeName().equalsIgnoreCase("WIFI") || netInfo.getTypeName().equalsIgnoreCase("MOBILE")) && netInfo.isConnected() && netInfo.isAvailable()) {
                            if (netInfo.getState() == NetworkInfo.State.CONNECTED) {
                                Log.d("Network", "NETWORKNAME: " + netInfo.getTypeName());
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;

    }

}
