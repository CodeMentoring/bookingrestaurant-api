package com.pe.hampcode.mappers;

import com.pe.hampcode.dto.ReservationDto;
import com.pe.hampcode.dto.ReservationResponseDto;
import com.pe.hampcode.entity.Reservation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReservationMapper {
    private final ModelMapper modelMapper;

    public ReservationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Reservation resourceToEntity(ReservationDto reservationResource) {
        return modelMapper.map(reservationResource, Reservation.class);
    }

    public ReservationDto entityToResource(Reservation reservation) {
        return modelMapper.map(reservation, ReservationDto.class);
    }

    public ReservationResponseDto entityToResponseResource(Reservation reservation) {
        return modelMapper.map(reservation, ReservationResponseDto.class);
    }

    public List<Reservation> resourceListToEntityList(List<ReservationDto> reservationResources) {
        return reservationResources
                .stream()
                .map(this::resourceToEntity)
                .toList();
    }

    public List<ReservationDto> entityListToResourceList(List<Reservation> reservations) {
        return reservations
                .stream()
                .map(this::entityToResource)
                .toList();
    }

    public List<ReservationResponseDto> entityListToResponseResourceList(List<Reservation> reservations) {
        return reservations
                .stream()
                .map(this::entityToResponseResource)
                .toList();
    }

}
