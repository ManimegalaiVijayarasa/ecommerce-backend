package Practice.Service;
import Practice.Model.Order;
import Practice.Model.Product;
import Practice.Repository.OrderRepository;
import Practice.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository,
                        ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    // CREATE ORDER
    public Order createOrder(Order order) {

        if (order == null) {
            throw new RuntimeException("Order cannot be null");
        }

        if (order.getCustomerEmail() == null ||
                !order.getCustomerEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new RuntimeException("Invalid email");
        }

        if (order.getProducts() == null || order.getProducts().isEmpty()) {
            throw new RuntimeException("Order must contain products");
        }

        // Validate products exist
        for (Product p : order.getProducts()) {
            productRepository.findById(p.getProductId())
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Product not found: " + p.getProductId()));
        }

        order.setOrderDate(LocalDateTime.now());

        order.setTotalAmount(calculateTotal(order));

        if (order.getStatus() == null) {
            order.setStatus("PENDING");
        }

        return orderRepository.save(order);
    }

    // CALCULATE TOTAL + DISCOUNT
    private Double calculateTotal(Order order) {

        double total = 0;

        for (Product p : order.getProducts()) {

            Product dbProduct = productRepository.findById(p.getProductId())
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Product not found: " + p.getProductId()
                            ));

            if (dbProduct.getPrice() == null) {
                throw new RuntimeException("Product price is missing for: " + dbProduct.getProductName());
            }
            total += dbProduct.getPrice();
        }

        if (total > 5000) {
            total = total * 0.90;
        }

        return total;
    }

    // STATUS UPDATE VALIDATION
    public Order updateStatus(String id, String status) {

        List<String> validStatuses = Arrays.asList(
                "PENDING",
                "CONFIRMED",
                "SHIPPED",
                "DELIVERED"
        );

        if (!validStatuses.contains(status)) {
            throw new RuntimeException("Invalid status");
        }

        Order order = getOrderById(id);
        order.setStatus(status);

        return orderRepository.save(order);
    }

    // READ ALL
    // READ ALL
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // READ BY ID
    public Order getOrderById(String id) {
        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));
    }

    // UPDATE FULL
    public Order updateOrder(String id, Order order) {

        Order existing = getOrderById(id);

        existing.setCustomerEmail(order.getCustomerEmail());
        existing.setProducts(order.getProducts());
        existing.setPaymentMethods(order.getPaymentMethods());

        existing.setTotalAmount(calculateTotal(existing));

        return orderRepository.save(existing);
    }

    // DELETE
    public void deleteOrder(String id) {
        Order existing = getOrderById(id);
        orderRepository.delete(existing);
    }
}