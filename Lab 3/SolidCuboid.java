class SolidCuboid extends SolidShape3D{

	public SolidCuboid(double height, double width, double length, double density){
		super(new Cuboid(height, width, length), density);
	}
	
	private SolidCuboid(Cuboid cuboid, double density){
		super(cuboid, density);
	}
	public SolidCuboid setHeight(double height){
		Cuboid cuboid = (Cuboid) shape;
		return new SolidCuboid(cuboid.setHeight(height), density);
	}

	public SolidCuboid setWidth(double width){
		Cuboid cuboid = (Cuboid) shape;
		return new SolidCuboid(cuboid.setWidth(width), density);
	}
	
	public SolidCuboid setLength(double length){
		Cuboid cuboid = (Cuboid) shape;
		return new SolidCuboid(cuboid.setLength(length), density);
	}
	
}
