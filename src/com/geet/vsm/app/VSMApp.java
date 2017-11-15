package com.geet.vsm.app;

import java.util.ArrayList;
import java.util.List;

import com.geet.vsm.model.BugReport;
import com.geet.vsm.model.VectorSpaceModel;

public class VSMApp {

	public static void main(String[] args) {
		List<BugReport> documents = new ArrayList<BugReport>();
		documents.add(new BugReport("Ram and Rahim HELLO10World are good friends!"));
		documents.add(new BugReport("Ram is a good boy"));
		VectorSpaceModel vsm = new VectorSpaceModel();
		vsm.documentsInVSM(documents);
		for (BugReport document : documents) {
			System.out.println(document.toStringWithTermsWeight());
		}
	}
}
