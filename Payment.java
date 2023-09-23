import java.util.ArrayList;
import java.util.Scanner;

public class Payment {
  static Scanner input = new Scanner(System.in);
  static ArrayList<Order> orders = new ArrayList<>();
  public static void main(String[] args) {
    int pilih;
    do {
      System.out.println("----------");
      System.out.println(" BUCKS COFFEE ");
      System.out.println("----------");
      System.out.println("1. Buy Coffee");
      System.out.println("2. Checkout");
      System.out.println("3. Exit");
      System.out.println("----------");
      System.out.print("Pilihanmu: ");
      pilih = input.nextInt();

      // Error Handling after Input an Integer
      input.nextLine();

      if (pilih == 1) {
        orders = buyCoffee(orders);
      } else if (pilih == 2) {
        orders = checkout(orders);
      }
    } while (pilih != 3);

    orders.clear();
  }

  private static ArrayList<Order> buyCoffee(ArrayList<Order> orders) {
    String name, type, sugar = null;
    int qty;

    System.out.print("Input nama kopi: ");
    name = input.nextLine();

    boolean ok;
    do {
      System.out.print("Input nama tipe [ Cappucino, Latte, Americano ]: ");
      type = input.nextLine();
      ok = cekKopi(type);
    } while (ok == false);

    do {
      System.out.print("Tambah gula [Y/T]: ");
      sugar = input.nextLine();
      ok = cekGula(sugar);
    } while (ok == false);

    System.out.print("Quantity: ");
    qty = input.nextInt();

    // Input to ArrayList
    orders.add(new Order(name, type, sugar, qty));
    return orders;
  }

  private static ArrayList<Order> checkout(ArrayList<Order> orders) {
    int harga, jumlah, total = 0, bayar;

    System.out.println("Jumlah order: " + String.valueOf(orders.size()));
    System.out.println("----------");
    System.out.printf("| %-5s| %-15s| %-13s| %-13s| %-13s| %-13s| %-13s|",
      "No. ", "Name ", "Type ", "Extra ", "Qty ", "Price ", "Total ");
    System.out.println();
    System.out.println("----------");
    
    int num = 1;
    for (int i = 0; i < orders.size(); i++) {
      harga = (orders.get(i).getQty()
        * orders.get(i).getName().length()
        * 100);

      if (orders.get(i).getSugar().equals("Y")
          || orders.get(i).getSugar().equals("y")) {
        jumlah = orders.get(i).getQty() * harga;
      } else {
        jumlah = (int)((int)(orders.get(i).getQty() * harga)
          + (orders.get(i).getQty()
          * 0.03));
      }

      System.out.printf("| %-5s| %-15s| %-13s| %-13s| %-13s| %-13s| %-13s|",
        num++,
        orders.get(i).getName(),
        orders.get(i).getType(),
        orders.get(i).getSugar(),
        orders.get(i).getQty(),
        harga,
        jumlah);

      System.out.println();

      total += jumlah;
    }

    System.out.println("----------");
    System.out.println("Total: " + total);

    boolean ok;
    do {
      System.out.print("Bayar: ");
      bayar = input.nextInt();
      ok = cekBayar(total, bayar);
    } while (ok == false);

    orders.clear();

    System.out.println("Kembalian: " + (bayar - total));
    System.out.println("Successfully add new order!");
    System.out.println("Press enter to continue..");

    input.nextLine();
    input.nextLine();

    return orders;
  }

  private static boolean cekKopi(String type) {
    boolean ok = false;
    if (type.equals("Cappucino")
        || type.equals("Latte")
        || type.equals("Americano")) {
      ok = true;
    }
    
    return ok;
  }

  private static boolean cekGula(String sugar) {
    boolean ok = false;
    if (sugar.equals("Y")
        || sugar.equals("y")
        || sugar.equals("T")
        || sugar.equals("T")) {
      ok = true;
    }
    
    return ok;
  }

  private static boolean cekBayar(int total, int bayar) {
    boolean ok = false;

    if (bayar >= total) {
      ok = true;
    }

    return ok;
  }
}

class Order {
  String name, type, sugar;
  int qty;

  public Order(String name, String type, String sugar, int qty) {
    this.name = name;
    this.type = type;
    this.sugar = sugar;
    this.qty = qty;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public String getSugar() {
    return sugar;
  }

  public int getQty() {
    return qty;
  }
}