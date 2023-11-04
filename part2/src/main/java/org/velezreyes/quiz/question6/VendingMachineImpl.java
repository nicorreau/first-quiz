package org.velezreyes.quiz.question6;

import java.util.HashMap;
import java.util.Map;

public class VendingMachineImpl implements VendingMachine {

    private int currentBalance;
    private Map<String, Drink> drinks;

    public VendingMachineImpl() {
        currentBalance = 0;
        drinks = new HashMap<>();
        // Agregar las bebidas disponibles con sus propiedades
        drinks.put("ScottCola", new Drink() {
            @Override
            public String getName() {
                return "ScottCola";
            }

            @Override
            public boolean isFizzy() {
                return true;
            }
        });

        drinks.put("KarenTea", new Drink() {
            @Override
            public String getName() {
                return "KarenTea";
            }

            @Override
            public boolean isFizzy() {
                return false;
            }
        });
    }

    @Override
    public void insertQuarter() {
        currentBalance += 25; // Incrementar el balance por cada moneda de 25 centavos
    }

    @Override
    public Drink pressButton(String name) throws NotEnoughMoneyException, UnknownDrinkException {
        if (!drinks.containsKey(name)) {
            throw new UnknownDrinkException();
        }

        Drink selectedDrink = drinks.get(name);
        int price = getPrice(name);

        if (currentBalance < price) {
            throw new NotEnoughMoneyException();
        }

        currentBalance -= price;
        return selectedDrink;
    }

    // Método interno para obtener el precio de una bebida
    private int getPrice(String drinkName) {
        // Lógica para obtener el precio de una bebida (75 centavos o 100 centavos)
        // Este ejemplo utiliza precios fijos, pero podrían ser variables según la bebida
        return (drinkName.equals("ScottCola")) ? 75 : 100;
    }

    public static VendingMachine getInstance() {
      return new VendingMachineImpl();
    }
}
