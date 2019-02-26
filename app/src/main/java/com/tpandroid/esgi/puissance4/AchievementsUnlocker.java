package com.tpandroid.esgi.puissance4;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.tpandroid.esgi.puissance4.Game.Cache;

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

    private void unlock(Cache cachedir){
        //TODO Unlocker

    }
}
