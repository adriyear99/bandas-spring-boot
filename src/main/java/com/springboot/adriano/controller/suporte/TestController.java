package com.springboot.adriano.controller.suporte;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teste")
@RequiredArgsConstructor
public class TestController {

    @GetMapping
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("Deu certo!");
    }
}
