package com.tpandroid.esgi.puissance4.Game;

import android.app.Activity;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.games.Games;
import com.tpandroid.esgi.puissance4.Game.Cache;
import com.tpandroid.esgi.puissance4.R;

import java.util.ArrayList;

public class AchievementsUnlocker {

    private ArrayList<Integer> achievements;

    public AchievementsUnlocker()
    {
        achievements = new ArrayList<>();
        achievements.add(R.string.achievement_test_achievement);

        achievements.add(R.string.achievement_first_blood_facile);
        achievements.add(R.string.achievement_hat_trick_facile);
        achievements.add(R.string.achievement_carnage_facile);

        achievements.add(R.string.achievement_first_blood_moyen);
        achievements.add(R.string.achievement_hat_trick_moyen);
        achievements.add(R.string.achievement_carnage_moyen);

        achievements.add(R.string.achievement_first_blood_difficile);
        achievements.add(R.string.achievement_hat_trick_difficile);
        achievements.add(R.string.achievement_carnage_difficile);

        achievements.add(R.string.achievement_first_blood_online);
        achievements.add(R.string.achievement_hat_trick_online);
        achievements.add(R.string.achievement_carnage_online);

        achievements.add(R.string.achievement_friendly_fire);
    }

    public void unlock(Cache cachedir, Activity activity, GoogleSignInAccount player){
        int nbEasyVictory = cachedir.getScore(1).getFirst();
        int nbMediumVictory = cachedir.getScore(2).getFirst();
        int nbHardVictory = cachedir.getScore(3).getFirst();

        //Test Achievement
        Games.getAchievementsClient(activity, player).unlock(activity.getString(achievements.get(0)));
        Log.i("TEST_RESULT", activity.getString(achievements.get(0)));

        //Vs Easy IA Victories Achievements
        if(nbEasyVictory >= 1)
        {
            Games.getAchievementsClient(activity, player).unlock(activity.getString(achievements.get(1)));
        }
        if(nbEasyVictory >= 3)
        {
            Games.getAchievementsClient(activity, player).unlock(activity.getString(achievements.get(2)));
        }
        if(nbEasyVictory >= 5)
        {
            Games.getAchievementsClient(activity, player).unlock(activity.getString(achievements.get(3)));
        }

        //Vs Medium IA Victories Achievements
        if(nbMediumVictory >= 1)
        {
            Games.getAchievementsClient(activity, player).unlock(activity.getString(achievements.get(4)));
        }
        if(nbMediumVictory >= 3)
        {
            Games.getAchievementsClient(activity, player).unlock(activity.getString(achievements.get(5)));
        }
        if(nbMediumVictory >= 5)
        {
            Games.getAchievementsClient(activity, player).unlock(activity.getString(achievements.get(6)));
        }

        //Vs Hard IA Victories Achievements
        if(nbHardVictory >= 1)
        {
            Games.getAchievementsClient(activity, player).unlock(activity.getString(achievements.get(7)));
        }
        if(nbHardVictory >= 3)
        {
            Games.getAchievementsClient(activity, player).unlock(activity.getString(achievements.get(8)));
        }
        if(nbHardVictory >= 5)
        {
            Games.getAchievementsClient(activity, player).unlock(activity.getString(achievements.get(9)));
        }
    }
}
