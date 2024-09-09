package com.example.test_flutter_01_be.controller;

import com.example.test_flutter_01_be.entity.Place;
import com.example.test_flutter_01_be.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/places")
@CrossOrigin
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @PostMapping("/add")
    public ResponseEntity<Place> addPlace(
            @RequestParam("name") String name,
            @RequestParam("country") String country,
            @RequestParam("image") MultipartFile image) {
        try {
            Place place = new Place();
            place.setName(name);
            place.setCountry(country);

            Place savedPlace = placeService.savePlace(place, image);
            return ResponseEntity.ok(savedPlace);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/getAllPlace")
    public ResponseEntity<List<Place>> getAllPlace() {
        List<Place> places = placeService.getAllPlace();
        return ResponseEntity.ok(places);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Place> updatePlace(
            @PathVariable int id,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam(value = "image", required = false) MultipartFile image) {
        try {
            Place updatedPlace = placeService.updatePlace(id, name, description, image);
            return ResponseEntity.ok(updatedPlace);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Place> getPlaceById(@PathVariable int id) {
        Optional<Place> place = placeService.getPlaceById(id);
        return place.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body(null));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePlace(@PathVariable int id) {
        try {
            placeService.deletePlace(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
