package lidl;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;

public class Parser {
    static List<Product> products;

    public static void parseProducts() {
        // Parse json file with products
        try {
            String dataPath = System.getProperty("user.dir") + "/data/small_product_dataset.json";
            System.out.println("Data path: " + dataPath);
            products = new ArrayList<>();
            Object obj = new JSONParser().parse(new FileReader(dataPath));
            JSONArray ja = (JSONArray) obj;
            for (int i = 0; i < ja.size(); ++i) {
                JSONObject jo = (JSONObject) ja.get(i);
                Product prod = new Product();
                prod.SetProductId((String) jo.get("ProductId"));
                prod.SetCategory((String) jo.get("Category"));
                prod.SetMainCategory((String) jo.get("MainCategory"));
                prod.SetSupplierName((String) jo.get("SupplierName"));
                prod.SetDescription((String) jo.get("Description"));
                prod.SetName((String) jo.get("Name"));
                prod.SetStatus((String) jo.get("Status"));
                prod.SetProductPicUrl((String) jo.get("ProductPicUrl"));
                Object price = jo.get("Price");
                if (price instanceof Long)
                    prod.SetPrice(((Long) price).doubleValue());
                else
                    prod.SetPrice((Double) price);
                prod.SetQuantity((Long) jo.get("Quantity"));
                prod.SetCurrencyCode((String) jo.get("CurrencyCode"));
                products.add(prod);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Product> getProducts() {
        return products;
    }

}
