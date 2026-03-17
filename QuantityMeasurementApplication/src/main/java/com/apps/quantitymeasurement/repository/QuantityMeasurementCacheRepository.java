package com.apps.quantitymeasurement.repository;
import com.apps.quantitymeasurement.app.QuantityMeasurementApp;
import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

class AppendableObjectOutputStream extends ObjectOutputStream{

    public AppendableObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        File file = new File(QuantityMeasurementCacheRepository.FILE_NAME);
        if(!file.exists() || file.length() == 0){
            super.writeStreamHeader();
        } else{
            reset();
        }
    }
}


public class QuantityMeasurementCacheRepository implements IQuantityMeasurementRepository {
    private static final Logger logger = Logger.getLogger(QuantityMeasurementApp.class.getName());
    public static final String FILE_NAME = "quantity_measurement_repo.ser";
    List<QuantityMeasurementEntity> quantityMeasurementEntityCache;
    private static QuantityMeasurementCacheRepository instance;

    private QuantityMeasurementCacheRepository(){
        quantityMeasurementEntityCache = new ArrayList<>();
        loadFromDisk();
        logger.info("QuantityMeasurementCacheRepository initialized with "+quantityMeasurementEntityCache.size() + " entities loaded from disk");
    }

    public static QuantityMeasurementCacheRepository getInstance(){
        if (instance == null){
            instance = new QuantityMeasurementCacheRepository();
        }
        return instance;
    }

    @Override
    public void save(QuantityMeasurementEntity entity) {
        quantityMeasurementEntityCache.add(entity);
        saveToDisk(entity);
    }

    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {
        return quantityMeasurementEntityCache;
    }

    private void saveToDisk(QuantityMeasurementEntity entity){
        try(FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME,true);
            AppendableObjectOutputStream objectOutputStream = new AppendableObjectOutputStream(fileOutputStream)){
            objectOutputStream.writeObject(entity);
        } catch (IOException e) {
            System.err.println("Error saving entity : "+e.getMessage());
        }
    }

    private void loadFromDisk() {
        File file = new File(FILE_NAME);
        if(file.exists()){
            try(FileInputStream fileInputStream = new FileInputStream(FILE_NAME);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
            ){
                while(true){
                    try{
                        QuantityMeasurementEntity entity = (QuantityMeasurementEntity) objectInputStream.readObject();
                        quantityMeasurementEntityCache.add(entity);
                    }catch (EOFException e){
                        break;
                    }
                }
                System.out.println("Loaded "+quantityMeasurementEntityCache.size()+" quantity measurement entities from storage");
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println("Error loading quantity measurement entities : "+ex.getMessage());
            }
        }
    }
}
