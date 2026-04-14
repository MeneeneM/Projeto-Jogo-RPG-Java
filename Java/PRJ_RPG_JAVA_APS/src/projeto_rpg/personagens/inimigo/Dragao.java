package projeto_rpg.personagens.inimigo;

public class Dragao extends Inimigo{
	
	public Dragao()
	{
		super("Dragao", "Monstro");
		
		this.xp = 250;
		
		setAtaque(30);
		setDefesa(35);
		setVidaMaxima(500);
		setPontosVida(500);
	}

}
