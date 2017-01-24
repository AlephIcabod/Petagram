package com.alephicabod.petagram;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.transition.Explode;
import android.transition.Slide;
import android.transition.Visibility;
import android.view.Gravity;

/**
 * Created by angel on 24/01/2017.
 */

public  final class Utilidades {

    public final static void establecerTransitions(Activity activity){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            Slide fade=new Slide(Gravity.RIGHT);
            fade.setDuration(1000);
            Explode explode=new Explode();
            explode.setMode(Visibility.MODE_IN);
            explode.setDuration(1000);
            activity.getWindow().setEnterTransition(fade);
            activity.getWindow().setReturnTransition(explode);
            explode.setMode(Visibility.MODE_OUT);
            activity.getWindow().setExitTransition(explode);
        }
    }

    public final static  void iniciarActividad(Intent i,Activity activity){
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                activity.startActivity(i,
                        ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle());
            }else{
                activity.startActivity(i);
            }
    }

}
