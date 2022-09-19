package miguelguzman.venusrestblog.repository;

import miguelguzman.venusrestblog.data.Category;
import miguelguzman.venusrestblog.data.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}