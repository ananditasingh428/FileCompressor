import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class makeLower {
	public static void main(String[] args) throws IOException {
		FileInputStream inputStream = new FileInputStream("sampleFile.txt");
		FileWriter fileWriter = new FileWriter(new File("sampleFile.txt"));
		Scanner sc = new Scanner(inputStream);

		while (sc.hasNextLine()) {
			fileWriter.write(sc.nextLine().toLowerCase());
		}
		sc.close();
		fileWriter.close();
		inputStream.close();
	}

}
