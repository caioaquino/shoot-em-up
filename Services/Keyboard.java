package Services;

import java.util.ArrayList;

import Entitys.Player;
import Entitys.Projectiles;
import GameLib.GameLib;
import Interfaces.IKeyboard;
import Types.States;

public class Keyboard implements IKeyboard {
    public void handlePlayerInput(Player player, long delta, long currentTime,
            ArrayList<Projectiles> player_projectiles) {
        if (player.getState() == States.ACTIVE) {

            if (GameLib.iskeyPressed(GameLib.KEY_UP))
                player.setY(player.getY() - delta * player.getVy());
            if (GameLib.iskeyPressed(GameLib.KEY_DOWN))
                player.setY(player.getY() + delta * player.getVy());
            if (GameLib.iskeyPressed(GameLib.KEY_LEFT))
                player.setX(player.getX() - delta * player.getVx());
            if (GameLib.iskeyPressed(GameLib.KEY_RIGHT))
                player.setX(player.getX() + delta * player.getVx());

            if (GameLib.iskeyPressed(GameLib.KEY_CONTROL)) {

                if (currentTime > player.getNextShot()) {
                    Projectiles projectiles = new Projectiles();
                    projectiles.setState(States.ACTIVE);
                    projectiles.setX(player.getX());
                    projectiles.setY(player.getY() - 2 * player.getRadius());
                    projectiles.setVx(0.0);
                    projectiles.setVy(-1.0);
                    player_projectiles.add(projectiles);
                    player.setNextShot(currentTime + 100);

                }
            }
        }
    }
}
