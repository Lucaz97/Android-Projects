package luca.ortodromiasolver;

/**
 * Created by lucaz on 28/03/2016.
 */
public class Wind {

    private double th, tc, tas, gs, windV, windD;

    //only default constructor
    public Wind (){
        th = 0;
        tc = 0;
        tas = 0;
        gs = 0;
        windV = 0;
        windD = 0;
    }

    //get methods
    public double getGs() {
        return gs;
    }

    public double getTas() {
        return tas;
    }

    public double getTc() {
        return tc;
    }

    public double getTh() {
        return th;
    }

    public double getWindD() {
        return windD;
    }

    public double getWindV(){
        return windV;
    }

    //set methods
    public void setTh(double th) {
        this.th = th;
    }

    public void setGs(double gs) {
        this.gs = gs;
    }

    public void setTas(double tas) {
        this.tas = tas;
    }

    public void setTc(double tc) {
        this.tc = tc;
    }

    public void setWindD(double windD) {
        this.windD = windD;
    }

    public void setWindV(double windV) {
        this.windV = windV;
    }

    //solving problems
    public void calculateFirstWindProblem(){
        double xc = windV*Math.sin(Math.toRadians(windD - tc));
        double lc = windV*Math.cos(Math.toRadians(windD - tc));
        double wca = Math.asin(xc/tas);
        if(xc < 0 && wca >0){
            wca = -1*wca;
        }
        double etas = tas*Math.cos(wca);
        gs = etas + lc;
        th = tc + wca;
    }

    public void calculateSecondWindProblem(){
        double gs1 = tas*Math.sin(Math.toRadians(th) - windV*Math.sin(Math.toDegrees(windD)));
        double gs2 = tas*Math.cos(Math.toRadians(th) - windV * Math.cos(Math.toDegrees(windD)));
        gs = Math.sqrt(gs1 * gs1 + gs2 * gs2);
        tc = Math.toDegrees(Math.atan(gs1/gs2));

        if(gs1>= 0 && gs2 >= 0){
            tc = primoQuadrante(tc);
        } else if(gs1 >= 0 && gs2 <=0){
            tc = secondoQuadrante(tc);
        } else if (gs1 <= 0 && gs2 <= 0){
            tc = terzoQuandrante(tc);
        } else if (gs1 <=0 && gs2 >=0){
            tc = quartoQuadrante(tc);
        }
    }

    public void calculateThirdWindProblem(){
        double v1 = tas*Math.sin(Math.toRadians(th)) - gs*Math.sin(Math.toRadians(tc));
        double v2 = tas*Math.cos(Math.toRadians(th)) - gs*Math.cos(Math.toRadians(tc));
        windV = Math.sqrt(v1*v1 + v2*v2);
        windD = Math.toDegrees(Math.atan(v1/v2));

        if(v1>= 0 && v2 >= 0){
            windD = primoQuadrante(windD);
        } else if(v1 >= 0 && v2 <=0){
            windD = secondoQuadrante(windD);
        } else if (v1 <= 0 && v2 <= 0){
            windD = terzoQuandrante(windD);
        } else if (v1 <=0 && v2 >=0){
            windD = quartoQuadrante(windD);
        }

    }

    public void calculateFourthWindProblem(){
        double tas1 = gs*Math.sin(Math.toRadians(tc)) - windV* Math.sin(Math.toRadians(windD));
        double tas2 = gs*Math.cos(Math.toRadians(tc)) - windV* Math.cos(Math.toRadians(windD));
        tas = Math.sqrt(tas1*tas1 + tas2*tas2);
        th = Math.toDegrees(tas1/tas2);

        if(tas1>= 0 && tas2 >= 0){
            th = primoQuadrante(th);
        } else if(tas1 >= 0 && tas2 <=0){
            th = secondoQuadrante(th);
        } else if (tas1 <= 0 && tas2 <= 0){
            th = terzoQuandrante(th);
        } else if (tas1 <=0 && tas2 >=0){
            th = quartoQuadrante(th);
        }
    }

    private double primoQuadrante(double angle){
        if (angle < 0){
            angle = 360 + angle;
        }
        return angle;
    }

    private double secondoQuadrante(double angle){
        angle = 180 - angle;

        return angle;
    }

    private double terzoQuandrante(double angle){
        angle = 180 + angle;

        return angle;
    }

    private double quartoQuadrante(double angle){
        angle = 360 - angle;

        return angle;
    }

}