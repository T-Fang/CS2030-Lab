import java.lang.Math.*;

class Sphere extends Shape3D{
	protected double radius;
	
	public Sphere(double radius){
		this.radius = radius;
	}

	public Sphere setRadius(double radius){
		return new Sphere(radius);
	}
	
	public double getVolume(){
		return 	(4.0/3.0) * Math.PI * radius * radius * radius; 
	}

	public double getSurfaceArea(){
		return 4 * Math.PI * radius * radius;
	}
	
	
	public String toString(){
		return "Sphere [" + String.format("%.2f", radius) + "]"; 
	}

}
