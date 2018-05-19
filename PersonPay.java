package test;

import java.math.BigDecimal;
import java.util.Scanner;

public class PersonPay {

    public static void main(String[] args) {
        if (args.length != 6) {
            System.out.print("null params is give or error pls make sure");
            return;
        }

        PayBean payBean = new PayBean(args);

        if (!makeSureUserWriteParams(payBean)) {
            System.out.println("即将退出。。。。");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }

        BillBean userBill = new BillBean(payBean);

        printUserBill(userBill, payBean);
    }

    private static void printUserBill(BillBean billBean, PayBean payBean) {

        BigDecimal sumMoney = new BigDecimal(0);
        BigDecimal sumWMoney = new BigDecimal(0);
        BigDecimal sumEMoney = new BigDecimal(0);
        BigDecimal sumFMoney = new BigDecimal(0);
        for (int i = 0; i < billBean.getWaterBill().length; i++) {
            BigDecimal tempMoney = getSum(getSum(billBean.getWaterBill()[i], billBean.getElectBill()[i]), billBean
                    .getFireBill()[i]);
            System.out.println("第" + (i + 1) + "户应缴水金额" + billBean.getWaterBill()[i].toString());
            System.out.println("第" + (i + 1) + "户应缴电金额" + billBean.getElectBill()[i].toString());
            System.out.println("第" + (i + 1) + "户应缴气金额" + billBean.getFireBill()[i].toString());
            System.out.println("第" + (i + 1) + "户应缴总金额" + tempMoney.toString());
            System.out.println("=========================================");
            System.out.println("=============    next  ==================");
            System.out.println("=========================================");
            sumMoney = sumMoney.add(tempMoney);
            sumWMoney = getSum(sumWMoney, billBean.getWaterBill()[i]);
            sumEMoney = getSum(sumEMoney, billBean.getElectBill()[i]);
            sumFMoney = getSum(sumFMoney, billBean.getFireBill()[i]);
        }
        System.out.println("用户水总共实际缴费：" + sumWMoney.toString());
        System.out.println("用户电总共实际缴费：" + sumEMoney.toString());
        System.out.println("用户气总共实际缴费：" + sumFMoney.toString());
        System.out.println("用户总共实际缴费：" + sumMoney.toString());
        System.out.println("用户应缴金额：" + getSum(getSum(payBean.getWaterPrice(), payBean.getElectPrice()), payBean
                .getFirePrice()));
    }

    private static boolean makeSureUserWriteParams(PayBean payBean) {
        Boolean res = false;
        System.out.println("请确认输入信息");
        System.out.println("水费总金额：" + payBean.getWaterPrice() + "元  电费总金额：" + payBean.getElectPrice() + "元  燃气费总金额："
                + payBean.getFirePrice() + "元");
        System.out.println("============================");
        for (int i = 1; i <= payBean.getUserOccupyDate().length; i++) {
            System.out.println("第" + i + "户使用天数为:" + payBean.getUserOccupyDate()[i - 1]);
        }
        System.out.println("确认计算请输入1，重新设置费用请输入0");
        try {
            Scanner scanner = new Scanner(System.in);
            if (Integer.valueOf(scanner.nextLine()) == 1) {
                res = true;
            }
        } catch (Exception e) {
            System.out.println("读取输入异常,请重新计算：" + e);
            res = false;
        }
        return res;
    }

    private static BigDecimal getSum(BigDecimal num1, BigDecimal num2) {
        return num1.add(num2);
    }
}
