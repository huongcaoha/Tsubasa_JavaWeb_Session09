package com.ra.session09.controller;
import com.ra.session09.model.entity.Seed;
import com.ra.session09.service.CategoryService;
import com.ra.session09.service.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/seeds")
public class SeedController {
    @Autowired
    private SeedService seedService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showSeeds(Model model , @RequestParam(name = "page"  ,defaultValue = "1") int page,
                            @RequestParam(name = "size" , defaultValue = "5") int size,
                            @RequestParam(name = "searchName", required = false) String searchName) {
        List<Seed> seeds = seedService.findAllAndSearch(page, size , searchName);
        long totalElements = seedService.countAllSeeds(searchName);
        int totalPages = (int) Math.ceil(totalElements / (double) size);
        List<Integer> pages = new ArrayList<Integer>();
        for (int i = 1; i <= totalPages; i++) {
            pages.add(i);
        }
        model.addAttribute("searchName", searchName);
        model.addAttribute("pages", pages);
        model.addAttribute("seeds", seeds);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        return "seeds";
    }

    @GetMapping("/add")
    public String showAdd(Model model) {
        model.addAttribute("seed", new Seed());
        model.addAttribute("categories", categoryService.findAll());
        return "addSeed";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Seed seed, Model model , RedirectAttributes redirectAttributes) {
       if (seedService.checkSeedNameExists(seed.getSeedName())){
           model.addAttribute("error", "This seed already exists");
           model.addAttribute("seed", seed);
           return "addSeed";
       }else {
           boolean rs = seedService.saveSeed(seed);
           if(rs) {
               redirectAttributes.addFlashAttribute("message", "Seed added successfully");
               return "redirect:/seeds";
           }else {
               model.addAttribute("message", "Add seed failed");
               model.addAttribute("seed", seed);
               return "addSeed";
           }
       }
    }

    @GetMapping("/edit/{id}")
    public String showEdit(Model model, @PathVariable long id) {
        model.addAttribute("seed", seedService.findById(id));
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("seedId" ,id);
        return "editSeed";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable long id,@ModelAttribute Seed seed, Model model , RedirectAttributes redirectAttributes) {
        Seed oldSeed = seedService.findById(id);
        if (seedService.checkSeedNameExists(seed.getSeedName()) && !seed.getSeedName().equalsIgnoreCase(oldSeed.getSeedName())) {
            model.addAttribute("error", "This seed already exists");
            model.addAttribute("seed", seed);
            model.addAttribute("seedId" ,id);
            model.addAttribute("categories", categoryService.findAll());
            return "editSeed";
        }else {
            boolean rs = seedService.updateSeed(seed);
            if(rs) {
                redirectAttributes.addFlashAttribute("message", "Seed update successfully");
                return "redirect:/seeds";
            }else {
                model.addAttribute("message", "Update seed failed");
                model.addAttribute("seed", seed);
                model.addAttribute("seedId" ,id);
                model.addAttribute("categories", categoryService.findAll());
                return "editSeed";
            }
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id, RedirectAttributes redirectAttributes) {
        boolean rs = seedService.deleteSeedById(id);
        if(rs) {
            redirectAttributes.addFlashAttribute("message", "Seed deleted successfully");
        }else {
            redirectAttributes.addFlashAttribute("message", "Delete seed failed");
        }
        return "redirect:/seeds";
    }
}
