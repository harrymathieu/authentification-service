package org.sample.devops.service.authentication.exposition;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
public class HttpRequestUnitTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void authentication_should_retrun_not_found_error_when_incorrect_password() throws Exception {
        final String baseUrl = "http://localhost:" + port + "/api/authenticate";
        URI uri = new URI(baseUrl);
        AuthenticationRequest request = new AuthenticationRequest("user", "password");
        ResponseEntity<AuthenticationResponse> response = this.restTemplate.postForEntity(uri, request, AuthenticationResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void authentication_should_retrun_user_when_correct_mail_and_password() throws Exception {
        final String baseUrl = "http://localhost:" + port + "/api/authenticate";
        URI uri = new URI(baseUrl);
        AuthenticationRequest request = new AuthenticationRequest("user1@mail.com", "password1");
        ResponseEntity<AuthenticationResponse> response = this.restTemplate.postForEntity(uri, request, AuthenticationResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(new AuthenticationResponse("user1@mail.com", "User1FirstName", "User1LastName"));
    }
}