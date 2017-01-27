package weatherDotCom;

public class StartSearch {

	public static void main(String[] args) throws Exception{
		TestSuite t = new TestSuite();
		String loc = "Pleven Bulgaria";

		t.setUp();
		t.openSite();
		t.startSearch(loc);
		t.daysOfForecast();
		t.getDataFromTable();
		t.pause(2000);
		t.tearDown();

	}

}
