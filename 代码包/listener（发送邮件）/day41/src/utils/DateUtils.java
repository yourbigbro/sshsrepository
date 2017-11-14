package utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    /**
     * ��õ�ǰʱ���������������ĺ���ʱ��
     * 
     * @param amount
     * @return
     */
    public static Long getDelayTime(int amount) {
        // 1 ���õ�ǰʱ��
        Calendar calendar = Calendar.getInstance();
        Date newDate = new Date();
        calendar.setTime(newDate);
        // 2 ��ʱ�������ó�0
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        // 3 ����ָ������
        calendar.add(Calendar.DATE, amount);
        // 4 ���㵱ǰʱ������������������ӳ�ʱ��
        return calendar.getTimeInMillis() - newDate.getTime();
    }

    /**
     * ��ǰʱ������������ĺ���ʱ��
     * 
     * @return
     */
    public static Long getDelayTime() {
        return getDelayTime(1);
    }

    /**
     * ���һ��ĺ���ֵ
     * 
     * @return
     */
    public static Long getOneDay() {
        return 24 * 60 * 60 * 1000L;
    }

    /**
     * ��ü���(��λ)
     * 
     * @return
     */
    public static String getCurrentMonth() {
        // 1 ���õ�ǰʱ��
        Calendar calendar = Calendar.getInstance();
        Date newDate = new Date();
        calendar.setTime(newDate);

        int m = calendar.get(Calendar.MONTH) + 1;
        if (m < 10) {
            return "0" + m;
        }
        return "" + m;
    }

    /**
     * ��ü���(��λ)
     * 
     * @return
     */
    public static String getCurrentDay() {
        // 1 ���õ�ǰʱ��
        Calendar calendar = Calendar.getInstance();
        Date newDate = new Date();
        calendar.setTime(newDate);

        int d = calendar.get(Calendar.DATE);
        if (d < 10) {
            return "0" + d;
        }
        return "" + d;
    }

    public static void main(String[] args) {
        System.out.println(getCurrentMonth());
        System.out.println(getCurrentDay());
    }

}

