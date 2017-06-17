package com.rp.member.config;

public class BizEnum {
	public static enum ORDER_STAT {
		결제완료("OS001"),
		배송중("OS002"),
		배송완료("OS003"),
		구매확정("OS004");
		
		private String code;
		private String name;
		
		private ORDER_STAT(String code) {
			this.code = code;
			this.name = name();
		}
		
		public String getCode() {
			return code;
		}
		
		public String getName() {
			return name;
		}
	}
	
	public static enum ORDER_EVENT {
		결제완료("OE001"),
		배송중("OE002"),
		배송완료("OE003"),
		구매확정("OE004");
		
		private String code;
		private String name;
		
		private ORDER_EVENT(String code) {
			this.code = code;
			this.name = name();
		}
		
		public String getCode() {
			return code;
		}
		
		public String getName() {
			return name;
		}
	}

}
