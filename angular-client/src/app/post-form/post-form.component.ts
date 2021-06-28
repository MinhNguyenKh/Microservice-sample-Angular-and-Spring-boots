import { Component, OnInit } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';
import { Post } from '../post';
import { PostService } from '../services/post/post.service';

@Component({
  selector: 'app-post-form',
  templateUrl: './post-form.component.html',
  styleUrls: ['./post-form.component.css']
})
export class PostFormComponent implements OnInit {

  post: Post;

  constructor(private postService: PostService, private keycloakService: KeycloakService) {
    this.post = new Post();
  }

  onSubmit() {
    console.log(this.post);
    this.postService.save(this.post).subscribe();
    setTimeout(() => {
      window.location.reload();
    }, 1500);
  }

  logout() {
    this.keycloakService.logout();
  }

  ngOnInit() {
    let role = this.keycloakService.getKeycloakInstance().hasResourceRole('ADMIN');
    
    let button = <HTMLButtonElement>document.getElementById('createPostBtn');
    console.log(role);
    if (!role) {
      button.disabled = true;
    }
  }

}
