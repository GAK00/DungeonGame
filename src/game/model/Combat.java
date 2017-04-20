package game.model;

public interface Combat 
{	
	public boolean monsterHitChance();
	
	public void monsterAttack();
	
	public  boolean playerHitChance();
	
	public void playerAttack();
	
	public void monsterDeath();
	
	public void playerDeath();
	
	public void run();
	
	
}
