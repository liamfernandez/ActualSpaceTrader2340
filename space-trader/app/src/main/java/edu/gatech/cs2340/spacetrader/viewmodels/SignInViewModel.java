package edu.gatech.cs2340.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import edu.gatech.cs2340.spacetrader.entity.Player;
import edu.gatech.cs2340.spacetrader.model.Model;
import edu.gatech.cs2340.spacetrader.model.PlayerInteractor;


public class SignInViewModel extends AndroidViewModel {
    private PlayerInteractor interactor;

    public SignInViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getPlayerInteractor();
    }

    public String addPlayer(Player player) {
        if (validatePlayer(player)) {
            interactor.addPlayerToBackEnd(player);
            return "Player Created";
        } else {
            return "Need 16 total skill points";
        }
    }

    public boolean validatePlayer(Player player) {
        int sumSkills = 0;
        sumSkills += player.getSkill1();
        sumSkills += player.getSkill2();
        sumSkills += player.getSkill3();
        sumSkills += player.getSkill4();
        if (sumSkills == 16) {
            return true;
        }
        return false;
    }
}
