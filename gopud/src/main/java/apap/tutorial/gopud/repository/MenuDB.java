package apap.tutorial.gopud.repository;

import apap.tutorial.gopud.model.MenuModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MenuDB extends JpaRepository<MenuModel, Long> {
    List<MenuModel> findByRestoranIdRestoran(Long restoranId);
    List<MenuModel> findByRestoranIdRestoranOrderByHargaAsc(Long restoranId);
    @Override
    Optional<MenuModel> findById(Long id);
}
