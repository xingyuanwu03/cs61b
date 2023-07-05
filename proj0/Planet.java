public class Planet{
	public double xxPos=0;
	public double yyPos=0;
	public double xxVel=0;
	public double yyVel=0;
	public double mass=0;
	public String imgFileName="";
	public static final double G=6.67e-11;
	public Planet(double xP,double yP,double xV,double yV,
		double m,String img){
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
	public double calcDistance(Planet p){
		double dx=p.xxPos-xxPos;
		double dy=p.yyPos-yyPos;
		double r=Math.sqrt(dx*dx+dy*dy);
		return r;
	}
	public double calcForceExertedBy(Planet p){
		double r=calcDistance(p);
		double Force=G*mass*p.mass/(r*r);
		return Force;
	}

	public double calcForceExertedByX(Planet p){
		double dx=p.xxPos-xxPos;
		double r =calcDistance(p);
		double fx=calcForceExertedBy(p)*dx/r;
		return fx;
	}
	public double calcForceExertedByY(Planet p){
		double dy=p.yyPos-yyPos;
		double r =calcDistance(p);
		double fy=calcForceExertedBy(p)*dy/r;
		return fy;
	}
	public double calcNetForceExertedByX(Planet[]allPlanets)
{
	double fnx=0;
	for(Planet p:allPlanets){
		if(!this.equals(p))
		fnx+=calcForceExertedByX(p);
	}
	return fnx;
}
public double calcNetForceExertedByY(Planet[]allPlanets)
{
	double fny=0;
	for(Planet p:allPlanets){
		if(!this.equals(p))
		fny+=calcForceExertedByY(p);
	}
	return fny;
}
public void update(double dt,double fx,double fy){
	double ax=fx/mass;
	double ay=fy/mass;
	xxVel+=ax*dt;
	yyVel+=ay*dt;
	xxPos+=xxVel*dt;
	yyPos+=yyVel*dt;}

public void draw(){
	StdDraw.picture(xxPos,yyPos,"images"+"/"+imgFileName);
}




}