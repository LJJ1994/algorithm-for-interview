package java_demo.order.service;

public class ALIPayServiceImpl implements ALIPayService{
    @Override
    public Result queryOrderPayStatus(Integer out_trade_no, Integer trade_no) {
        Result result = new Result();
        result.setCode(10001);
        result.setMsg("success");
        return result;
    }

    @Override
    public Result cancelOrder(Integer out_trade_no, Integer trade_no) {
        Result result = new Result();
        result.setCode(10001);
        result.setMsg("success");
        return result;
    }
}
