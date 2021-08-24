import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class principal extends JFrame {
	JMenuBar menuBar;
	JMenu Arquivo;
	JMenuItem miSair;
	JTable table;
	JScrollPane scrollTable;

	// VARS DO CAIXA
	JPanel pCaixa, pTitulo, pTabela, pSubTotal, pSubT2, pRecebido, pRecebT2, pTroco, pTrocoT2, pImagem, pPrecoU,
			pPrecoUT2, pQuanti, pQuantiT2, pEstoque, pEstoqueT2, pNomeP, pNomePT2;
	JLabel jlTitulo, jlTituloSub, jlValorSub, jlTituloReceb, jlTituloTroco, jlValorTroco, jlTituloPrecoU, jlValorPrecoU,
			jlTituloQuanti, jlTituloEstoque, jlValorEstoque, jlTituloNomeP, jlValorNomeP;
	JTextField tfValorReceb, tfQuantidade;

	// VARS DOS PRODUTOS
	JPanel pProdutos, pPTitulo, pPProdutos;
	JLabel jlPTitulo, imgComidaFull0, imgComidaFull1, imgComidaFull2, imgComidaFull3, imgComidaFull4, imgComidaFull5;
	ImageIcon comida, comidaFull0, comidaFull1, comidaFull2, comidaFull3, comidaFull4, comidaFull5;
	JButton Prod[] = new JButton[10];

	JButton Adicionar, Troco, Remover, Finalizar;
	double ValorSub = 0, ValorTroco = 0, ValorUnitario = 0;

	int Selecionado = 0;
	int Estoque[] = new int[7];

	String sValorEstoque;

	public principal() {
		Frame();
		Componentes();
		Eventos();
	}

	public void Componentes() {
		setLayout(null);

		// Adicionando Estoque Aleatoriamente
		for (int i = 1; i < Estoque.length; i++) {
			Estoque[0] = 0;
			Estoque[i] = (int) (Math.random() * 20)+5;
			System.out.println(Estoque[i]);

		}

		// Menus
		menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		setJMenuBar(menuBar);
		Arquivo = new JMenu("Arquivo");
		menuBar.add(Arquivo);
		miSair = new JMenuItem("Sair");
		miSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		miSair.setMnemonic('S');
		miSair.setBackground(Color.WHITE);
		Arquivo.add(miSair);

		// INICIO CAIXA

		// Painel do "Caixa"
		pCaixa = new JPanel(null);
		pCaixa.setBounds(0, 0, 900, 800);
		pCaixa.setBackground(new Color(255, 255, 255));
		add(pCaixa);

		// Painel do Titulo
		pTitulo = new JPanel(null);
		pTitulo.setBounds(10, 25, 880, 65);
		pTitulo.setBackground(new Color(248, 248, 255));
		pCaixa.add(pTitulo);

		// Titulo
		jlTitulo = new JLabel("Caixinha Apenas");
		jlTitulo.setBounds(280, 15, 275, 30);
		jlTitulo.setForeground(new Color(19, 57, 92));
		jlTitulo.setFont(new Font("Arial", Font.BOLD, 35));
		pTitulo.add(jlTitulo);

		// Painel da Tabela
		pTabela = new JPanel(new BorderLayout());
		pTabela.setBorder(BorderFactory.createTitledBorder(null, "TABELA", TitledBorder.CENTER, TitledBorder.TOP,
				new Font("Arial", Font.BOLD, 18), new Color(19, 57, 92)));
		pTabela.setBackground(new Color(248, 248, 255));
		scrollTable = new JScrollPane();

		DefaultTableModel tableModel = new DefaultTableModel(new String[] { "Produto", "Qtde", "Preço Un.", "Total" },
				0) {
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};

		table = new JTable(tableModel);
		DefaultTableCellRenderer alinhaDireita = new DefaultTableCellRenderer();
		alinhaDireita.setHorizontalAlignment(SwingConstants.RIGHT);

		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(87);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setCellRenderer(alinhaDireita);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setCellRenderer(alinhaDireita);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setCellRenderer(alinhaDireita);
		table.getTableHeader().setReorderingAllowed(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollTable.setViewportView(table);
		pTabela.add(scrollTable);
		pTabela.setBounds(450, 130, 440, 300);
		pCaixa.add(pTabela);

		// Painel SubTotal
		pSubTotal = new JPanel(null);
		pSubTotal.setBounds(450, 440, 440, 80);
		pSubTotal.setBackground(new Color(248, 248, 255));
		pCaixa.add(pSubTotal);

		// Valor do SubTotal
		String sValorSub = String.format("%.2f", ValorSub);
		jlValorSub = new JLabel("R$ " + sValorSub);
		jlValorSub.setBounds(5, 35, 440, 40);
		jlValorSub.setFont(new Font("Arial", Font.BOLD, 35));
		jlValorSub.setForeground(Color.BLACK);
		pSubTotal.add(jlValorSub);

		// Painel SubTotal Azul
		pSubT2 = new JPanel(null);
		pSubT2.setBounds(0, 0, 445, 30);
		pSubT2.setBackground(new Color(0, 63, 106));
		pSubTotal.add(pSubT2);

		// Titulo do SubTotal Azul
		jlTituloSub = new JLabel("SUBTOTAL");
		jlTituloSub.setBounds(5, 0, 100, 30);
		jlTituloSub.setFont(new Font("Arial", Font.BOLD, 15));
		jlTituloSub.setForeground(Color.white);
		pSubT2.add(jlTituloSub);

		// Painel Recebido
		pRecebido = new JPanel(null);
		pRecebido.setBounds(450, 540, 210, 80);
		pRecebido.setBackground(new Color(248, 248, 255));
		pCaixa.add(pRecebido);

		// Valor do Recebido
		tfValorReceb = new JTextField();
		tfValorReceb.setBounds(5, 35, 200, 40);
		tfValorReceb.setFont(new Font("Arial", Font.BOLD, 35));
		tfValorReceb.setForeground(Color.BLACK);
		pRecebido.add(tfValorReceb);

		// Painel Recebido Azul
		pRecebT2 = new JPanel(null);
		pRecebT2.setBounds(0, 0, 210, 30);
		pRecebT2.setBackground(new Color(0, 63, 106));
		pRecebido.add(pRecebT2);

		// Titulo do Recebido Azul
		jlTituloReceb = new JLabel("VALOR RECEBIDO");
		jlTituloReceb.setBounds(5, 0, 150, 30);
		jlTituloReceb.setFont(new Font("Arial", Font.BOLD, 15));
		jlTituloReceb.setForeground(Color.white);
		pRecebT2.add(jlTituloReceb);

		// Painel Troco
		pTroco = new JPanel(null);
		pTroco.setBounds(680, 540, 210, 80);
		pTroco.setBackground(new Color(248, 248, 255));
		pCaixa.add(pTroco);

		// Valor do Troco
		String sValorTroco = String.format("%.2f", ValorTroco);
		jlValorTroco = new JLabel("R$ " + sValorTroco);
		jlValorTroco.setBounds(5, 35, 200, 40);
		jlValorTroco.setFont(new Font("Arial", Font.BOLD, 35));
		jlValorTroco.setForeground(Color.BLACK);
		pTroco.add(jlValorTroco);

		// Painel Troco Azul
		pTrocoT2 = new JPanel(null);
		pTrocoT2.setBounds(0, 0, 210, 30);
		pTrocoT2.setBackground(new Color(0, 63, 106));
		pTroco.add(pTrocoT2);

		// Titulo do Troco Azul
		jlTituloTroco = new JLabel("TROCO");
		jlTituloTroco.setBounds(5, 0, 150, 30);
		jlTituloTroco.setFont(new Font("Arial", Font.BOLD, 15));
		jlTituloTroco.setForeground(Color.white);
		pTrocoT2.add(jlTituloTroco);

		// Painel Imagem
		pImagem = new JPanel(null);
		pImagem.setBounds(10, 130, 280, 300);
		pImagem.setBackground(new Color(248, 248, 255));
		pCaixa.add(pImagem);
		
		comidaFull0 = new ImageIcon("ComidasImg//comida0Full.png");
		imgComidaFull0 = new JLabel(comidaFull0);
		imgComidaFull0.setBounds(0, 50, 280, 177);
		imgComidaFull0.setVisible(false);
		pImagem.add(imgComidaFull0);
		
		comidaFull1 = new ImageIcon("ComidasImg//comida1Full.png");
		imgComidaFull1 = new JLabel(comidaFull1);
		imgComidaFull1.setBounds(0, 50, 280, 177);
		imgComidaFull1.setVisible(false);
		pImagem.add(imgComidaFull1);
		
		comidaFull2 = new ImageIcon("ComidasImg//comida2Full.png");
		imgComidaFull2 = new JLabel(comidaFull2);
		imgComidaFull2.setBounds(0, 50, 280, 177);
		imgComidaFull2.setVisible(false);
		pImagem.add(imgComidaFull2);
		
		comidaFull3 = new ImageIcon("ComidasImg//comida3Full.png");
		imgComidaFull3 = new JLabel(comidaFull3);
		imgComidaFull3.setBounds(0, 50, 280, 177);
		imgComidaFull3.setVisible(false);
		pImagem.add(imgComidaFull3);
		
		comidaFull4 = new ImageIcon("ComidasImg//comida4Full.png");
		imgComidaFull4 = new JLabel(comidaFull4);
		imgComidaFull4.setBounds(0, 50, 280, 177);
		imgComidaFull4.setVisible(false);
		pImagem.add(imgComidaFull4);
		
		comidaFull5 = new ImageIcon("ComidasImg//comida5Full.png");
		imgComidaFull5 = new JLabel(comidaFull5);
		imgComidaFull5.setBounds(0, 50, 280, 177);
		imgComidaFull5.setVisible(false);
		pImagem.add(imgComidaFull5);
		

		// Painel Preço Unitario
		pPrecoU = new JPanel(null);
		pPrecoU.setBounds(295, 130, 150, 80);
		pCaixa.add(pPrecoU);

		// Valor do Preço Unitario
		if (Selecionado == 0) {
			jlValorPrecoU = new JLabel("Indefinido");
			jlValorPrecoU.setBounds(5, 35, 150, 40);
			jlValorPrecoU.setFont(new Font("Arial", Font.BOLD, 30));
			jlValorPrecoU.setForeground(Color.BLACK);
			pPrecoU.add(jlValorPrecoU);
		} else {
			String sValorUnitario = String.format("%.2f", ValorUnitario);
			jlValorPrecoU = new JLabel("R$ " + sValorUnitario);
			jlValorPrecoU.setBounds(5, 35, 150, 40);
			jlValorPrecoU.setFont(new Font("Arial", Font.BOLD, 30));
			jlValorPrecoU.setForeground(Color.BLACK);
			pPrecoU.add(jlValorPrecoU);
		}

		// Painel Preço Unitario Azul
		pPrecoUT2 = new JPanel(null);
		pPrecoUT2.setBounds(0, 0, 150, 30);
		pPrecoUT2.setBackground(new Color(0, 63, 106));
		pPrecoU.add(pPrecoUT2);

		// Titulo do Preço Unitario Azul
		jlTituloPrecoU = new JLabel("VALOR KG.");
		jlTituloPrecoU.setBounds(5, 0, 150, 30);
		jlTituloPrecoU.setFont(new Font("Arial", Font.BOLD, 15));
		jlTituloPrecoU.setForeground(Color.white);
		pPrecoUT2.add(jlTituloPrecoU);

		// Painel Quantidade
		pQuanti = new JPanel(null);
		pQuanti.setBounds(295, 240, 150, 80);
		pCaixa.add(pQuanti);

		// Valor Quantidade
		tfQuantidade = new JTextField();
		tfQuantidade.setBounds(5, 35, 140, 40);
		tfQuantidade.setFont(new Font("Arial", Font.BOLD, 35));
		tfQuantidade.setForeground(Color.BLACK);
		tfQuantidade.setEnabled(false);
		pQuanti.add(tfQuantidade);

		// Painel Quantidade Azul
		pQuantiT2 = new JPanel(null);
		pQuantiT2.setBounds(0, 0, 150, 30);
		pQuantiT2.setBackground(new Color(0, 63, 106));
		pQuanti.add(pQuantiT2);

		// Titulo Quantidade Azul
		jlTituloQuanti = new JLabel("QUANTIDADE");
		jlTituloQuanti.setBounds(5, 0, 150, 30);
		jlTituloQuanti.setFont(new Font("Arial", Font.BOLD, 15));
		jlTituloQuanti.setForeground(Color.white);
		pQuantiT2.add(jlTituloQuanti);

		// Painel Estoque
		pEstoque = new JPanel(null);
		pEstoque.setBounds(295, 350, 150, 80);
		pCaixa.add(pEstoque);

		// Valor Estoque
		jlValorEstoque = new JLabel("Indefinido");
		jlValorEstoque.setBounds(5, 35, 150, 40);
		jlValorEstoque.setFont(new Font("Arial", Font.BOLD, 30));
		jlValorEstoque.setForeground(Color.BLACK);
		pEstoque.add(jlValorEstoque);

		// Painel Estoque Azul
		pEstoqueT2 = new JPanel(null);
		pEstoqueT2.setBounds(0, 0, 150, 30);
		pEstoqueT2.setBackground(new Color(0, 63, 106));
		pEstoque.add(pEstoqueT2);

		// Titulo Estoque Azul
		jlTituloEstoque = new JLabel("QUANTI. ESTOQUE");
		jlTituloEstoque.setBounds(5, 0, 150, 30);
		jlTituloEstoque.setFont(new Font("Arial", Font.BOLD, 15));
		jlTituloEstoque.setForeground(Color.white);
		pEstoqueT2.add(jlTituloEstoque);

		// Painel Nome do Produto
		pNomeP = new JPanel(null);
		pNomeP.setBounds(10, 440, 280, 80);
		pCaixa.add(pNomeP);

		// Valor Nome do Produto
		jlValorNomeP = new JLabel("Indefinido");
		jlValorNomeP.setBounds(5, 35, 280, 40);
		jlValorNomeP.setFont(new Font("Arial", Font.BOLD, 30));
		jlValorNomeP.setForeground(Color.BLACK);
		pNomeP.add(jlValorNomeP);

		// Painel Nome do Produto Azul
		pNomePT2 = new JPanel(null);
		pNomePT2.setBounds(0, 0, 280, 30);
		pNomePT2.setBackground(new Color(0, 63, 106));
		pNomeP.add(pNomePT2);

		// Titulo Nome do Produto Azul
		jlTituloNomeP = new JLabel("NOME DO PRODUTO");
		jlTituloNomeP.setBounds(5, 0, 280, 30);
		jlTituloNomeP.setFont(new Font("Arial", Font.BOLD, 15));
		jlTituloNomeP.setForeground(Color.white);
		pNomePT2.add(jlTituloNomeP);

		// FIM CAIXA

		// INICIO PRODUTOS

		// Painel dos "Produtos"
		pProdutos = new JPanel(null);
		pProdutos.setBounds(900, 0, 520, 800);
		pProdutos.setBackground(new Color(78, 119, 161));
		add(pProdutos);

		// P Painel do Titulo
		pPTitulo = new JPanel(null);
		pPTitulo.setBounds(30, 25, 400, 65);
		pPTitulo.setBackground(new Color(248, 248, 255));
		pProdutos.add(pPTitulo);

		// P Titulo
		jlPTitulo = new JLabel("PRODUTOS");
		jlPTitulo.setBounds(105, 15, 210, 30);
		jlPTitulo.setForeground(new Color(19, 57, 92));
		jlPTitulo.setFont(new Font("Arial", Font.BOLD, 35));
		pPTitulo.add(jlPTitulo);

		// P Painel com Produtos
		pPProdutos = new JPanel(new GridLayout(5, 5, 2, 2));
		pPProdutos.setBounds(30, 130, 400, 490);
		pPProdutos.setBackground(new Color(248, 248, 255));
		pProdutos.add(pPProdutos);

		// P Botões Produtos
		for (int i = 0; i < Prod.length; i++) {
			comida = new ImageIcon("ComidasImg//comida" + i + ".png");
			Prod[i] = new JButton(comida);
			Prod[i].setContentAreaFilled(false);
			Prod[i].setBorder(null);
			pPProdutos.add(Prod[i]);
		}

		Prod[0].setToolTipText("Filé");
		Prod[0].setCursor(new Cursor(Cursor.HAND_CURSOR));
		Prod[1].setToolTipText("Filé de Frango");
		Prod[1].setCursor(new Cursor(Cursor.HAND_CURSOR));
		Prod[2].setToolTipText("Asas de Frango");
		Prod[2].setCursor(new Cursor(Cursor.HAND_CURSOR));
		Prod[3].setToolTipText("Bisteca de Porco");
		Prod[3].setCursor(new Cursor(Cursor.HAND_CURSOR));
		Prod[4].setToolTipText("Picanha");
		Prod[4].setCursor(new Cursor(Cursor.HAND_CURSOR));
		Prod[5].setToolTipText("Costela");
		Prod[5].setCursor(new Cursor(Cursor.HAND_CURSOR));
		

		// BOTÕES
		Adicionar = new JButton("Adicionar");
		Adicionar.setBounds(295, 440, 150, 80);
		pCaixa.add(Adicionar);

		Troco = new JButton("Troco");
		Troco.setBounds(160, 540, 130, 80);
		pCaixa.add(Troco);
		
		Remover = new JButton("Remover");
		Remover.setBounds(295, 540, 150, 80);
		pCaixa.add(Remover);

		Finalizar = new JButton("Finalizar");
		Finalizar.setBounds(10, 540, 130, 80);
		pCaixa.add(Finalizar);

	}

	public void Eventos() {
		miSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		// Adiciona item na tabela
		Adicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jlValorNomeP.getText().equals("Indefinido") || jlValorEstoque.getText().equals("Indefinido") || jlValorPrecoU.getText().equals("Indefinido") || tfQuantidade.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Selecione algum item");
				}else {
					for (int i = 0; i < Estoque.length ; i++) {
						if(Selecionado == i) {
							if(Estoque[i] == 0) {
								JOptionPane.showMessageDialog(null, "O Estoque esta zerado");
								return;
							}else if(Integer.parseInt(tfQuantidade.getText()) > Estoque[i]) {
								JOptionPane.showMessageDialog(null, "A Quantidade ultrapassa o estoque");
								return;
							}else {
								Estoque[i] = Estoque[i] - Integer.parseInt(tfQuantidade.getText());
								jlValorEstoque.setText(""+Estoque[i]);
							}
						}
					}
					DefaultTableModel dtm = (DefaultTableModel) table.getModel();
					dtm.addRow(new Object[] {jlValorNomeP.getText(), tfQuantidade.getText(), ValorUnitario,""+ Integer.parseInt
						(tfQuantidade.getText()) * ValorUnitario});

					calcularTotal() ;
				}
			}
		});
		
		// Remove item da tabela
		Remover.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int[] linhas = table.getSelectedRows();
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				for(int i = (linhas.length -1 ); i>=0; --i){ 
					
					if(table.getValueAt(linhas[i],2).equals(40.00)) {
						Estoque[1] += Double.parseDouble((String) table.getValueAt(linhas[i],1));
					}else if(table.getValueAt(linhas[i],2).equals(18.50)) {
						Estoque[2] += Double.parseDouble((String) table.getValueAt(linhas[i],1));
					}else if(table.getValueAt(linhas[i],2).equals(13.10)) {
						Estoque[3] += Double.parseDouble((String) table.getValueAt(linhas[i],1));
					}else if(table.getValueAt(linhas[i],2).equals(21.99)) {
						Estoque[4] += Double.parseDouble((String) table.getValueAt(linhas[i],1));
					}else if(table.getValueAt(linhas[i],2).equals(48.50)) {
						Estoque[5] += Double.parseDouble((String) table.getValueAt(linhas[i],1));
					}else if(table.getValueAt(linhas[i],2).equals(31.90)) {
						Estoque[6] += Double.parseDouble((String) table.getValueAt(linhas[i],1));
					}
					
					dtm.removeRow(linhas[i]);
				}
				calcularTotal();
			}
		});
		
		// Calcula o Troco
		Troco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfValorReceb.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha o Valor Recebido");
					return;
				}
				String recebido = ""+ tfValorReceb.getText();
				recebido = recebido.replace(",", ".");
				double ValorRecebido = Double.parseDouble(recebido);
				
				if (ValorRecebido < ValorSub) {
					double ValorFalta = ValorSub - ValorRecebido;
					JOptionPane.showMessageDialog(null, "Falta: R$ "+ValorFalta);
					return;
				}else {
					ValorTroco = ValorRecebido - ValorSub;
					String sValorTroco = String.format("%.2f", ValorTroco);
					jlValorTroco.setText("R$ "+sValorTroco);
					System.out.println(ValorTroco);
				}
			}
		});
		
		// Finaliza a Compra
		Finalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getRowCount() != 0) {
					if(tfValorReceb.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Adicione o Valor Recebido");
					}else {
						String Produtos = "";
						for (int linha = 0; linha < table.getRowCount(); linha++) {
							Produtos = Produtos +", "+ table.getValueAt(linha, 0);
						}
						if(tfValorReceb.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Preencha o Valor Recebido");
							return;
						}
						String recebido = ""+ tfValorReceb.getText();
						recebido = recebido.replace(",", ".");
						double ValorRecebido = Double.parseDouble(recebido);
						
						if (ValorRecebido < ValorSub) {
							double ValorFalta = ValorSub - ValorRecebido;
							JOptionPane.showMessageDialog(null, "Falta: R$ "+ValorFalta);
							return;
						}else {
							ValorTroco = ValorRecebido - ValorSub;
							String sValorTroco = String.format("%.2f", ValorTroco);
							jlValorTroco.setText("R$ "+sValorTroco);
							System.out.println(ValorTroco);
						}
						JOptionPane.showMessageDialog(null, "<html> Produtos Vendidos:"+Produtos+" <br> Valor Total: "+ValorSub+" <br> Valor Recebido: R$ "+tfValorReceb.getText()+" <br> Troco: R$"+ValorTroco+"</html>");	
					}
				}else {
					JOptionPane.showMessageDialog(null, "Adicione algo na Tabela");
				}
			}
		});
		
		// Seleciona a Comida 0
		Prod[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfQuantidade.setText("");
				jlValorNomeP.setText("Filé");
				Selecionado = 1;
				for (int i = 0; i < Estoque.length ; i++) {
					if(Selecionado == i) {
						jlValorEstoque.setText("" + Estoque[i]);
						System.out.println("Selecionado: "+i);
						System.out.println("Estoque: "+Estoque[i]);
					}
				}
				ValorUnitario = 40.00;
				tfQuantidade.setEnabled(true);
				jlValorPrecoU.setText("R$ "+ ValorUnitario);
				
				imgComidaFull5.setVisible(false);
				imgComidaFull4.setVisible(false);
				imgComidaFull3.setVisible(false);
				imgComidaFull2.setVisible(false);
				imgComidaFull1.setVisible(false);
				imgComidaFull0.setVisible(true);
			}
		});
		
		// Seleciona a Comida 1
		Prod[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfQuantidade.setText("");
				jlValorNomeP.setText("Filé de Frango");
				Selecionado = 2;
				for (int i = 0; i < Estoque.length ; i++) {
					if(Selecionado == i) {
						jlValorEstoque.setText("" + Estoque[i]);
						System.out.println("Selecionado: "+i);
						System.out.println("Estoque: "+Estoque[i]);
					}
				}
				ValorUnitario = 18.50;
				tfQuantidade.setEnabled(true);
				jlValorPrecoU.setText("R$ "+ ValorUnitario);
				
				imgComidaFull5.setVisible(false);
				imgComidaFull4.setVisible(false);
				imgComidaFull3.setVisible(false);
				imgComidaFull2.setVisible(false);
				imgComidaFull0.setVisible(false);
				imgComidaFull1.setVisible(true);
			}
		});
		
		// Seleciona a Comida 2
		Prod[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfQuantidade.setText("");
				jlValorNomeP.setText("Asa de Frango");
				Selecionado = 3;
				for (int i = 0; i < Estoque.length ; i++) {
					if(Selecionado == i) {
						jlValorEstoque.setText("" + Estoque[i]);
						System.out.println("Selecionado: "+i);
						System.out.println("Estoque: "+Estoque[i]);
					}
				}
				ValorUnitario = 13.10;
				tfQuantidade.setEnabled(true);
				jlValorPrecoU.setText("R$ "+ ValorUnitario);
				
				imgComidaFull5.setVisible(false);
				imgComidaFull4.setVisible(false);
				imgComidaFull3.setVisible(false);
				imgComidaFull0.setVisible(false);
				imgComidaFull1.setVisible(false);
				imgComidaFull2.setVisible(true);
			}
		});
		
		// Seleciona a Comida 3
		Prod[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfQuantidade.setText("");
				jlValorNomeP.setText("Bisteca");
				Selecionado = 4;
				for (int i = 0; i < Estoque.length ; i++) {
					if(Selecionado == i) {
						jlValorEstoque.setText("" + Estoque[i]);
						System.out.println("Selecionado: "+i);
						System.out.println("Estoque: "+Estoque[i]);
					}
				}
				ValorUnitario = 21.99;
				tfQuantidade.setEnabled(true);
				jlValorPrecoU.setText("R$ "+ ValorUnitario);

				imgComidaFull5.setVisible(false);
				imgComidaFull4.setVisible(false);
				imgComidaFull2.setVisible(false);
				imgComidaFull1.setVisible(false);
				imgComidaFull0.setVisible(false);
				imgComidaFull3.setVisible(true);
			}
		});
		
		// Seleciona a Comida 4
		Prod[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfQuantidade.setText("");
				jlValorNomeP.setText("Picanha");
				Selecionado = 5;
				for (int i = 0; i < Estoque.length ; i++) {
					if(Selecionado == i) {
						jlValorEstoque.setText("" + Estoque[i]);
						System.out.println("Selecionado: "+i);
						System.out.println("Estoque: "+Estoque[i]);
					}
				}
				ValorUnitario = 48.50;
				tfQuantidade.setEnabled(true);
				jlValorPrecoU.setText("R$ "+ ValorUnitario);
				
				imgComidaFull5.setVisible(false);
				imgComidaFull3.setVisible(false);
				imgComidaFull2.setVisible(false);
				imgComidaFull1.setVisible(false);
				imgComidaFull0.setVisible(false);
				imgComidaFull4.setVisible(true);
			}
		});
		
		// Seleciona a Comida 5
		Prod[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfQuantidade.setText("");
				jlValorNomeP.setText("Costela");
				Selecionado = 6;
				for (int i = 0; i < Estoque.length ; i++) {
					if(Selecionado == i) {
						jlValorEstoque.setText("" + Estoque[i]);
						System.out.println("Selecionado: "+i);
						System.out.println("Estoque: "+Estoque[i]);
					}
				}
				ValorUnitario = 31.90;
				tfQuantidade.setEnabled(true);
				jlValorPrecoU.setText("R$ "+ ValorUnitario);
				
				
				imgComidaFull4.setVisible(false);
				imgComidaFull3.setVisible(false);
				imgComidaFull2.setVisible(false);
				imgComidaFull1.setVisible(false);
				imgComidaFull0.setVisible(false);
				imgComidaFull5.setVisible(true);
			}
		});
	}

	// Metodo para calcular o Total
	private void calcularTotal() {
		ValorSub = 0;
		for (int linha = 0; linha < table.getRowCount(); linha++) {
			String valor = " " + table.getValueAt(linha, 3);
			valor = valor.replace(",", ".");
			ValorSub = ValorSub + Double.parseDouble(valor);
		}
		System.out.println(ValorSub);
		String sValorSub = String.format("%.2f", ValorSub);
		jlValorSub.setText("R$ " + sValorSub.replace(".", ","));
	}

	public void Frame() {
		setSize(1380, 700);
		setTitle("Compras");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(false);
	}

	public static void main(String args[]) {
		new principal();

	}
}
