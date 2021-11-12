public class SolidShape3D{
	protected double density;
	protected Shape3D shape;
	
	public SolidShape3D(Shape3D shape, Material material){
		this.shape = shape;
		this.density = material.getDensity();
	}
	
	public SolidShape3D(Shape3D shape, double density){
		this.shape = shape;
		this.density = density;
	}

	@Override
	public String toString(){
		return "Solid" + shape + String.format(" with a mass of %.2f", this.getMass());
	}

	public double getSurfaceArea(){
		return shape.getSurfaceArea();
	}
	
	public double getVolume(){
		return shape.getVolume();
	}
	
	public double getMass(){
		return shape.getVolume() * density;
	}
		
	public double getDensity(){
		return density;
	}


}
