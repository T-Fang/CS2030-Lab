class SolidSphere extends SolidShape3D{
	
	public SolidSphere(double radius, double density){
		super(new Sphere(radius), density);
	}
	
	public SolidSphere setRadius(double radius){
		return new SolidSphere(radius, density);
	}
}
