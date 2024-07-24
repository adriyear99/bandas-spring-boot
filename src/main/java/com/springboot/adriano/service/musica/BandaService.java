package com.springboot.adriano.service.musica;

import com.springboot.adriano.dto.musica.BandaGeneroDTO;
import com.springboot.adriano.dto.musica.BandaRequest;
import com.springboot.adriano.entity.Banda;
import com.springboot.adriano.repository.musica.BandaRepository;
import com.springboot.adriano.repository.musica.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class BandaService {

    @Autowired
    private BandaRepository bandaRepository;

    @Autowired
    private GeneroRepository generoRepository;

    public List<BandaGeneroDTO> getbandas() {
        List<Banda> bandas = bandaRepository.findAllByOrderByIdAsc();
        List<BandaGeneroDTO> model = new ArrayList<>();
        for(Banda banda : bandas) {
            BandaGeneroDTO bandaGeneroDTO = new BandaGeneroDTO(banda.getNome(),banda.getGenero().getNome());
            model.add(bandaGeneroDTO);
        }
        return model;
    }

    public Banda addBanda(BandaRequest model) {
        Banda banda = new Banda();
        banda.setId(null);
        banda.setNome(model.getNome());
        banda.setGenero(generoRepository.findById(model.getIdGenero()).orElse(null));
        banda.setQtdMembros(model.getQtdMembros());
        bandaRepository.save(banda);
        return banda;
    }

    public Banda updateBanda(Long id, BandaRequest model) {
        Banda banda = bandaRepository.findById(id).orElse(null);
        if(banda == null) {
            throw new RuntimeException("Banda não encontrada!");
        } else {
            banda.setNome(model.getNome());
            banda.setGenero(generoRepository.findById(model.getIdGenero()).orElse(null));
            banda.setQtdMembros(model.getQtdMembros());
            bandaRepository.save(banda);
            return banda;
        }
    }

    public void deleteBanda(Long id) {
        Banda banda = bandaRepository.findById(id).orElse(null);
        if(banda == null) {
            throw new RuntimeException("Banda não encontrada!");
        } else {
            bandaRepository.delete(banda);
        }
    }
}
