package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.repository.MenuDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.awt.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class MenuRestServiceImpl implements MenuRestService {
    @Autowired
    private MenuDB menuDB;

    @Override
    public MenuModel createMenu(MenuModel menuModel) {
        return menuDB.save(menuModel);
    }

    @Override
    public List<MenuModel> retriveListMenu() {
        return menuDB.findAll();
    }

    @Override
    public MenuModel getMenuByIdMenu(Long idMenu) {
        Optional<MenuModel> menu = menuDB.findById(idMenu);
        if(menu.isPresent()){
            return menu.get();
        }else{
            throw new NoSuchElementException();
        }
    }

    @Override
    public MenuModel changeMenu(Long idMenu, MenuModel menuUpdate) {
        MenuModel menu = getMenuByIdMenu(idMenu);
        menu.setNama(menuUpdate.getNama());
        menu.setDeskripsi(menuUpdate.getDeskripsi());
        menu.setHarga(menuUpdate.getHarga());
        menu.setDurasiMasak(menuUpdate.getDurasiMasak());
        return menuDB.save(menu);
    }

    @Override
    public void deleteMenu(Long idMenu) {
        MenuModel menu = getMenuByIdMenu(idMenu);
        menuDB.delete(menu);
    }
}
