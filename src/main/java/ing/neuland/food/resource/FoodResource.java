package ing.neuland.food.resource;

import ing.neuland.food.model.Food;
import ing.neuland.food.service.FoodService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@ApplicationScoped
@Path("/food")
public class FoodResource {
  private final FoodService foodService;

  public FoodResource(FoodService foodService) {
    this.foodService = foodService;
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Food getFoodById(@PathParam("id") Long id) {
    if (id < 0) {
      throw new BadRequestException("ID must be positive");
    }

    try {
      Long.parseLong(id.toString());
    } catch (NumberFormatException e) {
      throw new BadRequestException("ID must be a number");
    }

    return foodService.getFoodById(id).orElseThrow(() -> new NotFoundException("Food " + id + " not found"));
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Food> getAllFoods() {
    return foodService.getAllFoods();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Food saveFood(Food food) {
    foodService.save(food);
    return food;
  }

  @DELETE
  @Path("/{id}")
  public void deleteFoodById(@PathParam("id") Long id) {
    foodService.deleteFoodById(id);
  }
}
