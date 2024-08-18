package com.springboot.adriano.service.musica;

import com.springboot.adriano.dto.musica.ChartRequest;
import com.springboot.adriano.dto.musica.Resultado;
import com.springboot.adriano.entity.musica.Chart;
import com.springboot.adriano.entity.musica.Item;
import com.springboot.adriano.repository.musica.ChartRepository;
import com.springboot.adriano.repository.musica.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ChartService {

    @Autowired
    ChartRepository chartRepository;

    @Autowired
    ItemsRepository itemsRepository;

    public Chart sendCollageResult(ChartRequest model) {
        Chart chart = Chart
                .builder()
                .name(model.getName())
                .textColor(model.getTextColor())
                .backgroundColor(model.getBackgroundColor())
                .items(model.getItems())
                .selectionType(model.getSelectionType())
                .creationDate(new Date())
                .build();
        chartRepository.save(chart);

        List<Item> items = new ArrayList<>();
        for(Resultado res : model.getResultados()) {
            items.add(
                    Item.builder()
                    .position(res.getId())
                    .img(res.getImg())
                    .track(res.getTrack())
                    .album(res.getAlbum())
                    .artist(res.getArtist())
                    .build()
            );
        }
        itemsRepository.saveAll(items);

        return chart;
    }
}
