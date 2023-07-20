// Name: Amrit Kafle
// Regd No: 19410121179
// Sec: M

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Huffman {

	private class TreeNode {
		private TreeNode left;
		private TreeNode right;
		private Character character;
		private int frequency;

		public TreeNode(TreeNode left, TreeNode right, Character c, int frequency) {
			this.left = left;
			this.right = right;
			this.frequency = frequency;
			this.character = c;
		}

		public boolean isLeaf() {
			return left == null && right == null;
		}

	}

	// returns 'a' -> '1' like mapping
	public HashMap<Character, String> buildHuffTree(List<Map.Entry<Character, Integer>> sortedList) {

		PriorityQueue<TreeNode> pq = new PriorityQueue<TreeNode>(sortedList.size(), new Comparator<TreeNode>() {

			@Override
			public int compare(Huffman.TreeNode o1, Huffman.TreeNode o2) {
				return o1.frequency - o2.frequency;
			}

		});

		for (int i = 0; i < sortedList.size(); i++) {
			pq.add(new TreeNode(null, null, sortedList.get(i).getKey(), sortedList.get(i).getValue()));
		}

		while (pq.size() > 1) {
			TreeNode left = pq.poll();
			TreeNode right = pq.poll();

			pq.add(new TreeNode(left, right, null, left.frequency + right.frequency));
		}
		return generatePrefix(new HashMap<Character, String>(), pq.peek(), "");
	}

	// generates prefix codes
	public HashMap<Character, String> generatePrefix(HashMap<Character, String> map, TreeNode root, String s) {

		if (root.isLeaf())
			map.put(root.character, s);
		else {
			generatePrefix(map, root.left, s + "0");
			generatePrefix(map, root.right, s + "1");
		}

		return map;

	}

	public static void main(String[] args) {
		Huffman huffman = new Huffman();
		Compressor compressor = new Compressor();

		System.out.println(huffman.buildHuffTree(compressor.getSortedList()));
	}
}
