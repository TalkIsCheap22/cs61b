public class NBody{

    public static double readRadius(String file_name){
        In in=new In(file_name);
        in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String file_name){
        In in =new In(file_name);
        int n=in.readInt();
        in.readDouble();
        Planet[] planets=new Planet[5];
        for(int i=0;i<n;i+=1){
            double xxP=in.readDouble();
            double yyP=in.readDouble();
            double xxV=in.readDouble();
            double yyV=in.readDouble();
            double m=in.readDouble();
            String img=in.readString();
            planets[i]=new Planet(xxP,yyP,xxV,yyV,m,img);
        }
        return planets;
    }

    public static void main(String[]args){
        double T=Double.parseDouble(args[0]);
        double dt=Double.parseDouble(args[1]);
        String file_name=args[2];

        double radius=readRadius(file_name);
        Planet[] planets=readPlanets(file_name);

        StdDraw.setXscale(-radius,radius);
        StdDraw.setYscale(-radius,radius);

        StdDraw.enableDoubleBuffering();
        StdDraw.clear();

        double time=0;

        while(time<T){
            int len= planets.length;
            double []xForces=new double[len];
            double []yForces=new double[len];

            for(int i=0;i<len;++i){
                xForces[i]=planets[i].calcNetForceExertedByX(planets);
                yForces[i]=planets[i].calcNetForceExertedByY(planets);
            }
            StdDraw.picture(0,0,"images/starfield.jpg",2*radius,2*radius);

            for(int i=0;i<planets.length;++i){
                planets[i].update(dt,xForces[i],yForces[i]);

                planets[i].draw();
            }

            StdDraw.show();
            StdDraw.pause(10);

            time+=dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}