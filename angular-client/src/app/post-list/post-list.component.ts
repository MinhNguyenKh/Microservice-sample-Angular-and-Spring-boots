import { Component, OnInit } from '@angular/core';
import { Post } from '../post';
import { PostService } from '../services/post/post.service';
import { Comment } from '../comment';

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.css']
})
export class PostListComponent implements OnInit {

  postDataArr: Post[] = [];  

  constructor(private postService: PostService) {
  }

  ngOnInit() {
    this.postService.findAll().subscribe(data => {
      console.log(data);
      this.postDataArr = data;
    });
  }

}
