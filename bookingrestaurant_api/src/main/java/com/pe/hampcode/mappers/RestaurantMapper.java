package com.pe.hampcode.mappers;

import com.pe.hampcode.dto.RestaurantDto;
import com.pe.hampcode.entity.Restaurant;
import com.pe.hampcode.dto.RestaurantResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestaurantMapper {
    private final ModelMapper modelMapper;

    public RestaurantMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Restaurant resourceToEntity(RestaurantDto restaurantResource) {
        return modelMapper.map(restaurantResource, Restaurant.class);
    }

    public RestaurantDto entityToResource(Restaurant restaurant) {
        return modelMapper.map(restaurant, RestaurantDto.class);
    }

    public RestaurantResponseDto entityToResponseResource(Restaurant restaurant) {
        return modelMapper.map(restaurant, RestaurantResponseDto.class);
    }

    public List<Restaurant> resourceListToEntityList(List<RestaurantDto> restaurantResources) {
        return restaurantResources
                .stream()
                .map(this::resourceToEntity)
                .toList();
    }

    public List<RestaurantDto> entityListToResourceList(List<Restaurant> restaurants) {
        return restaurants
                .stream()
                .map(this::entityToResource)
                .toList();
    }

    public List<RestaurantResponseDto> entityListToResponseResourceList(List<Restaurant> restaurants) {
        return restaurants
                .stream()
                .map(this::entityToResponseResource)
                .toList();
    }

}
