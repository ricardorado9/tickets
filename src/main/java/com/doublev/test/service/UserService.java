package com.doublev.test.service;

import com.doublev.test.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UserService {

    private final RestTemplate restTemplate;
    @Value("#{${app.users.endpoint}}")
    private String usersEndpoint;

    public UserDto findUsers(String username) {
        ResponseEntity<UserDto> responseEntity = restTemplate.getForEntity(usersEndpoint.concat(String.format("?q=%s", username)), UserDto.class);
        return responseEntity.getBody();
    }
}
