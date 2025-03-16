package br.com.pucminas.edutech.service;

import br.com.pucminas.edutech.model.dto.BadgeDTO;
import br.com.pucminas.edutech.model.entity.Badge;
import br.com.pucminas.edutech.repository.BadgeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BadgeService {
    private final BadgeRepository badgeRepository;
    private final ModelMapper modelMapper;

    public BadgeService(BadgeRepository badgeRepository, ModelMapper modelMapper) {
        this.badgeRepository = badgeRepository;
        this.modelMapper = modelMapper;
    }

    //Entity to DTO
    private BadgeDTO convertToDTO(Badge badge) {
        return modelMapper.map(badge, BadgeDTO.class);
    }

    // DTO to Entity
    private Badge convertToEntity(BadgeDTO badgeDTO) {
        return modelMapper.map(badgeDTO, Badge.class);
    }

    public BadgeDTO createBadge(BadgeDTO badgeDTO) {
        Badge badge = convertToEntity(badgeDTO);
        Badge savedBadge = badgeRepository.save(badge);
        return convertToDTO(savedBadge);
    }

    public List<BadgeDTO> getAllBadges() {
        return badgeRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public BadgeDTO getBadgeById(Long id){
        Badge badge = badgeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Badge not found"));
        return convertToDTO(badge);
    }

    public BadgeDTO updateBadge(Long id, BadgeDTO badgeDTO){
        Badge existingBadge = badgeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Badge not found"));

        existingBadge.setName(badgeDTO.getName());
        existingBadge.setImgUrl(badgeDTO.getImgUrl());

        Badge updatedBadge = badgeRepository.save(existingBadge);

        return convertToDTO(updatedBadge);
    }

    public void deleteBadge(Long id){
        Badge badge = badgeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Badge not found"));

        badgeRepository.delete(badge);
    }
}
