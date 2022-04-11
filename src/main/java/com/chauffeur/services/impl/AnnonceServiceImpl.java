package com.chauffeur.services.impl;

import com.chauffeur.dto.AnnonceDto;
import com.chauffeur.exceptions.ResourceNotFoundException;
import com.chauffeur.models.Annonce;
import com.chauffeur.repository.AnnonceRepository;
import com.chauffeur.services.AnnonceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class AnnonceServiceImpl implements AnnonceService {

    @Autowired
    private final AnnonceRepository annonceRepository;

    public AnnonceServiceImpl(AnnonceRepository annonceRepository) {
        this.annonceRepository = annonceRepository;
    }

    @Override
    public AnnonceDto save(AnnonceDto annonceDto) {
        annonceDto.setStatus("ENCOURS");

        return AnnonceDto.fromEntityToDto(
                annonceRepository.save(
                        AnnonceDto.fromDtoToEntity(annonceDto)
                )
        );
    }

    @Override
    public AnnonceDto update(Long idAnnonce, AnnonceDto annonceDto) {
        if (!annonceRepository.existsById(idAnnonce)) {
            throw new ResourceNotFoundException("Annonce not found");
        }

        Optional<Annonce> annonce = annonceRepository.findById(idAnnonce);

        if (!annonce.isPresent()) {
            throw new ResourceNotFoundException("Annonce not found");
        }

        AnnonceDto annonceDtoResult = AnnonceDto.fromEntityToDto(annonce.get());
        annonceDtoResult.setReference(annonceDto.getReference());
        annonceDtoResult.setLibelle(annonceDto.getLibelle());
        annonceDtoResult.setTime(annonceDto.getTime());
        annonceDtoResult.setProExperience(annonceDto.getProExperience());
        annonceDtoResult.setTypeContrat(annonceDto.getTypeContrat());
        annonceDtoResult.setSelected(annonceDto.isSelected());
        annonceDtoResult.setDescription(annonceDto.getDescription());
        annonceDtoResult.setLieuPoste(annonceDto.getLieuPoste());
        annonceDtoResult.setSalaire(annonceDto.getSalaire());
        annonceDtoResult.setStatus(annonceDto.getStatus());
        annonceDtoResult.setDateCandidature(annonceDto.getDateCandidature());
        annonceDtoResult.setDateCloture(annonceDto.getDateCloture());
        annonceDtoResult.setPermisDto(annonceDto.getPermisDto());
        annonceDtoResult.setAddresseDto(annonceDto.getAddresseDto());

        return AnnonceDto.fromEntityToDto(
                annonceRepository.save(
                        AnnonceDto.fromDtoToEntity(annonceDtoResult)
                )
        );
    }

    @Override
    public AnnonceDto updateStatusOfAnnonce(String status, String id) {
        Optional<Annonce> annonceOptional = annonceRepository.findById(Long.valueOf(id));

        AnnonceDto annonceDtoResult = AnnonceDto.fromEntityToDto(annonceOptional.get());

        annonceDtoResult.setStatus(status);

        return AnnonceDto.fromEntityToDto(
                annonceRepository.save(
                        AnnonceDto.fromDtoToEntity(annonceDtoResult)
                )
        );
    }


    @Override
    public AnnonceDto findById(Long id) {
        if (id == null) {
            log.error("Produit Id is null");
            return null;
        }

        Optional<Annonce> annonce = annonceRepository.findById(id);

        return Optional.of(AnnonceDto.fromEntityToDto(annonce.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Annonce avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public AnnonceDto findByReference(String reference) {
        if (!StringUtils.hasLength(reference)) {
            log.error("Annonce REFERENCE is null");
        }

        Optional<Annonce> annonce = annonceRepository.findAnnonceByReference(reference);

        return Optional.of(AnnonceDto.fromEntityToDto(annonce.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Annonce avec l'Id = " + reference + "n'a été trouvé")
        );

    }

    @Override
    public List<AnnonceDto> findAll() {
        return annonceRepository.findAll().stream()
                .map(AnnonceDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AnnonceDto> findByAnnonceByIdDesc() {
        return annonceRepository.findByOrderByIdDesc().stream()
                .map(AnnonceDto::fromEntityToDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<AnnonceDto> findListAnnonceBySelected() {
        return annonceRepository.findAnnonceBySelected().stream()
                .map(AnnonceDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AnnonceDto> findListAnnonceByKeyword(String keyword) {
        if (keyword == null) {
            log.error("Annonce not found");
        }
        return annonceRepository.findAnnonceByKeyword(keyword).stream()
                .map(AnnonceDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AnnonceDto> findListAnnonceByLibelle(String libelle) {
        if (libelle == null) {
            log.error("Annonce not found");
        }
        return annonceRepository.findListAnnonceByLibelle(libelle)
                .stream()
                .map(AnnonceDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AnnonceDto> find5LatestRecordsByOrderByIdDesc() {
        return annonceRepository.findTop5ByOrderByIdDesc()
                .stream()
                .map(AnnonceDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AnnonceDto> find6LatestValidatedRecordsByOrderByIdDesc() {
        return annonceRepository.findTop6ValidatedByOrderByIdDesc()
                .stream()
                .map(AnnonceDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AnnonceDto> findListAnnonceByPermis(Long pId) {
        return annonceRepository.findAnnonceByPermis(pId).stream()
                .map(AnnonceDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AnnonceDto> FindListAnnonceByCustomerId(Long userId) {
        return annonceRepository.FindListAnnonceByCustomerId(userId).stream()
                .map(AnnonceDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AnnonceDto FindAnnonceByCustomerId(Long userId) {
        Annonce annonce = annonceRepository.FindAnnonceByCustomerId(userId);

        return AnnonceDto.fromEntityToDto(annonce);
    }

    @Override
    public BigDecimal countNumbersOfAnnonces() {
        return annonceRepository.countNumberOfAnnonces();
    }

    @Override
    public BigDecimal countNumberOfAnnoncesInMonth() {
        return annonceRepository.countNumberOfAnnoncesInMonth();
    }

    @Override
    public BigDecimal countNumberOfAnnonceByStatusPending() {
        return annonceRepository.countNumberOfAnnonceByStatusPending();
    }

    @Override
    public List<AnnonceDto> findListAnnonceByStatusPending() {
        return annonceRepository.findListAnnonceByStatusPending().stream()
                .map(AnnonceDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AnnonceDto> findListAnnonceByStatusValid() {
        return annonceRepository.findListAnnonceByStatusValidated().stream()
                .map(AnnonceDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AnnonceDto> findListAnnonceByStatusRejet() {
        return annonceRepository.findListAnnonceByStatusRejected().stream()
                .map(AnnonceDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<?> countNumberTotalOfAnnonceByMonth() {
        return annonceRepository.countNumberOfAnnonceByMonth()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<?> countNumberTotalOfAnnonceByYear() {
        return annonceRepository.countNumberOfAnnonceByYear()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public Page<AnnonceDto> findAnnonceByPermisByPageable(Long permisId, Pageable pageable) {
        return annonceRepository.findAnnonceByPermisPageables(permisId, pageable)
                .map(AnnonceDto::fromEntityToDto);
    }

    @Override
    public Page<AnnonceDto> findAnnonceByPageable(Pageable pageable) {
        return annonceRepository.findAll(pageable)
                .map(AnnonceDto::fromEntityToDto);
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Annonce Id is null");
            return;
        }
        annonceRepository.deleteById(id);

    }

    @Override
    public List<AnnonceDto> getAllAnnonceDtos(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return annonceRepository.findAll(pageable)
                .map(AnnonceDto::fromEntityToDto).getContent();
    }

    @Override
    public List<AnnonceDto> getAllAnnonceDtosByIdPermis(Long id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return annonceRepository.findByPermisId(id, pageable)
                .map(AnnonceDto::fromEntityToDto).getContent();
    }

    @Override
    public List<AnnonceDto> getAllAnnonceDtosByKey(String libelle, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return annonceRepository.findByLibelleContaining(libelle, pageable)
                .map(AnnonceDto::fromEntityToDto).getContent();
    }

    @Override
    public long getAllAnnonceDtosSize() {
        return annonceRepository.count();
    }

    @Override
    public long getAnnonceDtosByPermisIdLength(Long id) {
        return annonceRepository.getAnnonceLengthByPermisId(id);
    }

    @Override
    public long getAnnonceDtosSizeByKey(String libelle) {
        return annonceRepository.getAnnonceSizeByKey(libelle);
    }


}
