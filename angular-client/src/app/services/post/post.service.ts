import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiUrl } from 'src/app/constant/api-url.enum';
import { Post } from 'src/app/post';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  private postApiUrl: string;
  private queryApiUrl: string;

  constructor(private http: HttpClient) {
    this.postApiUrl = ApiUrl.POST_SERVICE_URL;
    this.queryApiUrl = ApiUrl.QUERY_SERVICE_URL;
  }

  public findAll(): Observable<Post[]> {
    return this.http.get<Post[]>(this.queryApiUrl + 'posts');
  }

  public save(post: Post) {
    return this.http.post<Post>(this.postApiUrl, post);
  }
}
