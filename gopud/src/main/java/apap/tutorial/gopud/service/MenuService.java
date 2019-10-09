package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.MenuModel;

import java.util.List;
import java.util.Optional;

public interface MenuService {
    // Method untuk menambahkan menu ke restoran
    void addMenu(MenuModel menu);

    // Method untuk melihat semua menu berdasarkan idRestoran
    List<MenuModel> findAllMenuByIdRestoran(Long idRestoran);

    // Method untuk mendapatkan sebuah Menu berdasarkan id
    Optional<MenuModel> getMenuByIdMenu(Long id);

    // Method untuk mengganti menu
    MenuModel changeMenu(MenuModel menuModel);

    // Method untuk menghapus menu
    void deleteMenu(MenuModel menuModel);

    // Method untuk mendapatkan menu berdasarkan harga asc
    List<MenuModel> getListMenuOrderByHargaAsc(Long idRestoran);
}
