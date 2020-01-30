package code_lecteur;

public abstract class FileReader {
	String name;
	public FileReader() {
	}
	public FileReader(String n) {
		name=n;
	}
	 public FileReader getFile() {
		 return this;
	 }
	
	
	
}
