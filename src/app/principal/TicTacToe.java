package app.principal;

/*
 * Aplicativo Jogo da Velha atualização do trabalho da faculdade
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import app.tabuleiro.*;
import app.sobre.*;

/**
 * Classe Gráfica do Jogo da Velha
 * 
 * @author Juliano Dorigão
 */
@SuppressWarnings("serial")
public class TicTacToe extends JFrame {

	// Botões do Tabuleiro
	private JButton DS2 = new JButton(), CS1 = new JButton(), ES0 = new JButton(); // Botões da linha Superior																				 
	private JButton DC5 = new JButton(), CC4 = new JButton(), EC3 = new JButton(); // Botões da linha do Meio
	private JButton DI8 = new JButton(), CI7 = new JButton(), EI6 = new JButton(); // Botões da Linha Inferiro

	// Informações na tela como nome e status de jogada
	private JLabel Ljogador1 = new JLabel("Jogador 1"), Ljogador2 = new JLabel("Jogador 2"), Lempate = new JLabel("Empate");
	private JLabel Pjogador1 = new JLabel("", SwingConstants.CENTER), Pjogador2 = new JLabel("", SwingConstants.CENTER), Pempate = new JLabel("", SwingConstants.CENTER);
	private JMenuItem iniciar, novo, encerrar, sair;
	
	// Caracter que indica o jogador
	private String XO = "X";
	private int VerificaVencedor = 0;// Verifica a cada jogada se houve vencedor ou empate
	private int TFonte = 52; // Tamanho da fonte dos botões (X e O)

	/**
	 * Declaração de Objeto
	 */
	Tabuleiro Partida = new Tabuleiro();
	JogadorCpu Cpu = new JogadorCpu();
	Sobre MenuSobre = new Sobre();
	

	/**
	 * Classe construtura - JFrame
	 */
	public TicTacToe() {
		super("..:: Tic Tac Toe ::..");// Título da Janela

		this.getContentPane();
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setLayout(null);

		// Chama barra de Ferramenta
		barradeferramenta();

		// Chama placar do Jogo
		placardojogo(Partida.getJogador1(), Partida.getEmpate(), Partida.getJogador2());

		// Tabuleiro Gráfico do Jogo
		for (int i = 0; i <= 8; i++)
			tabuleirodojogo(i, false, null);

		// Trata o evento dos botões do tabuleiro
		eventosbotoes();

	}// Fim jogodavelha

	/**
	 * Barra de ferramenta da aplicação
	 */
	public void barradeferramenta() {

		/*------------BARRA DE FERRAMENTA------------*/
		JMenuBar barra = new JMenuBar();
		setJMenuBar(barra);
		

		// ------------Menu Jogar------------------
		JMenu jogar = new JMenu("Jogar");
		barra.add(jogar);
		jogar.setMnemonic('J');

		// Itens do menu Arquivo
		iniciar = new JMenuItem("Iniciar");
		jogar.add(iniciar);

		novo = new JMenuItem("Novo");
		jogar.add(novo);
		novo.setEnabled(false);

		encerrar = new JMenuItem("Encerrar");
		jogar.add(encerrar);
		jogar.addSeparator();
		encerrar.setEnabled(false);

		sair = new JMenuItem("Sair");
		jogar.add(sair);

		// -------------Menu Sobre-------------------
		JMenu sobre = new JMenu("Sobre");
		barra.add(sobre);
		sobre.setMnemonic('S');

		JMenuItem ajuda = new JMenuItem("Ajuda");
		sobre.add(ajuda);

		JMenuItem versao = new JMenuItem("versao");
		sobre.add(versao);

		/**
		 * Tratando os botões da barra de ferramentas
		 */
		iniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// Habilita os botões do tabuleiro
				for (int i = 0; i <= 8; i++)
					tabuleirodojogo(i, true, null);

				Partida.iniciaTabuleiro(); // Inicia a Matriz do tabuleiro
				Cpu.iniciaCpu();
				if ((int) (Math.random() * 2) == 1)
					JogadadaCPU(); // Jogada jogador2				
				iniciar.setEnabled(false);
				novo.setEnabled(false);
				encerrar.setEnabled(true);
			}
		});

		novo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// Habilita os botões do tabuleiro
				for (int i = 0; i <= 8; i++)
					tabuleirodojogo(i, true, null);

				Partida.iniciaTabuleiro(); // Inicia a Matriz do tabuleiro
				Partida.setTabuleiro(2, Cpu.jogadaCpu(Partida.getTabuleiro())); // Inicializa CPU para o jogo
			}
		});


		encerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// Habilita menu inicar
				iniciar.setEnabled(true);
				// Desabilita os botões do tabuleiro
				for (int i = 0; i <= 8; i++)
					tabuleirodojogo(i, false, null);

				Partida.encerraPartida();
				Cpu.iniciaCpu();
				placardojogo(0, 0, 0);
			}
		});

		// Botão sair
		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int escolha = JOptionPane.showConfirmDialog(null,
						"Deseja sair do Jogo?", "Tic Tac Toe",
						JOptionPane.YES_NO_OPTION);

				if (escolha == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

		// Botão ajuda
		ajuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(null, MenuSobre.ajuda(),
						"Tic Tac Toe - Ajuda", JOptionPane.QUESTION_MESSAGE);
			}
		});

		// Botão versão
		versao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(null, MenuSobre.versao(),
						"Tic Tac Toe", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		// Trata o evento de Fechar a janela
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				sair.doClick();
			}
		});
	}// Fim barradeferramenta

	/**
	 * Apresenta o nome e pontuação dos jogadores e também o status da partida
	 */
	public void placardojogo(int pontosJogador1, int pontosEmpate,int pontosJogador2) {
		/**
		 * Apresenta a pontuação
		 */
		//System.out.printf("%d %d %d \n", pontosJogador1, pontosEmpate,pontosJogador2);

		// Vitória
		this.Ljogador1.setFont(new Font("Serif", Font.BOLD, 12));
		this.Ljogador1.setForeground(Color.GRAY);
		this.Ljogador1.setBounds(30, 40, 75, 30);
		this.add(this.Ljogador1);

		this.Pjogador1.setText(pontosJogador1 + "");
		this.Pjogador1.setFont(new Font("Serif", Font.BOLD, 20));
		this.Pjogador1.setForeground(Color.GRAY);
		this.Pjogador1.setBounds(25, 20, 75, 20);
		this.add(this.Pjogador1);

		// Empate
		this.Lempate.setFont(new Font("Serif", Font.BOLD, 12));
		this.Lempate.setForeground(Color.GRAY);
		this.Lempate.setBounds(115, 40, 75, 30);
		this.add(this.Lempate);

		this.Pempate.setText(pontosEmpate + "");
		this.Pempate.setFont(new Font("Serif", Font.BOLD, 20));
		this.Pempate.setForeground(Color.GRAY);
		this.Pempate.setBounds(105, 20, 75, 20);
		this.add(this.Pempate);

		// Derrota
		this.Ljogador2.setFont(new Font("Serif", Font.BOLD, 12));
		this.Ljogador2.setForeground(Color.GRAY);
		this.Ljogador2.setBounds(195, 40, 75, 30);
		this.add(this.Ljogador2);

		this.Pjogador2.setText(pontosJogador2 + "");
		this.Pjogador2.setFont(new Font("Serif", Font.BOLD, 20));
		this.Pjogador2.setForeground(Color.GRAY);
		this.Pjogador2.setBounds(190, 20, 75, 20);
		this.add(this.Pjogador2);
	
	}

	/**
	 * Botões que representa o tabuleiro do jogo ativando e desativando
	 */
	@SuppressWarnings("deprecation")
	public void tabuleirodojogo(int id, boolean status, String letrajogador) {
		switch (id) {
		case 0:
			// Esquerdo Superior
			ES0.setBounds(20, 90, 80, 80);
			this.add(ES0);
			ES0.setBackground(Color.GRAY);
			ES0.setLabel(letrajogador);
			ES0.setFont(new Font("Serif", Font.BOLD, TFonte));
			ES0.setEnabled(status);
			break;
		
		case 1:
			// Central Superior
			CS1.setBounds(105, 90, 80, 80);
			this.add(CS1);
			CS1.setBackground(Color.GRAY);
			CS1.setLabel(letrajogador);
			CS1.setFont(new Font("Serif", Font.BOLD, TFonte));
			CS1.setEnabled(status);
			break;
			
		case 2:
			// Direito Superior
			DS2.setBounds(190, 90, 80, 80);
			this.add(DS2);
			DS2.setBackground(Color.GRAY);
			DS2.setLabel(letrajogador);
			DS2.setFont(new Font("Serif", Font.BOLD, TFonte));
			DS2.setEnabled(status);
			break;

		case 3:
			// Esquerdo Central
			EC3.setBounds(20, 175, 80, 80);
			this.add(EC3);
			EC3.setBackground(Color.GRAY);
			EC3.setLabel(letrajogador);
			EC3.setFont(new Font("Serif", Font.BOLD, TFonte));
			EC3.setEnabled(status);
			break;
			
		case 4:
			// Central Central
			CC4.setBounds(105, 175, 80, 80);
			this.add(CC4);
			CC4.setBackground(Color.GRAY);
			CC4.setLabel(letrajogador);
			CC4.setFont(new Font("Serif", Font.BOLD, TFonte));
			CC4.setEnabled(status);
			break;
			
		case 5:
			// Direito Central
			DC5.setBounds(190, 175, 80, 80);
			this.add(DC5);
			DC5.setBackground(Color.GRAY);
			DC5.setLabel(letrajogador);
			DC5.setFont(new Font("Serif", Font.BOLD, TFonte));
			DC5.setEnabled(status);
			break;

		case 6:
			// Esquerdo Inferior
			EI6.setBounds(20, 260, 80, 80);
			this.add(EI6);
			EI6.setBackground(Color.GRAY);
			EI6.setLabel(letrajogador);
			EI6.setFont(new Font("Serif", Font.BOLD, TFonte));
			EI6.setEnabled(status);
			break;

		case 7:
			// Central Inferior
			CI7.setBounds(105, 260, 80, 80);
			this.add(CI7);
			CI7.setBackground(Color.GRAY);
			CI7.setLabel(letrajogador);
			CI7.setFont(new Font("Serif", Font.BOLD, TFonte));
			CI7.setEnabled(status);
			break;

		case 8:
			// Direito Inferior
			DI8.setBounds(190, 260, 80, 80);
			this.add(DI8);
			DI8.setBackground(Color.GRAY);
			DI8.setLabel(letrajogador);
			DI8.setFont(new Font("Serif", Font.BOLD, TFonte));
			DI8.setEnabled(status);
			break;
		}// Fim case
	}

	/**
	 * Trata o evento dos botões do tabuleiro
	 */
	public void eventosbotoes() {
		// Botão esquerda superior
		ES0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//System.out.println("0");
				EventoBotoes(1, 0);
			}
		});

		// Botão centro superior
		CS1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//System.out.println("1");
				EventoBotoes(1, 1);
			}
		});
		
		// Botão direita superior
		DS2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//System.out.println("2");
				EventoBotoes(1, 2);
			}
		});		
		
		// Botão esquerda central
		EC3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//System.out.println("3");
				EventoBotoes(1, 3);
			}
		});
		
		// Botão centro centro
		CC4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//System.out.println("4");
				EventoBotoes(1, 4);
			}
		});
		
		// Botão direita centro
		DC5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//System.out.println("5");
				EventoBotoes(1, 5);
			}
		});
		
		// Botão esquerda inferior
		EI6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//System.out.println("6");
				EventoBotoes(1, 6);
			}
		});

		// Botão centro inferior
		CI7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//System.out.println("7");
				EventoBotoes(1, 7);
			}
		});

		// Botão direita inferior
		DI8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//System.out.println("8");
				EventoBotoes(1, 8);
			}
		});
	}
	
	/**
	 * Faz a jogada da CPU
	 */
	private void JogadadaCPU() {
		XO = "O";// Muda o identificado do jogador
		int aux = Cpu.jogadaCpu(Partida.getTabuleiro());	// jogada da CPU
		Partida.setTabuleiro(2, aux);
		VerificaVencedor = Partida.verificaVencedor();
				
		tabuleirodojogo(aux, false, XO);// Disabilita o botão e insere a letra
										// do jogador
		XO = "X";

		if (VerificaVencedor != 0) {
			ContinuaPartida(VerificaVencedor);
		}
	}

	

	/**
	 * Ao clicar nos botões do tabuleiro essa classe é chama realizando os
	 * procedimento necessário.
	 * 
	 * @param jogador
	 * @param jogada
	 */
	private void EventoBotoes(int jogador, int jogada) {
		
		Partida.setTabuleiro(jogador, jogada);		
		VerificaVencedor = Partida.verificaVencedor();
				

		// Disabilita o botão e insere a letra do jogador
		tabuleirodojogo(jogada, false, "X"); //Jogador 1

		if (VerificaVencedor == 0 && jogador == 1) {
			JogadadaCPU();			
			
		} else {

			ContinuaPartida(VerificaVencedor);
		}

	}

	private void ContinuaPartida(int vencedor) {
		int partida = 0;
	
		placardojogo(Partida.getJogador1(), Partida.getEmpate(), Partida.getJogador2());
		

		if (vencedor == 1)
			partida = JOptionPane.showConfirmDialog(null,
					"Você venceu...!\n\n", "Deseja continuar?",
					JOptionPane.YES_NO_OPTION);

		if (vencedor == 2)
			partida = JOptionPane.showConfirmDialog(null,
					"Você perdeu...!\n\n", "Deseja continuar?",
					JOptionPane.YES_NO_OPTION);

		if (vencedor == 3)
			partida = JOptionPane.showConfirmDialog(null, "Empate...!\n\n",
					"Deseja continuar?", JOptionPane.YES_NO_OPTION);

		if (partida == JOptionPane.YES_OPTION) {
			Cpu.iniciaCpu();
			iniciar.setEnabled(true);
			iniciar.doClick();
		} else {
			encerrar.doClick();
		}
	}
}// Fim da Classe janeladojogo