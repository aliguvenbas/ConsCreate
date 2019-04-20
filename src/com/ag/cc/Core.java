package com.ag.cc;

import java.util.List;

import com.ag.cc.content.ContentManager;
import com.ag.cc.db.Intarrogator;
import com.ag.cc.file.FileCreator;

public class Core {

	public static void main(String args[]) {
		//Connect to db
		//get related constantPairs from db 
		Intarrogator intarrogator = new Intarrogator();
		List<ConstantPair> contentList = intarrogator.getConstantPairs("DESCRIPTION", "CODE", null);
		//send to content manager
		ContentManager contentManager = new ContentManager("ItemCodes");
		//generate java file
		FileCreator fileCreator = new FileCreator();
		if(fileCreator.createFileWithName("ItemCodes", "java")) {
			fileCreator.writeToFile(contentManager.generateContent(contentList));
		}
		
	}
}

