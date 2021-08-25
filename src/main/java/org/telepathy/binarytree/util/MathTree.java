package org.telepathy.binarytree.util;

import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MathTree {

	private static Logger LOG = LoggerFactory.getLogger(MathTree.class);

	public Node buildTree(String s) throws Exception {

		if (s == null && s.length() == 0) {
			LOG.error("Invallid input string");
			throw new Exception("Invallid input string");
		}

		// Stack to hold nodes
		Stack<Node> stNumbers = new Stack<>();

		// Stack to hold chars
		Stack<Character> stChars = new Stack<>();
		Node t, t1, t2 = null;

		// loop each char in given string
		for (int i = 0; i < s.length(); i++) {

			// ignore spaces
			if (s.charAt(i) == ' ') {
				continue;
			} else if (s.charAt(i) == ')') {
				// evaluate sub expression
				while (!stChars.isEmpty() && stChars.peek() != '(') {
					t = new Node(stChars.pop() + "");
					t1 = stNumbers.pop();
					t2 = stNumbers.pop();
					t.setLeft(t2);
					t.setRight(t1);
					stNumbers.add(t);
				}
				stChars.pop();
			}

			// Push the operands in node stack
			else if (Character.isDigit(s.charAt(i)) || (s.charAt(i) == '-' && Character.isDigit(s.charAt(i + 1)))) {
				StringBuilder sb = new StringBuilder();
				// handle negative numbers
				if (s.charAt(i) == '-') {
					sb.append(s.charAt(i));
					i++;
				}
				while (i < s.length() && Character.isDigit(s.charAt(i))) {
					sb.append(s.charAt(i));
					i++;
				}
				i--;
				t = new Node(sb.toString());
				stNumbers.add(t);
			} else {

				// Push in char stack
				stChars.add(s.charAt(i));
			}
		}

		// merge remaining nodes
		while (!stChars.isEmpty() && !stNumbers.isEmpty()) {
			t = new Node(stChars.peek() + "");
			stChars.pop();

			t1 = stNumbers.peek();
			stNumbers.pop();
			t2 = stNumbers.peek();
			stNumbers.pop();
			t.setLeft(t2);
			t.setRight(t1);

			stNumbers.add(t);
		}

		if (stNumbers.size() != 1) {
			LOG.error("Invallid input string {} ", s);
			throw new Exception("Invallid input string");
		}

		return stNumbers.peek();
	}

	public double evaluate(Node root) {
		// base case: invalid input
		if (root == null) {
			return 0;
		}

		// the leaves of a binary expression tree are operands
		if (isLeaf(root)) {
			return Integer.valueOf(root.getData());
		}

		// recursively evaluate the left and right subtree
		double x = evaluate(root.getLeft());
		double y = evaluate(root.getRight());

		// apply the operator at the root to the values obtained in the previous step
		return process(root.getData(), x, y);
	}

	// Utility function to check if a given node is a leaf node
	public boolean isLeaf(Node node) {
		return node.getLeft() == null && node.getRight() == null;
	}

	// Function to apply an operator `op` to values `x` and `y` and return the
	// result
	public double process(String op, double x, double y) {
		if (op.equals("+")) {
			return x + y;
		}
		if (op.equals("-")) {
			return x - y;
		}
		if (op.equals("×") || op.equals("*")) {
			return x * y;
		}
		if (op.equals("/") || op.equals("÷")) {
			return x / y;
		}

		return 0;
	}

	/* Given a binary tree, print its nodes in inorder */
	private void getInorder(Node node, StringBuilder result) {
		if (node == null)
			return;

		/* first recur on left child */
		getInorder(node.getLeft(), result);

		/* then print the data of node */
		result.append(node.getData() + " ");

		/* now recur on right child */
		getInorder(node.getRight(), result);
	}

	public String getInorder(Node node) {
		StringBuilder result = new StringBuilder();
		getInorder(node, result);
		return result.toString();

	}

	/* Given a binary tree, print its nodes in preorder */
	private void getPreorder(Node node, StringBuilder result) {
		if (node == null)
			return;

		/* first print data of node */
		result.append(node.getData() + " ");

		/* then recur on left sutree */
		getPreorder(node.getLeft(), result);

		/* now recur on right subtree */
		getPreorder(node.getRight(), result);
	}

	public String getPreorder(Node node) {
		StringBuilder result = new StringBuilder();
		getPreorder(node, result);
		return result.toString();

	}

	/*
	 * Given a binary tree, print its nodes according to the "bottom-up" postorder
	 * traversal.
	 */
	private void getPostorder(Node node, StringBuilder result) {
		if (node == null)
			return;

		// first recur on left subtree
		getPostorder(node.getLeft(), result);

		// then recur on right subtree
		getPostorder(node.getRight(), result);

		// now deal with the node
		result.append(node.getData() + " ");
	}

	public String getPostorder(Node node) {
		StringBuilder result = new StringBuilder();
		getPostorder(node, result);
		return result.toString();

	}

}
