import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApiUrl } from 'src/app/constant/api-url.enum';
import { Comment } from '../../comment';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private commentApiUrl: string;

  constructor(private http: HttpClient) {
    this.commentApiUrl = ApiUrl.COMMENT_SERVICE_URL;
  }

  public save(comment: Comment) {
    return this.http.post<Comment>(this.commentApiUrl, comment);
  }
}
