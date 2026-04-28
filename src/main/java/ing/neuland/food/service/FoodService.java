package ing.neuland.food.service;

import ing.neuland.food.model.Food;
import ing.neuland.food.model.FoodRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class FoodService {
  private final FoodRepository foodRepository;

  public FoodService(FoodRepository foodRepository) {
    this.foodRepository = foodRepository;
  }

  public List<Food> getAllFoods() {
    return foodRepository.listAll();
  }

  public Optional<Food> getFoodById(Long id) {
    return foodRepository.findByIdOptional(id);
  }

  @Transactional
  public void save(Food food) {
    foodRepository.persist(food);
  }

  @Transactional
  public void deleteFoodById(Long id) {
    foodRepository.deleteById(id);
  }
}
