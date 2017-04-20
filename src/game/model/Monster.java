package game.model;

import java.awt.Color;
import java.awt.Dimension;

import world.model.Room;

public abstract class Monster implements java.io.Serializable

{
	private boolean hasMoved;
	private int monsterHealth;
	private int monsterCurrentHealth;
	private int monsterStrength;
	private int dropChance;	
	private String monsterType;
	private int monsterPrecision;
	private int dropResist;
	private int monsterAgility;
	private int monsterSpeed;
	private int monsterXP;
	private String monsterPic;
	private String monsterName;
	Player player; 

	public Monster(int monsterHealth, int monsterCurrentHealth, int monsterStrength, int dropChance, int monsterPrecision, 
			int dropResist, int monsterAgility, int monsterSpeed, int monsterXP, String monsterPic, String monsterName)
	{
		this.monsterHealth = monsterHealth;
		this.setMonsterCurrentHealth(monsterCurrentHealth);
		this.monsterStrength = monsterStrength;
		this.dropChance = dropChance;
		this.monsterPrecision = monsterPrecision;
		this.dropResist = dropResist;
		this.setMonsterAgility(monsterAgility);
		this.setMonsterSpeed(monsterSpeed);
		this.monsterName = monsterName;
		this.monsterPic = monsterPic;
		this.monsterXP = monsterXP;
		hasMoved = false;
	}
	
	public String getType()
	{
		return this.getClass().getSimpleName();
	}
	
	

	

	public int getMonsterAgility() {
		return monsterAgility;
	}

	public void setMonsterAgility(int monsterAgility) {
		this.monsterAgility = monsterAgility;
	}

	public int getMonsterCurrentHealth() {
		return monsterCurrentHealth;
	}

	public void setMonsterCurrentHealth(int monsterCurrentHealth) {
		this.monsterCurrentHealth = monsterCurrentHealth;
	}

	public int getMonsterSpeed() {
		return monsterSpeed;
	}

	public void setMonsterSpeed(int monsterSpeed) {
		this.monsterSpeed = monsterSpeed;
	}
	
	public int getMonsterStrength()
	{
		return monsterStrength;
	}
	
	public void setMonsterStrength()
	{
		this.monsterStrength = monsterStrength;
	}
	
	public void setPlayer(Player current)
	{
		this.player = current;
	}
	
	public int getMonsterPrecision()
	{
		return monsterPrecision;
	}

	public void setMonsterPrecision(int monsterPrecision)
	{
		this.monsterPrecision = monsterPrecision;
	}

	public int getDropChance()
	{
		return dropChance;
	}

	public void setDropChance(int dropChance)
	{
		this.dropChance = dropChance;
	}
	
	public int getDropResist()
	{
		return dropResist;
	}

	public void setDropResist(int dropResist)
	{
		this.dropResist = dropResist;
	}

	public int getMonsterXP()
	{
		return monsterXP;
	}

	public void setMonsterXP(int monsterXP)
	{
		this.monsterXP = monsterXP;
	}

	public int getMonsterHealth()
	{
		return monsterHealth;
	}

	public void setMonsterHealth(int monsterHealth)
	{
		this.monsterHealth = monsterHealth;
	}

	public boolean HasMoved()
	{
		return hasMoved;
	}

	public void setHasMoved(boolean hasMoved)
	{
		this.hasMoved = hasMoved;
	}

	public Dimension move(Room room,Dimension Position)
	{
		return null;
	}
	
	public Color getColor()
	{
		return Color.WHITE;
	}

}
