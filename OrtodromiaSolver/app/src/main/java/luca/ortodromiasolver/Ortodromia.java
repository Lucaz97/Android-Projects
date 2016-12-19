package luca.ortodromiasolver;
import java.lang.Object;
import java.lang.Math;
/**
 * Created by lucaz on 22/03/2016.
 */
public class Ortodromia {

    private Point puntoA;
    private Point puntoB;
    private double distanzaABo, deltaLong, deltaLat, coLatA, coLatB , rottaIniziale;

    //defualt constructor for ortodromia
    public Ortodromia(){
        //setting variables with default values
        puntoA = new Point();
        puntoB= new Point();
        distanzaABo = 0;
        deltaLong = 0;
        rottaIniziale = 0;
    }

    //overload ortodromia's constructor
    public Ortodromia(Point puntoX, Point puntoY){
        //first initiate the points
        puntoA = new Point();
        puntoB= new Point();

        //setting the Points values with the values taken from the method
        puntoA.setLat(puntoX.getLat());
        puntoA.setLong(puntoX.getLong());
        puntoA.setIsNorth(puntoX.getIsNorth());
        puntoA.setIsEast(puntoX.getIsEast());
        puntoB.setLat(puntoY.getLat());
        puntoB.setLong(puntoY.getLong());
        puntoB.setIsNorth(puntoY.getIsNorth());
        puntoB.setIsEast(puntoY.getIsEast());

        //checking the signs of the variables, if they are S or W they need to be negative
        if(puntoA.getIsNorth()){
            puntoA.setLat(puntoA.getLat());
        } else {
            puntoA.setLat(-1*puntoA.getLat());
        }

        if(puntoA.getIsEast()){
            puntoA.setLong(puntoA.getLong());
        } else {
            puntoA.setLong(-1*puntoA.getLong());
        }


        if(puntoB.getIsNorth()){
            puntoB.setLat(puntoB.getLat());
        } else {
            puntoB.setLat(-1*puntoB.getLat());
        }

        if(puntoB.getIsEast()){
            puntoB.setLong(puntoB.getLong());
        } else {
            puntoB.setLong(-1*puntoB.getLong());
        }

        //once variables are ready to be used, start doing math
        //calculate longitude difference
        deltaLong = puntoB.getLong() - puntoA.getLong();
        //the absolute value of longitude difference can't be over 180, but it needs to be handled in two different ways!
        if (deltaLong > 180) {
            deltaLong = deltaLong - 360;
        }else if (deltaLong < -180){
            deltaLong = deltaLong + 360;
        }

        //calculate the latitude difference
        deltaLat = puntoB.getLat() - puntoA.getLat();

        //calculating colatitudes
        if(puntoA.getIsNorth() && !puntoB.getIsNorth()){
            coLatB = 90 + puntoB.getLat();
            coLatA = 90 - puntoA.getLat();
        } else if (!puntoA.getIsNorth() && puntoB.getIsNorth()) {
            coLatB = 90 - puntoB.getLat();
            coLatA = 90 + puntoA.getLat();
        }else{
            coLatA = 90 - puntoA.getLat();
            coLatB = 90 - puntoB.getLat();
        }

        //calculate distance first
        distanzaABo =Math.toDegrees(Math.acos((Math.cos(Math.toRadians(coLatA)) * Math.cos(Math.toRadians(coLatB))) + (Math.sin(Math.toRadians(coLatA)) * Math.sin(Math.toRadians(coLatB)) * Math.cos(Math.toRadians(deltaLong)))));
        //calculate initial curse
        rottaIniziale = Math.toDegrees( Math.acos((Math.cos(Math.toRadians(coLatB)) - Math.cos(Math.toRadians(coLatA))*Math.cos(Math.toRadians(distanzaABo)))/(Math.sin(Math.toRadians(coLatA))*Math.sin(Math.toRadians(distanzaABo)))));

    }


    //set methods
    public void setPointA(Point puntoX){
        puntoA.setLat(puntoX.getLat());
        puntoA.setLong(puntoX.getLong());
        puntoA.setIsNorth(puntoX.getIsNorth());
        puntoA.setIsEast(puntoX.getIsEast());
    }

    public void setPointB(Point puntoY){
        puntoB.setLat(puntoY.getLat());
        puntoB.setLong(puntoY.getLong());
        puntoB.setIsNorth(puntoY.getIsNorth());
        puntoB.setIsEast(puntoY.getIsEast());
    }


    //get methods
    public double getOrtodromicDistance(){
        return distanzaABo;
    }

    public double getDeltaLong(){
        return deltaLong;
    }

    public double getDeltaLat(){
        return deltaLat;
    }

    public double getRottaIniziale(){
        return rottaIniziale;
    }


    //calculate method to calculate results when data changes
    public void calculateResults(){

        //checking the signs of the variables, if they are S or W they need to be negative
        if(puntoA.getIsNorth()){
            puntoA.setLat(puntoA.getLat());
        } else {
            puntoA.setLat(-1 * puntoA.getLat());
        }

        if(puntoA.getIsEast()){
            puntoA.setLong(puntoA.getLong());
        } else {
            puntoA.setLong(-1 * puntoA.getLong());
        }

        if(puntoB.getIsNorth()){
            puntoB.setLat(puntoB.getLat());
        } else {
            puntoB.setLat(-1 * puntoB.getLat());
        }

        if(puntoB.getIsEast()){
            puntoB.setLong(puntoB.getLong());
        } else {
            puntoB.setLong(-1*puntoB.getLong());
        }

        //once variables are ready to be used, start doing math
        //calculate longitude difference
        deltaLong = puntoB.getLong() - puntoA.getLong();
        if (deltaLong > 180) {
            deltaLong = deltaLong - 360;
        }else if (deltaLong < -180){
            deltaLong = deltaLong + 360;
        }

        //calculate the latitude difference
        deltaLat = puntoB.getLat() - puntoA.getLat();

        //calculating colatitudes
        if(puntoA.getIsNorth() && !puntoB.getIsNorth()){
            coLatB = 90 + puntoB.getLat();
            coLatA = 90 - puntoA.getLat();
        } else if (!puntoA.getIsNorth() && puntoB.getIsNorth()) {
            coLatB = 90 - puntoB.getLat();
            coLatA = 90 + puntoA.getLat();
        }else{
            coLatA = 90 - puntoA.getLat();
            coLatB = 90 - puntoB.getLat();
        }


        //calculate distance first
        distanzaABo =Math.toDegrees( Math.acos((Math.cos(Math.toRadians(coLatA)) * Math.cos(Math.toRadians(coLatB))) + (Math.sin(Math.toRadians(coLatA)) * Math.sin(Math.toRadians(coLatB)) * Math.cos(Math.toRadians(deltaLong)) ) )  );
        //calculate initial curse
        rottaIniziale = Math.toDegrees( Math.acos((Math.cos(Math.toRadians(coLatB)) - Math.cos(Math.toRadians(coLatA))*Math.cos(Math.toRadians(distanzaABo)))/(Math.sin(Math.toRadians(coLatA))*Math.sin(Math.toRadians(distanzaABo)))));

    }


}
