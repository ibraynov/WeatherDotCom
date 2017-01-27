package weatherDotCom;

public class Params implements ParamsInterface {

	private String location;
	private String endPoint = "http://www.weather.com";
	
	@Override
	public void setLocation(String loc) {
		location = loc;
	}

	@Override
	public String getLocation() {
		return location;
	}

	@Override
	public String getEndPoint() {
		return endPoint;
	}

}
