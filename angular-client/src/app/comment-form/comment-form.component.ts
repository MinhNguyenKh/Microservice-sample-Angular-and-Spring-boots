import { Component, Input, OnInit } from '@angular/core';
import { CommentService } from '../services/comment/comment.service';
import { Comment } from '../comment';

@Component({
  selector: 'app-comment-form',
  templateUrl: './comment-form.component.html',
  styleUrls: ['./comment-form.component.css']
})
export class CommentFormComponent implements OnInit {

  @Input() postId: number | undefined;

  comment: Comment;

  constructor(private commentService: CommentService) {
    this.comment = new Comment();
  }

  onSubmit() {
    this.comment.postId = this.postId;
    this.commentService.save(this.comment).subscribe();
    setTimeout(() => {
      window.location.reload();
    }, 1500);
  }

  ngOnInit() {
  }

}
