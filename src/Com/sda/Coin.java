package Com.sda;

import java.util.Optional;

public enum Coin {
    CINCIZECI_BANI("50 de bani", 0.5F, "50BANI"),
    UN_LEU("1 leu", 1F, "1LEU"),
    CINCI_LEI("5 lei", 5F, "5LEI"),
    ZECE_LEI("10 lei", 10F, "10LEI");

    private String name;
    private float value;
    private String code;

    Coin(String name, float value, String code) {
        this.name = name;
        this.value = value;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public static Optional<Coin> getCoinByCode(String coinCode) {
        for (Coin coin : Coin.values()) {
            if (coin.getCode().equals(coinCode)) {
                return Optional.of(coin);
            }
        }
        return Optional.empty();
    }

    public static Coin getCoinByLowerOrEqualValue(float value){
        Coin maxCoinLowerOrEqualWithValue = CINCIZECI_BANI;
        for(Coin coin : Coin.values()){
            if(coin.getValue() <= value){
                if(maxCoinLowerOrEqualWithValue.getValue() < coin.getValue()){
                    maxCoinLowerOrEqualWithValue = coin; //devine coin
                }
            }
        }
        return maxCoinLowerOrEqualWithValue;
    }

    public float getValue() {
        return value;
    }
}
