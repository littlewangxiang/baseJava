package test;

import java.math.BigDecimal;

public class PayBean {

    private BigDecimal waterPrice;
    private BigDecimal electPrice;
    private BigDecimal firePrice;

    private Integer[] userOccupyDate = new Integer[3];

    public PayBean(String[] mainArgs) {
        this.waterPrice = new BigDecimal(mainArgs[0]);
        this.electPrice = new BigDecimal(mainArgs[1]);
        this.firePrice = new BigDecimal(mainArgs[2]);

        this.userOccupyDate[0] = Integer.valueOf(mainArgs[3]);
        this.userOccupyDate[1] = Integer.valueOf(mainArgs[4]);
        this.userOccupyDate[2] = Integer.valueOf(mainArgs[5]);
    }

    public BigDecimal getWaterPrice() {
        return waterPrice;
    }

    public void setWaterPrice(BigDecimal waterPrice) {
        this.waterPrice = waterPrice;
    }

    public BigDecimal getElectPrice() {
        return electPrice;
    }

    public void setElectPrice(BigDecimal electPrice) {
        this.electPrice = electPrice;
    }

    public BigDecimal getFirePrice() {
        return firePrice;
    }

    public void setFirePrice(BigDecimal firePrice) {
        this.firePrice = firePrice;
    }

    public Integer[] getUserOccupyDate() {
        return userOccupyDate;
    }

    public void setUserOccupyDate(Integer[] userOccupyDate) {
        this.userOccupyDate = userOccupyDate;
    }
}
