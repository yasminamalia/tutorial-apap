package apap.tutorial.gopud.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChefDetail {
    @JsonProperty("nama")
    private String nama;

    @JsonProperty("spesialis")
    private String spesialis;

    @JsonProperty("experience_in_years")
    private Integer experience_in_years;

    @JsonProperty("listmenu")
    private List<MenuDetail> listMenu;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getSpesialis() {
        return spesialis;
    }

    public void setSpesialis(String spesialis) {
        this.spesialis = spesialis;
    }

    public Integer getExperience_in_years() {
        return experience_in_years;
    }

    public void setExperience_in_years(Integer experience_in_years) {
        this.experience_in_years = experience_in_years;
    }

    public List<MenuDetail> getListMenu() {
        return listMenu;
    }

    public void setListMenu(List<MenuDetail> listMenu) {
        this.listMenu = listMenu;
    }
}
