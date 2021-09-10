import java.awt.BorderLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Interface implements ActionListener {
	JTextField path;
	JFrame window;
	JTextArea textArea;
	JScrollPane scroll;
	JMenuBar menuBar;
	JMenu menuFile;
	JMenuItem New, Open, Save, SaveAs;
	JFrame frame;
	JPanel statusPanel;
	JPanel contentPanel;
	JTextField textField;
	JLabel stateBarLabel;
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
		createContentPanel();
		textField = new JTextField();
		textField.setEditable(false);
		scroll.setColumnHeaderView(textField);
		
		
	}
	
	public void createWindow() {//Para criacao da janela
		window = new JFrame("TEditor");
		window.setSize(800, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void createContentPanel() {
		contentPanel = new JPanel();
		contentPanel.setBackground(SystemColor.desktop);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BorderLayout(0, 0));
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPanel.add(tabbedPane, BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Aba 1", null, scrollPane, null);
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
		BorderLayout stateBar = new BorderLayout();
		stateBarLabel = new JLabel("");
		window.add(stateBarLabel,stateBar.SOUTH);
		
		
	}
	
	
	
	public void fileMenuItens() {//Para a criação dos submenus
		New = new JMenuItem("New");
		New.addActionListener(this);
		New.setActionCommand("New");
		
		Open = new JMenuItem("Open");
		Open.addActionListener(this);
		Open.setActionCommand("Open");
		
		Save = new JMenuItem("Save");
		Save.addActionListener(this);
		Save.setActionCommand("Save");
		
		SaveAs = new JMenuItem("Save as");
		SaveAs.addActionListener(this);
		SaveAs.setActionCommand("Save as");
		
		menuFile.add(New);
		menuFile.add(Open);
		menuFile.add(Save);
		menuFile.add(SaveAs);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		switch(command) {
			case "New": 
				file.newFile(); 
				break;
				
			case "Open": 
				file.open(); 
				break;
				
			case "Save as": 
				file.saveAs(); 
				break;
				
			case "Save": 
				file.save(); 
				break;
		}
	}

}
