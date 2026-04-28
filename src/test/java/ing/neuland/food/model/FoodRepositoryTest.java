package ing.neuland.food.model;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@QuarkusTest
class FoodRepositoryTest {
  private final FoodRepository foodRepository;

  public FoodRepositoryTest(FoodRepository foodRepository) {
    this.foodRepository = foodRepository;
  }

  @Test
  @TestTransaction
  void shouldPersistFood() {
    // given
    Food food = new Food();
    food.setName("Viktoriabarsch");
    food.setDescription("Zartes Filet aus dem Viktoriasee.");

    // when
    foodRepository.persist(food);

    // then
    assertThat(foodRepository.listAll(), hasSize(1));
  }
}