package se.jensen.duaa.cloudstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import se.jensen.duaa.cloudstore.model.Product;
import se.jensen.duaa.cloudstore.service.ProductService;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Redirect root URL to fetch products- slippa/gå till rätt sida
    @GetMapping("/")
    public String home() {
        return "redirect:/products/fetch";
    }

    // Fetch products from API and save to RDS
    @GetMapping("/products/fetch")
    public String fetchProducts(Model model) {
        model.addAttribute("products", productService.fetchAndSaveProducts());
        return "product"; // product.html
    }

    // Show all products from RDS
    @GetMapping("/product")
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "product"; // product.html
    }

    // Redirect /products → /product
    @GetMapping("/products")
    public String getAllProductsRedirect() {
        return "redirect:/product";
    }

    // Product details page
    @GetMapping("/products/{id}")
    public String productDetails(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product-details";
    }

    // Order confirmation page
    @GetMapping("/order/{id}")
    public String showOrderConfirmation(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "order-confirmation";
    }
}
