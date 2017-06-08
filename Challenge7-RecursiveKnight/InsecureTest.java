//UIUC CS125 SPRING 2014 MP. File: InsecureTest.java, CS125 Project: Challenge7-RecursiveKnight, Version: 2014-04-11T09:32:59-0500.857314000
import junit.framework.TestCase;


public class InsecureTest extends TestCase {
	// Remove the xxx if you'd like to work on the Combination Lock locally
	// For 'real' grading of the MP I will not run the 
	//TestCombinationLockBreaker
	// However a reminder that all code in your MP must compile.
	public void xxxTestCombinationLockBreaker() {
		for(int test = 0; test<10;test++) {
			InsecureCombinationLock lock = new InsecureCombinationLock();
			InsecureCombinationLockBreaker.breakLock(lock);
			assertTrue(lock.isUnlocked());
		}
	}
	public void testPasswordLockBreaker() {
		for(int len = 30; len<=50;len++) {
			InsecurePasswordLock lock = new InsecurePasswordLock(len);
			InsecurePasswordLockBreaker.breakLock(lock);
			assertTrue(lock.isUnlocked());
		}
	}

}
