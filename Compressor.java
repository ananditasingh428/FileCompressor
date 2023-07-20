
//! Name: Amrit Kafle
//! Regd No: 19410121179
//! Sec: M

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Compressor {
	private HashMap<Character, Integer> hm;
	private List<Map.Entry<Character, Integer>> sortedList;

	public static void main(String[] args) throws IOException {
		Huffman huffman = new Huffman();
		Compressor com = new Compressor();

		com.writeDecoded();
		System.out.println(huffman.buildHuffTree(com.getSortedList()));

	}

	private void countDigits(String s) {

		for (int i = 0; i < s.length(); i++) {
			hm.put(s.charAt(i), hm.getOrDefault(s.charAt(i), 0) + 1);
		}
	}

	public List<Map.Entry<Character, Integer>> getSortedList() {

		hm = new HashMap<>();

		try {
			InputStream inpStream = new FileInputStream("sampleFile.txt");

			Scanner sc = new Scanner(inpStream);

			while (sc.hasNextLine()) {
				String s = sc.nextLine();
				countDigits(s);
			}

			sortedList = new LinkedList<>(hm.entrySet());

			Collections.sort(sortedList, new Comparator<Map.Entry<Character, Integer>>() {

				@Override
				public int compare(Entry<Character, Integer> o1, Entry<Character, Integer> o2) {
					return o1.getValue().compareTo(o2.getValue());
				}
			});

			sc.close();
			inpStream.close();

		} catch (Exception e) {
			System.out.println("Error in reading file " + e);
		}

		return sortedList;
	}

	public void writeDecoded() throws IOException {

		FileInputStream inpStream = new FileInputStream("./sampleFile.txt");
		FileWriter fileWriter = new FileWriter("./output.txt");
		Huffman huffman = new Huffman();
		Compressor compressor = new Compressor();

		HashMap<Character, String> mapping = huffman.buildHuffTree(compressor.getSortedList());
		StringBuilder sb = new StringBuilder();

		int c = inpStream.read();

		while (c != -1) {

			while (sb.length() <= 8) {
				if (c == -1)
					break;
				sb.append(mapping.get((char) c));
				c = inpStream.read();
			}
			fileWriter.write(Integer.parseInt(sb.toString(), 2));
			sb.setLength(0);
		}

		inpStream.close();
		fileWriter.close();

	}
}