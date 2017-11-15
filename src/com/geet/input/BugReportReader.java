package com.geet.input;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.geet.vsm.model.BugReport;

public class BugReportReader {
	static List<BugReport> bugReports = new ArrayList<BugReport>();
	public static void readBugReports(String filePath){
		File file = new File(filePath);
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
				if (tokens.length>=3) {
					BugReport bugReport = new BugReport(Long.parseLong(tokens[0]), tokens[1], tokens[2]);					
					System.out.println(bugReport.toCorpus());
					bugReports.add(bugReport);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		writeReports();
	}
	
	public static void writeReports(){
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(new File("D:/MSSE0503/datasets/ambari_corpus.xml"));
			fileWriter.write("<reports>\n");
			for (BugReport bugReport : bugReports) {
				fileWriter.write(bugReport.toXML()+"\n");
			}
			fileWriter.write("</reports>");
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		readBugReports("D:/MSSE0503/datasets/dataset-ambari-csv.csv");
	}
	
}
