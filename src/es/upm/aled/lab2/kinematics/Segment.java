package es.upm.aled.lab2.kinematics;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase representa un segmento del exoesqueleto. 
 * 
 * @author m.vasserot
 */
public class Segment {
	
	private double length; 
	private double angle; 
	private List <Segment> children; 

	public Segment (double length, double angle) {
		this.length = length; 
		this.angle = angle; 
		this.children = new ArrayList<>(); 
	}

	public double getLength() {
		return length; 
	}	

	public double getAngle() {
		return angle; 
	}

	public void setAngle(double angle) {
		this.angle = angle; 
	}

	public List<Segment> getChildren(){
		return children; 
	}
	/**
	 * Adds a new Segment to the List of Nodes this one is connected to. Each Segment can
	 * only appear as a child once.
	 */
	public void addChild(Segment child) {
		if (!this.children.contains(child))
			this.children.add(child);
	}

}
