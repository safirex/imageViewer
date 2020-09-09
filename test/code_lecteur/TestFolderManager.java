 package code_lecteur;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class TestFolderManager {

	@Test
	void test() throws FileNotFoundException {
		FolderManager fold=new FolderManager();
		fold.getFileImageList();
		FolderManager.printArray(fold.imageNameList);
		assertTrue(fold.currentImageName.equals("p4.jpg"));
		assertTrue(fold.path.equals(".\\pics"));
	}

}
