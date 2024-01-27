package nitconf.nitconf.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import nitconf.nitconf.models.Author;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/authors")
public class AuthorController{

   @GetMapping("{authorId}")
   public Author getAuthor(){

       return new Author("C1", "Rafael", "rafael@gmail.com", "999999999");
       
   }

   @GetMapping("/hello")
   public String hello() {
       return "Hello world!";
   }
   
}
