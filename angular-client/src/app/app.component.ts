import { Component } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';
import { CommentService } from './services/comment/comment.service';
import { PostService } from './services/post/post.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [PostService, CommentService]
})
export class AppComponent {
  title = 'angular-client';
  constructor(private keycloakService: KeycloakService) {}
  logout() {
    this.keycloakService.logout();
  }
}
