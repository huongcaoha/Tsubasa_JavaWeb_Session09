package com.ra.session09.service;

import com.ra.session09.model.dto.SeedDTO;
import com.ra.session09.model.entity.Seed;
import com.ra.session09.repository.SeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SeedService {
    @Autowired
    private SeedRepository seedRepository;

    @Autowired
    private CategoryService categoryService;

    @Transactional(readOnly = true)
    public List<Seed> findAllAndSearch(int page, int size,String searchName) {
        return seedRepository.getAllAndPagination(page, size,searchName);
    }

    @Transactional(readOnly = true)
    public Seed findById(long id) {
        return seedRepository.findSeedById(id);
    }

    @Transactional(readOnly = true)
    public long countAllSeeds(String searchName) {
        return seedRepository.countAllSeeds(searchName);
    }

    @Transactional
    public boolean saveSeed(SeedDTO seedDTO) {
        return seedRepository.addSeed(convertSeedDTOToSeed(seedDTO));
    }

    @Transactional
    public boolean updateSeed(long id ,SeedDTO seedDTO) {
        Seed oldSeed = findById(id);
        if (oldSeed == null) {
            return false;
        }else {
            Seed newSeed = convertSeedDTOToSeed(seedDTO);
            newSeed.setId(oldSeed.getId());
            return seedRepository.updateSeed(newSeed);
        }
    }

    @Transactional
    public boolean deleteSeedById(long id) {
        Seed seed = findById(id);
        if (seed == null) {
            return false;
        }else {
            return seedRepository.deleteSeedById(id);
        }
    }

    @Transactional
    public boolean checkSeedNameExists(String seedName) {
        return seedRepository.checkSeedNameExists(seedName) > 0;
    }

    public Seed convertSeedDTOToSeed(SeedDTO seedDTO) {
        return Seed
                .builder()
                .seedName(seedDTO.getSeedName())
                .quantity(seedDTO.getQuantity())
                .category(categoryService.findById(seedDTO.getCategoryId()))
                .build();
    }

    public SeedDTO convertSeedToSeedDTO(Seed seed) {
        return SeedDTO
                .builder()
                .seedName(seed.getSeedName())
                .quantity(seed.getQuantity())
                .categoryId(seed.getCategory().getId())
                .build();
    }
}
