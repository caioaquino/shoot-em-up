package Interfaces;

import java.util.ArrayList;

import Entitys.Enimy;
import Entitys.EnimyConfig;
import Entitys.Player;
import Entitys.PowerUp;
import Entitys.Projectiles;

public interface IGameStates {
        public void verifyPlayerCoordinates(Player player);

        public void verifyPlayerState(Player player, long currentTime);

        public void shouldSpawnEnimy2(ArrayList<Enimy> enimies_2, long currentTime, int WIDTH,
                        EnimyConfig enimiesConfig_2);

        public void shouldSpawnEnimy1(ArrayList<Enimy> enimies_1, long currentTime, int WIDTH,
                        EnimyConfig enimyConfig_1);

        public void updateEnimy2(ArrayList<Enimy> enimies_2, Player player, ArrayList<Projectiles> enimy_projectiles,
                        long delta, long currentTime, int HEIGHT, int WIDTH);

        public void updateEnimy1(ArrayList<Enimy> enimies_1, Player player, ArrayList<Projectiles> enimy_projectiles,
                        long delta, long currentTime, int HEIGHT);

        public void updateEnimy3(ArrayList<Enimy> enimies_3, Player player, ArrayList<Projectiles> enimy_projectiles,
                        long delta, long currentTime, int HEIGHT, int WIDTH);

        public void updateEnimyProjectiles(ArrayList<Projectiles> enimy_projectiles, long delta, int HEIGHT);

        public void updatePlayerProjectileState(ArrayList<Projectiles> player_projectiles, long delta);

        public void updatePowerUp(ArrayList<PowerUp> powerUpList, Player player,
                        long delta, long currentTime, int HEIGHT);
}
