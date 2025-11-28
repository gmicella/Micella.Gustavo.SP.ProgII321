package config;

import java.nio.file.Path;
import java.nio.file.Paths;

public interface RutasArchivos {
    
    static final String BASE = "src/resources";
    static final String FILE_CSV = "modificaciones.csv";
    static final String FILE_BIN = "modificaciones.dat";
    
    static Path getRutaCSV(){
        return Paths.get(BASE, FILE_CSV);
    }
    
    static Path getRutaBIN(){
        return Paths.get(BASE, FILE_BIN);
    }
    
    static String getRutaCSVString(){
        return getRutaCSV().toString();
    }
    
    static String getRutaBINString(){
        return getRutaBIN().toString();
    }
    
}
