package Practice.Repository;

import Practice.Model.PaymentMethod;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentMethodRepository extends MongoRepository<PaymentMethod, String> {
}
