package opg.softuni.panda.domain.dto;

public class PackageViewHomePageModel {
    private String id;

    private String description;

    public String getId() {
        return id;
    }

    public PackageViewHomePageModel() {
    }

    public PackageViewHomePageModel(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
