package com.geet.vsm.app;

import java.util.ArrayList;
import java.util.List;

import com.geet.vsm.model.Document;
import com.geet.vsm.model.VectorSpaceModel;

public class VSMApp {

	public static void main(String[] args) {
		List<Document> documents = new ArrayList<Document>();
		documents.add(new Document("Ram and Rahim HELLO10World are good friends!"));
		documents.add(new Document("Ram is a good boy"));
		VectorSpaceModel vsm = new VectorSpaceModel();
		vsm.documentsInVSM(documents);
		for (Document document : documents) {
			System.out.println(document.toStringWithTermsWeight());
		}
	}
}
