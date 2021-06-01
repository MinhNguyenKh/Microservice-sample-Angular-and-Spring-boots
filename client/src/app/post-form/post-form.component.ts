import { Component, OnInit } from '@angular/core';
import { Post } from '../post';
import { PostService } from '../post.service';

@Component({
  selector: 'app-post-form',
  templateUrl: './post-form.component.html',
  styleUrls: ['./post-form.component.css']
})
export class PostFormComponent implements OnInit {

  post: Post;

  constructor(private postService: PostService) {
    this.post = new Post();
   }

   onSubmit() {
     console.log(this.post);
    this.postService.save(this.post).subscribe();
    setTimeout(() => {
      window.location.reload();
    }, 1500);
  }

  ngOnInit() {
  }

}
