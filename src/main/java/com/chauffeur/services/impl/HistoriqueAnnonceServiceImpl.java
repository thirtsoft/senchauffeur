package com.chauffeur.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chauffeur.dto.HistoriqueAnnonceDto;
import com.chauffeur.exceptions.ResourceNotFoundException;
import com.chauffeur.models.HistoriqueAnnonce;
import com.chauffeur.repository.HistoriqueAnnonceRepository;
import com.chauffeur.services.HistoriqueAnnonceService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class HistoriqueAnnonceServiceImpl implements HistoriqueAnnonceService {
	
	@Autowired
    private final HistoriqueAnnonceRepository historiqueAnnonceRepository;
	
	public HistoriqueAnnonceServiceImpl(HistoriqueAnnonceRepository historiqueAnnonceRepository) {
        this.historiqueAnnonceRepository = historiqueAnnonceRepository;
    }
	
	@Override
	public HistoriqueAnnonceDto save(HistoriqueAnnonceDto historiqueAnnonceDto) {
		return HistoriqueAnnonceDto.fromEntityToDto(
				historiqueAnnonceRepository.save(
						HistoriqueAnnonceDto.fromDtoToEntity(historiqueAnnonceDto)
	                )
	        );
	}

	@Override
	public HistoriqueAnnonceDto update(Long id, HistoriqueAnnonceDto historiqueAnnonceDto) {
		if (!historiqueAnnonceRepository.existsById(id)) {
            throw new ResourceNotFoundException("HistoriqueAnnonce not found");
        }

        Optional<HistoriqueAnnonce> historiqueAnnonce = historiqueAnnonceRepository.findById(id);

        if (!historiqueAnnonce.isPresent()) {
            throw new ResourceNotFoundException("HistoriqueLogin not found");
        }
        
        HistoriqueAnnonceDto historiqueAnnonceDtoResult = HistoriqueAnnonceDto.fromEntityToDto(historiqueAnnonce.get());
        historiqueAnnonceDtoResult.setAction(historiqueAnnonceDto.getAction());
        historiqueAnnonceDtoResult.setCreatedDate(historiqueAnnonceDto.getCreatedDate());
        historiqueAnnonceDtoResult.setAnnonceDto(historiqueAnnonceDto.getAnnonceDto());
       
        return HistoriqueAnnonceDto.fromEntityToDto(
        		historiqueAnnonceRepository.save(
        				HistoriqueAnnonceDto.fromDtoToEntity(historiqueAnnonceDtoResult)
                )
        );
        
	}

	@Override
	public HistoriqueAnnonceDto findById(Long id) {
		if (id == null) {
            log.error("HistoriqueLogin Id is null");
            return null;
        }

        Optional<HistoriqueAnnonce> historiqueAnnonce = historiqueAnnonceRepository.findById(id);

        return Optional.of(HistoriqueAnnonceDto.fromEntityToDto(historiqueAnnonce.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun HistoriqueAnnonce avec l'Id = " + id + "n'a été trouvé")
        );
	}

	@Override
	public List<HistoriqueAnnonceDto> findAll() {
		 return historiqueAnnonceRepository.findAll()
				 .stream()
	             .map(HistoriqueAnnonceDto::fromEntityToDto)
	             .collect(Collectors.toList());
	}

	@Override
	public List<HistoriqueAnnonceDto> findHistoriqueAnnonceByOrderByIdDesc() {
		return historiqueAnnonceRepository.findHistoriqueAnnonceByOrderByIdDesc()
				 .stream()
	             .map(HistoriqueAnnonceDto::fromEntityToDto)
	             .collect(Collectors.toList());
	}

	@Override
	public BigDecimal countNumbersOfHistoriqueAnnonces() {
		return historiqueAnnonceRepository.countNumberOfHistoriqueAnnonces();
	}

	@Override
	public void delete(Long id) {
		if (id == null) {
            log.error("HistoriqueAnnonce Id is null");
            return;
        }
		historiqueAnnonceRepository.deleteById(id);
	}

}
