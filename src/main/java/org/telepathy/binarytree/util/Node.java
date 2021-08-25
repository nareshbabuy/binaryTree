package org.telepathy.binarytree.util;

public class Node {

	String data;
	Node left, right;

	public Node(String data) {
		this.data = data;
		this.left = this.right = null;

	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public String getData() {
		return data;
	}

}
