package files.exercise1;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Files_Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Path cSFile = Paths.get("CS.txt");
		Path hedvaFile = Paths.get("hedva.txt");
		try (BufferedReader readerCS = Files.newBufferedReader(cSFile);
			BufferedReader readerHedva = Files.newBufferedReader(hedvaFile)) {
			
			String lineCS = readerCS.readLine();
			String lineHedva = readerHedva.readLine();
			Path reportFile = Paths.get("report.txt");
			if (Files.exists(reportFile))
				Files.writeString(reportFile,"");
			else
				Files.createFile(reportFile);
			
			while ((lineCS != null) && (lineHedva != null)) {
				
				String [] csWords = lineCS.split("\\s+");
				String [] hedvaWords = lineHedva.split("\\s+");
				
				int csID = Integer.parseInt(csWords[2]);
				int hedvaID = Integer.parseInt(hedvaWords[0]);
				
				if (csID == hedvaID) {
					int averageGrades = (Integer.parseInt(hedvaWords[1]) + Integer.parseInt(hedvaWords[2]))/2;
					String toReportFile = csID + " " + csWords[0] + " " + csWords[1] + " " + averageGrades + "\n";
					Files.writeString(reportFile,toReportFile ,StandardOpenOption.APPEND);
					lineCS = readerCS.readLine();
					lineHedva = readerHedva.readLine();
				}
				else if (csID > hedvaID)
					lineHedva = readerHedva.readLine();
				else if (csID < hedvaID)
					lineCS = readerCS.readLine();
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
	
	}
}
