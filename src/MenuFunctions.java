
public class MenuFunctions {
	Interface gui;
	
	public MenuFunctions(Interface gui) {
		this.gui = gui;
	}
	
	public void newFile() {
		gui.textArea.setText("");
		gui.window.setTitle("New");
	}
}
