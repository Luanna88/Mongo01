package com.br.mongo.mongo;

import model.Produto;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import java.util.function.Consumer;
import static com.mongodb.client.model.Updates.set;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class App {
	 public static void main(String[] args) {
		
		 CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
				 fromProviders(PojoCodecProvider.builder().automatic(true).build()));
		 
		 MongoClient mongoClient =  new MongoClient("localhost:27017",
				 MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
		 
		 MongoDatabase database = mongoClient.getDatabase("exemplo")
				 .withCodecRegistry(pojoCodecRegistry);
		 
		 MongoCollection<Produto> collection = database.getCollection("Produto", Produto.class);
		
		 // A cada vez que for adicionar ou manipular os dados dentro do banco de dados , execute o comando e apos deixe o comando comentado antes de executar o proxima para evitar o erro!
		 // Inserindo um produto
 		collection.insertOne(new Produto(1, "Arroz" , 5));
		 
		 //Atualizando um objeto
//		 collection.updateOne(new Document("_id", 1), set("descricao", "Arroz Parbolizado"));
		 
		 //Deletar um produto
//		 collection.deleteOne(new Document("descricao", "Arroz Parbolizado"));
	}
}