package telran.b7a.forum.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.b7a.forum.model.Post;

public interface PostRepository extends MongoRepository<Post, String> {

	Stream<Post> findByAuthor(String author);
	
	Stream<Post> findByTagsIn(List<String> tags);

	Stream<Post> findByDateCreatedBetween(LocalDate from, LocalDate to);

}
