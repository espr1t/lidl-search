package lidl;

public class Product {
    String productId;
    String category;
    String mainCategory;
    String taxTarifCode;
    String supplierName;
    Double weightMeasure;
    String weightUnit;
    String description;
    String name;
    String dateOfSale;
    String productPicUrl;
    String status;
    Integer quantity;
    String currencyCode;
    Double price;
    Double width;
    Double depth;
    Double height;
    String dimUnit;

    void SetProductId(String prod) {
        productId = prod;
    }

    void SetCategory(String cat) {
        category = cat;
    }

    void SetMainCategory(String mainCat) {
        mainCategory = mainCat;
    }

    void SetTaxTarifCode(String taxCode) {
        taxTarifCode = taxCode;
    }

    void SetSupplierName(String supplier) {
        supplierName = supplier;
    }

    void SetWeightMeasure(Double wMeasure) {
        weightMeasure = wMeasure;
    }

    void SetWeightUnit(String wUnit) {
        weightUnit = wUnit;
    }

    void SetDescription(String desc) {
        description = desc;
    }

    void SetName(String n) {
        name = n;
    }
}
