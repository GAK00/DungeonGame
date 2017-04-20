package dungeon.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import game.controller.GameController;
import game.model.Monster;
import game.model.Player;
import game.view.GameFrame;

public class VictoryPanel extends JPanel
{
	private GameController baseController;
	private SpringLayout baseLayout;
	private JLabel victoryLabel;
	private JLabel spoilsLabel;
	private JButton continueButton;
	private JLabel victoryPic;
	private JLabel healthLabel;
	private JLabel strengthLabel;
	private JLabel agilityLabel;
	private JLabel precisionLabel;
	private JLabel speedLabel;
	private JLabel levelLabel;
	private JLabel toNextLabel;
	private JLabel levelUpLabel;
	Monster monster;
	Player player;
	
	public VictoryPanel(GameController baseController, Player player, Monster monster)
	{
		super();
		this.baseController = baseController;
		this.player = player;
		this.monster = monster;
		baseLayout = new SpringLayout();
		victoryLabel = new JLabel("Congratulations you are victorious");
		spoilsLabel = new JLabel("You gained " + monster.getMonsterXP() + " xp");
		continueButton = new JButton("Continue");
		victoryPic = new JLabel(new ImageIcon(getClass().getResource("images/victory.jpg")));
		healthLabel = new JLabel("Your maximum health is now " + (player.getMaxHealth() + 10));
		agilityLabel= new JLabel("Your agility is now " + (player.getAgility() + 2));
		precisionLabel= new JLabel("Your accuracy is now " + (player.getPrecision() + 10));
		speedLabel= new JLabel("Your speed is now " + (player.getPlayerSpeed() + 10));
		levelLabel= new JLabel("Your level is now " + (player.getPlayerLevel() + 1));
		toNextLabel= new JLabel("You need " + ((player.getPlayerLevel() * 10) - player.getPlayerXP()) + " more xp for your next level");
		levelUpLabel = new JLabel("Congratulations you leveled up");
		strengthLabel= new JLabel("Your attack damage is now " + (player.getPlayerStrength() + 2));
		

		
		healthLabel.setVisible(false);
		strengthLabel.setVisible(false);
		agilityLabel.setVisible(false);
		precisionLabel.setVisible(false);
		speedLabel.setVisible(false);
		levelLabel.setVisible(false);
		levelUpLabel.setVisible(false);
		
		if(player.getPlayerXP() >= (player.getPlayerLevel() * 10))
		{
			healthLabel.setVisible(true);
			strengthLabel.setVisible(true);
			agilityLabel.setVisible(true);
			precisionLabel.setVisible(true);
			speedLabel.setVisible(true);
			levelLabel.setVisible(true);
			levelUpLabel.setVisible(true);
		}
		
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	private void setupPanel()
	{
		this.setLayout(baseLayout);;
		this.setPreferredSize(new Dimension(960, 540));
		this.setBackground(Color.GRAY);
		
		this.add(continueButton);
		this.add(spoilsLabel);
		this.add(victoryLabel);
		this.add(victoryPic);
		this.add(agilityLabel);
		this.add(healthLabel);
		this.add(levelLabel);
		this.add(precisionLabel);
		this.add(speedLabel);
		this.add(strengthLabel);
		this.add(toNextLabel);
		this.add(levelUpLabel);
		
		
	}
	
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.NORTH, continueButton, 6, SpringLayout.SOUTH, victoryPic);
		baseLayout.putConstraint(SpringLayout.EAST, victoryPic, -238, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, victoryPic, 91, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.EAST, continueButton, -425, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, toNextLabel, 6, SpringLayout.SOUTH, spoilsLabel);
		baseLayout.putConstraint(SpringLayout.WEST, toNextLabel, 0, SpringLayout.WEST, spoilsLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, spoilsLabel, 45, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, spoilsLabel, 222, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.WEST, victoryLabel, 0, SpringLayout.WEST, spoilsLabel);
		baseLayout.putConstraint(SpringLayout.SOUTH, victoryLabel, -6, SpringLayout.NORTH, spoilsLabel);
		baseLayout.putConstraint(SpringLayout.SOUTH, healthLabel, -6, SpringLayout.NORTH, strengthLabel);
		baseLayout.putConstraint(SpringLayout.WEST, strengthLabel, 0, SpringLayout.WEST, agilityLabel);
		baseLayout.putConstraint(SpringLayout.SOUTH, strengthLabel, -6, SpringLayout.NORTH, agilityLabel);
		baseLayout.putConstraint(SpringLayout.WEST, levelUpLabel, 0, SpringLayout.WEST, agilityLabel);
		baseLayout.putConstraint(SpringLayout.SOUTH, levelUpLabel, -6, SpringLayout.NORTH, levelLabel);
		baseLayout.putConstraint(SpringLayout.WEST, levelLabel, 0, SpringLayout.WEST, agilityLabel);
		baseLayout.putConstraint(SpringLayout.SOUTH, levelLabel, -6, SpringLayout.NORTH, healthLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, speedLabel, 227, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, speedLabel, 0, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, precisionLabel, -6, SpringLayout.NORTH, speedLabel);
		baseLayout.putConstraint(SpringLayout.WEST, precisionLabel, 0, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, agilityLabel, -6, SpringLayout.NORTH, precisionLabel);
		baseLayout.putConstraint(SpringLayout.WEST, agilityLabel, 0, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.WEST, healthLabel, 0, SpringLayout.WEST, agilityLabel);
		
	}
	
	private void setupListeners()
	{
		continueButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent click)
					{
						baseController.levelPlayer();
						JFrame frame = baseController.getFrame();
						GameFrame game = (GameFrame) frame;
						game.switchPanel("World");
					}
				});
	}
}
