import static org.junit.Assert.*;
import org.junit.Test;

//java -cp D:\workspaces\test\bin;junit-4.9b2.jar org.junit.runner.JUnitCore TestJUnit
public class TestJUnit {
	@Test
	public void t1() {
		assertEquals(1^2, 3);
	}
	
	@Test
	public void t2() {
		assertEquals(2^4, 6);
	}
}
