import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;


public class MenuFunctions {
	Interface gui;
	String fileName;
	String filePath;
	
	public MenuFunctions(Interface gui) {
		this.gui = gui;
	}
	
	public void newFile() {
		
		gui.textArea.setText("");
		gui.window.setTitle("New - TEditor");
		fileName = null;
		filePath = null;
	}
	
	public void open() {
		System.out.print("APERTEI");
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Select a file:");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file", "txt");
		
		fileChooser.setFileFilter(filter);
		fileChooser.showOpenDialog(fileChooser);
		fileChooser.setVisible(true);
		
		File file = new File(fileChooser.getSelectedFile().toString());
		
		if (file != null) {
			filePath = file.getPath();
			fileName = file.getName();
			gui.window.setTitle(fileName);

		}
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String data = scanner.nextLine();
				gui.textArea.insert(data, 0);
			}
		} catch (FileNotFoundException e) {
			System.out.println("The file cannot be opened. Check if is correct.");
		}
		
		/*FileDialog fd = new FileDialog(gui.window, "Choose a txt file to open on TEditor", FileDialog.LOAD);
		fd.setVisible(true);
		
		if(fd.getFile() != null) {
			fileName = fd.getFile();
			filePath = fd.getDirectory();
			gui.window.setTitle(fileName);
			BorderLayout stateBar = new BorderLayout();
			JLabel stateBarLabel = new JLabel(filePath);
			gui.window.add(stateBarLabel,stateBar.SOUTH);
		}
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath + fileName));
			
			gui.textArea.setText("");
			String line = null;
			
			while((line = br.readLine())!= null){
				gui.textArea.append(line + "\n");
			}
			br.close();
		}catch(Exception e) {
			System.out.println("The file cannot be opened. Check if is correct.");
		}*/
	}
	
	public void save() {
		JFileChooser chooser = new JFileChooser();
		int i = chooser.showSaveDialog(chooser);
		if(i!=JFileChooser.APPROVE_OPTION)return;
		try {
			BufferedWriter bf = new BufferedWriter(new FileWriter(chooser.getSelectedFile()+".txt"));
			bf.write(gui.textArea.getText());
			bf.flush();
			bf.close();
		}catch(IOException el){
			el.printStackTrace();
		}
		//FUNCAO SAVE PRECISA MELHORAR!!!
		/*if (fileName == null) {
			System.out.print("DEU RUIM");
			saveAs();
			
		}
		else {
			try {
				System.out.print(fileName);
				FileWriter fw = new FileWriter (fileName);
				String text = gui.textArea.getText();
				fw.write(text);
				fw.close();
				
			}catch(Exception e){
				System.out.println("Error!");
			}
		}*/
	}
	
	public void saveAs() {
		JFileChooser saveChooser = new JFileChooser();
		saveChooser.showSaveDialog(saveChooser);
		File savingFile = saveChooser.getSelectedFile();
		
		try {
			filePath = savingFile.getPath();
			fileName = savingFile.getName();
			gui.window.setTitle(fileName);
			FileWriter fw = new FileWriter (savingFile);
			String text = gui.textArea.getText();
			fw.write(text);
			fw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
