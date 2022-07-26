package Interfaces;

import java.util.ArrayList;

import Entitys.Enimy;
import Entitys.Player;
import Entitys.Projectiles;

public interface IColisions {
        public void colisionPlayerProjectile(ArrayList<Projectiles> enimy_projectiles, Player player,
                        long currentTime,
                        double e_projectile_radius);

        public void colisionPlayerEnimy(ArrayList<Enimy> enimies_1, ArrayList<Enimy> enimies_2,
                        ArrayList<Enimy> enimies_3, Player player,
                        long currentTime,
                        double enemy1_radius, double enemy2_radius, double enemy3_radius);

        public void colisionEnimyProjectile(ArrayList<Enimy> enimies_1, ArrayList<Enimy> enimies_2,
                        ArrayList<Enimy> enimies_3, Player player,
                        ArrayList<Projectiles> player_projectiles,
                        long currentTime,
                        double enemy1_radius, double enemy2_radius, double enemy3_radius);
}
