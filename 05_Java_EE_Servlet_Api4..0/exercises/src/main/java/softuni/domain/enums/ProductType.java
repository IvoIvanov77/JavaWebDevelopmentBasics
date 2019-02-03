package softuni.domain.enums;

public enum ProductType {

    FOOD, DOMESTIC, HEALTH, COSMETIC, OTHER;

    @Override
    public String toString() {
        switch (this.name()){
            case "FOOD" : return "Food";
            case "DOMESTIC" : return "Domestic";
            case "HEALTH" : return "Health";
            case "COSMETIC" : return "Cosmetic";
            case "OTHER" : return "Other";
        }
        return this.name();
    }
}
