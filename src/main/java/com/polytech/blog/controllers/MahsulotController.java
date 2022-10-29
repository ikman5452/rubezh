package com.polytech.blog.controllers;

import com.polytech.blog.models.Mahsulot;
import com.polytech.blog.repo.MahsulotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class MahsulotController {

    @Autowired
    private final MahsulotRepository mahsulotRepository;

    public MahsulotController(MahsulotRepository mahsulotRepository) {
        this.mahsulotRepository = mahsulotRepository;
    }

    @GetMapping("/mahsulot-main")
    public String mahsulotMain(Model model) {
        Iterable<Mahsulot> mahsulots = mahsulotRepository.findAll();
        model.addAttribute("mahsulots", mahsulots);
        return "mahsulot-main";
    }

    @GetMapping("/mahsulot-main/add")
    public String mahsulotAdd(Model model) {
        return "mahsulot-add";
    }

    @PostMapping("/mahsulot-main/add")
    public String mahsulotPostAdd(@RequestParam String nom, @RequestParam String srok, @RequestParam int narkh, Model model) {
        Mahsulot mahsulot = new Mahsulot(nom, srok, narkh);
        mahsulotRepository.save(mahsulot);
        return "redirect:/mahsulot-main";
    }

    @GetMapping("/mahsulot-main/{id}")
    public String mahsulotDetails(@PathVariable(value = "id") long id, Model model) {
        if (!mahsulotRepository.existsById(id)) {
            return "redirect:/mahsulot-main";
        }
        Optional<Mahsulot> mahsulots = mahsulotRepository.findById(id);
        ArrayList<Mahsulot> res = new ArrayList<>();
        mahsulots.ifPresent(res::add);
        model.addAttribute("mahsulot", res);
        return "mahsulot-details";
    }

    @GetMapping("/mahsulot-main/{id}/edit")
    public String mahsulotEdit(@PathVariable(value = "id") long id, Model model) {
        if (!mahsulotRepository.existsById(id)) {
            return "redirect:/mahsulot-main";
        }
        Optional<Mahsulot> mahsulot = mahsulotRepository.findById(id);
        ArrayList<Mahsulot> res = new ArrayList<>();
        mahsulot.ifPresent(res::add);
        model.addAttribute("mahsulot", res);
        return "mahsulot-edit";
    }

    @PostMapping("/mahsulot-main/{id}/edit")
    public String mahsulotUpdate(@PathVariable(value = "id") long id, @RequestParam String nom, @RequestParam String srok, @RequestParam int narkh, Model model) {
        Mahsulot mahsulot = mahsulotRepository.findById(id).orElseThrow();
        mahsulot.setNom(nom);
        mahsulot.setSrok(srok);
        mahsulot.setNarkh(narkh);
        mahsulotRepository.save(mahsulot);
        return "redirect:/mahsulot-main";
    }

    @PostMapping("/mahsulot-main/{id}/remove")
    public String mahsulotDelete(@PathVariable(value = "id") long id, Model model) {
        Mahsulot mahsulot = mahsulotRepository.findById(id).orElseThrow();
        mahsulotRepository.delete(mahsulot);
        return "redirect:/mahsulot-main";
    }
}
