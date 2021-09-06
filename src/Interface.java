import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Interface implements ActionListener {
	
	JFrame window;
	JTextArea textArea;
	JScrollPane scroll;
	JMenuBar menuBar;
	JMenu menuFile;
	JMenuItem New, Open, Save;
	JFrame frame;
	JPanel statusPanel;
	MenuFunctions file = new MenuFunctions(this);//funcao para o menu "New"
	
	public static void main(String[] args) {
		new Interface();

	}
	public Interface() {//Nesse metodo, chamarei os metodos construtores dos elementos do TEditor
		createWindow();
		createTextArea();
		window.setVisible(true);
		createMenuBar();
		fileMenuItens();
		createStateBar();
		
	}
	
	public void createWindow() {//Para criacao da janela
		window = new JFrame("TEditor");
		window.setSize(800, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void createTextArea() {//Para criacao da  area de texto
		textArea = new JTextArea();
		scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		window.add(scroll);
	}
	
	public void createMenuBar() {//Para criacao da barra de menus e a adicao dos menus
		menuBar = new JMenuBar();
		window.setJMenuBar(menuBar);
		
		menuFile = new JMenu("File");
		menuBar.add(menuFile);
	}
	
	public void createStateBar() {//barra na borda inferior que mostra o path do arquivo aberto
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setSize(200,200);
		
		statusPanel = new JPanel();
		
	}
	
	public void fileMenuItens() {//Para a criação dos submenus
		New = new JMenuItem("New");
		New.addActionListener(this);
		New.setActionCommand("New");
		
		Open = new JMenuItem("Open");
		Open.addActionListener(this);
		Open.setActionCommand("Open");
		
		Save = new JMenuItem("Save");
		
		menuFile.add(New);
		menuFile.add(Open);
		menuFile.add(Save);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		switch(command) {
			case "New": file.newFile(); break;
			case "Open": file.open(); break;
		}
	}

}
