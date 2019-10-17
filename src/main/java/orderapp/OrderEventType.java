package orderapp;

public enum OrderEventType {;

	public static enum Order {
		PREVIEW, CREATE, CANCLE, PAY;
	}

	public static enum Return {
		PREVIEW, CREATE, CANCLE, SHOP_APPROVE, PLATFORM_APPROVE, SHOP_REJECT, PLATFORM_REJECT,;
	}

}
