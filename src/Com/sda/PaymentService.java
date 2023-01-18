package Com.sda;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentService {

    private IOService ioService;
    private CoinBox coinBox;

    public PaymentService(IOService ioService) {
        this.ioService = ioService;
        this.coinBox = new CoinBox();
    }
    //luam codul de moneda de la  user
    //pe baza codului identificam moneda
    //luam valoarea monedei si o adaugam la sold, care initial e 0
    //daca soldul coincide returam true
    //daca nu coincide:
    //soldul e mai mare decat pretul produsului si trebuie sa dam rest
    //soldul e mai mica si trebuie sa calculam cati bani trebuie sa mai introduca. Missing value = productprice - sold
    //afisam meniul de coins iar
    //luam codul de moneda de la user
    //pe baza codului identificam moneda
    //luam valoarea monedei si o adaugam la sold
    //daca soldul coincide returam true
    //daca nu coincide
    //soldul e mai mare decat pretul produsului si trebuie sa dam rest
    //soldul e mai mica si trebuie sa calculam cati bani trebuie sa mai introduca. Missing value = productprice - sold
    //bucla repetitiva. while

    public boolean processPaymentFor(Product product) {
        float balance = 0.0F;
        List<Coin> userCoins = new ArrayList<>();
        while (balance < product.getPrice()) {
            ioService.displayPaymentMenu();
            String coinCode = ioService.getUserInput();
            Optional<Coin> optionalCoin = Coin.getCoinByCode(coinCode);

            if (optionalCoin.isEmpty()) {
                ioService.displayInputErrorMessage();
                continue;
            }
            Coin coin = optionalCoin.get();
            userCoins.add(coin);
            balance = balance + coin.getValue();

            if (balance == product.getPrice()) {
                ioService.displayCoins(coinBox);
                return true;
            }
            if (balance > product.getPrice()) {
                boolean wasChangeReleased = releaseChange(balance - product.getPrice());
                if(wasChangeReleased){
                    coinBox.add(userCoins);
                }else{
                    ioService.displayReturnCoinsMessage(userCoins);
                }
             //   ioService.displayCoins(coinBox);
                return wasChangeReleased;
            }
            float missingValue = product.getPrice() - balance;
            ioService.displayMissingValue(missingValue);
        }
        return true;
    }

    private boolean releaseChange(float changeValue) {
        //identificam cea mai mare bancnota cu proprietatea ca e mai mica sau egala cu valoarea restului
        //scoatem moneda din coinbox
        //actualizam valoarea restului pe care trebuie sa il dam userului

        List<Coin> candidateChangeCoins = new ArrayList<>();  //lista/cutia cu restul complet, sa vedem daca il avem sau nu, ca sa il putem da
        float candidateCoinValue = changeValue; //moneda pe care incercam sa o gasim, sa o dam rest;
        while (changeValue > 0) {
            Coin coin = Coin.getCoinByLowerOrEqualValue(candidateCoinValue);
            Optional<Coin> optionalCoin = coinBox.getCoinFromVault(coin);
            if(optionalCoin.isPresent()){
                candidateChangeCoins.add(optionalCoin.get());
                changeValue = changeValue - optionalCoin.get().getValue();
            }else{
                candidateCoinValue = candidateCoinValue - 1;
                if(candidateCoinValue <= 0){
                    coinBox.add(candidateChangeCoins);
                    return false;
                }
            }
        }
        ioService.displayChange(candidateChangeCoins);
        return true;
    }

    public void loadWithCoins(int numberOfCoins) {
        for(Coin coin : Coin.values()){
            for(int index = 0; index < numberOfCoins; index ++) {
                coinBox.add(coin);
            }
        }
    }
}
