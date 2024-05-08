/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD_22090124_C;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
/**
 *
 * @author Berliani Risqi
 */
public class CRUD_22090124_C {
    


public static void main(String[] args) {
    try {
        // koneksi ke database MongoDB
        MongoDatabase database = connect.konekMongoDB();
	
	// melihat daftar koleksi (tabel)
            System.out.println("==========================");
            System.out.println("Daftar Tabel dalam Database");
            MongoIterable<String> tables = database.listCollectionNames();

            for (String name : tables) {
                System.out.println(name);
            }

        // Menambah 3 data (document)
        System.out.println("==========================");
        System.out.println("Menambahkan 3 data (document)");

        // Document pertama berisi 2 data
        MongoCollection<Document> col = database.getCollection("col_22090124_C");
        Document doc1 = new Document();
	doc1.put("nama", "Laptop HP 14s");
        doc1.put("harga", 12500000);
        col.insertOne(doc1);

        // Document kedua berisi 3 data
        Document doc2 = new Document();
	doc2.put("nama", "Iphone 15");
        doc2.put("harga", 20000000);
        doc2.put("warna", "pink");
        col.insertOne(doc2);

        // Document ketiga berisi 4 data
        Document doc3 = new Document();
	doc3.put("nama", "Kamera Canon");
        doc3.put("harga", 10000000);
        doc3.put("warna", "hitam");
        doc3.put("stok", 3);
        col.insertOne(doc3);

        System.out.println("3 data telah disimpan dalam koleksi");

        // Menampilkan data yang telah disimpan di database
        System.out.println("==========================");
        System.out.println("Data dalam koleksi produk");
        MongoCursor<Document> cursor = col.find().iterator();
        while (cursor.hasNext()) {
            System.out.println(cursor.next().toJson());
        }

        // Merubah isi data (value) pada document kedua
        System.out.println("==========================");
        System.out.println("Mengubah isi data (value) pada document kedua");
        Document updateDoc2 = new Document("$set", new Document("harga", 19000000));
        UpdateResult hasilEdit = col.updateOne(eq("_id", doc2.getObjectId("_id")), updateDoc2);
        System.out.println(hasilEdit.getModifiedCount() + " data telah diubah");

        // Menampilkan data setelah perubahan
        System.out.println("==========================");
        System.out.println("Data dalam koleksi produk setelah diubah");
        cursor = col.find().iterator();
        while (cursor.hasNext()) {
            System.out.println(cursor.next().toJson());
        }

        // Menghapus document ketiga
        System.out.println("==========================");
        System.out.println("Menghapus document ketiga");
        DeleteResult hasilHapus = col.deleteOne(eq("_id", doc3.getObjectId("_id")));
        System.out.println(hasilHapus.getDeletedCount() + " data telah dihapus");

        // Menampilkan data setelah penghapusan
        System.out.println("==========================");
        System.out.println("Data dalam koleksi produk setelah dihapus");
        cursor = col.find().iterator();
        while (cursor.hasNext()) {
            System.out.println(cursor.next().toJson());
        }

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}

}
