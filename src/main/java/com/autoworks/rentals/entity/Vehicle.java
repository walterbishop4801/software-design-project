package com.autoworks.rentals.entity;

import com.autoworks.rentals.enums.Color;
import com.autoworks.rentals.enums.VehicleState;

public abstract class Vehicle {

    private Long id;
    private String make;
    private String model;
    private String description;
    private Color color;
    private int year;
    private VehicleState state;

    
    // Constructor for initialization
    public Vehicle(Long id, String make, String model, String description, int year, Color color) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.description = description;
        this.year = year;
        this.color = color;
    }

    // Default constructor
    public Vehicle() {}

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

	public VehicleState getState() {
		return state;
	}

	public void setState(VehicleState state) {
		this.state = state;
	}

	public double getRentingCost() {
		// TODO Auto-generated method stub
		return 0;
	}


}
