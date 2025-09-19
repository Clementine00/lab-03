package com.example.listycitylab3;

import java.io.Serializable;

public class City implements Serializable {
    private String cityName;
    private String provinceName;
    public City(String cityName, String provinceName) {
        this.cityName = cityName;
        this.provinceName = provinceName;
    }
    public String getCityName() {
        return cityName;
    }
    public String getProvince() {
        return provinceName;
    }

    public void setCityName(String name){
        this.cityName = name;
    }

    public void setProvince(String province){
        this.provinceName = province;
    }

    @Override
    public String toString(){
        return cityName + ", " + provinceName;
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof City))
            return false;
        City other = (City) o;
        return this.cityName.equals(other.cityName) && this.provinceName.equals(other.provinceName);
    }
}
