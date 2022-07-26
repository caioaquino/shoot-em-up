package Interfaces;

import java.util.ArrayList;

import Entitys.Player;
import Entitys.Projectiles;

public interface IKeyboard {
    public void handlePlayerInput(Player player, long delta, long currentTime,
            ArrayList<Projectiles> player_projectiles);
}
