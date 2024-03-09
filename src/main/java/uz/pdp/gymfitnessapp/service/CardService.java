package uz.pdp.gymfitnessapp.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.gymfitnessapp.common.ApiException;
import uz.pdp.gymfitnessapp.dto.CardCreateDto;
import uz.pdp.gymfitnessapp.dto.CardUpdateDto;
import uz.pdp.gymfitnessapp.entity.Card;
import uz.pdp.gymfitnessapp.repository.CardRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository repository;
    private final ModelMapper mapper;

    public Card get(UUID id) {
        return repository.findById(id).orElseThrow(() -> ApiException.throwException("Card not found"));
    }

    public Card create(CardCreateDto createDto) {
        if (repository.existsByNumber(createDto.getNumber())) {
            throw ApiException.throwException("Such card already exists");
        }
        return repository.save(mapper.map(createDto, Card.class));
    }

    public Card update(UUID id, CardUpdateDto updateDto) {
        Card card = get(id);
        card = mapper.map(updateDto, Card.class);
        return repository.save(card);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
