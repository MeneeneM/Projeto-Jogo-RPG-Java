package projeto_rpg;
import java.util.Random;

public class Personagem {
	// Atributos - basicos
	private String nome;
	private String classe;
	private int idade;
	private char sexo;
	
	// Atributos - jogo
	private int level = 1;
	private int experiencia = 0;
	private int vidaMaxima = 100;
	private int pontosVida = 100;
	private int curasVidaDisponiveis = 3;
	private int ataque = 10;
	private int defesa = 10;
	private boolean estaDefendendo = false;
	private int manaMaxima = 50;
	private int mana = 50;
	private int curasManaDisponiveis = 3;
	private int forca = 5;
	private int precisao = 60;
	
	// Atributo Arma - Classe
	class Arma
	{
		String Nome;
		int dano;
		int bonusCritico;
	}
	
	private  Arma arma;
	
	// Metodos - basicos - Getters Setters
	
	// Nome
	public String getNome()
	{
		return nome;
	}
	
	public void setNome(String nome1)
	{
		if(nome1 != null && !nome1.isEmpty())
			nome = nome1;
		else
			System.out.println("Digite algo para o nome!!!");
	}
	
	// Classe
	public String getClasse()
	{
		return classe;
	}
	
	public void setClasse(String classe1)
	{
		classe = classe1;
	}
	
	// Idade
	public int getIdade()
	{
		return idade;
	}
	
	public void setIdade(int idade1)
	{
		if(idade1 >=8) 
		{
			idade = idade1;
		}
		else
			System.out.println("Digite uma idade valida!!!");
	}
	
	// Sexo
	public char getSexo()
	{
		return sexo;
	}
	
	public void setSexo(char sexo1)
	{
		if(sexo1 == 'F' || sexo1 == 'f' || sexo1 == 'M' || sexo1 == 'm')
			sexo = sexo1;
		else 
		{
	        System.out.println("Sexo inválido! Use 'M' ou 'F'.");
	    }
	}
	
	// Metodos - Jogo - Getters
	
	// Vida
	public int getPontosVida() {
	    return pontosVida;
	}

	public int getVidaMaxima() {
	    return vidaMaxima;
	}

	// Mana
	public int getMana() {
	    return mana;
	}

	public int getManaMaxima() {
	    return manaMaxima;
	}

	// Combate
	public int getAtaque() {
	    return ataque;
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

	// Arma
	public Arma getArma() {
	    return arma;
	}
	
	// Cura de Vida
	public int getCurasVidaDisponiveis() {
	    return curasVidaDisponiveis;
	}

	// Cura de Mana
	public int getCurasManaDisponiveis() {
	    return curasManaDisponiveis;
	}
	
	// Metodos - jogo
	
	public void exibirFicha()
	{
		System.out.println("---- FICHA DO PERSONAGEM ----");
		System.out.println("Nome: " + nome);
		System.out.println("Classe: " + classe);
		System.out.println("Level: " + level);
		System.out.println("Experiencia: " + experiencia);
		System.out.println("Pontos de Vida: " + pontosVida);
		System.out.println("Ataque: " + ataque);
		System.out.println("Defesa: " + defesa);
		System.out.println("Mana: " + mana);
		System.out.println("Forca: " + forca);
		System.out.println("Precisão: " + precisao);
		System.out.println("Arma: " + arma.Nome + " (Dano: " + arma.dano + ", Crit: " + arma.bonusCritico + ")");
	}
	
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
			System.out.println("O ataque falhou!!!");
			return 0;
		}
		
		int dano = ataque + arma.dano + forca;
		
		// Variação de dano (-20% até +20%)
		double variacao = random.nextDouble() * 0.4 - 0.2;
		dano += (int)(dano * variacao);
		
		// Critico
		int chanceCritico = 5;
		int critico = chanceCritico + arma.bonusCritico;
		int multiplicadorCritico = 2;
		
		if(random.nextInt(100) < critico)
		{
			System.out.println("Ataque Critico!!!");
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
		System.out.println(nome + " recebeu " + danoFinal + " de dano. Vida atual: " + pontosVida);
	}
	
	public void defender()
	{
		estaDefendendo = true;
		System.out.println(nome + " está defendendo e receberá menos dano no próximo ataque!");
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
	    System.out.println(nome + " usou Cura! Restam " + curasVidaDisponiveis + " curas.");
		}
        else 
        {
        	System.out.println("Você não pode mais usar Cura de Vida!");
        }
		
	}
	
	// Metodo para encher a mana
	public boolean gastarMana(int custo)
	{
		if(mana >= custo)
		{
			mana -= custo;
			return true;
		}
		return false;
	}
	
	public void restaurarMana(int valor)
	{
		if(curasManaDisponiveis > 0) {
		mana += valor;

		if(mana > manaMaxima)
			mana = manaMaxima;
		curasManaDisponiveis--;
		System.out.println(nome + " usou Cura de Mana! Restam " + curasManaDisponiveis + " usos.");
		}
		else
		{
			System.out.println("Você não pode mais usar Cura de Mana!");
		}
	}
	
	public void resetarCuras() {
	    curasVidaDisponiveis = 3;
	    curasManaDisponiveis = 3;
	}
	
	// Metodo subir de level
	
	public void ganharExperiencia(int xp)
	{
		experiencia += xp;
		System.out.println(nome + " ganhou " + xp + " de experiencia!");
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
			mana += 5;
			forca += 3;
			precisao += 5;
			
			System.out.println("Você subiu para o level " + level + "!");
			
			xp = 100 * level;
		}
	}
}
