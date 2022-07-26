package Services;

import java.util.ArrayList;

import Entitys.Enimy;
import Entitys.Player;
import Entitys.PowerUp;
import Entitys.Projectiles;
import Interfaces.IColisions;
import Types.States;

public class Colisions implements IColisions {
    public void colisionPlayerProjectile(ArrayList<Projectiles> enimy_projectiles, Player player,
            long currentTime,
            double e_projectile_radius) {
        for (int i = 0; i < enimy_projectiles.size(); i++) {

            double dx = enimy_projectiles.get(i).getX() - player.getX();
            double dy = enimy_projectiles.get(i).getY() - player.getY();
            double dist = Math.sqrt(dx * dx + dy * dy);

            if (dist < (player.getRadius() + e_projectile_radius) * 0.8) {

                player.setState(States.EXPLODING);
                player.setExplosionStart(currentTime);
                player.setExplosionEnd(currentTime + 2000);
            }
        }
    }

    public void colisionPlayerEnimy(ArrayList<Enimy> enimies_1, ArrayList<Enimy> enimies_2, ArrayList<Enimy> enimies_3,
            Player player,
            long currentTime,
            double enemy1_radius, double enemy2_radius, double enemy3_radius) {

        for (int i = 0; i < enimies_1.size(); i++) {

            double dx = enimies_1.get(i).getX() - player.getX();
            double dy = enimies_1.get(i).getY() - player.getY();
            double dist = Math.sqrt(dx * dx + dy * dy);

            if (dist < (player.getRadius() + enemy1_radius) * 0.8) {
                player.setState(States.EXPLODING);
                player.setExplosionStart(currentTime);
                player.setExplosionEnd(currentTime + 2000);
            }
        }

        for (int i = 0; i < enimies_2.size(); i++) {

            double dx = enimies_2.get(i).getX() - player.getX();
            double dy = enimies_2.get(i).getY() - player.getY();
            double dist = Math.sqrt(dx * dx + dy * dy);

            if (dist < (player.getRadius() + enemy2_radius) * 0.8) {

                player.setState(States.EXPLODING);
                player.setExplosionStart(currentTime);
                player.setExplosionEnd(currentTime + 2000);
            }
        }

        for (int i = 0; i < enimies_3.size(); i++) {

            double dx = enimies_3.get(i).getX() - player.getX();
            double dy = enimies_3.get(i).getY() - player.getY();
            double dist = Math.sqrt(dx * dx + dy * dy);

            if (dist < (player.getRadius() + enemy3_radius) * 0.8) {
                player.setState(States.EXPLODING);
                player.setExplosionStart(currentTime);
                player.setExplosionEnd(currentTime + 2000);
            }
        }
    }

    public void colisionPlayerPowerUp(ArrayList<PowerUp> powerUpList,
            Player player,
            long currentTime, double powerUpRadius) {

        for (int i = 0; i < powerUpList.size(); i++) {

            double dx = powerUpList.get(i).getX() - player.getX();
            double dy = powerUpList.get(i).getY() - player.getY();
            double dist = Math.sqrt(dx * dx + dy * dy);

            if (dist < (player.getRadius() + powerUpRadius) * 0.8) {
                player.setVy(player.getVy() + 0.005);
                player.setVx(player.getVx() + 0.005);

                powerUpList.get(i).setState(States.EXPLODING);

                powerUpList.get(i).setExplosionStart(currentTime);
                powerUpList.get(i).setExplosionEnd(currentTime);
            }
        }

    }

    public void colisionEnimyProjectile(ArrayList<Enimy> enimies_1, ArrayList<Enimy> enimies_2,
            ArrayList<Enimy> enimies_3, Player player,
            ArrayList<Projectiles> player_projectiles,
            long currentTime,
            double enemy1_radius, double enemy2_radius, double enemy3_radius) {
        for (int k = 0; k < player_projectiles.size(); k++) {

            for (int i = 0; i < enimies_1.size(); i++) {

                if (enimies_1.get(i).getState() == States.ACTIVE) {

                    double dx = enimies_1.get(i).getX() - player_projectiles.get(k).getX();
                    double dy = enimies_1.get(i).getY() - player_projectiles.get(k).getY();
                    double dist = Math.sqrt(dx * dx + dy * dy);

                    if (dist < enemy1_radius) {

                        enimies_1.get(i).setState(States.EXPLODING);
                        enimies_1.get(i).setExplosionStart(currentTime);
                        enimies_1.get(i).setExplosionEnd(currentTime + 500);
                    }
                }
            }

            for (int i = 0; i < enimies_2.size(); i++) {

                if (enimies_2.get(i).getState() == States.ACTIVE) {

                    double dx = enimies_2.get(i).getX() - player_projectiles.get(k).getX();
                    double dy = enimies_2.get(i).getY() - player_projectiles.get(k).getY();
                    double dist = Math.sqrt(dx * dx + dy * dy);

                    if (dist < enemy2_radius) {

                        enimies_2.get(i).setState(States.EXPLODING);
                        enimies_2.get(i).setExplosionStart(currentTime);
                        enimies_2.get(i).setExplosionEnd(currentTime + 500);
                    }
                }
            }

            for (int i = 0; i < enimies_3.size(); i++) {

                if (enimies_3.get(i).getState() == States.ACTIVE) {

                    double dx = enimies_3.get(i).getX() - player_projectiles.get(k).getX();
                    double dy = enimies_3.get(i).getY() - player_projectiles.get(k).getY();
                    double dist = Math.sqrt(dx * dx + dy * dy);

                    if (dist < enemy3_radius) {

                        enimies_3.get(i).setState(States.EXPLODING);
                        enimies_3.get(i).setExplosionStart(currentTime);
                        enimies_3.get(i).setExplosionEnd(currentTime + 500);
                    }
                }
            }
        }
    }

}