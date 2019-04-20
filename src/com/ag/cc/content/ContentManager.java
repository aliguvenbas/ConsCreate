package com.ag.cc.content;

import java.util.ArrayList;
import java.util.List;

import com.ag.cc.ConstantPair;

public class ContentManager {

	public String className;

	public ContentManager(String className) {
		super();
		this.className = className;
	}

	public String generateContent(List<ConstantPair> list) {
		StringBuffer content = new StringBuffer();
		content.append("public class " + className + "{");
		if (list != null && list.size() > 0) {
			generateConstans(content, list);
		}
		content.append("}");
		return content.toString();
	}

	public void generateConstans(StringBuffer content, List<ConstantPair> list) {
		for (ConstantPair pair : list) {
			if (pair.getType().equals("String")) {
				content.append("public static final " + pair.getType() + " " + generateValuableName(pair.getName()) + " = \"" + pair.getValue() + "\";\n");
			} else {
				content.append("public static final " + pair.getType() + " " + generateValuableName(pair.getName()) + " = " + pair.getValue() + ";\n");
			}
		}
	}

	public String generateValuableName(String pairName) {
		return pairName.replace(" ", "_").replace(",", "_").replace("'", "_").replace("\"", "_").toUpperCase();
	}

}
