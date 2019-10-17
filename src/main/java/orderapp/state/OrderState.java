package orderapp.state;

public enum OrderState {INIT;

    public static enum Order{
        /**
         * 1：已下单待支付，2：已支付待排课，3：上课中，5：待清算，6：订单结束。7：退课中，8：退款中，9：取消订单，10：退款成功
         */
        CREATE(1), ALLOCATING(2), SCHOOLING(3), CLEARING(5), COMPLETE(6), RETURING(7), REFUNDING(8), CANCLE(9), REFUNDED(10);
        private Integer status;

        private Order() {
        }
        private Order(Integer status) {
            this.status = status;
        }

        public Byte getStatus() {
            return status.byteValue();
        }
    }

    /**
     * 11：待机构处理，12：待平台处理，13：机构拒绝，14：退课撤销，15：机构同意退款，16：平台同意退款，17：平台拒绝 ,18:完课退款
     */
    public static enum Return {
        SHOP_TODO(11), PLATFORM_TODO(12), SHOP_REJECT(13), CANCEL(14), SHOP_APPROVE(15), PLATFORM_APPROVE(16), PLATFORM_REJECT(17), COMPLETE_REFUND(18);

        private Integer status;

        private Return() {
        }
        private Return(Integer status) {
            this.status = status;
        }

        public Byte getStatus() {
            return status.byteValue();
        }
    }

}
