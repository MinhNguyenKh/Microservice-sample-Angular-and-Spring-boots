import { Component } from '@angular/core';
import { CommentService } from './comment.service';
import { PostService } from './post.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [PostService, CommentService]
})
export class AppComponent {
  title = 'app';
}
