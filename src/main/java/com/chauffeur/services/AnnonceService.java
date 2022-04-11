package com.chauffeur.services;

import com.chauffeur.dto.AnnonceDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface AnnonceService {

    AnnonceDto save(AnnonceDto annonceDto);

    AnnonceDto update(Long idAnnonce, AnnonceDto annonceDto);

    AnnonceDto updateStatusOfAnnonce(String status, String id);

    AnnonceDto findById(Long id);

    AnnonceDto findByReference(String reference);

    AnnonceDto FindAnnonceByCustomerId(Long userId);

    BigDecimal countNumbersOfAnnonces();

    BigDecimal countNumberOfAnnoncesInMonth();

    BigDecimal countNumberOfAnnonceByStatusPending();

    List<AnnonceDto> findAll();

    List<AnnonceDto> findByAnnonceByIdDesc();

    List<AnnonceDto> findListAnnonceBySelected();

    List<AnnonceDto> FindListAnnonceByCustomerId(Long userId);

    List<AnnonceDto> findListAnnonceByKeyword(String keyword);

    List<AnnonceDto> findListAnnonceByLibelle(String libelle);

    List<AnnonceDto> findListAnnonceByPermis(Long pId);

    List<AnnonceDto> find5LatestRecordsByOrderByIdDesc();

    List<AnnonceDto> find6LatestValidatedRecordsByOrderByIdDesc();

    List<AnnonceDto> findListAnnonceByStatusPending();

    List<AnnonceDto> findListAnnonceByStatusValid();

    List<AnnonceDto> findListAnnonceByStatusRejet();

    List<?> countNumberTotalOfAnnonceByMonth();

    List<?> countNumberTotalOfAnnonceByYear();

    Page<AnnonceDto> findAnnonceByPageable(Pageable pageable);

    Page<AnnonceDto> findAnnonceByPermisByPageable(Long permisId, Pageable pageable);

    void delete(Long id);

    List<AnnonceDto> getAllAnnonceDtos(int page, int size);

    List<AnnonceDto> getAllAnnonceDtosByIdPermis(Long id, int page, int size);

    List<AnnonceDto> getAllAnnonceDtosByKey(String libelle, int page, int size);

    long getAllAnnonceDtosSize();

    long getAnnonceDtosByPermisIdLength(Long id);

    long getAnnonceDtosSizeByKey(String libelle);


}
