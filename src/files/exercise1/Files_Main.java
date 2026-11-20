//Yarin Rozilyo, 206519928 | Atawna Belal, 326309770 
package files.exercise1;
import java.util.Scanner;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Files_Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		// Get the input file paths from the user
		System.out.print("Enter the path to the student details file (or just the file name if it is in the project folder): ");
		Path cSFile = Paths.get(input.nextLine());
		System.out.print("Enter the path to the grades file (or just the file name if it is in the project folder): ");
		Path hedvaFile = Paths.get(input.nextLine());
		// Open both files for reading
		try (BufferedReader readerCS = Files.newBufferedReader(cSFile);
			BufferedReader readerHedva = Files.newBufferedReader(hedvaFile)) {
			
			String lineCS = readerCS.readLine();
			String lineHedva = readerHedva.readLine();
			Path reportFile = Paths.get("report.txt");
			// Create the report file or clear its previous content
			if (Files.exists(reportFile))
				Files.writeString(reportFile,"");
			else 
				Files.createFile(reportFile);
			// Compare the two sorted files line by line
			while ((lineCS != null) && (lineHedva != null)) {
				
				String [] csWords = lineCS.split("\\s+");
				String [] hedvaWords = lineHedva.split("\\s+");
				
				int csID = Integer.parseInt(csWords[2]);
				int hedvaID = Integer.parseInt(hedvaWords[0]);
				
				// Matching IDs and write the student's details and average grade
				if (csID == hedvaID) {
					int grade1 = Integer.parseInt(hedvaWords[1]);
					int grade2 = Integer.parseInt(hedvaWords[2]);
					long total = (long) grade1 + grade2;

					String averageGrades;
					if (total % 2 == 0) {
					    averageGrades = Long.toString(total / 2);
					} else {
					    averageGrades = String.format(java.util.Locale.ROOT, "%.1f", total / 2.0);
					}		
					
					String toReportFile = csWords[2] + " " + csWords[0] + " " + csWords[1] + " " + averageGrades + "\n";
					Files.writeString(reportFile,toReportFile ,StandardOpenOption.APPEND);
					lineCS = readerCS.readLine();
					lineHedva = readerHedva.readLine();
				}
				// Advance in the file containing the smaller ID
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
