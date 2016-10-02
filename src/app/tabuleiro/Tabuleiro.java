package app.tabuleiro;

/**
 * Classe que controla o jogo, verifica e realiza as jogadas A parte motora do
 * jogo
 *
 * Posição do tabuleiro: [0][0]=0 [0][1]=1 [0][2]=2 [1][0]=3 [1][1]=4 [1][2]=5
 * [2][0]=6 [2][1]=7 [2][2]=8
 * 
 * Posição livre = 0 Jogador = 1 CPU = 2 Empate = 3
 * 
 * @author Juliano Dorigão
 */
public class Tabuleiro {

	private int tabuleiro[][] = new int[3][3];// Matriz do tabuleiro
	private int jogador1= 0;// Pontuação dos jogadores
	private int empate = 0;// Pontuação dos jogadores
	private int jogador2 = 0;// Pontuação dos jogadores
	private int vencedor = 0;// Recebe o valor referente a quem ganhou a partida

	/**
	 * Inicializa o tabuleiro com 0
	 */
	public void iniciaTabuleiro() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++)
				this.tabuleiro[i][j] = 0;
		}// Fim for i
	}// Fim IniciaPartida

	/**
	 * Retorna a matriz do tabuleiro com os valores.
	 * 
	 * @return tabuleiro
	 */
	public int[][] getTabuleiro() {
		return this.tabuleiro;
	}

	
	/**
	 * Insere a jogada escolhida pelo jogador no tabuleiro Posição do tabuleiro:
	 * [0][0]=0 [0][1]=1 [0][2]=2 [1][0]=3 [1][1]=4 [1][2]=5 [2][0]=6 [2][1]=7
	 * [2][2]=8
	 * 
	 * Posição livre = 0 Jogador = 1 CPU = 2
	 * 
	 * @param jogador
	 * @param jogada
	 * @return Status do jogo
	 */
	public void setTabuleiro(int jogador, int jogada) {
		switch (jogada) {
		case 0:
			this.tabuleiro[0][0] = jogador;
			break;

		case 1:
			this.tabuleiro[0][1] = jogador;
			break;

		case 2:
			this.tabuleiro[0][2] = jogador;
			break;

		case 3:
			this.tabuleiro[1][0] = jogador;
			break;

		case 4:
			this.tabuleiro[1][1] = jogador;
			break;

		case 5:
			this.tabuleiro[1][2] = jogador;
			break;

		case 6:
			this.tabuleiro[2][0] = jogador;
			break;

		case 7:
			this.tabuleiro[2][1] = jogador;
			break;

		case 8:
			this.tabuleiro[2][2] = jogador;
			break;
		}// Fim case
		
		//imprimeMatriz();
		
	}// Fim InsereJogada

	/**
	 * Método VerificaVencedor retorna se ouve vencedor ou empate Controla a
	 * pontuação dos jogadores
	 * 
	 * Posição livre = 0 Jogador1 = 1 Jogador2 = 2 Empate = 3
	 * 
	 * @return Situação atual do jogo se houve ganhador empate ou nenhum dos
	 *         dois
	 */
	public int verificaVencedor() {
		// Inicializando vencedor
		this.vencedor = 0;

		// Vamos verificar as 8 possíveis combinações para ganhar o jogo.
		// Primeira linha.
		if ((this.tabuleiro[0][0] == this.tabuleiro[0][1])
				&& (this.tabuleiro[0][1] == this.tabuleiro[0][2]))
			this.vencedor = this.tabuleiro[0][0];

		// Segunda linha.
		else if ((this.tabuleiro[1][0] == this.tabuleiro[1][1])
				&& (this.tabuleiro[1][1] == this.tabuleiro[1][2]))
			this.vencedor = this.tabuleiro[1][0];

		// Terceira linha.
		else if ((this.tabuleiro[2][0] == this.tabuleiro[2][1])
				&& (this.tabuleiro[2][1] == this.tabuleiro[2][2]))
			this.vencedor = this.tabuleiro[2][0];

		// Primeira coluna.
		else if ((this.tabuleiro[0][0] == this.tabuleiro[1][0])
				&& (this.tabuleiro[1][0] == this.tabuleiro[2][0]))
			this.vencedor = this.tabuleiro[0][0];

		// Segunda coluna.
		else if ((this.tabuleiro[0][1] == this.tabuleiro[1][1])
				&& (this.tabuleiro[1][1] == this.tabuleiro[2][1]))
			this.vencedor = this.tabuleiro[0][1];

		// Terceira coluna.
		else if ((this.tabuleiro[0][2] == this.tabuleiro[1][2])
				&& (this.tabuleiro[1][2] == this.tabuleiro[2][2]))
			this.vencedor = this.tabuleiro[0][2];

		// Diagonal descendente.
		else if ((this.tabuleiro[0][0] == this.tabuleiro[1][1])
				&& (this.tabuleiro[1][1] == this.tabuleiro[2][2]))
			this.vencedor = this.tabuleiro[0][0];

		// Diagonal ascendente.
		else if ((this.tabuleiro[2][0] == this.tabuleiro[1][1])
				&& (this.tabuleiro[1][1] == this.tabuleiro[0][2]))
			this.vencedor = this.tabuleiro[2][0];

		else // Nenhuma das combinações existentes
		{
			int vazio = 0;// Variável auxiliar para verificar se houve empate

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (this.tabuleiro[i][j] == 0)
						vazio++;
				}// Fim for j
			}// Fim for i

			if (vazio == 0) {
				this.vencedor = 3;// Empate
				this.empate++;
			}
		}// Fim else

		if (this.vencedor == 1)
			this.jogador1++; // Acrescenta vitória para o Jogador

		if (this.vencedor == 2)
			this.jogador2++; // Acrescenta vitória para o CPU		

		return this.vencedor; // Retorna o status do jogo
	}// Fim VerificaVencedor

	/**
	 * Recebe como parâmetro o ID do jogador e retorna a pontuação
	 * 
	 * @param id
	 * @return Pontuação do jogador
	 */
	public int getJogador1() {
		return this.jogador1;// Pontuação do Jogador
	}// Fim Pontuacao do Jogador

	public int getEmpate() {
		return this.empate;// Pontuação do Jogador
	}// Fim Pontuacao do Jogador

	public int getJogador2() {
		return this.jogador2;// Pontuação do Jogador
	}// Fim Pontuacao do Jogador

	/**
	 * Encerra a partida zerando a pontuação e o tabuleiro
	 */
	public void encerraPartida() {
		// Zera contagem de vitório, empate e derrota da partida
		this.jogador1 = 0;
		this.empate = 0;
		this.jogador2 = 0;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++)
				this.tabuleiro[i][j] = 0;
		}// Fim for i
	}// Fim EncerraPartida
} // Fim Class tabuleiro
