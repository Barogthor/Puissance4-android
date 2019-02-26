package com.tpandroid.esgi.puissance4.Game;

import android.app.Activity;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.games.Games;
import com.tpandroid.esgi.puissance4.Game.Cache;
import com.tpandroid.esgi.puissance4.R;

import java.util.List;

public class AchievementsUnlocker {

    private List<Integer> achievements;

    public AchievementsUnlocker()
    {
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
        //TODO Unlocker
        int nbEasyVictory = cachedir.getScore(1).getFirst();
        int nbMediumVictory = cachedir.getScore(2).getFirst();
        int nbHardVictory = cachedir.getScore(3).getFirst();

        //Test Achievement
        Games.getAchievementsClient(activity, player).unlock(achievements.get(0).toString());

        //Vs Easy IA Victories Achievements
        if(nbEasyVictory >= 1)
        {
            Games.getAchievementsClient(activity, player).unlock(achievements.get(1).toString());
        }
        if(nbEasyVictory >= 3)
        {
            Games.getAchievementsClient(activity, player).unlock(achievements.get(2).toString());
        }
        if(nbEasyVictory >= 5)
        {
            Games.getAchievementsClient(activity, player).unlock(achievements.get(3).toString());
        }

        //Vs Medium IA Victories Achievements
        if(nbMediumVictory >= 1)
        {
            Games.getAchievementsClient(activity, player).unlock(achievements.get(4).toString());
        }
        if(nbMediumVictory >= 3)
        {
            Games.getAchievementsClient(activity, player).unlock(achievements.get(5).toString());
        }
        if(nbMediumVictory >= 5)
        {
            Games.getAchievementsClient(activity, player).unlock(achievements.get(6).toString());
        }

        //Vs Hard IA Victories Achievements
        if(nbHardVictory >= 1)
        {
            Games.getAchievementsClient(activity, player).unlock(achievements.get(7).toString());
        }
        if(nbHardVictory >= 3)
        {
            Games.getAchievementsClient(activity, player).unlock(achievements.get(8).toString());
        }
        if(nbHardVictory >= 5)
        {
            Games.getAchievementsClient(activity, player).unlock(achievements.get(9).toString());
        }
    }
}
