package miguelguzman.venusrestblog.controller;

import lombok.AllArgsConstructor;
import miguelguzman.venusrestblog.data.Category;
import miguelguzman.venusrestblog.data.Post;
import miguelguzman.venusrestblog.data.User;
import miguelguzman.venusrestblog.repository.CategoriesRepository;
import miguelguzman.venusrestblog.repository.PostsRepository;
import miguelguzman.venusrestblog.repository.UsersRepository;
import miguelguzman.venusrestblog.security.OAuthConfiguration;
import miguelguzman.venusrestblog.service.EmailService;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/posts", produces = "application/json")
public class PostsController {
    private PostsRepository postsRepository;
    private UsersRepository usersRepository;
    private CategoriesRepository categoriesRepository;

    @GetMapping("")
    public List<Post> fetchPosts() {
        return postsRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Post> fetchPostById(@PathVariable long id) {
        return postsRepository.findById(id);
    }

    @PostMapping("")
    public void createPost(@RequestBody Post newPost, OAuthConfiguration auth) {
        String userName = auth.getName();
        User author = usersRepository.findByUserName(userName);
        newPost.setAuthor(author);

        // use a fake author for the post
        User author = usersRepository.findById(1L).get();
        newPost.setAuthor(author);

        Category cat1 = categoriesRepository.findById(1L).get();
        Category cat2 = categoriesRepository.findById(2L).get();
        newPost.setCategories(new ArrayList<>());
        newPost.getCategories().add(cat1);
        newPost.getCategories().add(cat2);
//
//        // make some fake categories and throw them in the new post
//        Category cat1 = new Category(1L, "bunnies", null);
//        Category cat2 = new Category(2L, "margaritas", null);
//        newPost.setCategories(new ArrayList<>());
//        newPost.getCategories().add(cat1);
//        newPost.getCategories().add(cat2);

        postsRepository.save(newPost);
    }

    @DeleteMapping("/{id}")
    public void deletePostById(@PathVariable long id) {
        postsRepository.deleteById(id);
        // what to do if we don't find it
//        throw new RuntimeException("Post not found");
    }

    @PutMapping("/{id}")
    public void updatePost(@RequestBody Post updatedPost, @PathVariable long id) {
        // in case id is not in the request body (i.e., updatedPost), set it
        // with the path variable id
        updatedPost.setId(id);
        postsRepository.save(updatedPost);

//        // find the post to update in the posts list
//
//        Post post = findPostById(id);
//        if(post == null) {
//            System.out.println("Post not found");
//        } else {
//            if(updatedPost.getTitle() != null) {
//                post.setTitle(updatedPost.getTitle());
//            }
//            if(updatedPost.getContent() != null) {
//                post.setContent(updatedPost.getContent());
//            }
//            return;
//        }
//        throw new RuntimeException("Post not found");
    }


}