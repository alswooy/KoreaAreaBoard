package com.min.koreaareaboard.category.enums;

public enum CategoryType {
    // **특별시 (Special City)**
    SEOUL("Seoul", "특별시"),

    // **광역시 (Metropolitan Cities)**
    BUSAN("Busan", "광역시"),
    DAEGU("Daegu", "광역시"),
    INCHEON("Incheon", "광역시"),
    GWANGJU("Gwangju", "광역시"),
    DAEJEON("Daejeon", "광역시"),
    ULSAN("Ulsan", "광역시"),

    // **특별자치시 (Special Self-Governing City)**
    SEJONG("Sejong", "특별자치시"),

    // **경기도 (Gyeonggi-do)**
    SUWON("Suwon", "경기도"),
    GOYANG("Goyang", "경기도"),
    YONGIN("Yongin", "경기도"),
    SEONGNAM("Seongnam", "경기도"),
    BUCHEON("Bucheon", "경기도"),

    // **강원도 (Gangwon-do)**
    CHUNCHEON("Chuncheon", "강원도"),
    WONJU("Wonju", "강원도"),
    GANGNEUNG("Gangneung", "강원도"),

    // **충청북도 (Chungcheongbuk-do)**
    CHEONGJU("Cheongju", "충청북도"),
    CHUNGJU("Chungju", "충청북도"),

    // **충청남도 (Chungcheongnam-do)**
    CHEONAN("Cheonan", "충청남도"),
    ASAN("Asan", "충청남도"),

    // **전라북도 (Jeollabuk-do)**
    JEONJU("Jeonju", "전라북도"),
    IKSAN("Iksan", "전라북도"),

    // **전라남도 (Jeollanam-do)**
    MOKPO("Mokpo", "전라남도"),
    YEOSU("Yeosu", "전라남도"),
    SUNCHEON("Suncheon", "전라남도"),

    // **경상북도 (Gyeongsangbuk-do)**
    POHANG("Pohang", "경상북도"),
    GUMI("Gumi", "경상북도"),
    GYEONGJU("Gyeongju", "경상북도"),

    // **경상남도 (Gyeongsangnam-do)**
    CHANGWON("Changwon", "경상남도"),
    JINJU("Jinju", "경상남도"),
    YANGSAN("Yangsan", "경상남도"),

    // **제주특별자치도 (Jeju Special Self-Governing Province)**
    JEJU("Jeju City", "제주특별자치도"),
    SEOGWIPO("Seogwipo", "제주특별자치도");

    private final String englishName;
    private final String province;

    CategoryType(String englishName, String province) {
        this.englishName = englishName;
        this.province = province;
    }

    public String getEnglishName() {
        return englishName;
    }

    public String getProvince() {
        return province;
    }

    public static CategoryType getEnglishName(String name) {
        for (CategoryType city : values()) {
            if (city.englishName.equalsIgnoreCase(name)) {
                return city;
            }
        }
        throw new IllegalArgumentException("Unknown city name: " + name);
    }
}
