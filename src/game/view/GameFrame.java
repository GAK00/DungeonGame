package game.view;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import dungeon.view.CombatPanel;
import dungeon.view.DeathPanel;
import dungeon.view.DungeonEscape;
import dungeon.view.VictoryPanel;
import game.controller.GameController;
import world.model.Tile;
import world.view.WorldPanel;

public class GameFrame extends JFrame
{
	private GameController baseController;
	private WorldPanel panel;
	private CombatPanel fightAppPanel;
	private DeathPanel deathAppPanel;
	private VictoryPanel winAppPanel;
	private DungeonEscape escapeAppPanel;

	// baseController.startCombat(baseController.getCurrentMonster());
	public GameFrame(GameController controller, int width, int height, int posx, int posy)
	{
		super();
		this.baseController = controller;

		fightAppPanel = new CombatPanel(baseController, baseController.getPlayerProfile(), baseController.getCurrentMonster());
		deathAppPanel = new DeathPanel(baseController);
		winAppPanel = new VictoryPanel(baseController, baseController.getPlayerProfile(), baseController.getCurrentMonster());
		escapeAppPanel = new DungeonEscape(baseController);
		panel = new WorldPanel(controller, width, height);
		setup(width, height, posx, posy);
		setupListeners();
	}

	private void setup(int width, int height, int posx, int posy)
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Dungeon");
		this.setSize(width, height);
		this.setLocation(posx, posy);
		this.setContentPane(panel);
		this.setVisible(true);
	}

	public JPanel getPanel()
	{
		return panel;
	}

	private void setupListeners()
	{
		this.addComponentListener(new ComponentListener()
		{

			@Override
			public void componentHidden(ComponentEvent arg0)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void componentMoved(ComponentEvent arg0)
			{
				baseController.saveWindowData();

			}

			@Override
			public void componentResized(ComponentEvent arg0)
			{
				panel.resized(baseController.getFrame().getWidth(), baseController.getFrame().getHeight());
				if (baseController.getFrame().getWidth() < (int) (Tile.getMinimumSize() * baseController.getMap().getCurrentRoom().getSize().getWidth()) || baseController.getFrame().getHeight() < (int) (Tile.getMinimumSize() * baseController.getMap().getCurrentRoom().getSize().getHeight()))
				{
					baseController.getFrame().setSize(new Dimension((int) baseController.getMap().getCurrentRoom().getSize().getWidth() * Tile.getMinimumSize(), (int) baseController.getMap().getCurrentRoom().getSize().getHeight() * Tile.getMinimumSize()));
				}

				baseController.saveWindowData();

			}

			@Override
			public void componentShown(ComponentEvent arg0)
			{
				// TODO Auto-generated method stub

			}
		});

		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				baseController.saveMap();
			}
		});

	}

	public int[] getScreenSize()
	{
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		int[] toReturn = { width, height };
		return toReturn;

	}
	public void switchPanel(String Panel)
	{
		panel.setVisible(false);
		this.setVisible(false);
		if(Panel.equals("Death"))
		{
			this.setContentPane(deathAppPanel);
		}
		if(Panel.equals("Victory"))
		{
			winAppPanel = new VictoryPanel(baseController, baseController.getPlayerProfile(), baseController.getCurrentMonster());
			this.setContentPane(winAppPanel);
		}
		if(Panel.equals("DungeonEscape"))
		{
			this.setContentPane(escapeAppPanel);
			
		}
		if(Panel.equals("Combat"))
		{
			this.setContentPane(new CombatPanel(baseController, baseController.getPlayerProfile(), baseController.getCurrentMonster()));
		}
		if(Panel.equals("World"))
		{
			panel.setVisible(true);
			this.setContentPane(panel);
		}
		this.revalidate();
		this.setVisible(true);
		
	}
	public CombatPanel getCombat()
	{
		return this.fightAppPanel;
	}
	public WorldPanel getWorld()
	{
		return panel;
	}
}
