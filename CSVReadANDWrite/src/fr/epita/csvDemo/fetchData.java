package fr.epita.csvDemo;

public class fetchData {
	
	    String Id;
	    String localaddress;
	    String street;
	    String dateofcount;
	    String vehiclevolumn;
	    String latitude;
	    String longitude;
	    String location;

	    public String getNorthdata() {
	        return northdata;
	    }

	    public void setNorthdata(String northdata) {
	        this.northdata = northdata;
	    }

	    public String getSouthdata() {
	        return southdata;
	    }

	    public void setSouthdata(String southdata) {
	        this.southdata = southdata;
	    }

	    public String getDay() {
	        return day;
	    }

	    public void setDay(String day) {
	        this.day = day;
	    }

	    public String getMonth() {
	        return month;
	    }

	    public void setMonth(String month) {
	        this.month = month;
	    }

	    public String getStreetindex() {
	        return streetindex;
	    }

	    public void setStreetindex(String streetindex) {
	        this.streetindex = streetindex;
	    }

	    String northdata;
	    String southdata;
	    String day;
	    String month;
	    String streetindex;
	    int total_vehicle;

	    public fetchData(){

	    }

	    public String getId() {
	        return Id;
	    }

	    public void setId(String id) {
	        Id = id;
	    }

	    public String getLocaladdress() {
	        return localaddress;
	    }

	    public void setLocaladdress(String localaddress) {
	        this.localaddress = localaddress;
	    }

	    public String getStreet() {
	        return street;
	    }

	    public void setStreet(String street) {
	        this.street = street;
	    }

	    public String getDateofcount() {
	        return dateofcount;
	    }

	    public void setDateofcount(String dateofcount) {
	        this.dateofcount = dateofcount;
	    }

	    public int getTotal_vehicle() {
	        return total_vehicle;
	    }

	    public void setTotal_vehicle(int total_vehicle) {
	        this.total_vehicle = total_vehicle;
	    }

	    public String getVehiclevolumn() {
	        return vehiclevolumn;
	    }

	    public void setVehiclevolumn(String vehiclevolumn) {
	        this.vehiclevolumn = vehiclevolumn;
	    }

	    public String getLatitude() {
	        return latitude;
	    }

	    public void setLatitude(String latitude) {
	        this.latitude = latitude;
	    }

	    public String getLongitude() {
	        return longitude;
	    }

	    public void setLongitude(String longitude) {
	        this.longitude = longitude;
	    }

	    public String getLocation() {
	        return location;
	    }

	    public void setLocation(String location) {
	        this.location = location;
	    }

	    public fetchData(String Id, String localaddress, String street, String dateofcount, int total_vehicle, String vehiclevolumn, String latitude, String longitude, String location){
	        this.Id=Id;
	        this.localaddress=localaddress;
	        this.street=street;
	        this.dateofcount=dateofcount;
	        this.total_vehicle=total_vehicle;
	        this.vehiclevolumn=vehiclevolumn;
	        this.latitude=latitude;
	        this.longitude=longitude;
	        this.location=location;

	    }

}
