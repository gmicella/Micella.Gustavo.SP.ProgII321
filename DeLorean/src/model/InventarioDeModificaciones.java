package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class InventarioDeModificaciones<T extends CSVSerializable>  {
    
    private static final long serialVersionUID = 1L;
    private List<T> items = new ArrayList<>();
    
    public void agregar(T item){
        Objects.requireNonNull(item, "Elemento nulo");
        items.add(item);
    }
    
    public T obtener(int indice){
        validarIndice(indice);
        return items.get(indice);
    }
    
    public void eliminar(int indice){
        validarIndice(indice);
        items.remove(indice);
    }
    
    private void validarIndice(int indice){
        if (indice < 0 || indice >= items.size()){
            throw new IndexOutOfBoundsException("Indice fuera de rango");
        }
    }
    
    public void paraCadaElemento(Consumer<T> accion){
        for (T item : items){
            accion.accept(item);
        }
    }
    
    public List<T> filtrar(Predicate<T> criterio){
        List<T> filtrada = new ArrayList<>();
        for (T item : items){
            if (criterio.test(item)){
                filtrada.add(item);
            }
        }
        return filtrada;
    }
    
    public void ordenar(Comparator<? super T> comp){
        items.sort(comp);
    }
    
    public void ordenar(){
        items.sort((Comparator<T>)Comparator.naturalOrder());
    }
    
    public void guardarEnArchivo(String path) throws IOException {
        try (ObjectOutputStream serializador = new ObjectOutputStream(new FileOutputStream(path))){
            serializador.writeObject(items);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void cargarDesdeArchivo(String path) throws IOException, ClassNotFoundException {
        items.clear();
        try (ObjectInputStream desearializador = new ObjectInputStream(new FileInputStream(path))) {
            items = (List<T>) desearializador.readObject();
        } catch (IOException | ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void guardarEnCSV(String path) throws IOException {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(path))){
            for (T item : items){
                escritor.write(item.toCSV());
            }
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void cargarDesdeCSV(String path, Function<String, T> transformacion) throws IOException {
        items.clear();
        try (BufferedReader lector = new BufferedReader(new FileReader(path))){
            String linea;
            while ((linea = lector.readLine()) != null){
                items.add(transformacion.apply(linea));
            }
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    
}
