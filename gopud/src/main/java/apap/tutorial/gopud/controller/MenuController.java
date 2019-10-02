package apap.tutorial.gopud.controller;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.service.MenuService;
import apap.tutorial.gopud.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class MenuController {
    @Autowired
    MenuService menuService;

    @Qualifier("restoranServiceImpl")
    @Autowired
    RestoranService restoranService;

    // API yang digunakan untuk menuju halaman form-add-restoran
    @RequestMapping(value = "/menu/add/{idRestoran}", method = RequestMethod.GET)
    private String addProductFormPage(@PathVariable(value = "idRestoran") Long idRestoran, Model model){
        MenuModel menu = new MenuModel();
        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran).get();
        restoran.setListMenu(new ArrayList<MenuModel>());
        restoran.getListMenu().add(menu);

        model.addAttribute("restoran", restoran);
        model.addAttribute("idRestoran", idRestoran);

        return "form-add-menu";
    }

    // API yang digunakan ketika menambah row
    @RequestMapping(value = "/menu/add/{idRestoran}", method = RequestMethod.POST, params = {"addRow"})
    private String addRow(@PathVariable(value = "idRestoran") Long idRestoran, @ModelAttribute RestoranModel restoran, Model model){
        MenuModel menu = new MenuModel();
        restoran.getListMenu().add(menu);

        model.addAttribute("restoran", restoran);
        model.addAttribute("idRestoran", idRestoran);

        return "form-add-menu";
    }

    // API yang digunakan ketika menghapus row
    @RequestMapping(value = "/menu/add/{idRestoran}", method = RequestMethod.POST, params = {"removeRow"})
    private String removeRow(@PathVariable(value = "idRestoran") Long idRestoran, @ModelAttribute RestoranModel restoran, Model model, HttpServletRequest request){
        Integer rowId = Integer.valueOf(request.getParameter("removeRow"));
        restoran.getListMenu().remove(rowId.intValue());

        model.addAttribute("idRestoran", idRestoran);
        model.addAttribute("restoran", restoran);

        return "form-add-menu";

    }

    @RequestMapping(value = "menu/add/{idRestoran}", method = RequestMethod.POST, params = {"add"})
    private String addProductSubmit(@PathVariable(value = "idRestoran") Long idRestoran, @ModelAttribute RestoranModel restoran, ModelMap model){
        RestoranModel oldRestoran = restoranService.getRestoranByIdRestoran(idRestoran).get();
        model.addAttribute("idRestoran", idRestoran);
        for(MenuModel menu : restoran.getListMenu()){
            menu.setRestoran(oldRestoran);
            menuService.addMenu(menu);
        }
        model.clear();

        return "add-menu";
    }

    //API yang digunakan untuk menuju halaman form change restoran
    @RequestMapping(value = "menu/change/{id}", method = RequestMethod.GET)
    public String changeMenuFormPage(@PathVariable Long id, Model model){
        //Mengambil existing data menu
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
    @RequestMapping(value = "menu/delete", method = RequestMethod.POST)
    private String delete(@ModelAttribute RestoranModel restoran, Model model){
        for (MenuModel menu : restoran.getListMenu()){
            menuService.deleteMenu(menu);
        }
        return "delete-menu";
    }
}
