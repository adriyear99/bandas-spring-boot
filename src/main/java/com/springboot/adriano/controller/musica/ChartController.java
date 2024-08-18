package com.springboot.adriano.controller.musica;

import com.springboot.adriano.dto.musica.ChartRequest;
import com.springboot.adriano.entity.musica.Chart;
import com.springboot.adriano.service.musica.ChartService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chart")
@RequiredArgsConstructor
public class ChartController {

    @Autowired
    private ChartService chartService;

    @PostMapping
    public ResponseEntity<Chart> sendCollageResult(@RequestBody ChartRequest model) {
        Chart chart = chartService.sendCollageResult(model);
        return new ResponseEntity<>(chart, HttpStatus.CREATED);
    }

}
