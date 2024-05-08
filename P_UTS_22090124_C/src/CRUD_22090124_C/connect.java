/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD_22090124_C;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
/**
 *
 * @author Berliani Risqi
 */
public class connect {
    public static MongoDatabase konekMongoDB(){
        try {
            MongoClient client = MongoClients.create();
            MongoDatabase database = client.getDatabase("uts_22090124_C");
            System.out.println("Koneksi Sukses");
            return database;
        } catch (Exception e) {
            System.out.println("Koneksi Gagal: " + e.getMessage());
        }
        
        return null;
    }
}
