package lidl;

public class Product {
    String productId;
    String category;
    String mainCategory;
    String supplierName;
    String description;
    String name;
    String productPicUrl;
    String status;
    Long quantity;
    String currencyCode;
    Double price;
    Double score;

    void SetProductId(String prod) {
        productId = prod;
    }

    void SetCategory(String cat) {
        category = cat;
    }

    void SetMainCategory(String mainCat) {
        mainCategory = mainCat;
    }

    void SetSupplierName(String supplier) {
        supplierName = supplier;
    }

    void SetDescription(String desc) {
        description = desc;
    }

    void SetName(String n) {
        name = n;
    }

    void SetProductPicUrl (String url) {
        productPicUrl = url;
    }

    void SetPrice (Double p) {
        price = p;
    }

    void SetQuantity (Long q) {
        quantity = q;
    }

    void SetStatus (String s) {
        status = s;
    }

    void SetCurrencyCode (String cCode) {
        currencyCode = cCode;
    }
}
