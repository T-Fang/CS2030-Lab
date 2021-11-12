class Cuboid extends Shape3D{
	protected double height;
	protected double width;
	protected double length;

	public Cuboid(double height, double width, double length){
		this.height = height;
		this.width = width;
		this.length = length;
	}
	
	public double getVolume(){
		return height * width * length;
	}

	public double getSurfaceArea(){
		return 2 * (height * width + height * length + width * length);
	}
	
	public Cuboid setHeight(double height){
		return new Cuboid(height, width, length);
	}

	public Cuboid setWidth(double width){
		return new Cuboid(height, width, length);
	}
	
	public Cuboid setLength(double length){
		return new Cuboid(height, width, length);
	}

	
	public String toString(){
		return "Cuboid [" + String.format("%.2f x %.2f x %.2f",height, width, length) + "]";
	}
}
