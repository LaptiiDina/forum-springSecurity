package telran.b7a.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import telran.b7a.forum.dto.DatePeriodDto;
import telran.b7a.forum.dto.NewCommentDto;
import telran.b7a.forum.dto.NewPostDto;
import telran.b7a.forum.dto.PostDto;
import telran.b7a.forum.service.ForumService;

@RestController
@RequestMapping("/forum")
public class ForumController {

	ForumService service;

	@Autowired
	public ForumController(ForumService service) {
		this.service = service;
	}

	@PostMapping("/post/{author}")
	@PostAuthorize("@customSecurity.checkExpiredDatePassword(authentication.name)")
	public PostDto addPost(@RequestBody NewPostDto newPost, @PathVariable String author) {
		return service.addNewPost(newPost, author);
	}

	@GetMapping("/post/{id}")
	@PostAuthorize("@customSecurity.checkExpiredDatePassword(authentication.name)")
	public PostDto getPost(@PathVariable String id) {
		return service.getPost(id);
	}

	@DeleteMapping("/post/{id}")
	@PostAuthorize("@customSecurity.checkExpiredDatePassword(authentication.name)")
	public PostDto removePost(@PathVariable String id) {
		return service.removePost(id);
	}

	@PutMapping("/post/{id}")
	@PostAuthorize("@customSecurity.checkExpiredDatePassword(authentication.name)")
	public PostDto updatePost(@PathVariable String id, @RequestBody NewPostDto postUpdateDto) {
		return service.updatePost(postUpdateDto, id);
	}

	@PutMapping("/post/{id}/like")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PostAuthorize("@customSecurity.checkExpiredDatePassword(authentication.name)")
	public void addLike(@PathVariable String id) {
		service.addLike(id);
	}

	@PutMapping("/post/{id}/comment/{author}")
	@PostAuthorize("@customSecurity.checkExpiredDatePassword(authentication.name)")
	public PostDto addComment(@PathVariable String id, @PathVariable String author,
			@RequestBody NewCommentDto newCommentDto) {
		return service.addComment(id, author, newCommentDto);
	}

	@GetMapping("/posts/author/{author}")
	
	public Iterable<PostDto> getPostsByAuthor(@PathVariable("author") String name) {
		return service.findPostsByAuthor(name);
	}
	
	@PostMapping("/posts/tags")
	public Iterable<PostDto> findPostsByTags(@RequestBody List<String> tags) {
		return service.findPostsByTags(tags);
	}
	
	@PostMapping("/posts/period")
	public Iterable<PostDto> findPostsByDate(@RequestBody DatePeriodDto datePeriodDto) {
		return service.findPostsByDates(datePeriodDto);
	}

}
