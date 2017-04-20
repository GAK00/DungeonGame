package dungeon.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import game.controller.GameController;
import game.model.HealthBar;
import game.model.Monster;
import game.model.Player;


public class CombatPanel extends JPanel
{	
	private GameController baseController;
	private SpringLayout baseLayout;
	private JButton fightButton;
	private JButton runButton;
	private JLabel playerDamage;
	private JLabel monsterPic;
	private JLabel playerPic;
	private JLabel monsterDamage;
	private int frameWidth;
	private int frameHeight;
	private HealthBar playerHealth;
	private HealthBar monsterHealth;
	private JLabel playerHealthRender;
	private JLabel monsterHealthRender;
	Player player;
	Monster currentMonster;
	
	public CombatPanel(GameController baseController, Player player, Monster currentMonster)
	{
		super();
		
		this.baseController = baseController;
		this.player = player;
		this.currentMonster = currentMonster;
		baseLayout = new SpringLayout();
		fightButton = new JButton("Attack");		
		runButton = new JButton("Run");		
		playerDamage = new JLabel("Your damage is " + Integer.toString(player.getPlayerStrength()));		
		monsterDamage = new JLabel("The monster's damage is " + Integer.toString(currentMonster.getMonsterStrength()));
		monsterPic = new JLabel(new ImageIcon(getClass().getResource(baseController.getMonsterPicture())));			
		baseLayout.putConstraint(SpringLayout.NORTH, monsterDamage, 25, SpringLayout.SOUTH, monsterPic);
		baseLayout.putConstraint(SpringLayout.WEST, monsterDamage, 0, SpringLayout.WEST, monsterPic);
		baseLayout.putConstraint(SpringLayout.EAST, monsterDamage, 0, SpringLayout.EAST, monsterPic);
		playerPic = new JLabel(new ImageIcon(getClass().getResource("images/redCube.jpg")));
		playerHealth = new HealthBar(500, 200, player.getMaxHealth(), player.getCurrentHealth());
		playerHealthRender = new JLabel(new ImageIcon(playerHealth.render()));
//		try
//		{
//			ImageIO.write(playerHealth.render(),"PNG",new File("/Users/nwhi5696/Desktop/test.png"));
//		} 
//		catch (IOException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		monsterHealth = new HealthBar(500, 200, currentMonster.getMonsterHealth(), currentMonster.getMonsterCurrentHealth());
		monsterHealthRender = new JLabel(new ImageIcon(monsterHealth.render()));

		
		
		
		
		
		
		setupPanel();
		setupLayout();
		setupListeners();
	}
	public void setPicture(String path)
	{
		this.remove(monsterPic);
		monsterPic = new JLabel(new ImageIcon(getClass().getResource(path)));
		this.add(monsterPic);
		this.setupLayout();
		monsterDamage = new JLabel("The monster's damage is " + Integer.toString(currentMonster.getMonsterStrength()));
		monsterPic = new JLabel(new ImageIcon(getClass().getResource(baseController.getMonsterPicture())));			
		baseLayout.putConstraint(SpringLayout.NORTH, monsterDamage, 25, SpringLayout.SOUTH, monsterPic);
		baseLayout.putConstraint(SpringLayout.WEST, monsterDamage, 0, SpringLayout.WEST, monsterPic);
		baseLayout.putConstraint(SpringLayout.EAST, monsterDamage, 0, SpringLayout.EAST, monsterPic);
		playerPic = new JLabel(new ImageIcon(getClass().getResource("images/redCube.jpg")));
		playerHealth = new HealthBar(500, 200, player.getMaxHealth(), player.getCurrentHealth());
		playerHealthRender = new JLabel(new ImageIcon(playerHealth.render()));
		monsterHealth = new HealthBar(500, 200, currentMonster.getMonsterHealth(), currentMonster.getMonsterCurrentHealth());
		monsterHealthRender = new JLabel(new ImageIcon(monsterHealth.render()));
	}
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		this.setPreferredSize(new Dimension(960, 540));
		this.setBackground(Color.GRAY);
		
		this.add(monsterPic);
		this.add(playerPic);
		this.add(fightButton);
		this.add(runButton);
		this.add(playerDamage);
		this.add(monsterDamage);
		this.add(playerHealthRender);
		this.add(monsterHealthRender);
	}
	
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.NORTH, monsterPic, 1, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.EAST, monsterPic, -10, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, fightButton, 28, SpringLayout.SOUTH, playerDamage);
		baseLayout.putConstraint(SpringLayout.WEST, playerDamage, 0, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, playerDamage, -6, SpringLayout.NORTH, playerPic);
		baseLayout.putConstraint(SpringLayout.WEST, playerPic, -96, SpringLayout.WEST, fightButton);
		baseLayout.putConstraint(SpringLayout.EAST, playerPic, -27, SpringLayout.WEST, fightButton);
		baseLayout.putConstraint(SpringLayout.SOUTH, playerPic, -51, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.NORTH, runButton, 0, SpringLayout.NORTH, fightButton);
		baseLayout.putConstraint(SpringLayout.WEST, runButton, 40, SpringLayout.EAST, fightButton);
		baseLayout.putConstraint(SpringLayout.EAST, fightButton, -769, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, playerHealthRender, 6, SpringLayout.SOUTH, playerPic);
		baseLayout.putConstraint(SpringLayout.WEST, playerHealthRender, 0, SpringLayout.WEST, playerPic);
		baseLayout.putConstraint(SpringLayout.NORTH, monsterHealthRender, 7, SpringLayout.SOUTH, monsterPic);
		baseLayout.putConstraint(SpringLayout.WEST, monsterHealthRender, 0, SpringLayout.WEST, monsterPic);
		baseLayout.putConstraint(SpringLayout.EAST, monsterHealthRender, 0, SpringLayout.EAST, monsterPic);
	}
	
	private void setupListeners()
	{
		fightButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent click)
					{
						if(player.getPlayerSpeed() > currentMonster.getMonsterSpeed())
						{
							baseController.playerAttack();
							baseController.monsterAttack();
						}
						else if(player.getPlayerSpeed() < currentMonster.getMonsterSpeed())
						{
							baseController.monsterAttack();
							baseController.playerAttack();
						}
						else
						{
							baseController.playerAttack();
							baseController.monsterAttack();
						}
						remove(playerHealthRender);
						playerHealth = new HealthBar(500, 200, player.getMaxHealth(), player.getCurrentHealth());
						playerHealthRender = new JLabel(new ImageIcon(playerHealth.render()));
						add(playerHealthRender);
						remove(monsterHealthRender);
						monsterHealth = new HealthBar(500, 200, baseController.getCurrentMonster().getMonsterHealth(), baseController.getCurrentMonster().getMonsterCurrentHealth());
						monsterHealthRender = new JLabel(new ImageIcon(monsterHealth.render()));
						add(monsterHealthRender);
						setupLayout();
						repaint();
						revalidate();
						
						if(baseController.combatOver())
						{
							baseController.combatEnd();
						}
					}
				});
		runButton.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent click)
					{
						baseController.run();
						//System.out.println(baseController.run());
						baseController.combatEnd();
					}
				});
	}
	public Monster getCurrentMonster()
	{
		return currentMonster;
	}
}


/* I like the concept of the project, and it seems really cool */