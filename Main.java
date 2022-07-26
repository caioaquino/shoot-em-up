import java.util.ArrayList;

import Entitys.Background;
import Entitys.BackgroundConfig;
import Entitys.Enimy;
import Entitys.EnimyConfig;
import Entitys.Player;
import Entitys.PowerUp;
import Entitys.PowerUpConfig;
import Entitys.Projectiles;
import GameLib.GameLib;
import Services.Colisions;
import Services.Draw;
import Services.GameStates;
import Services.Keyboard;
import Types.States;

/***********************************************************************/
/*                                                                     */
/* 	  Para jogar:                                                      */
/*                                                                     */
/*    - cima, baixo, esquerda, direita: movimentação do player.        */
/*    - control: disparo de projéteis.                                 */
/*    - ESC: para sair do jogo.                                        */
/*                                                                     */
/*                                                                     */
/***********************************************************************/

public class Main {
	/* Método principal */

	public static void main(String[] args) {

		/* ########################## INSTÂNCIAS ########################### */
		Colisions colisions = new Colisions();
		Draw draw = new Draw();
		GameStates gameStates = new GameStates();
		Keyboard keyboard = new Keyboard();
		/* ################################################################## */

		/* ##################### VARIAVEIS DE CONTROLE ##################### */
		boolean running = true;
		long delta;
		long currentTime = System.currentTimeMillis();
		/* ################################################################## */

		/* ##################### VARIAVEIS DO PLAYER ##################### */
		Player player = new Player(States.ACTIVE, GameLib.WIDTH / 2, GameLib.HEIGHT * 0.90, 0.25, 0.25, 12.0, 0, 0,
				currentTime);
		ArrayList<Projectiles> player_projectiles = new ArrayList<>();
		/* ################################################################## */

		/* ##################### VARIAVEIS DOS INIMIGOS ##################### */
		ArrayList<Enimy> enimies_1 = new ArrayList<>();
		EnimyConfig enimiesConfig_1 = new EnimyConfig(9.0, currentTime + 2000);

		ArrayList<Enimy> enimies_2 = new ArrayList<>();
		EnimyConfig enimiesConfig_2 = new EnimyConfig(12.0, currentTime + 7000, GameLib.WIDTH + 0.20, 0);

		ArrayList<Enimy> enimies_3 = new ArrayList<>();
		EnimyConfig enimiesConfig_3 = new EnimyConfig(17, currentTime + 4000, GameLib.WIDTH + 0.20, 0);

		ArrayList<Projectiles> enimy_projectiles = new ArrayList<>();
		double e_projectile_radius = 2.0; // raio (tamanho dos projéteis inimigos)
		/* ################################################################## */

		/* ##################### VARIAVEIS DO POWERUP ##################### */
		ArrayList<PowerUp> powerUpList = new ArrayList<>();
		PowerUpConfig powerUpConfig = new PowerUpConfig(15, currentTime + 9000);

		/* ##################### VARIAVEIS DOS BACKGROUNDS ##################### */
		ArrayList<Background> backgroundsFirst = new ArrayList<>();
		BackgroundConfig backgroundsFirstConfig = new BackgroundConfig(0.0, 0.070, 20, backgroundsFirst);

		ArrayList<Background> backgroundsSecond = new ArrayList<>();
		BackgroundConfig backgroundsSecondConfig = new BackgroundConfig(0, 0.045, 50, backgroundsSecond);
		/* ################################################################## */

		/* iniciado interface gráfica */

		GameLib.initGraphics();
		// GameLib.initGraphics_SAFE_MODE(); // chame esta versão do método caso nada
		// seja desenhado na janela do jogo.

		/*************************************************************************************************/
		/*                                                                                               */
		/* Main loop do jogo */
		/* ----------------- */
		/*                                                                                               */
		/* O main loop do jogo executa as seguintes operações: */
		/*                                                                                               */
		/*
		 * 1) Verifica se há colisões e atualiza estados dos elementos conforme a
		 * necessidade.
		 */
		/*                                                                                               */
		/*
		 * 2) Atualiza estados dos elementos baseados no tempo que correu entre a última
		 * atualização
		 */
		/*
		 * e o timestamp atual: posição e orientação, execução de disparos de projéteis,
		 * etc.
		 */
		/*                                                                                               */
		/*
		 * 3) Processa entrada do usuário (teclado) e atualiza estados do player
		 * conforme a necessidade.
		 */
		/*                                                                                               */
		/* 4) Desenha a cena, a partir dos estados dos elementos. */
		/*                                                                                               */
		/*
		 * 5) Espera um período de tempo (de modo que delta seja aproximadamente sempre
		 * constante).
		 */
		/*                                                                                               */
		/*************************************************************************************************/

		while (running) {

			/* Usada para atualizar o estado dos elementos do jogo */
			/* (player, projéteis e inimigos) "delta" indica quantos */
			/* ms se passaram desde a última atualização. */

			delta = System.currentTimeMillis() - currentTime;

			/* Já a variável "currentTime" nos dá o timestamp atual. */

			currentTime = System.currentTimeMillis();

			/* ################### Verificação de colisões ############################ */

			if (player.getState() == States.ACTIVE) {
				colisions.colisionPlayerProjectile(enimy_projectiles, player, currentTime, e_projectile_radius);
				colisions.colisionPlayerEnimy(enimies_1, enimies_2, enimies_3, player, currentTime,
						enimiesConfig_1.getRadius(),
						enimiesConfig_2.getRadius(), enimiesConfig_3.getRadius());
				colisions.colisionPlayerPowerUp(powerUpList, player, currentTime, powerUpConfig.getRadius());
			}

			colisions.colisionEnimyProjectile(enimies_1, enimies_2, enimies_3, player, player_projectiles, currentTime,
					enimiesConfig_1.getRadius(), enimiesConfig_2.getRadius(), enimiesConfig_3.getRadius());
			/* ######################################################################### */

			/* ################### ATUALIZAÇÃO DE ESTADOS ############################ */

			gameStates.updatePlayerProjectileState(player_projectiles, delta);

			gameStates.updateEnimyProjectiles(enimy_projectiles, delta, GameLib.HEIGHT);

			gameStates.updateEnimy1(enimies_1, player, enimy_projectiles,
					delta, currentTime, GameLib.HEIGHT);

			gameStates.updateEnimy2(enimies_2, player, enimy_projectiles,
					delta, currentTime, GameLib.HEIGHT, GameLib.WIDTH);

			gameStates.updateEnimy3(enimies_3, player, enimy_projectiles,
					delta, currentTime, GameLib.HEIGHT, GameLib.WIDTH);

			gameStates.updatePowerUp(powerUpList, player, delta, currentTime, GameLib.HEIGHT);

			gameStates.shouldSpawnEnimy1(enimies_1, currentTime,
					GameLib.WIDTH, enimiesConfig_1);

			gameStates.shouldSpawnEnimy2(enimies_2, currentTime,
					GameLib.WIDTH, enimiesConfig_2);

			gameStates.shouldSpawnEnimy3(enimies_3, currentTime,
					GameLib.WIDTH, enimiesConfig_3);

			gameStates.shouldSpawnPowerUp(powerUpList, currentTime,
					GameLib.WIDTH, powerUpConfig);

			gameStates.verifyPlayerState(player, currentTime);

			/* ######################################################################### */

			/* ################### VERIFICAÇÃO DE ENTRADA (TECLADO) #################### */

			keyboard.handlePlayerInput(player, delta, currentTime,
					player_projectiles);

			if (GameLib.iskeyPressed(GameLib.KEY_ESCAPE))
				running = false;

			gameStates.verifyPlayerCoordinates(player);

			/* ######################################################################### */

			/* ################### DESENHO DA CENA #################### */

			draw.drawDistantBackground(backgroundsSecond, backgroundsSecondConfig, delta);

			draw.drawNearBackground(backgroundsFirst, backgroundsFirstConfig, delta);

			draw.drawPlayer(player, currentTime);

			draw.drawPlayerProjectiles(player_projectiles);

			draw.drawEnimyProjectile(enimy_projectiles, e_projectile_radius);

			draw.drawEnimy1(enimies_1, enimiesConfig_1, currentTime);

			draw.drawEnimy2(enimies_2, enimiesConfig_2, currentTime);

			draw.drawEnimy3(enimies_3, enimiesConfig_3, currentTime);

			draw.drawPowerUp(powerUpList, powerUpConfig, currentTime);

			/* ######################################################################### */

			/*
			 * chamada a display() da classe GameLib atualiza o desenho exibido pela
			 * interface do jogo.
			 */

			GameLib.display();

			/*
			 * faz uma pausa de modo que cada execução do laço do main loop demore
			 * aproximadamente 3 ms.
			 */

			busyWait(currentTime + 3);
		}

		System.exit(0);
	}

	/* Espera, sem fazer nada, até que o instante de tempo atual seja */
	/* maior ou igual ao instante especificado no parâmetro "time. */

	public static void busyWait(long time) {

		while (System.currentTimeMillis() < time)
			Thread.yield();
	}

}
