package com.ra.session09.service;

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
    public boolean saveSeed(Seed seed) {
        return seedRepository.addSeed(seed);
    }

    @Transactional
    public boolean updateSeed(Seed seed) {
        Seed oldSeed = findById(seed.getId());
        if (oldSeed == null) {
            return false;
        }else {
            return seedRepository.updateSeed(seed);
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


}
