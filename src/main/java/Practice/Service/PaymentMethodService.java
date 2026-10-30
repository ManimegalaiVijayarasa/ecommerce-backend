package Practice.Service;
import Practice.Model.PaymentMethod;
import Practice.Repository.PaymentMethodRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodService {

    private final PaymentMethodRepository repository;

    public PaymentMethodService(PaymentMethodRepository repository) {
        this.repository = repository;
    }

    public PaymentMethod create(PaymentMethod paymentMethod) {
        return repository.save(paymentMethod);
    }

    public List<PaymentMethod> getAll() {
        return repository.findAll();
    }

    public PaymentMethod getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment method not found"));
    }

    public PaymentMethod update(String id, PaymentMethod paymentMethod) {

        PaymentMethod existing = getById(id);

        existing.setPaymentType(paymentMethod.getPaymentType());
        existing.setAccountNumber(paymentMethod.getAccountNumber());
        existing.setExpiryDate(paymentMethod.getExpiryDate());

        return repository.save(existing);
    }

    public void delete(String id) {
        PaymentMethod existing = getById(id);
        repository.delete(existing);
    }
}