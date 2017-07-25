package jsp.member.model;

public class GetIMEI {
	
	private static GetIMEI instance;
	// ΩÃ±€≈Ê ∆–≈œ
		private GetIMEI(){}
		public static GetIMEI getInstance(){
			if(instance==null)
				instance=new GetIMEI();
			return instance;
		}
	
	public String getIMEI() {
		String out = "";
		try {
			out = System.getProperty("com.imei");
			if (out == null || out.equals("null") || out.equals("")) {
				out = System.getProperty("phone.imei");
			}
			if (out == null || out.equals("null") || out.equals("")) {
				out = System.getProperty("com.nokia.IMEI");
			}
			if (out == null || out.equals("null") || out.equals("")) {
				out = System.getProperty("com.nokia.mid.imei");
			}
			if (out == null || out.equals("null") || out.equals("")) {
				out = System.getProperty("com.sonyericsson.imei");
			}
	 
			if (out == null || out.equals("null") || out.equals("")) {
				out = System.getProperty("IMEI");
			}
			if (out == null || out.equals("null") || out.equals("")) {
				out = System.getProperty("com.motorola.IMEI");
			}
	 
			if (out == null || out.equals("null") || out.equals("")) {
				out = System.getProperty("com.samsung.imei");
			}
			if (out == null || out.equals("null") || out.equals("")) {
				out = System.getProperty("com.siemens.imei");
			}
	 
			if (out == null || out.equals("null") || out.equals("")) {
				out = System.getProperty("imei");
			}
	 
		} catch (Exception e) {
			return out == null ? "" : out;
		}
		return out == null ? "" : out;
	}

}
