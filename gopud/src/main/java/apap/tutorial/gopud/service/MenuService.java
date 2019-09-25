package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.MenuModel;

import java.util.List;

public interface MenuService {
    // Method untuk menambahkan menu ke restoran
    void addMenu(MenuModel menu);

    // Method untuk melihat semua menu berdasarkan idRestoran
    List<MenuModel> findAllMenuByIdRestoran(Long idRestoran);
}
