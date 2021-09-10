package version2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import javax.swing.JScrollBar;
import java.awt.Window.Type;
import javax.swing.JLabel;

public class Teditor extends JFrame {

	private JPanel container;
	private JTextField textField1;
	private JTextField textField2;
	private JTextField currentLineValue;
	private String filePath1;
	private String filePath2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Teditor frame = new Teditor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Teditor() {
		setTitle("TEditor - v1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 761, 440);
		container = new JPanel();
		container.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(container);
		container.setLayout(null);
		
		JTabbedPane tabsArea = new JTabbedPane(JTabbedPane.TOP);
		tabsArea.setBounds(139, 42, 555, 313);
		container.add(tabsArea);
		
// ########################## TAB 1 ###############################	
		JScrollPane tab1 = new JScrollPane();
		tabsArea.addTab("Tab 1", null, tab1, null);
		
		JTextArea textArea1 = new JTextArea();
		tab1.setViewportView(textArea1);
		
		textField1 = new JTextField();
		textField1.setText("Path: empty");
		textField1.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		textField1.setBackground(new Color(255, 255, 204));
		textField1.setEditable(false);
		tab1.setColumnHeaderView(textField1);
		textField1.setColumns(10);
		
//########################### TAB 2 ###############################		
		JScrollPane tab2 = new JScrollPane();
		tabsArea.addTab("Tab 2", null, tab2, null);
		
		JTextArea textArea2 = new JTextArea();
		tab2.setViewportView(textArea2);
		
		textField2 = new JTextField();
		textField2.setEditable(false);
		textField2.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		textField2.setBackground(new Color(255, 255, 204));
		textField2.setText("Path: empty");
		tab2.setColumnHeaderView(textField2);
		textField2.setColumns(10);
		
		
//################## Bar that shows current line ##################		
		
		JLabel currentLineValue = new JLabel("New label");
		currentLineValue.setHorizontalAlignment(SwingConstants.RIGHT);
		currentLineValue.setBounds(579, 359, 115, 14);
		container.add(currentLineValue);
		
		//currentLineValue = new JTextField();
		currentLineValue.setText("Current line:");
		//currentLineValue.setEditable(false);
		//toolBar.add(currentLineValue);
		//currentLineValue.setColumns(10);
		
		
		
		JTree tree = new JTree();
		tree.setBounds(10, 66, 115, 289);
		container.add(tree);
		
		
//########################### MENU ################################		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		menuBar.setBounds(0, 0, 745, 35);
		container.add(menuBar);
		
		
		
//######################## NEW FILE METHOD ########################		
		JMenuItem itemNew = new JMenuItem("New");
		itemNew.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		itemNew.setForeground(new Color(0, 255, 102));
		itemNew.setBackground(Color.LIGHT_GRAY);
		itemNew.setHorizontalAlignment(SwingConstants.CENTER);
		
		itemNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int tabIndex = tabsArea.getSelectedIndex();
			    System.out.println(tabIndex);
			    if(tabIndex == 0) {
			    	textArea1.setText("");
			    	filePath1 = null;
			    	textField1.setText("Path: empty");
			    	
			    }
			    else if (tabIndex == 1) {
			    	textArea2.setText("");
			    	filePath2 = null;
			    	textField2.setText("Path: empty");
			    }
			}
		});
		menuBar.add(itemNew);

//######################## OPEN FILE METHOD ########################	
		JMenuItem itemOpen = new JMenuItem("Open");
		itemOpen.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		itemOpen.setBackground(Color.LIGHT_GRAY);
		itemOpen.setForeground(new Color(0, 0, 255));
		itemOpen.setHorizontalAlignment(SwingConstants.CENTER);
		
		itemOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new File("."));
	            fc.setFileFilter(new FileNameExtensionFilter("Text files in .txt", "txt"));
				int answer = fc.showOpenDialog(null);
				File f = fc.getSelectedFile();
				String pathfile = f.getAbsolutePath();
				if(answer == JFileChooser.APPROVE_OPTION) {
					try {
						int tabIndex = tabsArea.getSelectedIndex();
						
						if(tabIndex == 0) {
							filePath1 = pathfile;
							System.out.println("Log - file opened (tab1): " + filePath1);
							textField1.setText(pathfile);
							FileReader reader = new FileReader(pathfile);
			                BufferedReader br = new BufferedReader(reader);
			                textArea1.read(br,null);
			                br.close();
			                System.out.println("Log - file closed (tab1): " + filePath1);
			                textArea1.requestFocus();
						} 
						else if(tabIndex == 1) {
							filePath2 = pathfile;
							System.out.println("Log - file opened (tab2): " + filePath1);
							textField2.setText(pathfile);
							FileReader reader = new FileReader(pathfile);
			                BufferedReader br = new BufferedReader(reader);
			                textArea2.read(br,null);
			                br.close();
			                System.out.println("Log - file closed (tab2): " + filePath1);
			                textArea2.requestFocus();
						}
					} catch(Exception exception) {
		                JOptionPane.showMessageDialog(null,e);
		            }        
				}
		}
		});    
		menuBar.add(itemOpen);
		
//####################### SAVE METHOD ###########################
		
		JMenuItem itemSave = new JMenuItem("Save");
		itemSave.setHorizontalAlignment(SwingConstants.CENTER);
		itemSave.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		itemSave.setBackground(Color.LIGHT_GRAY);
		itemSave.setForeground(new Color(255, 0, 0));
		
		itemSave.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        	String text1 = textArea1.getText();
		        	System.out.println("aqui: " + text1);
		        	String text2 = textArea2.getText();
		        	try {
		        		int tabIndex = tabsArea.getSelectedIndex();
		        		if(tabIndex == 0) {
		        			if (filePath1 != null) {
			        			System.out.println("aqui: " + filePath1);
				        		FileWriter fw = new FileWriter(filePath1);
				        		fw.write(text1);
				        		fw.flush();
				        		infoMessage("Success on save!", "Alright!");
				        		fw.close();
		        			}
		        			else {
		        				infoMessage("You need to click in 'Save as' first! ", "ERROR!");
		        			}
		        		}
		        		if(tabIndex == 1) {
		        			if (filePath1 != null) {
			        			FileWriter fw = new FileWriter(filePath2);
				        		fw.write(text2);
				        		fw.flush();
				        		infoMessage("Success on save!", "Alright!");
				        		fw.close();
		        			}
		        			else {
		        				infoMessage("You need to click in 'Save as' first! ", "ERROR!");
		        			}
		        		}
			        } catch(Exception exception) {
			            JOptionPane.showMessageDialog(null,e);
			        } 
		        }
		});
		menuBar.add(itemSave);
		
		
		
//######################### SAVE AS METHOD ############################		
		JMenuItem itemSaveAs = new JMenuItem("Save as");
		itemSaveAs.setHorizontalAlignment(SwingConstants.CENTER);
		itemSaveAs.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		itemSaveAs.setBackground(Color.LIGHT_GRAY);
		itemSaveAs.setForeground(Color.MAGENTA);
		
		itemSaveAs.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		    	JFileChooser fcSaveAs = new JFileChooser();
				fcSaveAs.setCurrentDirectory(new File("."));
		        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files in .txt", "txt");
		        fcSaveAs.setFileFilter(filter);
		        
		        int answer = fcSaveAs.showSaveDialog(null);
		        if (answer == JFileChooser.APPROVE_OPTION) {
		        	int tabIndex = tabsArea.getSelectedIndex();
		        	if(tabIndex == 0) {
			        	File file = fcSaveAs.getSelectedFile();
			        	String text = textArea1.getText();
			        	try {
			        		FileWriter fw = new FileWriter(file.getPath());
			        		fw.write(text);
			        		fw.flush();
			        		fw.close();
				            System.out.println("Log - file saved with success at  " + file.getPath());
			        	} catch(Exception exception) {
				            JOptionPane.showMessageDialog(null,e);
				        }
		        	}
		        	else if(tabIndex == 1) {
			        	File file = fcSaveAs.getSelectedFile();
			        	String text = textArea2.getText();
			        	try {
			        		FileWriter fw = new FileWriter(file.getPath());
			        		fw.write(text);
			        		fw.flush();
			        		fw.close();
				            Desktop.getDesktop().open(file);
			        	} catch(Exception exception) {
				            JOptionPane.showMessageDialog(null,e);
				        }
		        	}
		        }
		    }
		});
		menuBar.add(itemSaveAs);
		
		JLabel lblNewLabel = new JLabel("TEditor v1.0 by - Cairo Araujo");
		lblNewLabel.setBounds(10, 387, 253, 14);
		container.add(lblNewLabel);
		


		textArea1.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent e) {
                try {
                	//PEGAR A POSIÇÃO ATUAL DO CURSOR:
                	int offset = textArea1.getCaretPosition();
                	int linenum = textArea1.getLineOfOffset(offset) + 1;
                	currentLineValue.setText("current line: "+ linenum);
                	//É DESCONSIDERADA A ABA ATUAL ABERTA
                }
                catch(Exception ex) { 
                	JOptionPane.showMessageDialog(null,e);
                }
            }
        });
		textArea2.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent e) {
                try {
                	//PEGAR A POSIÇÃO ATUAL DO CURSOR:
                	int offset = textArea2.getCaretPosition();
                	int linenum = textArea2.getLineOfOffset(offset) + 1;
                	currentLineValue.setText("current line: "+ linenum);
                	//É DESCONSIDERADA A ABA ATUAL ABERTA
                }
                catch(Exception ex) { 
                	JOptionPane.showMessageDialog(null,e);
                }
            }
        });
	}
	
	public void infoMessage(String message, String tittle) {
		JOptionPane.showMessageDialog(null, message, tittle, JOptionPane.INFORMATION_MESSAGE);
	}

}
