package logic;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;


public class MapTest{
	Map testMap = new Map("/resources/GUI/Test.txt");
	
	@Test 
	public void test_setCharMap(){
			char [][] testarray;
			testarray = new char[][]{{'|',' ','|','#'},{' ',' ','#','#'}};
			testMap.setCharMap(testarray);
			assertArrayEquals("Set Character Map to a new array. Unexpected character returned",testarray, testMap.getCharMap());
	}
	
	@Test 
	public void test_getCharMap(){
			char [][] testarray;
			testarray = new char[][]{{'|',' ','^','#'},{' ','|','#','^'}};
			assertArrayEquals("Unexpected character array returned",testarray, testMap.getCharMap());
	}
	
	@Test 
	public void test_createdcharMap(){
			char [][] testarray;
			testarray = new char[][]{{'|',' ','^','#'},{' ','|','#','^'}};
			assertArrayEquals("Unexpected character array conversion of Map textfile",testarray, testMap.getCharMap());
	}
	
	/**@Test 
	public void test_invalidfileinput(){
			Map testinvalidmap = new Map("/nottherightdirectory");
			assertNull("Invalid file input did not return exception", testinvalidmap.getCharMap());
	}
	*/
	
	@Test 
	public void test_getHeight(){
			assertEquals("Unexpected height of charMap returned ",2,testMap.getHeight());
	}
	
	@Test 
	public void test_setHeight(){
			testMap.setHeight(5);
			assertEquals("Set height of charMap to 5. Unexpected height of charMap returned ",5,testMap.getHeight());
	}
	
	@Test 
	public void test_getWidth(){
			assertEquals("Unexpected width of charMap returned ",4,testMap.getWidth());
	}
	
	@Test 
	public void test_setWidth(){
			testMap.setWidth(2);
			assertEquals("Set width of charMap to 2. Unexpected width of charMap returned ",2,testMap.getWidth());
	}
	
	
}