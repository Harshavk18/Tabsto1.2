package com.example.tabsto;

public class XYValue {
    private double x;
    private String name;
    private String type;
    private String Incharge;
    private String duration;
    private String department;
    private String date;
    private String Venue;

    public void setX(double x) {
        this.x = x;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIncharge() {
        return Incharge;
    }

    public void setIncharge(String incharge) {
        Incharge = incharge;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVenue() {
        return Venue;
    }

    public void setVenue(String venue) {
        Venue = venue;
    }
    public  XYValue(){

    }
    public XYValue(double x, String name, String type, String Incharge, String duration, String department, String date, String venue){
        this.x=x;
        this.name=name;
        this.type=type;
        this.Incharge=Incharge;
        this.duration=duration;
        this.department=department;
        this.date=date;
        this.Venue=venue;
    }
    public double getX(){
       return x;
    }

}
