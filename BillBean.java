package test;

import java.math.BigDecimal;
import static java.math.BigDecimal.ROUND_HALF_DOWN;

public class BillBean {

    BigDecimal[] waterBill = new BigDecimal[3];
    BigDecimal[] electBill = new BigDecimal[3];
    BigDecimal[] fireBill = new BigDecimal[3];

    public BillBean(PayBean payBean) {
        this.waterBill = getWaterNeePay(payBean.getWaterPrice(), payBean.getUserOccupyDate());
        this.electBill = getElectPrice(payBean.getElectPrice(), payBean.getUserOccupyDate());
        this.fireBill = getFirePrice(payBean.getFirePrice(), payBean.getUserOccupyDate());
    }

    //水费：三户均分+不在时间不计算
    private BigDecimal[] getWaterNeePay(BigDecimal waterPrice, Integer[] occupyDate) {
        BigDecimal allDate = new BigDecimal(0);
        for (Integer temp : occupyDate) {
            allDate = allDate.add(new BigDecimal(temp));
        }
        //2.计算一天消费的钱
        BigDecimal everyDatePay = waterPrice.divide(allDate, 5, ROUND_HALF_DOWN);
        //3.每天的钱＊所占天数就是要支付的钱
        BigDecimal[] result = new BigDecimal[occupyDate.length];
        for (int i = 0; i < occupyDate.length; i++) {
            result[i] = everyDatePay.multiply(new BigDecimal(occupyDate[i]));
        }
        return result;
    }

    //电费：
    private BigDecimal[] getElectPrice(BigDecimal electPrice, Integer[] dateRatio) {
        return getWaterNeePay(electPrice, dateRatio);
    }

    //燃气费 1/2的钱由两户分，1/2的钱由3户分
    private BigDecimal[] getFirePrice(BigDecimal firePrice, Integer[] dateRatio) {
        BigDecimal[] result = new BigDecimal[dateRatio.length];

        BigDecimal frontTwoDateSum = new BigDecimal((dateRatio[0] + dateRatio[1]));
        BigDecimal frontAllDateSum = new BigDecimal((dateRatio[0] + dateRatio[1] + dateRatio[2]));
        BigDecimal halfFirePrice = firePrice.divide(new BigDecimal(2), 5, ROUND_HALF_DOWN);

        //1.第一户的钱
        BigDecimal oneUser = new BigDecimal(0);
        //1.1第一户要加上1/2的按天比例费用
        oneUser = halfFirePrice.multiply(new BigDecimal(dateRatio[0])).divide(frontTwoDateSum, 5, ROUND_HALF_DOWN);
        //1.2还要加上另外的1/2的按天比例
        BigDecimal otherPay = halfFirePrice.multiply(new BigDecimal(dateRatio[0])).divide(frontAllDateSum, 5,
                ROUND_HALF_DOWN);
        oneUser = oneUser.add(otherPay);
        result[0] = oneUser;

        //2.第二户的钱
        BigDecimal twoUser = new BigDecimal(0);
        //2.1第一户要加上1/2的按天比例费用
        twoUser = halfFirePrice.multiply(new BigDecimal(dateRatio[1])).divide(frontTwoDateSum, 5, ROUND_HALF_DOWN);
        //1.2还要加上另外的1/2的按天比例
        BigDecimal otherTwoPay = halfFirePrice.multiply(new BigDecimal(dateRatio[1])).divide(frontAllDateSum, 5,
                ROUND_HALF_DOWN);
        twoUser = twoUser.add(otherTwoPay);
        result[1] = twoUser;


        //3.第三户的钱
        //3.1只上另外的1/2的按天比例
        BigDecimal threeUser = new BigDecimal(0);
        BigDecimal otherThreePay = halfFirePrice.multiply(new BigDecimal(dateRatio[2])).divide(frontAllDateSum, 5,
                ROUND_HALF_DOWN);
        threeUser = threeUser.add(otherThreePay);
        result[2] = threeUser;
        return result;
    }


    public BigDecimal[] getWaterBill() {
        return waterBill;
    }

    public void setWaterBill(BigDecimal[] waterBill) {
        this.waterBill = waterBill;
    }

    public BigDecimal[] getElectBill() {
        return electBill;
    }

    public void setElectBill(BigDecimal[] electBill) {
        this.electBill = electBill;
    }

    public BigDecimal[] getFireBill() {
        return fireBill;
    }

    public void setFireBill(BigDecimal[] fireBill) {
        this.fireBill = fireBill;
    }
}
