package Services;

import java.util.ArrayList;

import Entitys.Background;
import Entitys.BackgroundConfig;
import Entitys.Enimy;
import Entitys.EnimyConfig;
import Entitys.Player;
import Entitys.PowerUp;
import Entitys.PowerUpConfig;
import Entitys.Projectiles;
import java.awt.Color;
import GameLib.GameLib;
import Interfaces.IDraw;
import Types.States;

public class Draw implements IDraw {
    public void drawEnimyProjectile(ArrayList<Projectiles> enimy_projectiles, double e_projectile_radius) {
        for (int i = 0; i < enimy_projectiles.size(); i++) {

            if (enimy_projectiles.get(i).getState() == States.ACTIVE) {

                GameLib.setColor(Color.RED);
                GameLib.drawCircle(enimy_projectiles.get(i).getX(),
                        enimy_projectiles.get(i).getY(), e_projectile_radius);
            }
        }
    }

    public void drawDistantBackground(ArrayList<Background> backgroundsSecond,
            BackgroundConfig backgroundsSecondConfig, long delta) {
        GameLib.setColor(Color.DARK_GRAY);

        backgroundsSecondConfig.incrementCount(backgroundsSecondConfig.getSpeed() * delta);

        for (int i = 0; i < backgroundsSecondConfig.getQuantity(); i++) {

            GameLib.fillRect(backgroundsSecond.get(i).getX(),
                    (backgroundsSecond.get(i).getY() + backgroundsSecondConfig.getCount()) % GameLib.HEIGHT, 2, 2);
        }
    }

    public void drawNearBackground(ArrayList<Background> backgroundsFirst,
            BackgroundConfig backgroundsFirstConfig, long delta) {
        GameLib.setColor(Color.DARK_GRAY);

        GameLib.setColor(Color.GRAY);
        backgroundsFirstConfig.incrementCount(backgroundsFirstConfig.getSpeed() * delta);

        for (int i = 0; i < backgroundsFirstConfig.getQuantity(); i++) {

            GameLib.fillRect(backgroundsFirst.get(i).getX(),
                    (backgroundsFirst.get(i).getY() + backgroundsFirstConfig.getCount()) % GameLib.HEIGHT, 3, 3);
        }
    }

    public void drawPlayer(Player player, long currentTime) {
        if (player.getState() == States.EXPLODING) {

            double alpha = (currentTime - player.getExplosionStart())
                    / (player.getExplosionEnd() - player.getExplosionStart());
            GameLib.drawExplosion(player.getX(), player.getY(), alpha);
        } else {

            GameLib.setColor(Color.BLUE);
            GameLib.drawPlayer(player.getX(), player.getY(), player.getRadius());
        }
    }

    public void drawPlayerProjectiles(ArrayList<Projectiles> player_projectiles) {
        for (int i = 0; i < player_projectiles.size(); i++) {

            if (player_projectiles.get(i).getState() == States.ACTIVE) {

                GameLib.setColor(Color.GREEN);
                GameLib.drawLine(player_projectiles.get(i).getX(),
                        player_projectiles.get(i).getY() - 5,
                        player_projectiles.get(i).getX(),
                        player_projectiles.get(i).getY() + 5);
                GameLib.drawLine(player_projectiles.get(i).getX() - 1,
                        player_projectiles.get(i).getY() - 3,
                        player_projectiles.get(i).getX() - 1,
                        player_projectiles.get(i).getY() + 3);
                GameLib.drawLine(player_projectiles.get(i).getX() + 1,
                        player_projectiles.get(i).getY() - 3,
                        player_projectiles.get(i).getX() + 1,
                        player_projectiles.get(i).getY() + 3);
            }
        }
    }

    public void drawEnimy1(ArrayList<Enimy> enimies_1, EnimyConfig enimiesConfig_1, long currentTime) {
        for (int i = 0; i < enimies_1.size(); i++) {

            if (enimies_1.get(i).getState() == States.EXPLODING) {

                double alpha = (currentTime - enimies_1.get(i).getExplosionStart())
                        / (enimies_1.get(i).getExplosionEnd() - enimies_1.get(i).getExplosionStart());
                GameLib.drawExplosion(enimies_1.get(i).getX(), enimies_1.get(i).getY(), alpha);
            }

            if (enimies_1.get(i).getState() == States.ACTIVE) {

                GameLib.setColor(Color.CYAN);
                GameLib.drawDiamond(enimies_1.get(i).getX(), enimies_1.get(i).getY(), enimiesConfig_1.getRadius());
            }
        }
    }

    public void drawEnimy2(ArrayList<Enimy> enimies_2, EnimyConfig enimiesConfig_2, long currentTime) {
        for (int i = 0; i < enimies_2.size(); i++) {

            if (enimies_2.get(i).getState() == States.EXPLODING) {

                double alpha = (currentTime - enimies_2.get(i).getExplosionStart())
                        / (enimies_2.get(i).getExplosionEnd() - enimies_2.get(i).getExplosionStart());
                GameLib.drawExplosion(enimies_2.get(i).getX(), enimies_2.get(i).getY(), alpha);
            }

            if (enimies_2.get(i).getState() == States.ACTIVE) {

                GameLib.setColor(Color.MAGENTA);
                GameLib.drawDiamond(enimies_2.get(i).getX(), enimies_2.get(i).getY(), enimiesConfig_2.getRadius());
            }
        }
    }

    public void drawEnimy3(ArrayList<Enimy> enimies_3, EnimyConfig enimiesConfig_3, long currentTime) {
        for (int i = 0; i < enimies_3.size(); i++) {

            if (enimies_3.get(i).getState() == States.EXPLODING) {

                double alpha = (currentTime - enimies_3.get(i).getExplosionStart())
                        / (enimies_3.get(i).getExplosionEnd() - enimies_3.get(i).getExplosionStart());
                GameLib.drawExplosion(enimies_3.get(i).getX(), enimies_3.get(i).getY(), alpha);
            }

            if (enimies_3.get(i).getState() == States.ACTIVE) {

                GameLib.setColor(Color.YELLOW);
                GameLib.drawTriangle(enimies_3.get(i).getX(), enimies_3.get(i).getY(), enimiesConfig_3.getRadius());
            }
        }
    }

    public void drawPowerUp(ArrayList<PowerUp> powerUpList, PowerUpConfig powerUpConfig, long currentTime) {
        for (int i = 0; i < powerUpList.size(); i++) {

            if (powerUpList.get(i).getState() == States.EXPLODING) {

                double alpha = (currentTime - powerUpList.get(i).getExplosionStart())
                        / (powerUpList.get(i).getExplosionEnd() - powerUpList.get(i).getExplosionStart());
                GameLib.drawExplosion(powerUpList.get(i).getX(), powerUpList.get(i).getY(), alpha);
            }

            if (powerUpList.get(i).getState() == States.ACTIVE) {

                GameLib.setColor(Color.GREEN);
                GameLib.drawSquare(powerUpList.get(i).getX(), powerUpList.get(i).getY(), powerUpConfig.getRadius());
            }
        }
    }
}
