package java_demo.order;

import java_demo.order.service.ALIPayServiceImpl;
import java_demo.order.service.Result;

// 模拟支付订单轮询
public class SimulateTakeOrder {
    private static final int ORDER_PAY_AWAIT_TIMES = 20; // 订单支付等待时间，单位为秒
    private static final int SCHEDULE_TIMES = 5 * 1000; // 订单轮询时间，单位为毫秒
    /**
     * 轮询订单是否支付成功
     * @param tradeNo 支付宝交易号
     * @param outTradeNo 商户订单交易号
     * @return boolean 是否交易成功，true 为是，false为否
     */
    public static boolean isPaySuccess(Integer tradeNo, Integer outTradeNo) throws InterruptedException {
        Integer counter = 0;
        final boolean[] isSuccess = {false};
        ALIPayServiceImpl aliPayService = new ALIPayServiceImpl();
        while (true) {
            counter++;
            if (tradeNo != null && outTradeNo != null) {
                Result result = aliPayService.queryOrderPayStatus(tradeNo, outTradeNo);
                if (result.getCode() == 10000 && "success".equals(result.getMsg())) {
                    isSuccess[0] = true;
                    break;
                }
            }
            // 每五秒执行一次轮询
            Thread.sleep(SCHEDULE_TIMES);
            // 等待时间已到
            if (counter * 5 == ORDER_PAY_AWAIT_TIMES) {
                break;
            }
        }
        if (counter * 5 == ORDER_PAY_AWAIT_TIMES) {
            Result result = aliPayService.queryOrderPayStatus(tradeNo, outTradeNo);
            if (result.getCode() == 10000 && "success".equals(result.getMsg())) {
                isSuccess[0] = true;
                return isSuccess[0];
            } else {
                // 主动取消订单
                Result result1 = aliPayService.cancelOrder(outTradeNo, tradeNo);
                if (result1.getCode() == 10000 && "success".equals(result1.getMsg())) {
                    System.out.println("cancel order success!");
                } else {
                    // 继续取消订单(开启新线程轮询)
                    System.out.println("loop order..... to cancel order!");
                }
            }
        }
        return isSuccess[0];
    }
}
