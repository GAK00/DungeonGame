package game.controller;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import game.model.FileHandler;
import game.model.Goblin;
import game.model.HealthBar;
import game.model.Monster;
import game.model.Player;
import game.model.Slime;
import game.model.Troll;
import game.view.GameFrame;
import world.model.Map;
import world.model.MiniMap;
import world.model.MusicBox;
import world.view.WorldPanel;

public class GameController
{
	private Map map;
	private GameFrame frame;
	private int width;
	private int height;
	private int posx;
	private int posy;
	private WorldPanel panel;
	private Troll troll = new Troll();
	private Player playerProfile;
	public String monsterPicture;
	private Monster currentMonster;

	public GameController()
	{
		monsterPicture = "images/troll.jpg";
		currentMonster = troll;
		playerProfile = new Player();
		// appFrame = new DungeonFrame(this);
		FileHandler fh = new FileHandler();
		MusicBox music = new MusicBox("dank.wav", true);
		music.startThread();
		music.liveChangeDc(-20.0f);
		if (fh.readMap() != null)
		{
			map = fh.readMap();
		} else
		{
			map = new Map(this);
			map.setCurrentPos(new Dimension(5, 3));
			map.getCurrentRoom().getTile(new Dimension(4, 4)).setInhabited(true);
		}
		if (fh.readData("windowData") != null)
		{
			String data = fh.readData("windowData");
			String[] split = data.split("\n");
			try
			{
				width = Integer.parseInt(split[0].substring(split[0].indexOf(":") + 2));
			} catch (NumberFormatException e)
			{
				width = 350;
			} catch (ArrayIndexOutOfBoundsException e)
			{
				width = 350;
			}
			try
			{
				height = Integer.parseInt(split[1].substring(split[1].indexOf(":") + 2));
			} catch (NumberFormatException e)
			{
				height = 350;
			} catch (ArrayIndexOutOfBoundsException e)
			{
				height = 350;
			}
			String[] posSplit = split[2].substring(split[2].indexOf(":") + 2).split(",");
			// System.out.println(posSplit[1]);
			try
			{
				posx = Integer.parseInt(posSplit[0]);
			} catch (NumberFormatException e)
			{
				posx = 0;
			} catch (ArrayIndexOutOfBoundsException e)
			{
				posx = 0;
			}
			try
			{
				posy = Integer.parseInt(posSplit[1]);
			} catch (NumberFormatException e)
			{
				posy = 0;
			} catch (ArrayIndexOutOfBoundsException e)
			{

				posy = 0;
			}
		} else
		{
			width = 350;
			height = 350;
			posx = 0;
			posy = 0;

		}
		frame = new GameFrame(this, width, height, posx, posy);
		panel = (WorldPanel) frame.getPanel();
		map.setControl(this);
		map.getCurrentRoom().entered();
	}

	public void start()
	{
		frame.getWorld().beginRender();
	}

	public Map getMap()
	{
		return map;
	}

	public void updateMap(int[] direction, Dimension startPoint)
	{
		int spawnx = 5;
		int spawny = 5;
		if ((int) startPoint.getHeight() == 7)
		{
			spawny = 1;
			spawnx = (int) startPoint.getWidth();
		} else if ((int) startPoint.getHeight() == 1)
		{
			spawny = 7;
			spawnx = (int) startPoint.getWidth();
		} else if ((int) startPoint.getWidth() == 1)
		{
			spawnx = 7;
			spawny = (int) startPoint.getHeight();
		} else if ((int) startPoint.getWidth() == 7)
		{
			spawnx = 1;
			spawny = (int) startPoint.getHeight();
		}
		Dimension spawnPoint = new Dimension(spawnx, spawny);
		Dimension current = map.getCurrentPos();
		Dimension next = new Dimension((int) (current.getWidth() + direction[0]), (int) (current.getHeight() + direction[1]));
		map.setCurrentPos(next);
		map.getCurrentRoom().clean();
		map.getCurrentRoom().getTile(spawnPoint).setInhabited(true);
		map.getCurrentRoom().entered();
	}

	public GameFrame getFrame()
	{
		return frame;
	}

	public HealthBar getHealthBar()
	{
		WorldPanel panel = (WorldPanel) frame.getPanel();
		return new HealthBar(panel.getRenderSize()[0], panel.getRenderSize()[1], playerProfile.getMaxHealth(), playerProfile.getCurrentHealth());
	}

	public void increaseHealth()
	{
		if (playerProfile.getCurrentHealth() + 3 < playerProfile.getMaxHealth())
		{
			playerProfile.setCurrentHealth(playerProfile.getCurrentHealth() + 3);
		} else
		{
			playerProfile.setCurrentHealth(playerProfile.getMaxHealth());
		}
	}

	public void saveMap()
	{
		FileHandler fh = new FileHandler();
		fh.writeMap(map);
	}

	public void saveWindowData()
	{
		FileHandler fh = new FileHandler();
		int width = frame.getWidth();
		int height = frame.getHeight();
		int posx = frame.getLocation().x;
		int posy = frame.getLocation().y;
		if (posx >= frame.getScreenSize()[0])
		{
			posx = 0;
		}
		if (posy >= frame.getScreenSize()[1])
		{
			posy = 0;
		}
		fh.writeData("windowData", "windowWidth: " + Integer.toString(width) + "\nwindowHeight: " + Integer.toString(height) + "\nwindowPos: " + Integer.toString(posx) + "," + Integer.toString(posy));

	}

	public BufferedImage getMiniMap(int control)
	{
		if (control != 1)
		{
			MiniMap mini = new MiniMap(map, frame.getSize());
			return mini.render();
		} else
		{
			WorldPanel panel = (WorldPanel) frame.getPanel();
			MiniMap mini = new MiniMap(map, panel.getMapSize(), 7);
			return mini.render();
		}
	}


	public void startCombat(Monster monster)
	{
		if (monster instanceof Troll)
		{
			monsterPicture = "images/troll.jpg";
		}

		if (monster instanceof Goblin)
		{
			monsterPicture = "images/goblin.jpg";
		}

		if (monster instanceof Slime)
		{
			monsterPicture = "images/slime.jpg";
		}
		currentMonster = monster;
		currentMonster.setPlayer(playerProfile);
		frame.switchPanel("Combat");

	}

	// Player Methods

	public boolean playerHitChance()
	{
		int hitChance = (int) (Math.random() * 100 + 1) + (playerProfile.getPrecision());
		boolean hasHit = false;

		if (hitChance > currentMonster.getMonsterAgility())
		{
			hasHit = true;
		}
		return hasHit;

	}

	public void playerAttack()
	{

		if (playerHitChance() == true)
		{
			currentMonster.setMonsterCurrentHealth(currentMonster.getMonsterCurrentHealth() - playerProfile.getPlayerStrength());
		}
	}

	public boolean run()
	{
		int escapeChance = (int) (Math.random() * 100 + 1) + (playerProfile.getPlayerSpeed());

		if (escapeChance > currentMonster.getMonsterSpeed())
		{
			return true;
		} else
		{
			return false;
		}
	}

	// monster methods
	public boolean monsterHitChance()
	{
		int hitChance = (int) (Math.random() * 100 + 1) + (currentMonster.getMonsterPrecision());
		boolean hasHit = false;

		if (hitChance > playerProfile.getAgility())
		{
			hasHit = true;
		} else
		{
			hasHit = false;
		}
		return hasHit;
	}
	
	public boolean combatOver()
	{
		return (playerProfile.getCurrentHealth() <= 0 || currentMonster.getMonsterCurrentHealth() <= 0);
	}
	public void monsterAttack()
	{
		// System.out.println(playerProfile.getCurrentHealth());

		if (monsterHitChance())
		{

			playerProfile.setCurrentHealth(playerProfile.getCurrentHealth() - currentMonster.getMonsterStrength());
		}
		// System.out.println(playerProfile.getCurrentHealth());
	}

	public boolean monsterDeath()
	{
		if (currentMonster.getMonsterCurrentHealth() <= 0)
		{

			return true;
		} else
		{
			return false;
		}

	}

	public void combatEnd()
	{
		if (playerProfile.playerDeath())
		{
			// System.out.println("You dead");
			frame.switchPanel("Death");
		} else if (monsterDeath())
		{
			// System.out.println("They dead");
			if (currentMonster.getDropChance() >= currentMonster.getDropResist())
			{
				// drops item
			}
			// System.out.println(currentMonster.getMonsterXP());
			playerProfile.setPlayerXP(playerProfile.getPlayerXP() + currentMonster.getMonsterXP());
			frame.switchPanel("Victory");
		} else if (run())
		{
			// System.out.println("You coward");

			frame.switchPanel("DungeonEscape");
		} else
		{
			// System.out.println("Fail");
		}
	}

	public void levelPlayer()
	{
		playerProfile.levelUp();
	}

	public Monster getCurrentMonster()
	{
		return currentMonster;
	}

	public void setCurrentMonster(Monster currentMonster)
	{
		this.currentMonster = currentMonster;
	}

	public void setMonsterPicture(String monsterPicture)
	{
		this.monsterPicture = monsterPicture;
	}

	public String getMonsterPicture()
	{
		return monsterPicture;
	}

	public Player getPlayerProfile()
	{
		return playerProfile;
	}

	public void setPlayerProfile(Player playerProfile)
	{
		this.playerProfile = playerProfile;
	}
}
