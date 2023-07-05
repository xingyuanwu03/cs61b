public class NBody{
	public static double readRadius(String s){
		In in =new In(s);
		double Radius=in.readDouble();
		Radius=in.readDouble();
		return Radius;
	}
	public static Planet[] readPlanets(String s){
		In in =new In(s);
		int i=in.readInt();
		in.readDouble();
		Planet[] Planets=new Planet[i];
		int a=0;
		while(a<i){
			double xxPos = in.readDouble(); //读取每个属性的值
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			Planets[a] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
			a++;
		}
		return Planets;
	}
	public static void main(String[] args){
		double T=Double.parseDouble(args[0]);
		double dt =Double.parseDouble(args[1]);
		String filename=args[2];
		Planet[] planets=readPlanets(filename);
		double radius=readRadius(filename);
		StdDraw.setScale(-radius,radius);
		StdDraw.clear();
		StdDraw.picture(0,0,"images/starfield.jpg");
		for(Planet planet:planets){
			planet.draw();
		}
		StdDraw.enableDoubleBuffering();
		double time=0;
		while(time<T){
			double[] xForces=new double[planets.length];
			double[] yForces=new double[planets.length];
			for(int i=0;i<planets.length;i++)
				{
					xForces[i]=planets[i].calcNetForceExertedByX(planets);
					yForces[i]=planets[i].calcNetForceExertedByY(planets);
					planets[i].update(dt,xForces[i],yForces[i]);
				}
				StdDraw.clear();
				StdDraw.picture(0,0,"images/starfield.jpg");
				for(Planet planet:planets)
					{
						planet.draw();
					}	
				StdDraw.show();
				StdDraw.pause(10);
				time+=dt;


		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++)
			{
    			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
			}

	}

}