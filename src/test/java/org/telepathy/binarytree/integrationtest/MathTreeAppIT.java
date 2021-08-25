package org.telepathy.binarytree.integrationtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.telepathy.binarytree.MathTreeApp;
import org.telepathy.binarytree.dto.MathTreeResponseDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MathTreeApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MathTreeAppIT {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void testMathTree() throws IOException {

		HttpEntity<String> requestEntity = new HttpEntity<>("( ( 15/(7-(1+1)) )*-3 )-(2+(1+1))", new HttpHeaders());

		ResponseEntity<MathTreeResponseDTO> response = restTemplate.exchange("/mathTree/getMathTree", HttpMethod.POST,
				requestEntity, MathTreeResponseDTO.class);

		assertNotNull(response.getBody());
		assertEquals(response.getBody().getValue(), -13.0, 0);
		assertEquals(response.getBody().getInOrder(), "15 / 7 - 1 + 1 * -3 - 2 + 1 + 1 ");
		assertEquals(response.getBody().getPostOrder(), "15 7 1 1 + - / -3 * 2 1 1 + + - ");
		assertEquals(response.getBody().getPreOrder(), "- * / 15 - 7 + 1 1 -3 + 2 + 1 1 ");

	}

	@Test
	public void testMathTree2() throws IOException {

		HttpEntity<String> requestEntity = new HttpEntity<>("( ( 15÷(7-(1+1)) )×-3 )-(2+(1+1))", new HttpHeaders());

		ResponseEntity<MathTreeResponseDTO> response = restTemplate.exchange("/mathTree/getMathTree", HttpMethod.POST,
				requestEntity, MathTreeResponseDTO.class);

		assertNotNull(response.getBody());
		assertEquals(response.getBody().getValue(), -13.0, 0);
		assertEquals(response.getBody().getInOrder(), "15 ÷ 7 - 1 + 1 × -3 - 2 + 1 + 1 ");
		assertEquals(response.getBody().getPostOrder(), "15 7 1 1 + - ÷ -3 × 2 1 1 + + - ");
		assertEquals(response.getBody().getPreOrder(), "- × ÷ 15 - 7 + 1 1 -3 + 2 + 1 1 ");

	}
}
