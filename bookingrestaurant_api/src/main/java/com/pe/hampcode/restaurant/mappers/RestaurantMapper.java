package com.pe.hampcode.restaurant.mappers;

import com.pe.hampcode.restaurant.domain.entity.Restaurant;
import com.pe.hampcode.restaurant.resource.RestaurantResource;
import com.pe.hampcode.restaurant.resource.RestaurantResponseResource;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestaurantMapper {
    private final ModelMapper modelMapper;

    public RestaurantMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Restaurant resourceToEntity(RestaurantResource restaurantResource) {
        return modelMapper.map(restaurantResource, Restaurant.class);
    }

    public RestaurantResource entityToResource(Restaurant restaurant) {
        return modelMapper.map(restaurant, RestaurantResource.class);
    }

    public RestaurantResponseResource entityToResponseResource(Restaurant restaurant) {
        return modelMapper.map(restaurant, RestaurantResponseResource.class);
    }

    public List<Restaurant> resourceListToEntityList(List<RestaurantResource> restaurantResources) {
        return restaurantResources
                .stream()
                .map(this::resourceToEntity)
                .toList();
    }

    public List<RestaurantResource> entityListToResourceList(List<Restaurant> restaurants) {
        return restaurants
                .stream()
                .map(this::entityToResource)
                .toList();
    }

    public List<RestaurantResponseResource> entityListToResponseResourceList(List<Restaurant> restaurants) {
        return restaurants
                .stream()
                .map(this::entityToResponseResource)
                .toList();
    }

}
