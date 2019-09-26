package apap.tutorial.gopud.controller;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.service.MenuService;
import apap.tutorial.gopud.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MenuController {
    @Autowired
    MenuService menuService;

    @Qualifier("restoranServiceImpl")
    @Autowired
    RestoranService restoranService;

    @RequestMapping(value = "/menu/add/{idRestoran}", method = RequestMethod.GET)
    private String addProductFormPage(@PathVariable(value = "idRestoran") Long idRestoran, Model model){
        MenuModel menu = new MenuModel();
        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran).get();
        menu.setRestoran(restoran);

        model.addAttribute("menu", menu);

        return "form-add-menu";
    }

    @RequestMapping(value = "menu/add", method = RequestMethod.POST)
    private String addProductSubmit(@ModelAttribute MenuModel menu, Model model){
        menuService.addMenu(menu);
        model.addAttribute("nama", menu.getNama());

        return "add-menu";
    }

    //API yang digunakan untuk menuju halaman form change restoran
    @RequestMapping(value = "menu/change/{id}", method = RequestMethod.GET)
    public String changeMenuFormPage(@PathVariable Long id, Model model){
        //Mengambil existing data restoran
        MenuModel existingMenu = menuService.getMenuByIdMenu(id).get();
        model.addAttribute("menu", existingMenu);

        return "form-change-menu";
    }

    //API yang digunakan untuk submit form change menu
    @RequestMapping(value = "menu/change/{id}", method = RequestMethod.POST)
    public String changeMenuFormSubmit(@PathVariable Long id, @ModelAttribute MenuModel menu, Model model){
        MenuModel newMenuData = menuService.changeMenu(menu);
        model.addAttribute("menu", newMenuData);

        return "change-menu";
    }

    //Delete Menu
    @RequestMapping(value = "menu/delete/{id}", method = RequestMethod.GET)
    public String deleteMenuPage(@PathVariable Long id, @ModelAttribute MenuModel menu, Model model){
        //Mengambil existing data menu
        MenuModel
    }
}
