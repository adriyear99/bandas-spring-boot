package com.springboot.adriano.controller.musica;

import com.springboot.adriano.dto.musica.BandaGeneroDTO;
import com.springboot.adriano.dto.musica.BandaRequest;
import com.springboot.adriano.entity.Banda;
import com.springboot.adriano.service.BandaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BandaController {

    @Autowired
    private BandaService bandaService;

    @GetMapping("/bandas")
    public List<BandaGeneroDTO> getBanda() {
        return bandaService.getbandas();
    }

    @PostMapping(value = "/bandas/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Banda> addBanda(@RequestBody BandaRequest model) {
        Banda banda = bandaService.addBanda(model);
        return new ResponseEntity<>(banda, HttpStatus.CREATED);
    }

    @PutMapping(value = "/bandas/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Banda> updateBanda(@PathVariable Long id, @RequestBody BandaRequest model) {
        Banda banda = bandaService.updateBanda(id,model);
        return new ResponseEntity<>(banda, HttpStatus.OK);
    }

    @DeleteMapping(value = "/bandas/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,String>> deleteBanda(@PathVariable Long id, @RequestBody BandaRequest model) {
        bandaService.deleteBanda(id);
        HashMap<String,String> map = new HashMap<>();
        map.put("msg", model.getNome() + " foi deletado com sucesso");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
