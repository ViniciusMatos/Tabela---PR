import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class loading extends JFrame{
	ImageIcon imgLoading;
	JLabel jlLoading;
	JProgressBar loadingBar;
	JPanel loading;
	
	int porcem = 0;
	
	public loading() {
		Componentes();
	}
	
	public void Componentes(){
		setLayout(null);
		// Criando Painel pra tela
		loading = new JPanel(null);
		loading.setBackground(new Color(245,245,245));
		loading.setSize(1500,900);
		add(loading);
		
		// Gif do Loading
		imgLoading = new ImageIcon("LoadingImg//Carregamento.gif");
		jlLoading = new JLabel(imgLoading);
		jlLoading.setBounds(280, 120, 800, 300);
		loading.add(jlLoading);
		
		// Progress Bar
		loadingBar = new JProgressBar();
		loadingBar.setBounds(280, 420, 800, 25);
		loadingBar.setMaximum(100);
		loadingBar.setStringPainted(true);
		loadingBar.setForeground(new Color(1,63,108));
		Loading();
		add(loadingBar);
		

	}
	
	// Efeito de loading
	public void Loading(){
		new Thread() {
			public void run() {
				try {
					sleep(2000);
				}catch (InterruptedException ex){
					System.out.println(ex.getMessage());
				}
				for(int i = 0; i <= 100; i++) {
					loadingBar.setValue(i);
					loadingBar.setString(porcem + "%");
					
					try {
						sleep(20);
					}catch (InterruptedException ex){
						System.out.println(ex.getMessage());
					}
					
					porcem++;
					
					if (loadingBar.getValue() == 100) {
						try {
							sleep(1000);
						}catch (InterruptedException ex){
							System.out.println(ex.getMessage());
						}
						
						new principal().setVisible(true);
						setVisible(false);
					}
				}
				
			}
		}.start();
		

	}
	
	public static void main (String args[]) {
		loading frame = new loading();
		frame.setTitle("Loading");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1380, 700);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
}
