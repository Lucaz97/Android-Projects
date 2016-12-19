package luca.ortodromiasolver;

/**
 * Created by lucaz on 24/03/2016.
 */
public class Lossodromia {

    private double distanzaLossodromica ,latMedia,  deltaLat , deltaLong , rottaQuadrantale, rotta ;
    private Point puntoA, puntoB;



    //default constructor for Lossodromia Object
    public Lossodromia () {
        //setting variables with default values
        puntoA = new Point();
        puntoB = new Point();
        deltaLat = 0;
        deltaLong = 0;
        rotta = 0;
        rottaQuadrantale = 0;
        distanzaLossodromica = 0;
    }

    //overload of Lossodromia constructor
    public Lossodromia(Point puntoX, Point puntoY){
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

        //the absolute value of longitude difference can't be over 180, but it needs to be handled in two different ways!
        if (deltaLong > 180) {
            deltaLong = deltaLong - 360;
        }else if (deltaLong < -180){
            deltaLong = deltaLong + 360;
        }

        //calculate medium latitude and latitude difference
        latMedia = ( puntoA.getLat()+puntoB.getLat() )/2;
        deltaLat = puntoB.getLat() - puntoA.getLat();

        //calculating the course
        rottaQuadrantale = Math.toDegrees( Math.atan(deltaLong*Math.cos(Math.toRadians(latMedia) / deltaLat )) );

        //calculating distance
        if (deltaLong == 0) {
            distanzaLossodromica = deltaLat*60;
        }else {
            distanzaLossodromica = 60*deltaLong * Math.cos(Math.toRadians(latMedia)) / Math.sin(Math.toRadians(rottaQuadrantale));
        }

        if (distanzaLossodromica <0){
            distanzaLossodromica = -1* distanzaLossodromica;
        }
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

    public double getLossodromicDistance(){
        return distanzaLossodromica;
    }

    public double getDeltaLongitude(){
        return deltaLong;
    }

    public double getDeltaLatitude(){
        return deltaLat;
    }

    public double getRottaVera(){
        return rottaQuadrantale;
    }

    public double getRottaQuadrantale() { return rottaQuadrantale;}


    //method to calculate results when data changes
    public void calcolaRisultati(){

        //checking the signs of the variables, if they are S or W they need to be negative
        if(!puntoA.getIsNorth()){
            puntoA.setLat(-1 * puntoA.getLat());
        }

        if(!puntoA.getIsEast()){
            puntoA.setLong(-1 * puntoA.getLong());
        }


        if(!puntoB.getIsNorth()){
            puntoB.setLat(-1 * puntoB.getLat());
        }

        if(!puntoB.getIsEast()){
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

        //calculate medium latitude and latitude difference
        latMedia = ( puntoA.getLat()+puntoB.getLat() )/2;
        deltaLat = puntoB.getLat() - puntoA.getLat();


        //calculating the course
        rottaQuadrantale = Math.toDegrees(Math.atan(deltaLong*Math.cos(Math.toRadians(latMedia)) / deltaLat )) ;

        /*if(deltaLat < 0){

            if(deltaLong < 0){
                rotta = rottaQuadrantale + 180;

            } else {
                rotta = 180 - rottaQuadrantale;
            }


        } else {
            if(deltaLong < 0){
                rotta = 360 - rottaQuadrantale;

            } else {
                rotta = rottaQuadrantale;
            }
        }
*/


        //calculating distance
        if (deltaLong == 0) {
            distanzaLossodromica = deltaLat*60;
        }else {
            distanzaLossodromica = 60*deltaLong * Math.cos(Math.toRadians(latMedia)) / Math.sin(Math.toRadians(rottaQuadrantale));
        }


        if (distanzaLossodromica <0){
            distanzaLossodromica = -1* distanzaLossodromica;
        }


    }
}
