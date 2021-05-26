package java_demo.order.service;

public interface ALIPayService {
    Result queryOrderPayStatus(Integer out_trade_no, Integer trade_no);
    Result cancelOrder(Integer out_trade_no, Integer trade_no);
}
