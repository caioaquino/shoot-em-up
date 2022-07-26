package Services;

import java.util.ArrayList;
import GameLib.GameLib;
import Interfaces.IGameStates;
import Entitys.Enimy;
import Entitys.EnimyConfig;
import Entitys.Player;
import Entitys.PowerUp;
import Entitys.PowerUpConfig;
import Entitys.Projectiles;
import Types.States;

public class GameStates implements IGameStates {

    public void updatePlayerProjectileState(ArrayList<Projectiles> player_projectiles, long delta) {
        for (int i = 0; i < player_projectiles.size(); i++) {

            if (player_projectiles.get(i).getState() == States.ACTIVE) {

                /* verificando se projétil saiu da tela */
                if (player_projectiles.get(i).getY() < 0) {
                    player_projectiles.get(i).setState(States.INACTIVE);
                    player_projectiles.remove(i);
                    if (i > 0) {
                        i--;
                    } else {
                        break;
                    }

                } else {

                    player_projectiles.get(i).setX(player_projectiles.get(i).getX()
                            + player_projectiles.get(i).getVx() * delta);
                    player_projectiles.get(i).setY(player_projectiles.get(i).getY()
                            + player_projectiles.get(i).getVy() * delta);
                }
            }

        }
    }

    public void updateEnimyProjectiles(ArrayList<Projectiles> enimy_projectiles, long delta, int HEIGHT) {

        for (int i = 0; i < enimy_projectiles.size(); i++) {

            if (enimy_projectiles.get(i).getState() == States.ACTIVE) {

                /* verificando se projétil saiu da tela */
                if (enimy_projectiles.get(i).getY() > HEIGHT) {

                    enimy_projectiles.get(i).setState(States.INACTIVE);
                    enimy_projectiles.remove(i);
                    if (i > 0) {
                        i--;
                    } else {
                        break;
                    }
                } else {

                    enimy_projectiles.get(i).setX(enimy_projectiles.get(i).getX()
                            + enimy_projectiles.get(i).getVx() * delta);
                    enimy_projectiles.get(i).setY(enimy_projectiles.get(i).getY()
                            + enimy_projectiles.get(i).getVy() * delta);

                }
            }
        }
    }

    public void updateEnimy1(ArrayList<Enimy> enimies_1, Player player, ArrayList<Projectiles> enimy_projectiles,
            long delta, long currentTime, int HEIGHT) {
        for (int i = 0; i < enimies_1.size(); i++) {

            if (enimies_1.get(i).getState() == States.EXPLODING) {

                if (currentTime > enimies_1.get(i).getExplosionEnd()) {

                    enimies_1.get(i).setState(States.INACTIVE);
                    enimies_1.remove(i);
                    if (i > 0) {
                        i--;
                    } else {
                        break;
                    }
                }
            }

            if (enimies_1.get(i).getState() == States.ACTIVE) {

                /* verificando se inimigo saiu da tela */
                if (enimies_1.get(i).getY() > HEIGHT + 10) {

                    enimies_1.get(i).setState(States.INACTIVE);
                    enimies_1.remove(i);
                    if (i > 0) {
                        i--;
                    } else {
                        break;
                    }
                } else {

                    enimies_1.get(i).setX(enimies_1.get(i).getX()
                            + enimies_1.get(i).getVelocity() * Math.cos(enimies_1.get(i).getAngle()) * delta);

                    enimies_1.get(i).setY(enimies_1.get(i).getY() + enimies_1.get(i).getVelocity()
                            * Math.sin(enimies_1.get(i).getAngle()) * delta * (-1.0));
                    enimies_1.get(i)
                            .setAngle(enimies_1.get(i).getAngle() + enimies_1.get(i).getRotationVelocity() * delta);

                    if (currentTime > enimies_1.get(i).getNextShot()
                            && enimies_1.get(i).getY() < player.getY()) {

                        Projectiles projectile = new Projectiles();
                        projectile.setX(enimies_1.get(i).getX());
                        projectile.setY(enimies_1.get(i).getY());
                        projectile.setVx(Math.cos(enimies_1.get(i).getAngle()) * 0.45);
                        projectile.setVy(Math.sin(enimies_1.get(i).getAngle()) * 0.45 * (-1.0));
                        projectile.setState(States.ACTIVE);
                        enimy_projectiles.add(projectile);
                        enimies_1.get(i).setNextShot((long) (currentTime + 200 + Math.random() * 500));
                    }
                }
            }
        }
    }

    public void updateEnimy2(ArrayList<Enimy> enimies_2, Player player, ArrayList<Projectiles> enimy_projectiles,
            long delta, long currentTime, int HEIGHT, int WIDTH) {

        for (int i = 0; i < enimies_2.size(); i++) {

            if (enimies_2.get(i).getState() == States.EXPLODING) {

                if (currentTime > enimies_2.get(i).getExplosionEnd()) {

                    enimies_2.get(i).setState(States.INACTIVE);
                    enimies_2.remove(i);
                    if (i > 0) {
                        i--;
                    } else {
                        break;
                    }
                }
            }

            if (enimies_2.get(i).getState() == States.ACTIVE) {

                /* verificando se inimigo saiu da tela */
                if (enimies_2.get(i).getX() < -10 || enimies_2.get(i).getX() > WIDTH + 10) {

                    enimies_2.get(i).setState(States.INACTIVE);
                    enimies_2.remove(i);
                    if (i > 0) {
                        i--;
                    } else {
                        break;
                    }

                } else {

                    boolean shootNow = false;
                    double previousY = enimies_2.get(i).getY();
                    enimies_2.get(i).setX(enimies_2.get(i).getX()
                            + enimies_2.get(i).getVelocity() * Math.cos(enimies_2.get(i).getAngle()) * delta);
                    enimies_2.get(i)
                            .setY(enimies_2.get(i).getY()
                                    + enimies_2.get(i).getVelocity() * Math.sin(enimies_2.get(i).getAngle()) * delta
                                            * (-1.0));
                    enimies_2.get(i)
                            .setAngle(enimies_2.get(i).getAngle() + enimies_2.get(i).getRotationVelocity() * delta);

                    double threshold = HEIGHT * 0.30;

                    if (previousY < threshold && enimies_2.get(i).getY() >= threshold) {

                        if (enimies_2.get(i).getX() < WIDTH / 2)
                            enimies_2.get(i).setRotationVelocity(0.003);
                        else
                            enimies_2.get(i).setRotationVelocity(-0.003);
                    }

                    if (enimies_2.get(i).getRotationVelocity() > 0
                            && Math.abs(enimies_2.get(i).getAngle() - 3 * Math.PI) < 0.05) {

                        enimies_2.get(i).setRotationVelocity(0.0);
                        enimies_2.get(i).setAngle(3 * Math.PI);
                        shootNow = true;
                    }

                    if (enimies_2.get(i).getRotationVelocity() < 0
                            && Math.abs(enimies_2.get(i).getAngle()) < 0.05) {

                        enimies_2.get(i).setRotationVelocity(0.0);
                        enimies_2.get(i).setAngle(0.0);
                        shootNow = true;
                    }

                    if (shootNow) {

                        double[] angles = { Math.PI / 2 + Math.PI / 8, Math.PI / 2, Math.PI / 2 -
                                Math.PI / 8 };
                        int k = 0;
                        for (int j = 0; j < enimies_2.size(); j++) {
                            double a = angles[k] + Math.random() * Math.PI / 6 - Math.PI / 12;
                            double vx = Math.cos(a);
                            double vy = Math.sin(a);
                            Projectiles projectile = new Projectiles();
                            projectile.setX(enimies_2.get(i).getX());
                            projectile.setY(enimies_2.get(i).getY());
                            projectile.setVx(vx * 0.30);
                            projectile.setVy(vy * 0.30);
                            projectile.setState(States.ACTIVE);
                            enimy_projectiles.add(projectile);
                            k++;
                            if (k > 2) {
                                k = 0;
                            }
                        }
                    }
                }
            }
        }
    }

    public void updateEnimy3(ArrayList<Enimy> enimies_3, Player player, ArrayList<Projectiles> enimy_projectiles,
            long delta, long currentTime, int HEIGHT, int WIDTH) {

        for (int i = 0; i < enimies_3.size(); i++) {

            if (enimies_3.get(i).getState() == States.EXPLODING) {

                if (currentTime > enimies_3.get(i).getExplosionEnd()) {

                    enimies_3.get(i).setState(States.INACTIVE);
                    enimies_3.remove(i);
                    if (i > 0) {
                        i--;
                    } else {
                        break;
                    }
                }
            }

            if (enimies_3.get(i).getState() == States.ACTIVE) {

                /* verificando se inimigo saiu da tela */
                if (enimies_3.get(i).getX() < -10 || enimies_3.get(i).getX() > WIDTH + 10) {

                    enimies_3.get(i).setState(States.INACTIVE);
                    enimies_3.remove(i);
                    if (i > 0) {
                        i--;
                    } else {
                        break;
                    }

                } else {

                    boolean shootNow = false;
                    double previousY = enimies_3.get(i).getY();
                    enimies_3.get(i).setX(enimies_3.get(i).getX()
                            + enimies_3.get(i).getVelocity() * Math.cos(enimies_3.get(i).getAngle()) * delta);
                            enimies_3.get(i)
                            .setY(enimies_3.get(i).getY()
                                    + enimies_3.get(i).getVelocity() * Math.sin(enimies_3.get(i).getAngle()) * delta
                                            * (-1.0));
                    enimies_3.get(i)
                            .setAngle(enimies_3.get(i).getAngle() + enimies_3.get(i).getRotationVelocity() * delta);

                    double threshold = HEIGHT * 0.30;

                    if (previousY < threshold && enimies_3.get(i).getY() >= threshold) {

                        if (enimies_3.get(i).getX() < WIDTH / 2)
                            enimies_3.get(i).setRotationVelocity(0.003);
                        else
                            enimies_3.get(i).setRotationVelocity(-0.003);
                    }

                    if (enimies_3.get(i).getRotationVelocity() > 0
                            && Math.abs(enimies_3.get(i).getAngle() - 3 * Math.PI) < 0.05) {

                        enimies_3.get(i).setRotationVelocity(0.0);
                        enimies_3.get(i).setAngle(3 * Math.PI);
                        shootNow = true;
                    }

                    if (enimies_3.get(i).getRotationVelocity() < 0
                            && Math.abs(enimies_3.get(i).getAngle()) < 0.05) {

                        enimies_3.get(i).setRotationVelocity(0.0);
                        enimies_3.get(i).setAngle(0.0);
                        shootNow = true;
                    }

                    if (shootNow) {

                        double[] angles = { Math.PI / 2 + Math.PI / 8, Math.PI / 2, Math.PI / 2 -
                                Math.PI / 8 };
                        int k = 0;
                        for (int j = 0; j < enimies_3.size(); j++) {
                            double a = angles[k] + Math.random() * Math.PI / 6 - Math.PI / 12;
                            double vx = Math.cos(a);
                            double vy = Math.sin(a);
                            Projectiles projectile = new Projectiles();
                            projectile.setX(enimies_3.get(i).getX());
                            projectile.setY(enimies_3.get(i).getY());
                            projectile.setVx(vx * 0.30);
                            projectile.setVy(vy * 0.30);
                            projectile.setState(States.ACTIVE);
                            enimy_projectiles.add(projectile);
                            k++;
                            if (k > 2) {
                                k = 0;
                            }
                        }
                    }
                }
            }
        }
    }

    // public void updateEnimy3(ArrayList<Enimy> enimies_3, Player player, ArrayList<Projectiles> enimy_projectiles,
    //         long delta, long currentTime, int HEIGHT) {
    //     for (int i = 0; i < enimies_3.size(); i++) {

    //         if (enimies_3.get(i).getState() == States.EXPLODING) {

    //             if (currentTime > enimies_3.get(i).getExplosionEnd()) {

    //                 enimies_3.get(i).setState(States.INACTIVE);
    //                 enimies_3.remove(i);
    //                 if (i > 0) {
    //                     i--;
    //                 } else {
    //                     break;
    //                 }
    //             }
    //         }

    //         if (enimies_3.get(i).getState() == States.ACTIVE) {

    //             /* verificando se inimigo saiu da tela */
    //             if (enimies_3.get(i).getY() > HEIGHT + 10) {

    //                 enimies_3.get(i).setState(States.INACTIVE);
    //                 enimies_3.remove(i);
    //                 if (i > 0) {
    //                     i--;
    //                 } else {
    //                     break;
    //                 }
    //             } else {

    //                 enimies_3.get(i).setX(enimies_3.get(i).getX()
    //                         + enimies_3.get(i).getVelocity() * Math.cos(enimies_3.get(i).getAngle()) * delta);

    //                 enimies_3.get(i).setY(enimies_3.get(i).getY() + enimies_3.get(i).getVelocity()
    //                         * Math.sin(enimies_3.get(i).getAngle()) * delta * (-1.0));
    //                 enimies_3.get(i)
    //                         .setAngle(enimies_3.get(i).getAngle() + enimies_3.get(i).getRotationVelocity() * delta);

    //                 if (currentTime > enimies_3.get(i).getNextShot()
    //                         && enimies_3.get(i).getY() < player.getY()) {

    //                     Projectiles projectile = new Projectiles();
    //                     projectile.setX(enimies_3.get(i).getX());
    //                     projectile.setY(enimies_3.get(i).getY());
    //                     projectile.setVx(Math.cos(enimies_3.get(i).getAngle()) * 0.45);
    //                     projectile.setVy(Math.sin(enimies_3.get(i).getAngle()) * 0.45 * (-1.0));
    //                     projectile.setState(States.ACTIVE);
    //                     enimy_projectiles.add(projectile);
    //                     enimies_3.get(i).setNextShot((long) (currentTime + 2000 + Math.random() * 500));
    //                 }
    //             }
    //         }
    //     }
    // }

    public void updatePowerUp(ArrayList<PowerUp> powerUpList, Player player,
            long delta, long currentTime, int HEIGHT) {
        for (int i = 0; i < powerUpList.size(); i++) {

            if (powerUpList.get(i).getState() == States.EXPLODING) {

                if (currentTime > powerUpList.get(i).getExplosionEnd()) {

                    powerUpList.get(i).setState(States.INACTIVE);
                    powerUpList.remove(i);
                    if (i > 0) {
                        i--;
                    } else {
                        break;
                    }
                }
            }

            if (powerUpList.get(i).getState() == States.ACTIVE) {

                /* verificando se o power up saiu da tela */
                if (powerUpList.get(i).getY() > HEIGHT + 10) {

                    powerUpList.get(i).setState(States.INACTIVE);
                    powerUpList.remove(i);
                    if (i > 0) {
                        i--;
                    } else {
                        break;
                    }
                } else {

                    powerUpList.get(i).setX(powerUpList.get(i).getX()
                            + powerUpList.get(i).getVelocity() * Math.cos(powerUpList.get(i).getAngle()) * delta);

                    powerUpList.get(i).setY(powerUpList.get(i).getY() + powerUpList.get(i).getVelocity()
                            * Math.sin(powerUpList.get(i).getAngle()) * delta * (-1.0));
                    powerUpList.get(i)
                            .setAngle(powerUpList.get(i).getAngle() + powerUpList.get(i).getRotationVelocity() * delta);

                }
            }
        }
    }

    public void shouldSpawnEnimy1(ArrayList<Enimy> enimies_1, long currentTime, int WIDTH,
            EnimyConfig enimyConfig_1) {
        if (currentTime > enimyConfig_1.getNextEnimy()) {

            Enimy enimy = new Enimy();
            enimy.setX(Math.random() * (WIDTH - 20.0) + 10.0);
            enimy.setY(-10.0);
            enimy.setVelocity(0.20 + Math.random() * 0.15);
            enimy.setAngle((3 * Math.PI) / 2);
            enimy.setRotationVelocity(0.0);
            enimy.setState(States.ACTIVE);
            enimy.setNextShot(currentTime + 500);
            enimy.setRadius(enimyConfig_1.getRadius());
            enimies_1.add(enimy);
            enimyConfig_1.setNextEnimy(enimyConfig_1.getNextEnimy() + 500);

        }
    }

    public void shouldSpawnEnimy2(ArrayList<Enimy> enimies_2, long currentTime, int WIDTH,
            EnimyConfig enimiesConfig_2) {
        if (currentTime > enimiesConfig_2.getNextEnimy()) {
            Enimy enimy = new Enimy();
            enimy.setX(enimiesConfig_2.getSpawnX());
            enimy.setY(-10.0);
            enimy.setVelocity(0.42);
            enimy.setAngle((3 * Math.PI) / 2);
            enimy.setRotationVelocity(0.0);
            enimy.setState(States.ACTIVE);
            enimy.setNextShot(currentTime + 1000);
            enimy.setRadius(enimiesConfig_2.getRadius());
            enimy.setNextEnimy(enimiesConfig_2.getNextEnimy());
            enimies_2.add(enimy);
            enimiesConfig_2.setCount(enimiesConfig_2.getCount() + 1);
            if (enimiesConfig_2.getCount() < 10) {
                enimiesConfig_2.setNextEnimy(currentTime + 120);
            } else {
                enimiesConfig_2.setCount(0);
                enimiesConfig_2.setSpawnX(Math.random() > 0.5 ? WIDTH * 0.2
                        : WIDTH *
                                0.8);
                enimiesConfig_2.setNextEnimy((long) (currentTime + 3000 + Math.random() * 3000));
            }

        }
    }

    public void shouldSpawnEnimy3(ArrayList<Enimy> enimies_3, long currentTime, int WIDTH,
            EnimyConfig enimyConfig_3) {

        if (currentTime > enimyConfig_3.getNextEnimy()) {

            Enimy enimy = new Enimy();
            enimy.setX(Math.random() * (WIDTH - 20.0) + 10.0);
            enimy.setY(-10.0);
            enimy.setVelocity(0.1);
            enimy.setAngle((3 * Math.PI) / 2);
            enimy.setRotationVelocity(0.0);
            enimy.setState(States.ACTIVE);
            enimy.setNextShot(currentTime + 500);
            enimy.setRadius(enimyConfig_3.getRadius());
            enimies_3.add(enimy);
            enimyConfig_3.setNextEnimy(enimyConfig_3.getNextEnimy() + 9000);

        }
    }

    public void verifyPlayerState(Player player, long currentTime) {
        if (player.getState() == States.EXPLODING) {

            if (currentTime > player.getExplosionEnd()) {

                player.setState(States.ACTIVE);
            }
        }
    }

    public void verifyPlayerCoordinates(Player player) {
        if (player.getX() < 0.0)
            player.setX(0.0);
        if (player.getX() >= GameLib.WIDTH)
            player.setX(GameLib.WIDTH - 1);
        if (player.getY() < 25.0)
            player.setY(25.0);
        if (player.getY() >= GameLib.HEIGHT)
            player.setY(GameLib.HEIGHT - 1);
    }

    public void shouldSpawnPowerUp(ArrayList<PowerUp> powerUpList, long currentTime, int WIDTH,
            PowerUpConfig powerUpConfig) {
        if (currentTime > powerUpConfig.getNextPowerUp()) {
            PowerUp powerUp = new PowerUp();
            powerUp.setX(Math.random() * (WIDTH - 20.0) + 10.0);
            powerUp.setY(-10.0);
            powerUp.setVelocity(0.2 + Math.random() * 0.15);
            powerUp.setAngle((3 * Math.PI) / 2);
            powerUp.setRotationVelocity(0.0);
            powerUp.setState(States.ACTIVE);
            powerUp.setRadius(powerUpConfig.getRadius());
            powerUpList.add(powerUp);
            powerUpConfig.setNextPowerUp(powerUpConfig.getNextPowerUp() + 7000);

        }
    }
}
