package Interfaces;

import java.util.ArrayList;

import Entitys.Background;
import Entitys.BackgroundConfig;
import Entitys.Enimy;
import Entitys.EnimyConfig;
import Entitys.Player;
import Entitys.PowerUp;
import Entitys.PowerUpConfig;
import Entitys.Projectiles;

public interface IDraw {
        public void drawEnimyProjectile(ArrayList<Projectiles> enimy_projectiles, double e_projectile_radius);

        public void drawDistantBackground(ArrayList<Background> backgroundsSecond,
                        BackgroundConfig backgroundsSecondConfig, long delta);

        public void drawNearBackground(ArrayList<Background> backgroundsFirst,
                        BackgroundConfig backgroundsFirstConfig, long delta);

        public void drawPlayer(Player player, long currentTime);

        public void drawPlayerProjectiles(ArrayList<Projectiles> player_projectiles);

        public void drawEnimy1(ArrayList<Enimy> enimies_1, EnimyConfig enimiesConfig_1, long currentTime);

        public void drawEnimy2(ArrayList<Enimy> enimies_2, EnimyConfig enimiesConfig_2, long currentTime);

        public void drawPowerUp(ArrayList<PowerUp> powerUpList, PowerUpConfig powerUpConfig, long currentTime);

}
