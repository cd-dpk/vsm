package com.geet.input;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.geet.vsm.model.BugReport;
import com.geet.vsm.model.Document;

public class InputHandler {
	static List<BugReport> bugReports = new ArrayList<BugReport>();
	
	public static void readBugReportsCSVAndWriteToXML(String fileName){
		File file = new File("D:/MSSE0503/datasets/"+fileName+".csv");
		try {
			Scanner scanner = new Scanner(file);
			long i=0;
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				i++;
				if (i==1) {
					continue;
				}
				String tokens[] = line.split(",");
				if (tokens.length>=9) {
					BugReport bugReport = new BugReport(Long.parseLong(tokens[0]), tokens[1], tokens[2],Integer.parseInt(tokens[3]),Integer.parseInt(tokens[4]),Integer.parseInt(tokens[5]),Integer.parseInt(tokens[6]),Integer.parseInt(tokens[7]),Integer.parseInt(tokens[8]));					
					bugReports.add(bugReport);
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		writeReportsIntoXML(fileName);
	}
	
	private static void writeReportsIntoXML(String fileName){
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(new File("D:/MSSE0503/datasets/"+fileName+"_corpus.xml"));
			fileWriter.write("<reports>\n");
			for (BugReport bugReport : bugReports) {
				fileWriter.write(bugReport.toXML());
				System.out.println(bugReport.toXML());
			}
			fileWriter.write("</reports>");
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static List<Document> readDocumentsFromXML(String fileName){
		List<Document> documents = new ArrayList<Document>();
		File inputFile = new File(fileName);
        DocumentBuilderFactory dbFactory 
           = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        org.w3c.dom.Document doc;

		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(inputFile);
	        doc.getDocumentElement().normalize();
	        System.out.println("Root element :" 
	           + doc.getDocumentElement().getNodeName());
	        NodeList nList = doc.getElementsByTagName("report");
	        System.out.println("----------------------------");
			for (int i = 0; i < nList.getLength(); i++) {
				Element reportElement = (Element) nList.item(i);
//				System.out.println(reportElement.getNodeName());
				long ID = Long.parseLong(reportElement.getAttribute("id"));
				String corpus = "";
				NodeList corpusNodeList = reportElement.getElementsByTagName("corpus");
				for (int j = 0; j < corpusNodeList.getLength(); ++j) {
					Element option = (Element) corpusNodeList.item(j);
					if (option.hasChildNodes()) {
						String optionText = option.getFirstChild()
								.getNodeValue();
						corpus += optionText;
					}
				}
				int Surprising = 0;
				NodeList SurprisingNodeList = reportElement.getElementsByTagName("Surprising");
				for (int j = 0; j < SurprisingNodeList.getLength(); ++j) {
					Element option = (Element) SurprisingNodeList.item(j);
					if (option.hasChildNodes()) {
						String optionText = option.getFirstChild()
								.getNodeValue();
						Surprising = Integer.parseInt(optionText);
					}
				}
				int Dormant = 0;
				NodeList DormantNodeList = reportElement.getElementsByTagName("Dormant");
				for (int j = 0; j < DormantNodeList.getLength(); ++j) {
					Element option = (Element) DormantNodeList.item(j);
					if (option.hasChildNodes()) {
						String optionText = option.getFirstChild()
								.getNodeValue();
						Dormant = Integer.parseInt(optionText);
					}
				}
				int Blocker = 0;
				NodeList BlockerNodeList = reportElement.getElementsByTagName("Blocker");
				for (int j = 0; j < BlockerNodeList.getLength(); ++j) {
					Element option = (Element) BlockerNodeList.item(j);
					if (option.hasChildNodes()) {
						String optionText = option.getFirstChild()
								.getNodeValue();
						Blocker = Integer.parseInt(optionText);
					}
				}
				int Security =0;
				NodeList SecurityNodeList = reportElement.getElementsByTagName("Security");
				for (int j = 0; j < SecurityNodeList.getLength(); ++j) {
					Element option = (Element) SecurityNodeList.item(j);
					if (option.hasChildNodes()) {
						String optionText = option.getFirstChild()
								.getNodeValue();
						Security = Integer.parseInt(optionText);
					}
				}
				int Performance = 0;
				NodeList PerformanceNodeList = reportElement.getElementsByTagName("Performance");
				for (int j = 0; j < PerformanceNodeList.getLength(); ++j) {
					Element option = (Element) PerformanceNodeList.item(j);
					if (option.hasChildNodes()) {
						String optionText = option.getFirstChild()
								.getNodeValue();
						Performance = Integer.parseInt(optionText);
					}
				}
				int Breakage = 0;
				NodeList BreakageNodeList = reportElement.getElementsByTagName("Breakage");
				for (int j = 0; j < BreakageNodeList.getLength(); ++j) {
					Element option = (Element) BreakageNodeList.item(j);
					if (option.hasChildNodes()) {
						String optionText = option.getFirstChild()
								.getNodeValue();
						Breakage = Integer.parseInt(optionText);
					}
				}
				documents.add(new Document(ID, corpus,Surprising,Dormant,Blocker,Security,Performance,Breakage));
			}
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			return documents;
	}
	
	
	public static void main(String[] args) {
		/*
		readBugReportsCSVAndWriteToXML("ambari");
		readBugReportsCSVAndWriteToXML("camel");
		readBugReportsCSVAndWriteToXML("derby");
		readBugReportsCSVAndWriteToXML("wicket");
		*/
//	/*
		createFeatureCSVFile(readDocumentsFromXML("D://MSSE0503//datasets//ambari_corpus.xml"));
//	*/
	}
	
	private static void createFeatureCSVFile(List<Document> documents ){
		String docString="";
		Set<String> features = new HashSet<String>();
		for (Document document : documents) {
			for (String key : document.terms.keySet()) {
				docString+=key+",";
				features.add(key);
			}
		}
		docString+="\n";
		for (Document document : documents) {
			for (String feature : features) {
				if (document.terms.containsKey(feature)) {
					docString+=document.terms.get(feature).getWeight()+",";
				} else {
					docString+=0.0+",";
				}
			}
			docString+="\n";
		}
		System.out.println(docString);
	}
	
}
