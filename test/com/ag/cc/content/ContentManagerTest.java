package com.ag.cc.content;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ag.cc.ConstantPair;

class ContentManagerTest {
	ContentManager manager;

	@BeforeEach
	void init() {
		manager = new ContentManager("StatusConstants");
	}

	@Test
	@DisplayName("variable named _(underscore) and upperCase")
	void test2() {
		String actual = manager.generateValuableName("Idle status");
		assertTrue(manager.generateValuableName("Idle status").equals("IDLE_STATUS"));
	
	}
	
	@Test
	@DisplayName("list is null for contentManager")
	void test7() {
		assertNotNull(manager.generateContent(null));
		assertTrue(manager.generateContent(null).toCharArray().length > 0);
	}

	@Test
	@DisplayName("list to Constants")
	void test3() {
		StringBuffer buffer = new StringBuffer();
		ArrayList<ConstantPair> list = new ArrayList<>();
		list.add(new ConstantPair("int", "Idle", 0));
		manager.generateConstans(buffer, list);
		assertTrue(buffer.toString().equals("public static final int IDLE = 0;\n"));
	}

	@Test
	@DisplayName("list to Constants, int constants, list size>0")
	void test4() {
		StringBuffer buffer = new StringBuffer();
		ArrayList<ConstantPair> list = new ArrayList<>();
		list.add(new ConstantPair("int", "Idle", 0));
		list.add(new ConstantPair("int", "Draft", 1));
		list.add(new ConstantPair("int", "Ready", 2));
		manager.generateConstans(buffer, list);
		assertTrue(buffer.toString().equals("public static final int IDLE = 0;\npublic static final int DRAFT = 1;\npublic static final int READY = 2;\n"));
	}

	@Test
	@DisplayName("list to Constants, String constants, list size>0")
	void test5() {
		StringBuffer buffer = new StringBuffer();
		ArrayList<ConstantPair> list = new ArrayList<>();
		list.add(new ConstantPair("String", "Idle", "0"));
		list.add(new ConstantPair("String", "Draft", "1"));
		list.add(new ConstantPair("String", "Ready", "2"));
		manager.generateConstans(buffer, list);
		assertEquals("public static final String IDLE = \"0\";\npublic static final String DRAFT = \"1\";\npublic static final String READY = \"2\";\n", buffer.toString());
	}
	
	@Test
	@DisplayName("list to Constants, Multitype constants, list size>0")
	void test6() {
		StringBuffer buffer = new StringBuffer();
		ArrayList<ConstantPair> list = new ArrayList<>();
		list.add(new ConstantPair("Integer", "Idle", "0"));
		list.add(new ConstantPair("String", "Draft", "1"));
		list.add(new ConstantPair("int", "Ready", "2"));
		manager.generateConstans(buffer, list);
		assertEquals("public static final Integer IDLE = 0;\npublic static final String DRAFT = \"1\";\npublic static final int READY = 2;\n", buffer.toString());
	}

}
