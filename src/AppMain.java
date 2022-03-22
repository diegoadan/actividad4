import java.util.Scanner;

public class AppMain {
    public static void main(String[] args){
        AddressBook addressBook = new AddressBook("contacts.csv");
        Scanner scanner = new Scanner(System.in);

        while(true){
            printMenu();
            switch(scanner.nextInt()){
                case 1:
                    addressBook.list();
                    break;
                case 2:
                    addressBook.create();
                    break;
                case 3:
                    addressBook.delete();
                    break;
                case 4:
                    addressBook.save();
                    return;
            }
        }
    }

    public static void printMenu (){
        System.out.println("Seleccione una opcion por favor:"+System.lineSeparator());
        System.out.println("1 - Listar contactos");
        System.out.println("2 - Crear contacto");
        System.out.println("3 - Eliminar contacto");
        System.out.println("4 - Salir");
    }
}
