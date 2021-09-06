import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileReader;

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
	}
	
	public void open() {
		FileDialog fd = new FileDialog(gui.window, "Choose a txt file to open on TEditor", FileDialog.LOAD);
		fd.setVisible(true);
		
		if(fd.getFile() != null) {
			fileName = fd.getFile();
			filePath = fd.getDirectory();
			gui.window.setTitle(fileName);
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
		}
	}
}
