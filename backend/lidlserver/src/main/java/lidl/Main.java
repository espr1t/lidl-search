package lidl;

import java.util.*;

import static spark.Spark.*;
import spark.Request;
import spark.Response;
import spark.Filter;
import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

    public static void main(String[] args) {
        Parser.parseProducts();

        // Enable CORS requests for working with local files.
        after((Filter) (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET");
        });
        post("/complete", Main::handleComplete);
    }

    private static JSONObject getItem(Product product) {
        JSONObject item = new JSONObject();
        item.put("name", product.name);
        item.put("vendor", product.supplierName);
        item.put("price", product.price);
        return item;
    }

    private static double getMatchScore(String needle, String haystack) {
        needle =  needle.toLowerCase();
        haystack = haystack.toLowerCase();
        return haystack.contains(needle) ? 1.0 : 0.0;
    }

    private static String handleComplete(Request request, Response response) {
        double SCORE_PRODUCT_ID = 100000;
        double SCORE_NAME = 10000;
        double SCORE_DESCRIPTION = 1000;
        double SCORE_SUPPLIER = 100;
        double SCORE_PRODUCT_CATEGORY = 10;
        double SCORE_PRODUCT_MAIN_CATEGORY = 1;

        List<Product> products = Parser.getProducts();
        System.out.println(products.size());

        String search = request.queryMap().get("text").value();
        System.out.println("Search string is: " + search);

        List<Product> selected = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            product.score = 0.0;
            product.score += SCORE_PRODUCT_ID * Main.getMatchScore(search, product.productId);
            product.score += SCORE_PRODUCT_CATEGORY * Main.getMatchScore(search, product.category);
            product.score += SCORE_PRODUCT_MAIN_CATEGORY * Main.getMatchScore(search, product.mainCategory);
            product.score += SCORE_SUPPLIER * Main.getMatchScore(search, product.supplierName);
            product.score += SCORE_NAME * Main.getMatchScore(search, product.name);
            product.score += SCORE_DESCRIPTION * Main.getMatchScore(search, product.description);
            if (product.score > 0.0) {
                selected.add(product);
            }
        }

        Collections.sort(selected, new Comparator<Product>() {
            @Override
            public int compare(final Product p1, final Product p2) {
                return p1.score < p2.score ? +1 : -1;
            }
        });

        System.out.println("New search");

        JSONArray results = new JSONArray();
        Set<String> seen = new HashSet<>();
        for (int i = 0; i < Math.min(5, selected.size()); i++) {
            if (seen.contains(selected.get(i).supplierName)) {
                continue;
            }
            seen.add(selected.get(i).supplierName);
            results.put(Main.getItem(selected.get(i)));
            System.out.println(selected.get(i).name + " " + selected.get(i).score);
        }

        JSONObject result = new JSONObject().put("items", results);
        return result.toString();
    }
}
