package Com.sda;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class VendingMachine {
    //products (on trays)
    //trays
    // coins (coin box - each type 1 leu, 5 lei, 10 lei)
    //coin box

    private Map<Product, Tray> stock;
    private IOService ioService;
    private PaymentService paymentService;

    public VendingMachine() {
        this.stock = new LinkedHashMap<>();
        for (Product product : Product.values()) {
            stock.put(product, new Tray());
        }
        this.ioService = new IOService();
        this.paymentService = new PaymentService(ioService);
    }

    public void loadWithProducts(int numberOfProducts) {
        for (Map.Entry<Product, Tray> entry : stock.entrySet()) {
            Product product = entry.getKey();
            Tray tray = entry.getValue();
            for (int index = 0; index < numberOfProducts; index++) {
                tray.addProduct(product);
            }
        }
    }

    public void displayStock() {
        System.out.println(stock);
    }

    public void start() {
        while (stockIsNotEmpty()) {
            ioService.displayMainMenu(stock);
             String userInput = ioService.getUserInput();  ////luam input de la utilizator
            process(userInput); //procesam inputul
        }
    }
    private void process(String productCode) {
        Optional <Product> optionalProduct = getProductByCode(productCode);
        if(optionalProduct.isEmpty()){
            ioService.displayInputErrorMessage();
            return;
        }
        Product product = optionalProduct.get();
        ioService.displayChosenProductInformation(product);
        boolean paymentWasSuccessful = paymentService.processPaymentFor(product);
        if(paymentWasSuccessful){
            releaseProduct(product);
        }
    }

    private void releaseProduct(Product product) {
        Tray tray = stock.get(product);
        tray.removeProduct(product);
    }

    private Optional<Product> getProductByCode(String productCode) {
        for (Product product : Product.values()) {  //iteram prin Product
            if(product.getCode().equals(productCode)){
                return Optional.of(product);  //ambalam produsul intr o cutie si o returnam
            }
        }
        return Optional.empty(); //cutie goala
    }

    private boolean stockIsNotEmpty() {  //daca toate tavitele sunt goale, ajunge la return false, altfel, daca gaseste o tavita cu produse intra in if si se opreste la return true.
        for (Map.Entry<Product, Tray> entry : stock.entrySet()) {
            Tray tray = entry.getValue();
            if(! tray.isEmpty()){
                return true;
            }
        }
        return false;
    }

    public void loadWithCoins(int numberOfCoins) {
        paymentService.loadWithCoins(numberOfCoins);
    }
}
