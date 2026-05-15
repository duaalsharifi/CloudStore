package se.jensen.duaa.cloudstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import se.jensen.duaa.cloudstore.model.Product;
import se.jensen.duaa.cloudstore.service.OrderService;
import se.jensen.duaa.cloudstore.service.ProductService;

import java.security.Principal;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;

    public OrderController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    // ⭐ 1. Skapa order
    @PostMapping("/order/{id}")
    public String placeOrder(@PathVariable Long id, Principal principal, Model model) {

        Product product = productService.getProductById(id);

        orderService.placeOrder(product, principal.getName());

        model.addAttribute("product", product);

        return "order-confirmation";
    }

    // ⭐ 2. Visa mina ordrar
    @GetMapping("/orders")
    public String myOrders(Model model, Principal principal) {
        model.addAttribute("orders", orderService.getOrdersForUser(principal.getName()));
        return "orders";
    }
}
