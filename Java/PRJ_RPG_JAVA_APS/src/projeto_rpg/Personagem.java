package projeto_rpg;
import java.util.Random;

public class Personagem {
	// Atributos - basicos
	private String nome;
	private String classe;
	
	// Atributos - jogo
	private int level = 1;
	private int experiencia = 0;
	private int vidaMaxima = 100;
	private int pontosVida = 100;
	private int curasVidaDisponiveis = 3;
	private int ataque = 10;
	private int defesa = 10;
	private boolean estaDefendendo = false;
	private int forca = 5;
	private int precisao = 60;
	
	// Construtor Personagem
	public Personagem(String nome, String classe)
	{
		this.nome = nome;
		this.classe = classe;
	}
	
	// Metodos - basicos - Getters Setters
	
	// Nome
	public String getNome()
	{
		return nome;
	}
	
	public void setNome(String nome)
	{
		if(nome != null && !nome.isEmpty())
			this.nome = nome;
	}
	
	// Classe
	public String getClasse()
	{
		return classe;
	}
	
	public void setClasse(String classe)
	{
		this.classe = classe;
	}
	
	// Metodos - Jogo - Getters
	
	// Vida
	public int getPontosVida() {
	    return pontosVida;
	}

	public int getVidaMaxima() {
	    return vidaMaxima;
	}
	
	public void setVidaMaxima(int vidaMaxima) {
	    this.vidaMaxima = vidaMaxima;
	}

	public void setPontosVida(int pontosVida) {
	    this.pontosVida = pontosVida;
	}


	// Combate
	
	public void setAtaque(int ataque) {
		if(ataque >= 0) {
			this.ataque = ataque;			
		}
	}
	
	public int getAtaque() {
	    return ataque;
	}
	
	public void setDefesa(int defesa) {
		if(defesa >= 0) {
			this.defesa = defesa;			
		}
	}

	public int getDefesa() {
	    return defesa;
	}

	public int getForca() {
	    return forca;
	}

	public int getPrecisao() {
	    return precisao;
	}

	// Progressão
	public int getLevel() {
	    return level;
	}

	public int getExperiencia() {
	    return experiencia;
	}

	// Estado de defesa
	public boolean estaDefendendo() {
	    return estaDefendendo;
	}
	
	// Cura de Vida
	public int getCurasVidaDisponiveis() {
	    return curasVidaDisponiveis;
	}

	// Metodos - jogo

	// Metodo Morte
	public boolean estaVivo()
	{
		return pontosVida > 0;
	}
	
	// Metodo Atacar personagem alvo
	
	private Random random = new Random();
	
	public int atacar()
	{
		return ataqueCritico();
	}
	
	// Metodo teste de Ataque Critico
	private int ataqueCritico()
	{
		// Chance do ataque acertar
		int chanceAcerto = precisao;
		if(random.nextInt(100) > chanceAcerto)
		{
			return 0;
		}
		
		int dano = ataque + forca;
		
		// Variação de dano (-20% até +20%)
		double variacao = random.nextDouble() * 0.4 - 0.2;
		dano += (int)(dano * variacao);
		
		// Critico
		int chanceCritico = 10;
		int multiplicadorCritico = 2;
		
		if(random.nextInt(100) < chanceCritico)
		{
		   dano *= multiplicadorCritico;
		}
		return dano;
	}
	
	// Metodo Defender de ataque alvo
	public void receberDano(int dano)
	{
		int danoFinal;
		
		if(estaDefendendo)
		{
			danoFinal = (int)((dano* 0.5) * (100.0 / (100 + defesa)));
			estaDefendendo = false;
		}
		else
		{
			danoFinal = (int)(dano * (100.0 / (100 + defesa)));
		}
		
		if(danoFinal < 0)
			danoFinal = 0;
		
		pontosVida -= danoFinal;
		
		if(pontosVida <0)
			pontosVida = 0;
	}
	
	public void defender()
	{
		estaDefendendo = true;
	}
	
	// Metodo para curar a vida
	public void curar(int valor)
	{
		if(curasVidaDisponiveis > 0) 
		{
			pontosVida += valor;
			
	    if(pontosVida > vidaMaxima)
	        pontosVida = vidaMaxima;
	    curasVidaDisponiveis--;
		}
	}
	
	public void resetarCuras() {
	    curasVidaDisponiveis = 3;
	}
	
	public void restaurarPosBatalha() {
	    this.pontosVida = this.vidaMaxima;
	    resetarCuras();
	}
	
	// Metodo subir de level
	
	public void ganharExperiencia(int xp)
	{
		experiencia += xp;
		subirLevel();
	}

	public void subirLevel()
	{
		int xp = 100 * level;
		
		while(experiencia >= xp)
		{
			// Level
			experiencia -= xp;
			level ++;
			
			// Atributos
			vidaMaxima += 10;
			pontosVida = vidaMaxima;
			ataque += 5;
			defesa += 5;
			forca += 3;
			precisao += 5;
			
			xp = 100 * level;
		}
	}
}
