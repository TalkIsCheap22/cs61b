public class Planet{

    static final double G=6.67e-11;
    
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;

    public Planet(double xP,double yP,double xV,double yV,double m,String img){
        xxPos=xP;
        yyPos=yP;
        xxVel=xV;
        yyVel=yV;
        mass=m;
        imgFileName=img;
    }

    public Planet(Planet p){
        xxPos=p.xxPos;
        yyPos=p.yyPos;
        xxVel=p.xxVel;
        yyVel=p.yyVel;
        mass=p.mass;
        imgFileName=p.imgFileName;
    }

    public double calcDistance(Planet other){
        double square=Math.pow(xxPos-other.xxPos,2)+Math.pow(yyPos-other.yyPos,2);
        return Math.sqrt(square);
    }

    public double calcForceExertedBy(Planet other){
        return G*mass*other.mass/Math.pow(calcDistance(other),2);
    }

    public double calcForceExertedByX(Planet other){
        double force=calcForceExertedBy(other);
        return force*(other.xxPos-xxPos)/calcDistance(other);
    }

    public double calcForceExertedByY(Planet other){
        double force=calcForceExertedBy(other);
        return force*(other.yyPos-yyPos)/calcDistance(other);
    }

    public double calcNetForceExertedByX(Planet[]others){
        double sum=0;
        for(int i=0;i<others.length;i+=1) {
            if (others[i] != this) {
                sum += calcForceExertedByX(others[i]);
            }
        }
        return sum;
    }

    public double calcNetForceExertedByY(Planet[]others){
        double sum=0;
        for(int i=0;i<others.length;i+=1){
            if(others[i]!=this){
                sum+=calcForceExertedByY(others[i]);
        
            }
        }
        return sum;
    }

    public void update(double dt,double fX,double fY){
        xxVel=xxVel+dt*fX/mass;
        yyVel=yyVel+dt*fY/mass;
        xxPos=xxPos+dt*xxVel;
        yyPos=yyPos+dt*yyVel;
    }

    public void draw(){
        StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
    }

}