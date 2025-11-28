package delorean;

import config.RutasArchivos;
import java.io.IOException;
import model.InventarioDeModificaciones;
import model.ModificacionDeLorean;
import model.TipoModificacion;

public class DeLorean {

    public static void main(String[] args) {
        try {
            InventarioDeModificaciones<ModificacionDeLorean> inv = new InventarioDeModificaciones<>();
            
            inv.agregar(new ModificacionDeLorean(1, "Flux Capacitor Mk1", "Doc Brown", TipoModificacion.TIEMPO));
            inv.agregar(new ModificacionDeLorean(2, "Mr. Fusion", "Doc Brown", TipoModificacion.ENERGIA));
            inv.agregar(new ModificacionDeLorean(3, "Hover Conversion", "Los Libios", TipoModificacion.CONVERSION_HOVER));
            inv.agregar(new ModificacionDeLorean(4, "Temporal Circuit V3", "Clara Clayton", TipoModificacion.TIEMPO));
            inv.agregar(new ModificacionDeLorean(5, "Shield Upgrade", "Marty McFly", TipoModificacion.SEGURIDAD));
            
            System.out.println("Modificaciones del DeLorean:");
            inv.paraCadaElemento(m -> System.out.println(m));
            
            System.out.println("\nModificaciones tipo TIEMPO:");
            inv.filtrar(m -> m.getTipo() == TipoModificacion.TIEMPO)
                .forEach(m -> System.out.println(m));
            
            System.out.println("\nModificaciones que contienen 'hover':");
            inv.filtrar(m -> m.getNombre().toLowerCase().contains("hover"))
                .forEach(m -> System.out.println(m));
            
            System.out.println("\nModificaciones ordenadas por ID:");
            inv.ordenar((m1, m2) -> Integer.compare(m1.getId(), m2.getId()));
            inv.paraCadaElemento(m -> System.out.println(m));
            
            System.out.println("\nModificaciones ordenadas por nombre:");
            inv.ordenar((m1, m2) -> m1.getNombre().compareToIgnoreCase(m2.getNombre()));
            
            inv.guardarEnArchivo(RutasArchivos.getRutaBINString());
            
            InventarioDeModificaciones<ModificacionDeLorean> cargado = new InventarioDeModificaciones<>();
            cargado.cargarDesdeArchivo(RutasArchivos.getRutaBINString());
            
            System.out.println("\nModificaciones cargadas desde archivo binario:");
            cargado.paraCadaElemento(m -> System.out.println(m));
            
            inv.guardarEnCSV(RutasArchivos.getRutaCSVString());
            
            cargado.cargarDesdeCSV(RutasArchivos.getRutaCSVString(), lineaCSV -> ModificacionDeLorean.fromCSV(lineaCSV));
            
            System.out.println("\nModificaciones cargadas desde archivo CSV:");
            cargado.paraCadaElemento(m -> System.out.println(m));
            
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
        
    }
    
}
