package org.telepathy.binarytree.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telepathy.binarytree.dto.MathTreeResponseDTO;
import org.telepathy.binarytree.util.MathTree;
import org.telepathy.binarytree.util.Node;

@Service
public class MathTreeService {
	private static Logger LOG = LoggerFactory.getLogger(MathTree.class);
	@Autowired
	private MathTree mathTree;

	public MathTreeResponseDTO getMathTree(String input) throws Exception {
		Node node = mathTree.buildTree(input);

		String pre = mathTree.getPreorder(node);
		String post = mathTree.getPostorder(node);
		String in = mathTree.getInorder(node);
		double val = mathTree.evaluate(node);

		LOG.info("pre-order {} , post-order {} , in-order {}, value {} ", pre, post, in, val);
		return new MathTreeResponseDTO(pre, post, in, val);
	}
}
