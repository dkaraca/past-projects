//UIUC CS125 SPRING 2014 MP. File: RobotLink.java, CS125 Project: Challenge7-RecursiveKnight, Version: 2014-04-11T09:32:59-0500.857314000
/** @author dkaraca2
 * 
 * @author dkaraca2
 *
 */
public class RobotLink {

	private RobotLink next; 	
	private final Robot robot;

	public Robot getRobot() {
		return robot;
	}
	/** Constructs this link.
	 * @param next ; the next item in the chain (null, if there are no more items).
	 * @param robot ; a single robot (never null).
	 */
	public RobotLink(RobotLink next,Robot robot) {
		this.robot = robot;
		this.next = next;
	}

	/**
	 * Returns the number of entries in the linked list.
	 * @return number of entries.
	 */
	public int count() {
		if (next == null)
			return 1; // BASE CASE; no more recursion required!

		// Recursive case:
		return 1 + next.count();
	}
	/**
	 * Counts the number of flying robots in the linked list.
	 * Hint: robot.isFlying is useful here.
	 */
	public int countFlyingRobots() {
		int count = 0;
		if(this.robot.isFlying()) count++;
		if(next != null) count += next.countFlyingRobots();
		return count;
	}
	/**
	 * Counts the number of flying robots upto and excluding a sad robot.
	 * (i.e. do not count the sad robot if it is flying).
	 * If there are no sad robots this function will return the same value as countFlyingRobots().
	 * Hint: robot.isHappy() is useful.
	 */
	public int countFlyingRobotsBeforeSadRobot() {
		int count = 0;
		if(this.robot.isFlying() && this.robot.isHappy()) count++;
		if(next != null && next.robot.isHappy() ) count+= next.countFlyingRobotsBeforeSadRobot();
		return count;
	}
	/** Creates a new LinkedList entry at the end of this linked list.
	 * Recursively finds the last entry then adds a new link to the end.
	 * @param robot - the robot value of the new last link
	 */
	public void append(Robot robot) {
		if(next != null) next.append(robot);
		else{
		next = new RobotLink(null,robot);
		}
	}
	/**
	 * Returns the first flying unhappy robot, or null
	 * if there are not robots that are flying and unhappy.
	 * @return
	 */
	public Robot getFirstFlyingUnhappyRobot() {
		if(next == null && this.robot.isHappy()) return null;
		if((this.robot.isHappy() == false) && this.robot.isFlying()) return this.robot;
		return next.getFirstFlyingUnhappyRobot();
		
	}
	/**
	 * Returns the last flying unhappy robotn the linked list, or null
	 * if there are not robots that are flying and unhappy.
	 * @return
	 */
	public Robot getLastFlyingUnhappyRobot() {
		if(next == null)
		{if(this.robot.isFlying() && (this.robot.isHappy() == false))
			return this.robot;
		return null;
		}
		Robot tmp = next.getLastFlyingUnhappyRobot();
		if(this.robot.isFlying() && (this.robot.isHappy() == false) &&  (tmp == null)) 
			return this.robot;
		return tmp;
	}
	/**
	 * Returns a reference to the happy most distant explorer.
	 * Returns null if there are no happy robots
	 * @return reference to the most distant happy robot
	 */
	public Robot findHappyRobotFurthestFromHome() {
		if(next == null)
		{if(this.robot.isHappy())
			return this.robot;
		return null;
		}
		Robot tmp = next.getLastFlyingUnhappyRobot();
		if(this.robot.isHappy() && (tmp == null)) 
			return this.robot;
		return tmp;
	}
	/**
	 * Returns true if linked list contains the robot.
	 * Hint: Use robot.equals(other).
	 * @param robot
	 * @return
	 */
	public boolean contains(Robot other) {
		if (next == null && (this.robot.equals(other) == false)) return false;
		if(this.robot.equals(other)) return true;
		return next.contains(other);
		
	}

	
}
