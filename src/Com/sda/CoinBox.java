package Com.sda;

import java.util.*;

public class CoinBox {
    private Map<Coin, List<Coin>> vault;

    public CoinBox() {
        this.vault = new LinkedHashMap<>();
        vault.put(Coin.CINCIZECI_BANI, new ArrayList<>());
        vault.put(Coin.UN_LEU, new ArrayList<>());
        vault.put(Coin.CINCI_LEI, new ArrayList<>());
        vault.put(Coin.ZECE_LEI, new ArrayList<>());
    }

    public void add(Coin coin) {
        List<Coin> coins = vault.get(coin);
        coins.add(coin);
    }

    public Optional<Coin> getCoinFromVault(Coin coin){
        List<Coin> coins = vault.get(coin);
        if(coins.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(coins.remove(0)); //prima moneda din lista este 0
    }

    public Map<Coin, List<Coin>> getVault() {
        return vault;
    }

    public void add(List<Coin> coins){
        for(Coin coin : coins){
            add(coin);
        }
    }
}
