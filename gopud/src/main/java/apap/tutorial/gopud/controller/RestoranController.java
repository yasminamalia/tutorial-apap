package apap.tutorial.gopud.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.service.RestoranService;

@Controller
public class RestoranController {
    @Qualifier("restoranServiceImpl")
    @Autowired
    private RestoranService restoranService;

    @Autowired
    private MenuService menuService;

//    @RequestMapping("/")
//    public String home() {
//        return "home";
//    }

    //URL mapping yang digunakan untuk mengakses halaman add restoran
    @RequestMapping(value = "/restoran/add", method = RequestMethod.GET)
    public String addRestoranFromPage(Model model){
        RestoranModel newRestoran = new RestoranModel();
        model.addAttribute("restoran", newRestoran);
        return "form-add-restoran";
    }

    //URL mapping yang digunakan untuk submit form yang telah dimasukkan pada halaman add restoran
    @RequestMapping(value = "/restoran/add", method = RequestMethod.POST)
    public String addRestoranSubmit(@ModelAttribute RestoranModel restoran, Model model){
        restoranService.addRestoran(restoran);
        model.addAttribute("namaResto", restoran.getNama());
        return "add-restoran";
    }

    //URL mapping view
    @RequestMapping(path = "/restoran/view", method = RequestMethod.GET)
    public String view(
            //Request parameter untuk dipass
            @RequestParam(value = "idRestoran") Long idRestoran, Model model
    ){
        //Mengambil objek RestoranModel yang dituju
        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran).get();

        List<MenuModel> menuList = menuService.getListMenuOrderByHargaAsc(restoran.getIdRestoran());
        restoran.setListMenu(menuList);

        //Add model restoran ke "resto" untuk dirender
        model.addAttribute("resto", restoran);
        model.addAttribute("listMenu", menuList);

        //Return view template
        return "view-restoran";
    }

    //URL mapping view all
    @RequestMapping(path = "/restoran/view-all", method = RequestMethod.GET)
    public String viewAll(Model model){
        //Mengambil semua objek RestoranModel yang ada
        List<RestoranModel> listResto = restoranService.getRestoranList();

        //Sorting restoran berdasarkan nama
        Collections.sort(listResto, new Comparator<RestoranModel>() {
            @Override
            public int compare(RestoranModel o1, RestoranModel o2) {
                return o1.getNama().compareTo(o2.getNama());
            }
        });

        // Add model restoran ke "resto" untuk dirender
        model.addAttribute("listRestoran", listResto);

        // Return view template
        return "view-all";
    }


    //API yang digunakan untuk menuju halaman form change restoran
    @RequestMapping(value = "restoran/change/{idRestoran}", method = RequestMethod.GET)
    public String changeRestoranFormPage(@PathVariable Long idRestoran, Model model){
        //Mengambil existing data restoran
        RestoranModel existingRestoran = restoranService.getRestoranByIdRestoran(idRestoran).get();
        model.addAttribute("restoran", existingRestoran);
        return "form-change-restoran";
    }

    //API yang digunakan untuk submit form change restoran
    @RequestMapping(value = "restoran/change/{idRestoran}", method = RequestMethod.POST)
    public String changeRestoranFormSubmit(@PathVariable Long idRestoran, @ModelAttribute RestoranModel restoran, Model model){
        RestoranModel newRestoranData = restoranService.changeRestoran(restoran);
        model.addAttribute("restoran", newRestoranData);

        return "change-restoran";
    }

    //Delete Restoran
    @RequestMapping(value = "restoran/delete/{idRestoran}")
    public String deleteRestoran(@PathVariable Long idRestoran, Model model){
        RestoranModel deleteRestoran = restoranService.getRestoranByIdRestoran(idRestoran).get();
        if (deleteRestoran == null){
            return "error";
        }else{
            if(deleteRestoran.getListMenu().isEmpty()){
                model.addAttribute("restoran", deleteRestoran.getNama());
                restoranService.deleteRestoran(deleteRestoran);
                return "delete-restoran";
            }
            return "delete-restoran-message";
        }
    }
}
