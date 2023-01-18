package Com.sda;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class IOService {

    public void displayMainMenu(Map<Product, Tray> stock) {
        System.out.println("Welcome! Please select a product from the list: ");
        for (Map.Entry<Product, Tray> entry : stock.entrySet()) {
            Product product = entry.getKey();
            Tray tray = entry.getValue();
            if (tray.isEmpty()) {
                continue;
            }
            System.out.println(product.getName() + "\t" + product.getQuantity() + "\t" + product.getPrice() + "RON" + "\t" + product.getCode());
        }
    }

    public String getUserInput() {
        System.out.print("Your input: ");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        return userInput;
    }

    public void displayChosenProductInformation(Product product) {
        System.out.println("You have selected " + product.getName() + " wich costs " + product.getPrice());
    }

    public void displayInputErrorMessage() {
        System.out.println("Sorry, the input is not valid.");
    }

    public void displayPaymentMenu() {
        System.out.println("Please introduce the coin code that you want to insert.");
        for (Coin coin : Coin.values()) {
            System.out.println(coin.getCode() + " for " + coin.getName());
        }
    }

    public void displayMissingValue(float missingvalue){
        System.out.println("Still to pay: " + missingvalue);
    }
    public void displayCoins(CoinBox coinBox) {
        System.out.println("Coin Box: " + coinBox.getVault());
    }
    public void displayChange(List<Coin> coins){
        for(Coin coin : coins){
            System.out.println("Releasing change a coin of value " + coin.getValue());
        }
    }

    public void displayReturnCoinsMessage(List<Coin> userCoins) {
        for(Coin coin : userCoins){
            System.out.println("Giving back " + coin.getName());
        }
    }
}
