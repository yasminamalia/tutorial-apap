package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.repository.MenuDB;
import apap.tutorial.gopud.model.RestoranModel;

import apap.tutorial.gopud.repository.RestoranDB;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MenuServiceImplTest {
    @InjectMocks
    MenuService menuService = new MenuServiceImpl();

    @Mock
    MenuDB menuDB;

    @Test
    public void whenAddValidMenuItShouldCallMenuRepositorySave(){
        MenuModel newMenu = new MenuModel();
        newMenu.setNama("burger");
        newMenu.setDeskripsi("burger ayam");
        newMenu.setHarga(BigInteger.valueOf(20000));
        newMenu.setDurasiMasak(10);

        menuService.addMenu(newMenu);

        verify(menuDB, times(1)).save(newMenu);
    }

    @Test
    public void whenFindAllMenuByIdRestoranCalledItShouldReturnAllMenu(){
        List<MenuModel> allMenuInDatabase = new ArrayList<>();
        for (int loopTimes = 3; loopTimes > 0; loopTimes--){
            allMenuInDatabase.add(new MenuModel());
        }

        when(menuService.findAllMenuByIdRestoran(Long.valueOf(1))).thenReturn(allMenuInDatabase);

        List<MenuModel> dataFromServiceCall = menuService.findAllMenuByIdRestoran(Long.valueOf(1));
        assertEquals(3, dataFromServiceCall.size());

        verify(menuDB, times(1)).findByRestoranIdRestoran(Long.valueOf(1));
    }

    @Test
    public void whenGetMenuByIdMenuCalledByExistingDataItShouldReturnCorrectData(){
        MenuModel returnedData = new MenuModel();
        returnedData.setNama("burger");
        returnedData.setDeskripsi("burger daging");
        returnedData.setHarga(BigInteger.valueOf(28000));
        returnedData.setDurasiMasak(10);

        when(menuService.getMenuByIdMenu(1L)).thenReturn(Optional.of(returnedData));

        Optional<MenuModel> dataFromServiceCall = menuService.getMenuByIdMenu(1L);

        verify(menuDB, times(1)).findById(1L);
        assertTrue(dataFromServiceCall.isPresent());

        MenuModel dataFromOptional = dataFromServiceCall.get();
        assertEquals("burger", dataFromOptional.getNama());
        assertEquals("burger daging", dataFromOptional.getDeskripsi());
        assertEquals(BigInteger.valueOf(28000), dataFromOptional.getHarga());
        assertEquals(Integer.valueOf(10), dataFromOptional.getDurasiMasak());
    }

    @Test
    public void whenChangeMenuCalledItShouldChangeMenu(){
        MenuModel updatedData = new MenuModel();
        updatedData.setId(Long.valueOf(1));
        updatedData.setNama("kebab");
        updatedData.setDeskripsi("kebab daging");
        updatedData.setHarga(BigInteger.valueOf(28000));
        updatedData.setDurasiMasak(10);

        when(menuDB.findById(1L)).thenReturn(Optional.of(updatedData));
        when(menuService.changeMenu(updatedData)).thenReturn(updatedData);

        MenuModel dataFromServiceCall = menuService.changeMenu(updatedData);
        assertEquals("kebab", dataFromServiceCall.getNama());
        assertEquals("kebab daging",dataFromServiceCall.getDeskripsi());
        assertEquals(BigInteger.valueOf(28000), dataFromServiceCall.getHarga());
        assertEquals(Integer.valueOf(10), dataFromServiceCall.getDurasiMasak());
    }

    @Test
    public void whenDeleteMenuCalledItShouldDeleteMenu(){
        MenuModel deletedData = new MenuModel();
        deletedData.setId(1L);
        deletedData.setNama("burger");
        deletedData.setDeskripsi("burger ayam");
        deletedData.setHarga(BigInteger.valueOf(20000));
        deletedData.setDurasiMasak(10);

        when(menuDB.findById(1L)).thenReturn(Optional.of(deletedData));
        menuService.addMenu(deletedData);
        menuService.deleteMenu(deletedData);

        verify(menuDB, times(1)).delete(deletedData);
    }

    @Test
    public void whenGetListMenuOrderByHargaAscCalledItShouldReturnListMenuOrderByHargaAsc(){
        List<MenuModel> allMenuInDatabase = new ArrayList<>();
        for (int loopTimes = 3; loopTimes > 0; loopTimes--){
            allMenuInDatabase.add(new MenuModel());
        }

        when(menuService.getListMenuOrderByHargaAsc(1L)).thenReturn(allMenuInDatabase);

        List<MenuModel> dataFromServiceCall = menuService.getListMenuOrderByHargaAsc(1L);
        assertEquals(3, dataFromServiceCall.size());

        verify(menuDB, times(1)).findByRestoranIdRestoranOrderByHargaAsc(1L);
    }
}
