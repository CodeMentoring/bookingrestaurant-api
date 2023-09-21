package com.pe.hampcode.reservation.mappers;

import com.pe.hampcode.reservation.domain.entity.Reservation;
import com.pe.hampcode.reservation.resource.ReservationResource;
import com.pe.hampcode.reservation.resource.ReservationResponseResource;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReservationMapper {
    private final ModelMapper modelMapper;

    public ReservationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Reservation resourceToEntity(ReservationResource reservationResource) {
        return modelMapper.map(reservationResource, Reservation.class);
    }

    public ReservationResource entityToResource(Reservation reservation) {
        return modelMapper.map(reservation, ReservationResource.class);
    }

    public ReservationResponseResource entityToResponseResource(Reservation reservation) {
        return modelMapper.map(reservation, ReservationResponseResource.class);
    }

    public List<Reservation> resourceListToEntityList(List<ReservationResource> reservationResources) {
        return reservationResources
                .stream()
                .map(this::resourceToEntity)
                .toList();
    }

    public List<ReservationResource> entityListToResourceList(List<Reservation> reservations) {
        return reservations
                .stream()
                .map(this::entityToResource)
                .toList();
    }

    public List<ReservationResponseResource> entityListToResponseResourceList(List<Reservation> reservations) {
        return reservations
                .stream()
                .map(this::entityToResponseResource)
                .toList();
    }

}
