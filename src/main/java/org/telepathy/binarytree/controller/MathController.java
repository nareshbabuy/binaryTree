package org.telepathy.binarytree.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.telepathy.binarytree.dto.MathTreeResponseDTO;
import org.telepathy.binarytree.service.MathTreeService;
import org.telepathy.binarytree.util.MathTree;

@Controller
@RequestMapping("/mathTree")
public class MathController {

	private static Logger LOG = LoggerFactory.getLogger(MathTree.class);

	@Autowired
	private MathTreeService mathTreeService;

	@PostMapping("/getMathTree")
	public ResponseEntity<MathTreeResponseDTO> getMathTree(@RequestBody(required = true) String input) {

		MathTreeResponseDTO dto = null;
		try {
			dto = mathTreeService.getMathTree(input);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (dto == null) {
			LOG.info("No data for input {} ", input);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.status(HttpStatus.OK).body(dto);

	}

}
