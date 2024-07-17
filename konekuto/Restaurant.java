package com.example.nagoyameshi.entity;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Table(name = "restaurants")
@Data
public class Restaurant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "address")
	private String address;

	@Column(name = "postal_code")
	private String postalCode;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "image_name")
	private String imageName;

	@Column(name = "description")
	private String description;

	@Column(name = "opening_time")
	private String openingTime;

	@Column(name = "close_time")
	private String closeTime;

	@Column(name = "regular_holiday")
	private String regularHoliday;

	@Column(name = "budget_min")
	private int budgetMin;

	@Column(name = "budget_max")
	private int budgetMax;

	@Column(name = "created_at", insertable = false, updatable = false)
	private Timestamp createdAt;

	@Column(name = "updated_at", insertable = false, updatable = false)
	private Timestamp updatedAt;

	@Transient
	private List<RestaurantCategory> restaurantCategories;
}
