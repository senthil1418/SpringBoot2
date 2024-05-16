package com.pack.SpringBoot2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {
   @Id
   private Integer id;
   private String name;
   private String language;
   private String type;
   private Integer rating;  
}
