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

    @PostMapping("/order/{id}")
    public String placeOrder(@PathVariable Long id, Principal principal) {

        Product product = productService.getAllProducts()
                .stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow();

        orderService.placeOrder(product, principal.getName());
        return "redirect:/products";

    }

    @GetMapping("/orders")
    public String myOrders(Model model, Principal principal) {
        model.addAttribute("orders", orderService.getOrdersForUser(principal.getName()));
        return "orders";

    }
}
