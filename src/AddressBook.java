import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.*;

public class AddressBook {
    HashMap<String,String> contacts;
    private String contactsFile;

    public AddressBook(String filePath){
         this.contactsFile = filePath;
        load();
    }

    public void list(){
        System.out.println("Contactos:"+System.lineSeparator());
        contacts.forEach((a,b)->{
            System.out.println(a+" : "+b);
        });
        System.out.println("");
    }

    void create(){
        System.out.println("Por favor ingresa el nombre:");
        Scanner scanner = new Scanner((System.in));
        String nombre = scanner.nextLine();
        System.out.println("Por favor ingresa el telefono:");
        String telefono = scanner.nextLine();
        telefono = telefono.replace(",","");
        nombre = nombre.replace(",","");
        if (telefono.length()>0 && nombre.length()> 0){
            contacts.put(telefono,nombre);
            System.out.println("Ok, "+contacts.size()+" contactos");
        }else{
            System.out.println("No se agregó el contacto, "+contacts.size()+" contactos");
        }


    }
    public void delete(){
        System.out.println("Por favor ingresa el telefono:");
        Scanner scanner = new Scanner((System.in));
        String telefono = scanner.nextLine();
        if (contacts.containsKey(telefono)){
            System.out.println("Eliminando contacto "+telefono);
            System.out.println("Resultado eliminar: "+contacts.remove(telefono));
        }else{
            System.out.println("No existe el contacto: "+telefono);
        }
    }
    public void save(){

        StringBuilder fileContent = new StringBuilder();
        contacts.forEach((a,b)->{
            fileContent.append(a).append(',').append(b).append(System.lineSeparator());
        });
        saveFileContent(fileContent.toString(),contactsFile);
    }
    public void load() {
        String stringOfContacts = getFileContent(contactsFile);
        contacts = new HashMap<String, String>();
        if (stringOfContacts.length()>=1){
            String[] lines = stringOfContacts.split(System.lineSeparator());
            for(String s:lines){
                 String[] ss =  s.split(",");
                 if (ss!=null){
                    if (ss.length==2) {
                        contacts.put(ss[0], ss[1]);
                    }
                 }
            }
        }

    }

    private void saveFileContent(String content, String filePath) {
        try(FileOutputStream writer = new FileOutputStream(filePath)) {
            writer.write(content.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private String getFileContent(String filePath){

        String content = "";
        try(   FileInputStream reader = new FileInputStream(filePath)) {
            //BufferedReader reader = new BufferedReader(new FileReader(filePath));
            content = new String(reader.readAllBytes());
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe, intentando crear el archivo de contactos....");
            try {
                Files.createFile(Paths.get(filePath));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }
}
