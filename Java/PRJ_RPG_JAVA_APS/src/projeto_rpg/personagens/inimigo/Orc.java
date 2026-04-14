package projeto_rpg.personagens.inimigo;

public class Orc extends Inimigo{
	
	public Orc()
	{
		super("Orc", "Monstro");
		
		this.xp = 175;
		
		setAtaque(16);
		setDefesa(14);
		setVidaMaxima(200);
		setPontosVida(200);
	}

}
