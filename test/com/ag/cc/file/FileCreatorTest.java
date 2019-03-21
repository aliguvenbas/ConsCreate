package com.ag.cc.file;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileCreatorTest {
	FileCreator fileCreator;

	@BeforeEach
	void init() {
		fileCreator = new FileCreator();
	}

	@Test
	@DisplayName("File creation, null parameters")
	void test() {
		String fileName = "Test";
		String extension = "java";
		assertAll(() -> assertFalse(fileCreator.createFileWithName(null, null), "if both parametes are null do not create file"), () -> assertFalse(fileCreator.createFileWithName(fileName, null), "if extension is  null do not create file"),
				() -> assertFalse(fileCreator.createFileWithName(null, extension), "if file name is null do not create file"));
	}

	@Test
	@DisplayName("File creation, empty parameters")
	void test3() {
		String fileName = "Test";
		String extension = "java";
		assertAll(() -> assertFalse(fileCreator.createFileWithName("", ""), "if both parametes are empty do not create file"), () -> assertFalse(fileCreator.createFileWithName(fileName, ""), "if extension is  empty do not create file"),
				() -> assertFalse(fileCreator.createFileWithName("", extension), "if file name is empty do not create file"));
	}

	@Test
	@DisplayName("File creation")
	void test2() {
		String fileName = "Test";
		String extension = "java";
		assertTrue(fileCreator.createFileWithName(fileName, extension));

		File file = new File("result/Test.java");
		assertTrue(file.exists());
	}

	@Test
	@DisplayName("write to file, compare content")
	void test6() {
		String filePath = "result/TestCompare.java";
		File file = new File(filePath);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		StringBuffer readedContent = new StringBuffer();
		if (file.exists()) {
			String content = "This is a /n test content";
			fileCreator.setFilePath(filePath);
			boolean ok = fileCreator.writeToFile(content);
			assertTrue(ok);

			FileReader fileReader;
			try {
				fileReader = new FileReader(filePath);

				BufferedReader bufferedReader = new BufferedReader(fileReader);
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					readedContent.append(line);
				}
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			assertEquals(content, readedContent.toString());
		} else {
			fail("test file can not be created for compare content");
		}
	}

	@Test
	@DisplayName("write to file, if fileName/file not exist")
	void test5() {
		fileCreator.setFilePath("");
		assertFalse(fileCreator.writeToFile("lorem ipsum"));

		fileCreator.setFilePath(null);
		assertFalse(fileCreator.writeToFile("lorem ipsum"));

	}

	@AfterAll
	static void finish() {
		File file = new File("result/Test.java");
		if (file.exists()) {
			file.delete();
		}
		
		File file2 = new File("result/TestCompare.java");
		if (file2.exists()) {
			file2.delete();
		}
	}

}
