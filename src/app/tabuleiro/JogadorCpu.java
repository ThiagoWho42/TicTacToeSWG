package app.tabuleiro;

/**
 * Classe Jogador CPU verifica o tabuleiro e realiza a jogada Escolhe a jogada
 * através de um random com as posições vagas Verifica se pode encerrar a
 * partida (Vencer) Se pode impedir o Jogador de vencer
 * 
 * @author Juliano Dorigão
 */
public class JogadorCpu {

	private int controlaJogada[] = new int[9]; // Controla as jogadas
	private int max; // Número de posição do tabuleiro
	private int rand; // Número randômico

	/**
	 * Prepara o CPU para o jogo
	 */
	public void iniciaCpu() {
		for (int i = 0; i < 9; i++) {
			this.controlaJogada[i] = 0; // Inicializa o vetor
		}
		this.max = 9;// Vetor recebe as pocições de jogo do tabuleiro
	}// Fim IniciaJogadorCPU

	/**
	 * Realiza a jogada do CPU Recebe a matriz do jogo (tabuleiro), verifica as
	 * possibilidade de jogo e retorna a psição de jogada
	 * 
	 * @param matriz
	 */
	public int jogadaCpu(int matriz[][]) {
		this.max -= 2; // Contador de jogadas
		int cont = 0; // Contador
		int matrizcont = 0; // Posição na matriz
		

		/**
		 * Verifica posições vaga no tabuleiro
		 */
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (matriz[i][j] == 0) {
					controlaJogada[matrizcont] = cont;
					matrizcont++;
				}// Fim if matriz
				cont++;
			}// Fim for j
		}// Fim for i

		// Escolhe uma posição vaga no tabuleiro
		this.rand = (int) (Math.random() * this.max);// Escolhe uma jogada
														// dentro das
														// possibilidades
														// armazenada no vetor
														// controlaJogada
		this.rand = this.controlaJogada[this.rand];// rand recebe a jogada
												// escolhida
		
		// Verifica se o Jogador esta para vencer e pode ser impedido
		verificaJogada(1, matriz);

		// Verifica se o CPU pode finalizar o jogo (Vencer)
		verificaJogada(2, matriz);

		// Retornando a jogada escolhida
		return this.rand;
	}// Fim JogadaCPU

	/**
	 * Verifica o tabuleiro para escolha da jogada
	 * 
	 * @param ID
	 * @param matriz
	 */
	private void verificaJogada(int ID, int matriz[][]) {
		if (matriz[0][0] == 0) {
			if (matriz[0][1] == ID && matriz[0][2] == ID)
				this.rand = 0;
			if (matriz[1][1] == ID && matriz[2][2] == ID)
				this.rand = 0;
			if (matriz[1][0] == ID && matriz[2][0] == ID)
				this.rand = 0;
		}

		if (matriz[0][1] == 0) {
			if (matriz[0][0] == ID && matriz[0][2] == ID)
				this.rand = 1;
			if (matriz[1][1] == ID && matriz[2][1] == ID)
				this.rand = 1;
		}

		if (matriz[0][2] == 0) {
			if (matriz[0][0] == ID && matriz[0][1] == ID)
				this.rand = 2;
			if (matriz[2][0] == ID && matriz[1][1] == ID)
				this.rand = 2;
			if (matriz[1][2] == ID && matriz[2][2] == ID)
				this.rand = 2;
		}

		if (matriz[1][0] == 0) {
			if (matriz[0][0] == ID && matriz[2][0] == ID)
				this.rand = 3;
			if (matriz[1][1] == ID && matriz[1][2] == ID)
				this.rand = 3;
		}

		if (matriz[1][1] == 0) {
			if (matriz[0][0] == ID && matriz[2][2] == ID)
				this.rand = 4;
			if (matriz[2][0] == ID && matriz[0][2] == ID)
				this.rand = 4;
			if (matriz[0][1] == ID && matriz[2][1] == ID)
				this.rand = 4;
			if (matriz[1][0] == ID && matriz[1][2] == ID)
				this.rand = 4;
		}

		if (matriz[1][2] == 0) {
			if (matriz[0][2] == ID && matriz[2][2] == ID)
				this.rand = 5;
			if (matriz[1][0] == ID && matriz[1][2] == ID)
				this.rand = 5;
		}

		if (matriz[2][0] == 0) {
			if (matriz[0][0] == ID && matriz[1][0] == ID)
				this.rand = 6;
			if (matriz[1][1] == ID && matriz[0][2] == ID)
				this.rand = 6;
			if (matriz[2][1] == ID && matriz[2][2] == ID)
				this.rand = 6;
		}

		if (matriz[2][1] == 0) {
			if (matriz[2][0] == ID && matriz[2][2] == ID)
				this.rand = 7;
			if (matriz[0][1] == ID && matriz[1][1] == ID)
				this.rand = 7;
		}

		if (matriz[2][2] == 0) {
			if (matriz[0][0] == ID && matriz[1][1] == ID)
				this.rand = 8;
			if (matriz[2][0] == ID && matriz[2][1] == ID)
				this.rand = 8;
			if (matriz[0][2] == ID && matriz[1][2] == ID)
				this.rand = 8;
		}
	}// Fim VerificaJogada
}// Fim Class jogadorcpu