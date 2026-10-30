package Practice.Service;
import Practice.Model.Category;
import Practice.Repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category create(Category category) {
        return repository.save(category);
    }

    public List<Category> getAll() {
        return repository.findAll();
    }

    public Category getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public Category update(String id, Category category) {

        Category existing = getById(id);

        existing.setCategoryName(category.getCategoryName());
        existing.setDescription(category.getDescription());

        return repository.save(existing);
    }

    public void delete(String id) {
        Category existing = getById(id);
        repository.delete(existing);
    }
}