package se.jensen.duaa.cloudstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import se.jensen.duaa.cloudstore.model.Product;
import se.jensen.duaa.cloudstore.service.ProductService;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Hämta produkter från API + spara i DB och visar upp det
    @GetMapping("/products")
    public String showProducts(Model model) {
        List<Product> products = productService.fetchAndSaveProducts();
        model.addAttribute("products", products);
        return "product"; // product.html
    }

    // Visa detaljer för en produkt
    @GetMapping("/products/{id}")
    public String productDetails(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product-details";
    }

    // Visa orderbekräftelse
    @GetMapping("/order/{id}")
    public String showOrderConfirmation(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "order-confirmation";
    }
}
