package luca.ortodromiasolver;

/**
 * Created by lucaz on 22/03/2016.
 */
public class Point {

    //point's data --> latitude, longitude and associated signs
    private double lat, longit;
    private boolean isNorth, isEast;

    //default constuctor for Point
    public Point(){
        lat = 0.0;
        isNorth = true;
        longit = 0.0;
        isEast = true;
    }

    //constructor overload for Point
    public Point(double latX, boolean N_S, double longX, boolean E_W) {
        lat = latX;
        isNorth = N_S;
        isEast = E_W;
        longit = longX;

    }


    //set methods
    public void setLat(double latX){
        lat = latX;
    }

    public void setLong(double longX){
        longit = longX;
    }

    public void setIsNorth(boolean N_S){
        isNorth = N_S;
    }

    public void setIsEast(boolean E_W){
        isEast = E_W;
    }


    //get methods
    public double getLat(){
        return lat;
    }

    public double getLong(){
        return longit;
    }

    public boolean getIsNorth(){
        return isNorth;
    }

    public boolean getIsEast(){
        return isEast;
    }



}
