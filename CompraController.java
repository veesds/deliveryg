package br.senai.controller;

import br.senai.model.Compra;
import br.senai.model.Evento;
import br.senai.service.CompraServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CompraController {
    @Autowired
    CompraServiceImpl compraService;

    @GetMapping("/compra/list")
    public String findAll(Model model){
        model.addAttribute("compras", compraService.findAll());
        return "compra/list";
    }

    @GetMapping("/compra/add")
    public String add(Model model){
        model.addAttribute("compra", new Compra());
        return "compra/add";
    }

    @GetMapping("/compra/edit/{id}")
    public String edit(Model model, @PathVariable long id){
        model.addAttribute("compra", compraService.findById(id));
        return "compra/edit";
    }

    @PostMapping("/compra/save")
    public String save(Compra compra, Model model){
        try{
            compraService.save(compra);
            model.addAttribute("compra", compra);
            model.addAttribute("isSave", true);
            model.addAttribute("isError", false);
            return "compra/add";
        } catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
            model.addAttribute("compra", compra);
            model.addAttribute("isError", true);
            model.addAttribute("errorMsg", e.getMessage());
            return "compra/add";
        }
    }

    @GetMapping("/compra/delete/{id}")
    public String delete(@PathVariable long id){
        try{
            compraService.deleteById(id);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return "redirect:/compra/list";
    }
    @PostMapping("/compra/search")
    public String search(Compra compra, Model model ){
        model.addAttribute("compra", compraService.searchFunction(compra.getProduto()));
        return "compra/list";
    }
}
