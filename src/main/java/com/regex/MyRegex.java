package com.regex;

public class MyRegex {
    private MyRegex() {
    }
    //   SP-XXXX
    public static final String REGEX_PRODUCT_ID = "^SP-[\\d]{4}$";

    //   KH-XXXX
    public static final String REGEX_CUSTOMER_ID = "^KH-[\\d]{4}$";
    //   DV-XXXX
    public static final String REGEX_VILLA_ID = "^SVVL-[\\d]{4}$";
    public static final String REGEX_HOUSE_ID = "^SVHO-[\\d]{4}$";
    public static final String REGEX_ROOM_ID = "^SVRO-[\\d]{4}$";

    // Tên sản phẩm
    public static final String REGEX_PRODUCT_NAME = "^.[^[\\d]]";

    //   Tên
    public static final String REGEX_NAME = "^[A-Z][a-z]+((\\s)[A-Z][a-z]+)*$";
    // Ngày tháng năm
    public static final String REGEX_DATE = "dd/MM/yyyy";
    //   Số điện thoại
    public static final String REGEX_PHONE_NUMBER = "^[\\d]{10,11}$";
    //   090xxxxxxx hoặc 091xxxxxxx hoặc (84)+90xxxxxxx hoặc (84)+91xxxxxxx
    public static final String REGEX_PHONE = "^(090|091|\\(84\\)\\+90|\\(84\\)\\+91)[\\d]{7}$";
    //   public static final String REGEX_PHONE = "^(090|091|[(]84[)][+]90|[(]84[)][+]91)[\\d]{7}$";
    //   CMND 9 số và 12 số
    public static final String REGEX_ID_NUMBER = "^([\\d]{9}|[\\d]{12})$";
    //   Email
    //   public static final String REGEX_EMAIL = "^[\\w.]+@[\\w&&[^_]]+([.][\\w&&[^_]]+){1,2}$";
    public static final String REGEX_EMAIL = "^[\\w.]+@[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+){1,2}$";
    //   số dương
    public static final String REGEX_POSITIVE_NUMBER = "^([1-9][\\d]*(\\.[\\d]*)?)|(0\\.[\\d]+)$";
    //   số nguyên dương
    public static final String REGEX_POSITIVE_INTEGER = "^[1-9][\\d]*$";
}
