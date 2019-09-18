package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.RestoranModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestoranInMemoryService implements RestoranService {
    private List<RestoranModel> listRestoran;

    // Constructor
    public RestoranInMemoryService() {
        listRestoran = new ArrayList<>();
    }

    @Override
    public void addRestoran(RestoranModel restoran){
        listRestoran.add(restoran);
    }

    @Override
    public List<RestoranModel> getRestoranList(){
        return listRestoran;
    }

    @Override
    public RestoranModel getRestoranByIdRestoran(String idRestoran) {
        for (RestoranModel restoran: listRestoran){
            if (restoran.getIdRestoran().equals(idRestoran)){
                return restoran;
            }
        }
        return null;
    }

    @Override
    public void deleteRestoran(RestoranModel restoran){
        listRestoran.remove(restoran);
    }

}
