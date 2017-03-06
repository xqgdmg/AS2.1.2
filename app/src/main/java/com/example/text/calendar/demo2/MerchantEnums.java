package com.example.text.calendar.demo2;


import com.example.text.calendar.R;

/**
 * 商户枚举类
 */
public class MerchantEnums {

//    /**
//     * 申请成为商户的审核状态
//     * 1 待审核 2 审核通过 3审核失败 4冻结 5删除
//     */
//    public enum MerchantStatus {
//
//        Auditing(R.string.Auditing, 1), AuditSuccess(R.string.AuditSuccess, 2),
//        AuditFail(R.string.AuditFail, 3), AuditFrost(R.string.AuditFail, 4),
//        AuditDelete(R.string.AuditFail, 5);
//
//        private int descriptionId;
//        private int value;
//
//        MerchantStatus(int descriptionId, int value) {
//            this.descriptionId = descriptionId;
//            this.value = value;
//        }
//
//        public int getDescriptionId() {
//            return descriptionId;
//        }
//
//        public void setDescriptionId(int descriptionId) {
//            this.descriptionId = descriptionId;
//        }
//
//        public int getValue() {
//            return value;
//        }
//
//        public void setValue(int value) {
//            this.value = value;
//        }
//
//        public static MerchantStatus getMerchantStatus(int val) {
//            for (MerchantStatus status : MerchantStatus.values()) {
//                if (status.getValue() == val) {
//                    return status;
//                }
//            }
//            return Auditing;
//        }
//
//    }
//
//    /**
//     * 商户类型
//     * 1 饮食 2 服务 3 零售
//     * 4 服装  5 酒店 6 房产
//     * 7 数码 8 茶叶 9 健身
//     * 10 珠宝 11 美容 12 汽车
//     */
//    public enum MerchantType {
//
//        Diet(R.string.diet, 1), Service(R.string.service, 2), Retail(R.string.retail, 3),
//        Clothing(R.string.clothing, 4), Hotels(R.string.hotels, 5), Property(R.string.property, 6),
//        Digital(R.string.digital, 7), TeaLeaves(R.string.teaLeaves, 8), Fitness(R.string.fitness, 9),
//        Jewelry(R.string.jewelry, 10), Beauty(R.string.beauty, 11), Cars(R.string.cars, 12);
//
//        private int typeResId;
//        private int value;
//
//        MerchantType(int typeResId, int value) {
//            this.typeResId = typeResId;
//            this.value = value;
//        }
//
//        public int getTypeResId() {
//            return typeResId;
//        }
//
//        public int getValue() {
//            return value;
//        }
//
//        public void setValue(int value) {
//            this.value = value;
//        }
//
//        public static MerchantType getMerchantType(int val) {
//            for (MerchantType type : MerchantType.values()) {
//                if (type.getValue() == val) {
//                    return type;
//                }
//            }
//            return Diet;
//        }
//
//        public static int getMerchantType(String val) {
//            for (MerchantType type : MerchantType.values()) {
//                String merchantType = MerchantApplication.getInstance().getResources().getString(type.typeResId);
//                if (merchantType.equalsIgnoreCase(val)) {
//                    return type.value;
//                }
//            }
//            return Diet.value;
//        }
//    }

    /**
     * 获取月份
     */
    public enum Month {

        January(R.string.january, 1), February(R.string.february, 2), March(R.string.march, 3),
        April(R.string.april, 4), May(R.string.may, 5), June(R.string.june, 6),
        July(R.string.july, 7), August(R.string.august, 8), September(R.string.september, 9),
        October(R.string.october, 10), November(R.string.november, 11), December(R.string.december, 12);

        private int monthResId;
        private int month;

        Month(int monthResId, int month) {
            this.monthResId = monthResId;
            this.month = month;
        }

        public int getMonthResId() {
            return monthResId;
        }

        public int getMonth() {
            return month;
        }

        public static Month getMonth(int monthValue) {
            for (Month month : Month.values()) {
                if (month.getMonth() == monthValue) {
                    return month;
                }
            }
            return January;
        }
    }

}
