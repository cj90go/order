package orderapp.state;

public enum OrderState {
    INIT(0),
    CREATE(1), ALLOCATING(2), SCHOOLING(3), CLEARING(5), COMPLETE(6), RETURING(7), REFUNDING(8), CANCLE(9), REFUNDED(10),
    SHOP_TODO(11), PLATFORM_TODO(12), SHOP_REJECT(13), CANCEL(14), SHOP_APPROVE(15), PLATFORM_APPROVE(16), PLATFORM_REJECT(17), COMPLETE_REFUND(18);


    private Integer status;

    public Byte getStatus() {
        return status.byteValue();
    }

    private OrderState() {
    }

    private OrderState(Integer status) {
        this.status = status;
    }

//    TEST;

}
