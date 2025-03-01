package org.example.springbootproj2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String model;

    int year;

    String brand;
}


