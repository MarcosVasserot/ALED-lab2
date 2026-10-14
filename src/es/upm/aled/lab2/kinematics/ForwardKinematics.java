package es.upm.aled.lab2.kinematics;

import es.upm.aled.lab2.gui.Node;

/**
 * This class implements a forward kinematics algorithm using recursion. It
 * expects a tree of Segments (defined by its length and angle with respect to
 * the previous Segment in the tree) and returns a tree of Nodes (defined by
 * their absolute coordinates in a 2-dimensional space).
 * 
 * @author rgarciacarmona
 */
public class ForwardKinematics {

	/**
	 * Returns a tree of Nodes to be used by SkeletonPanel to draw the position of
	 * an exoskeleton. This method is the public facade to a recursive method that
	 * builds the result from a tree of Segments defined by their angle and length,
	 * and the relationship between them (which Segment is children of which).
	 * 
	 * @param root    The root of the tree of Segments.
	 * @param originX The X coordinate for the origin point of the tree.
	 * @param originY The Y coordinate for the origin point of the tree.
	 * @return The tree of Nodes that represent the exoskeleton position in absolute
	 *         coordinates.
	 */
	// Public method: returns the root of the position tree
	public static Node computePositions(Segment root, double originX, double originY) {
		// TODO: Implemente este método
		
		return computePositions(root, originX, originY, 0.0);	
	}

	// Private helper method that implements the recursive algorithm
	private static Node computePositions(Segment link, double baseX, double baseY, double accumulatedAngle) {
		// TODO: Implemente este método
		
		long startTime = System.nanoTime(); 
		accumulatedAngle += link.getAngle(); //Al ángulo acumulado le sumo el ángulo del segmento actual 
		double x = baseX + link.getLength()*Math.cos(accumulatedAngle); //Obtengo la coordenada x del nuevo nodo con la fórmula que nos dan 
		double y = baseY + link.getLength()*Math.sin(accumulatedAngle); //Obtengo la coordenada y del nuevo nodo con la fórmula que nos dan 
		Node node = new Node(x, y); //Creo el nodo con las coordenadas x e y que acabo de calcular
		
		if (link.getChildren().isEmpty()) { //Si el segmento no tiene hiios simplemente devuelve el nodo y se acaba el método 
			long runningTime = System.nanoTime()- startTime;
			System.out.println("Tiempo de computePositions para un segmento con "
			+ link.getChildren().size() + " hijos: "
			+ runningTime + " nanosegundos");
			return node; 
		}
		//Si tiene hijos 
		for (Segment child: link.getChildren()  )//A cada segmento link le pido su colección de segmentos hijos y la recorro con un bucle for 
		node.addChild(computePositions(child, x, y, accumulatedAngle)); //Le pido a cada uno de los segmentos hijos que convierta a nodo el punto al que llegue y los añada como hijos del nodo que yo estoy fabricando.   
		long runningTime = System.nanoTime()- startTime;
		System.out.println("Tiempo de computePositions para un segmento con "
		+ link.getChildren().size() + " hijos: "
		+ runningTime + " nanosegundos");
		return node ;
		}
	}
