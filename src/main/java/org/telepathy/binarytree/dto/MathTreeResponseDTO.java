package org.telepathy.binarytree.dto;

import java.io.Serializable;

public class MathTreeResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String preOrder;
	private String postOrder;
	private String inOrder;
	private double value;

	public MathTreeResponseDTO() {

	}

	public MathTreeResponseDTO(String preOrder, String postOrder, String inOrder, double value) {
		super();
		this.preOrder = preOrder;
		this.postOrder = postOrder;
		this.inOrder = inOrder;
		this.value = value;
	}

	public String getPreOrder() {
		return preOrder;
	}

	public void setPreOrder(String preOrder) {
		this.preOrder = preOrder;
	}

	public String getPostOrder() {
		return postOrder;
	}

	public void setPostOrder(String postOrder) {
		this.postOrder = postOrder;
	}

	public String getInOrder() {
		return inOrder;
	}

	public void setInOrder(String inOrder) {
		this.inOrder = inOrder;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

}
