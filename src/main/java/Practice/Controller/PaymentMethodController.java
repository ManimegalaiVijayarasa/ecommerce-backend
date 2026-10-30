package Practice.Controller;
import Practice.Model.PaymentMethod;
import Practice.Service.PaymentMethodService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentMethodController {

    private final PaymentMethodService service;

    public PaymentMethodController(PaymentMethodService service) {
        this.service = service;
    }

    @PostMapping
    public PaymentMethod create(@RequestBody PaymentMethod paymentMethod) {
        return service.create(paymentMethod);
    }

    @GetMapping
    public List<PaymentMethod> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public PaymentMethod getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public PaymentMethod update(@PathVariable String id, @RequestBody PaymentMethod paymentMethod) {
        return service.update(id, paymentMethod);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "Payment method deleted";
    }
}