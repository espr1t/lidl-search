package lidl;
import lidl.Product;

import java.util.List;
import java.util.ArrayList;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import static spark.Spark.*;
import spark.Request;
import spark.Response;

public class Main {
    public static void main(String[] args) {
        // Parse json file with products
        try {
            List<Product> products = new ArrayList<Product>();
            Object obj = new JSONParser().parse(new FileReader("/Users/rdimitrova/IdeaProjects/lidl/src/main/java/small_product_dataset.json"));
            JSONArray ja = (JSONArray) obj;
            for (int i = 0; i < ja.size(); ++i) {
                JSONObject jo = (JSONObject) ja.get(i);
                Product prod = new Product();
                prod.SetProductId((String) jo.get("ProductId"));
                prod.SetCategory((String) jo.get("Category"));
                prod.SetMainCategory((String) jo.get("MainCategory"));
                prod.SetTaxTarifCode((String) jo.get("TaxTarifCode"));
                prod.SetSupplierName((String) jo.get("SupplierName"));
                prod.SetWeightMeasure((Double) jo.get("WeightMeasure"));
                prod.SetWeightUnit((String) jo.get("WeightUnit"));
                prod.SetDescription((String) jo.get("Description"));

                System.out.println(prod.weightUnit);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        get("/complete", lidl.Main::handleComplete);
    }

    private static String handleComplete(Request request, Response response) {
        //System.out.println(request.params());
        //System.out.println(request.attributes());
        //System.out.println(request.queryMap().get("string").value());
        return "Hello, World!";
    }
}
