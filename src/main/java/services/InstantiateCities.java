package services;

import java.util.List;

public class InstantiateCities {

	List<City> cities;

	public InstantiateCities() {
		City a1 = new City("Sydney");
		City a2 = new City("Melbourne");
		this.cities.add(a1);
		this.cities.add(a2);
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

}
