package com.iscourse.api.service;

import com.iscourse.api.domain.course.Place;
import com.iscourse.api.repository.course.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;

    @Transactional
    public void deletePlace(Long id) {
        Place place = placeRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        place.delete();
    }
}
