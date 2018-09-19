package seoulmobileplat.org.seoulbarrierfree;

public class Categorize {

    public static String categorize(String menu)
    {
        String category = "";
        switch(menu)
        {
            case "공공시설":
                category = "Public";
                break;
            case "공연장/영화관":
                category = "Auditorium";
                break;
            case "공원":
                category = "Park";
                break;
            case "공중화장실":
                category = "Ptoilet";
                break;
            case "관광지":
                category = "Attraction";
                break;
            case "금융":
                category = "Bank";
                break;
            case "기타":
                category = "Others";
                break;
            case "병원":
                category = "Hospital";
                break;
            case "슈퍼마켓 등 판매점":
                category = "Store";
                break;
            case "여객시설/지하철역":
                category = "Station";
                break;
            case "음식점":
                category = "Restaurant";
                break;
            case "이.미용실":
                category = "BShop";
                break;
            case "이/미용실":
                category = "BShop";
                break;
            case "전시장":
                category = "Showroom";
                break;
            case "종교시설":
                category = "Religion";
                break;
            case "체육관":
                category = "Gym";
                break;
            case "숙박":
                category = "Room";
                break;
        }

        return category;
    }


}