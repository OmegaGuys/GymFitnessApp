package uz.pdp.gymfitnessapp.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.gymfitnessapp.common.ApiException;
import uz.pdp.gymfitnessapp.dto.CardCreateDto;
import uz.pdp.gymfitnessapp.dto.CardUpdateDto;
import uz.pdp.gymfitnessapp.entity.Card;
import uz.pdp.gymfitnessapp.entity.enums.CardType;
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
        if (createDto.getNumber().length() != 16)
            throw ApiException.throwException("Invalid card number");

        Card card = mapper.map(createDto, Card.class);
        setType(card, createDto.getType());
        return repository.save(card);
    }

    public Card update(UUID id, CardUpdateDto updateDto) {
        Card card = get(id);
        mapper.map(updateDto, card);
        return repository.save(card);
    }

    public void delete(UUID id) {
        repository.delete(get(id));
    }

    private void setType(Card card, String type) {
        for (var cardType : CardType.values()) {
            if (cardType.name().equals(type)) {
                card.setType(cardType);
                return;
            }
        }
        throw ApiException.throwException("Such type does not exist");
    }
}
