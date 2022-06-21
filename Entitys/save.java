import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import Agents.Player;
import Entitys.Background;
import Entitys.Projectiles;
import Agents.Enimy;


/***********************************************************************/
/*                                                                     */
/* Para jogar:                                                         */
/*                                                                     */
/*    - cima, baixo, esquerda, direita: movimentação do player.        */
/*    - control: disparo de projéteis.                                 */
/*    - ESC: para sair do jogo.                                        */
/*                                                                     */
/***********************************************************************/

public class Main {

	/* Constantes relacionadas aos estados que os elementos */
	/* do jogo (player, projeteis ou inimigos) podem assumir. */

	public static final int INACTIVE = 0;
	public static final int ACTIVE = 1;
	public static final int EXPLODING = 2;

	/* Espera, sem fazer nada, até que o instante de tempo atual seja */
	/* maior ou igual ao instante especificado no parâmetro "time. */

	public static void busyWait(long time) {

		while (System.currentTimeMillis() < time)
			Thread.yield();
	}

	/* Encontra e devolve o primeiro índice do */
	/* array referente a uma posição "inativa". */

	public static <T> int findFreeIndexProjectile(ArrayList<Projectiles> list) {

		int i = 0;
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {

			if (((Projectiles) iterator.next()).getState() == INACTIVE) {
				break;
			}
			i++;
		}
		return i;

	}

	public static <T> int findFreeIndexEnimy(ArrayList<T> list) {

		int i = 0;
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {

			if (((Enimy) iterator.next()).getState() == INACTIVE) {
				break;
			}
			i++;
		}
		return i;

	}

	/* Encontra e devolve o conjunto de índices (a quantidade */
	/* de índices é defnida através do parâmetro "amount") do */
	/* array referente a posições "inativas". */

	public static int[] findFreeIndexProjectile(ArrayList<Projectiles> stateArray, int amount) {

		int i, k;
		int[] freeArray = new int[amount];

		for (i = 0; i < freeArray.length; i++)
			freeArray[i] = stateArray.size();

		for (i = 0, k = 0; i < stateArray.size() && k < amount; i++) {

			if (stateArray.get(i).getState() == INACTIVE) {

				freeArray[k] = i;
				k++;
			}
		}

		return freeArray;
	}

	public static int[] findFreeIndexEnimy(ArrayList<Enimy> stateArray, int amount) {

		int i, k;
		int[] freeArray = new int[amount];

		for (i = 0; i < freeArray.length; i++)
			freeArray[i] = stateArray.size();

		for (i = 0, k = 0; i < stateArray.size() && k < amount; i++) {

			if (stateArray.get(i).getState() == INACTIVE) {

				freeArray[k] = i;
				k++;
			}
		}

		return freeArray;
	}
	/* Método principal */

	public static void main(String[] args) {

		boolean running = true; /* Indica que o jogo está em execução */

		/* variáveis usadas no controle de tempo efetuado no main loop */
		long delta;
		long currentTime = System.currentTimeMillis();

		/* variáveis do player */
		Player player = new Player(ACTIVE, GameLib.WIDTH / 2, GameLib.HEIGHT * 0.90, 0.25, 0.25, 12.0, 0, 0,
				currentTime);

		/* variáveis dos projéteis disparados pelo player */
		ArrayList<Projectiles> player_projectiles = new ArrayList<>();
		int player_projectiles_quantity = 10;

		/* variáveis dos inimigos tipo 1 */
		ArrayList<Enimy> enimies_1 = new ArrayList<>();
		int enimies1Quantity = 10;
		double enimies1Radius = 9.0;
		long enimies1NextEnimy = currentTime + 2000;
		double enemy1_radius = 9.0; // raio (tamanho do inimigo 1)
		long nextEnemy1 = currentTime + 2000; // instante em que um novo inimigo 1 deve aparecer

		/* variáveis dos inimigos tipo 2 */
		ArrayList<Enimy> enimies_2 = new ArrayList<>();
		int enimies2Quantity = 10;

		double enimies2Radius = 12.0;
		long enimies2NextEnimy = currentTime + 7000;
		double enemy2_spawnX = GameLib.WIDTH * 0.20; // coordenada x do próximo inimigo tipo 2 a aparecer
		int enemy2_count = 0; // contagem de inimigos tipo 2 (usada na "formação de voo")
		double enemy2_radius = 12.0; // raio (tamanho aproximado do inimigo 2)
		long nextEnemy2 = currentTime + 7000; // instante em que um novo inimigo 2 deve aparecer

		/*
		 * variáveis dos projéteis lançados pelos inimigos (tanto tipo 1, quanto tipo 2)
		 */
		ArrayList<Projectiles> enimy_projectiles = new ArrayList<>();
		int enimy_projectilesQuantity = 200;
		double e_projectile_radius = 2.0; // raio (tamanho dos projéteis inimigos)

		/* estrelas que formam o fundo de primeiro plano */
		ArrayList<Background> backgroundsFirst = new ArrayList<>();
		int backgroundsFirstQuantity = 20;
		double background1_count = 0.0;
		double background1_speed = 0.070;

		/* estrelas que formam o fundo de segundo plano */
		ArrayList<Background> backgroundsSecond = new ArrayList<>();
		int backgroundsSecondQuantity = 50;
		double background2_count = 0.0;
		double background2_speed = 0.045;

		/* inicializações */

		for (int i = 0; i < enimies1Quantity; i++) {
			Enimy e = new Enimy();
			e.setRadius(enimies1Radius);
			e.setNextEnimy(enimies1NextEnimy);
			e.setState(INACTIVE);

			enimies_1.add(e);
		}

		for (int i = 0; i < enimies2Quantity; i++) {
			Enimy e = new Enimy();
			e.setRadius(enimies2Radius);
			e.setNextEnimy(enimies2NextEnimy);
			e.setState(INACTIVE);
			enimies_2.add(e);
		}

		for (int i = 0; i < player_projectiles_quantity; i++) {
			Projectiles projectiles = new Projectiles();
			projectiles.setState(INACTIVE);
			player_projectiles.add(projectiles);
		}

		for (int i = 0; i < enimy_projectilesQuantity; i++) {
			Projectiles projectiles = new Projectiles();
			projectiles.setState(INACTIVE);
			enimy_projectiles.add(projectiles);
		}

		for (int i = 0; i < backgroundsFirstQuantity; i++) {
			Background background = new Background();
			background.setX(Math.random() * GameLib.WIDTH);
			background.setY(Math.random() * GameLib.HEIGHT);
			backgroundsFirst.add(background);
		}

		for (int i = 0; i < backgroundsSecondQuantity; i++) {
			Background background = new Background();
			background.setX(Math.random() * GameLib.WIDTH);
			background.setY(Math.random() * GameLib.HEIGHT);
			backgroundsSecond.add(background);
		}

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

			/***************************/
			/* Verificação de colisões */
			/***************************/

			if (player.getState() == ACTIVE) {

				/* colisões player - projeteis (inimigo) */

				for (int i = 0; i < player_projectiles_quantity; i++) {

					double dx = enimy_projectiles.get(i).getX() - player.getX();
					double dy = enimy_projectiles.get(i).getY() - player.getY();
					double dist = Math.sqrt(dx * dx + dy * dy);

					if (dist < (player.getRadius() + e_projectile_radius) * 0.8) {

						player.setState(EXPLODING);
						player.setExplosionStart(currentTime);
						player.setExplosionEnd(currentTime + 2000);
					}
				}

				/* colisões player - inimigos */

				for (int i = 0; i < enimies1Quantity; i++) {

					double dx = enimies_1.get(i).getX() - player.getX();
					double dy = enimies_1.get(i).getY() - player.getY();
					double dist = Math.sqrt(dx * dx + dy * dy);

					if (dist < (player.getRadius() + enemy1_radius) * 0.8) {
						player.setState(EXPLODING);
						player.setExplosionStart(currentTime);
						player.setExplosionEnd(currentTime + 2000);
					}
				}

				for (int i = 0; i < enimies2Quantity; i++) {

					double dx = enimies_2.get(i).getX() - player.getX();
					double dy = enimies_2.get(i).getY() - player.getY();
					double dist = Math.sqrt(dx * dx + dy * dy);

					if (dist < (player.getRadius() + enemy2_radius) * 0.8) {

						player.setState(EXPLODING);
						player.setExplosionStart(currentTime);
						player.setExplosionEnd(currentTime + 2000);
					}
				}
			}

			/* colisões projeteis (player) - inimigos */

			for (int k = 0; k < player_projectiles_quantity; k++) {

				for (int i = 0; i < enimies1Quantity; i++) {

					if (enimies_1.get(i).getState() == ACTIVE) {

						double dx = enimies_1.get(i).getX() - player_projectiles.get(k).getX();
						double dy = enimies_1.get(i).getVelocity() - player_projectiles.get(k).getY();
						double dist = Math.sqrt(dx * dx + dy * dy);

						if (dist < enemy1_radius) {

							enimies_1.get(i).setState(EXPLODING);
							enimies_1.get(i).setExplosionStart(currentTime);
							enimies_1.get(i).setExplosionEnd(currentTime + 500);
						}
					}
				}

				for (int i = 0; i < enimies2Quantity; i++) {

					if (enimies_2.get(i).getState() == ACTIVE) {

						double dx = enimies_2.get(i).getX() - player_projectiles.get(k).getX();
						double dy = enimies_2.get(i).getY() - player_projectiles.get(k).getY();
						double dist = Math.sqrt(dx * dx + dy * dy);

						if (dist < enemy2_radius) {

							enimies_2.get(i).setState(EXPLODING);
							enimies_2.get(i).setExplosionStart(currentTime);
							enimies_2.get(i).setExplosionEnd(currentTime + 500);
						}
					}
				}
			}

			/***************************/
			/* Atualizações de estados */
			/***************************/

			/* projeteis (player) */

			for (int i = 0; i < player_projectiles_quantity; i++) {

				if (player_projectiles.get(i).getState() == ACTIVE) {

					/* verificando se projétil saiu da tela */
					if (player_projectiles.get(i).getY() < 0) {

						player_projectiles.get(i).setState(INACTIVE);
						
					} else {

						player_projectiles.get(i).setX(player_projectiles.get(i).getX()
								+ player_projectiles.get(i).getVx() * delta);
						player_projectiles.get(i).setY(player_projectiles.get(i).getY()
								+ player_projectiles.get(i).getVy() * delta);
					}
				}
			}

			/* projeteis (inimigos) */

			for (int i = 0; i < enimy_projectilesQuantity; i++) {

				if (enimy_projectiles.get(i).getState() == ACTIVE) {

					/* verificando se projétil saiu da tela */
					if (enimy_projectiles.get(i).getY() > GameLib.HEIGHT) {

						enimy_projectiles.get(i).setState(INACTIVE);
					} else {

						enimy_projectiles.get(i).setX(enimy_projectiles.get(i).getX()
								+ enimy_projectiles.get(i).getVx() * delta);
						enimy_projectiles.get(i).setY(enimy_projectiles.get(i).getY()
								+ enimy_projectiles.get(i).getVy() * delta);

					}
				}
			}

			/* inimigos tipo 1 */

			for (int i = 0; i < enimies1Quantity; i++) {

				if (enimies_1.get(i).getState() == EXPLODING) {

					if (currentTime > enimies_1.get(i).getExplosionEnd()) {

						enimies_1.get(i).setState(INACTIVE);
					}
				}

				if (enimies_1.get(i).getState() == ACTIVE) {

					/* verificando se inimigo saiu da tela */
					if (enimies_1.get(i).getY() > GameLib.HEIGHT + 10) {

						enimies_1.get(i).setState(INACTIVE);
					} else {

						enimies_1.get(i).setX(enimies_1.get(i).getX()
								+ enimies_1.get(i).getVelocity() * Math.cos(enimies_1.get(i).getAngle()) * delta);

						enimies_1.get(i).setY(enimies_1.get(i).getY() + enimies_1.get(i).getVelocity()
								* Math.sin(enimies_1.get(i).getAngle()) * delta * (-1.0));
						enimies_1.get(i)
								.setAngle(enimies_1.get(i).getAngle() + enimies_1.get(i).getRotationVelocity() * delta);

						if (currentTime > enimies_1.get(i).getNextShot()
								&& enimies_1.get(i).getY() < player.getY()) {

							int free = findFreeIndexProjectile(enimy_projectiles);

							if (free < enimy_projectilesQuantity) {

								enimy_projectiles.get(free).setX(enimies_1.get(i).getX());
								enimy_projectiles.get(free).setY(enimies_1.get(i).getVelocity());
								enimy_projectiles.get(free)
										.setVx(Math.cos(enimies_1.get(i).getAngle()) * 0.45);
								enimy_projectiles.get(free)
										.setVy(Math.sin(enimies_1.get(i).getAngle()) * 0.45 * (-1.0));
								enimy_projectiles.get(free).setState(ACTIVE);

								enimies_1.get(i).setNextShot((long) (currentTime + 200 + Math.random() * 500));
							}
						}
					}
				}
			}

			/* inimigos tipo 2 */

			for (int i = 0; i < enimies2Quantity; i++) {

				if (enimies_2.get(i).getState() == EXPLODING) {

					if (currentTime > enimies_2.get(i).getExplosionEnd()) {

						enimies_2.get(i).setState(INACTIVE);
					}
				}

				if (enimies_2.get(i).getState() == ACTIVE) {

					/* verificando se inimigo saiu da tela */
					if (enimies_2.get(i).getX() < -10 || enimies_2.get(i).getX() > GameLib.WIDTH + 10) {

						enimies_2.get(i).setState(INACTIVE);
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

						double threshold = GameLib.HEIGHT * 0.30;

						if (previousY < threshold && enimies_2.get(i).getY() >= threshold) {

							if (enimies_2.get(i).getX() < GameLib.WIDTH / 2)
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

							double[] angles = { Math.PI / 2 + Math.PI / 8, Math.PI / 2, Math.PI / 2 - Math.PI / 8 };
							int[] freeArray = findFreeIndexProjectile(enimy_projectiles, angles.length);

							for (int k = 0; k < freeArray.length; k++) {

								int free = freeArray[k];

								if (free < enimy_projectilesQuantity) {

									double a = angles[k] + Math.random() * Math.PI / 6 - Math.PI / 12;
									double vx = Math.cos(a);
									double vy = Math.sin(a);

									enimy_projectiles.get(free).setX(enimies_2.get(i).getX());
									enimy_projectiles.get(free).setY(enimies_2.get(i).getY());
									enimy_projectiles.get(free).setVx(vx * 0.30);
									enimy_projectiles.get(free).setVy(vy * 0.30);
									enimy_projectiles.get(free).setState(ACTIVE);

								}
							}
						}
					}
				}
			}

			/* verificando se novos inimigos (tipo 1) devem ser "lançados" */

			if (currentTime > nextEnemy1) {

				int free = findFreeIndexEnimy(enimies_1);

				if (free < enimies1Quantity) {

					enimies_1.get(free).setX(Math.random() * (GameLib.WIDTH - 20.0) + 10.0);
					enimies_1.get(free).setY(-10.0);
					enimies_1.get(free).setVelocity(0.20 + Math.random() * 0.15);
					enimies_1.get(free).setAngle((3 * Math.PI) / 2);
					enimies_1.get(free).setRotationVelocity(0.0);
					enimies_1.get(free).setState(ACTIVE);
					enimies_1.get(free).setNextShot(currentTime + 500);
					nextEnemy1 = currentTime + 500;
				}
			}

			/* verificando se novos inimigos (tipo 2) devem ser "lançados" */

			if (currentTime > nextEnemy2) {

				int free = findFreeIndexEnimy(enimies_2);

				if (free < enimies2Quantity) {

					enimies_2.get(free).setX(enemy2_spawnX);
					enimies_2.get(free).setY(-10.0);
					enimies_2.get(free).setVelocity(0.42);
					enimies_2.get(free).setAngle((3 * Math.PI) / 2);
					enimies_2.get(free).setRotationVelocity(0.0);
					enimies_2.get(free).setState(ACTIVE);

					enemy2_count++;

					if (enemy2_count < 10) {

						nextEnemy2 = currentTime + 120;
					} else {

						enemy2_count = 0;
						enemy2_spawnX = Math.random() > 0.5 ? GameLib.WIDTH * 0.2 : GameLib.WIDTH * 0.8;
						nextEnemy2 = (long) (currentTime + 3000 + Math.random() * 3000);
					}
				}
			}

			/* Verificando se a explosão do player já acabou. */
			/* Ao final da explosão, o player volta a ser controlável */
			if (player.getState() == EXPLODING) {

				if (currentTime > player.getExplosionEnd()) {

					player.setState(ACTIVE);
				}
			}

			/********************************************/
			/* Verificando entrada do usuário (teclado) */
			/********************************************/

			if (player.getState() == ACTIVE) {

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

						int free = findFreeIndexProjectile(player_projectiles);

						if (free < player_projectiles_quantity) {

							player_projectiles.get(free).setX(player.getX());
							player_projectiles.get(free).setY(player.getY() - 2 * player.getRadius());
							player_projectiles.get(free).setVx(0.0);
							player_projectiles.get(free).setVy(-1.0);
							player_projectiles.get(free).setState(ACTIVE);
							player.setNextShot(currentTime + 100);
						}
					}
				}
			}

			if (GameLib.iskeyPressed(GameLib.KEY_ESCAPE))
				running = false;

			/* Verificando se coordenadas do player ainda estão dentro */
			/* da tela de jogo após processar entrada do usuário. */

			if (player.getX() < 0.0)
				player.setX(0.0);
			if (player.getX() >= GameLib.WIDTH)
				player.setX(GameLib.WIDTH - 1);
			if (player.getY() < 25.0)
				player.setY(25.0);
			if (player.getY() >= GameLib.HEIGHT)
				player.setY(GameLib.HEIGHT - 1);

			/*******************/
			/* Desenho da cena */
			/*******************/

			/* desenhando plano fundo distante */

			GameLib.setColor(Color.DARK_GRAY);

			background2_count += background2_speed * delta;

			for (int i = 0; i < backgroundsSecondQuantity; i++) {

				GameLib.fillRect(backgroundsSecond.get(i).getX(),
						(backgroundsSecond.get(i).getY() + background2_count) % GameLib.HEIGHT, 2, 2);
			}

			/* desenhando plano de fundo próximo */

			GameLib.setColor(Color.GRAY);
			background1_count += background1_speed * delta;

			for (int i = 0; i < backgroundsFirstQuantity; i++) {

				GameLib.fillRect(backgroundsFirst.get(i).getX(),
						(backgroundsFirst.get(i).getY() + background1_count) % GameLib.HEIGHT, 3, 3);
			}

			/* desenhando player */

			if (player.getState() == EXPLODING) {

				double alpha = (currentTime - player.getExplosionStart())
						/ (player.getExplosionEnd() - player.getExplosionStart());
				GameLib.drawExplosion(player.getX(), player.getY(), alpha);
			} else {

				GameLib.setColor(Color.BLUE);
				GameLib.drawPlayer(player.getX(), player.getY(), player.getRadius());
			}

			/* deenhando projeteis (player) */

			for (int i = 0; i < player_projectiles_quantity; i++) {

				if (player_projectiles.get(i).getState() == ACTIVE) {

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

			/* desenhando projeteis (inimigos) */

			for (int i = 0; i < enimy_projectilesQuantity; i++) {

				if (enimy_projectiles.get(i).getState() == ACTIVE) {

					GameLib.setColor(Color.RED);
					GameLib.drawCircle(enimy_projectiles.get(i).getX(),
							enimy_projectiles.get(i).getY(), e_projectile_radius);
				}
			}

			/* desenhando inimigos (tipo 1) */

			for (int i = 0; i < enimies1Quantity; i++) {

				if (enimies_1.get(i).getState() == EXPLODING) {

					double alpha = (currentTime - enimies_1.get(i).getExplosionStart())
							/ (enimies_1.get(i).getExplosionEnd() - enimies_1.get(i).getExplosionStart());
					GameLib.drawExplosion(enimies_1.get(i).getX(), enimies_1.get(i).getY(), alpha);
				}

				if (enimies_1.get(i).getState() == ACTIVE) {

					GameLib.setColor(Color.CYAN);
					GameLib.drawDiamond(enimies_1.get(i).getX(), enimies_1.get(i).getY(), enemy1_radius);
				}
			}

			/* desenhando inimigos (tipo 2) */

			for (int i = 0; i < enimies2Quantity; i++) {

				if (enimies_2.get(i).getState() == EXPLODING) {

					double alpha = (currentTime - enimies_2.get(i).getExplosionStart())
							/ (enimies_2.get(i).getExplosionEnd() - enimies_2.get(i).getExplosionStart());
					GameLib.drawExplosion(enimies_2.get(i).getX(), enimies_2.get(i).getY(), alpha);
				}

				if (enimies_2.get(i).getState() == ACTIVE) {

					GameLib.setColor(Color.MAGENTA);
					GameLib.drawDiamond(enimies_2.get(i).getX(), enimies_2.get(i).getY(), enemy2_radius);
				}
			}

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
}
