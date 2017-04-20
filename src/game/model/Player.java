package game.model;


public class Player implements java.io.Serializable
{
	private int maxHealth = 100;
	private int currentHealth = 100;
	private int playerStrength = 10;
	private int agility = 100;
	private int precision = 0;
	private int playerSpeed = 0;
	private int playerXP = 0;
	private int playerLevel = 1; 	
 	
 	public Player()
 	{
 		
 	}
	

	public boolean playerDeath() 
	{
		if(currentHealth <= 0)
		{
			return true;		
		}
		else
		{
			return false;
		}
		
	}

	public void levelUp()
	{
		if(playerXP >= playerLevel * 10)
		{
			maxHealth = maxHealth + 10;
			currentHealth = currentHealth + 10;
			playerStrength = playerStrength + 2;
			agility = agility + 2;
			precision = precision + 10;
			playerSpeed = playerSpeed + 10;
			//playerXP = 0;
			playerLevel = playerLevel + 1;
		}
	}

	public int getAgility() 
	{
		return agility;
	}

	public void setAgility(int agility) 
	{
		this.agility = agility;
	}

	public int getCurrentHealth() 
	{
		return currentHealth;
	}

	public void setCurrentHealth(int currentHealth) 
	{
		this.currentHealth = currentHealth;
	}
	
	public int getMaxHealth()
	{
		return maxHealth;
	}
	
	public void setMaxHealth()
	{
		this.maxHealth = maxHealth;
	}

	public int getPlayerXP() 
	{
		return playerXP;
	}

	public void setPlayerXP(int playerXP) 
	{
		this.playerXP = playerXP;
	}
	
	public int getPlayerStrength()
	{
		return playerStrength;
	}
	
	public void setPlayerStrength(int playerStrength)
	{
		this.playerStrength = playerStrength;
	}
	
	
	
	public int getPrecision()
	{
		return precision;
	}

	public void setPrecision(int precision)
	{
		this.precision = precision;
	}
	
	public int getPlayerSpeed()
	{
		return playerSpeed;
	}


	public void setPlayerSpeed(int playerSpeed)
	{
		this.playerSpeed = playerSpeed;
	}
	
	public int getPlayerLevel()
	{
		return playerLevel;
	}


	public void setPlayerLevel(int playerLevel)
	{
		this.playerLevel = playerLevel;
	}


	public void setMaxHealth(int maxHealth)
	{
		this.maxHealth = maxHealth;
	}
	

}
