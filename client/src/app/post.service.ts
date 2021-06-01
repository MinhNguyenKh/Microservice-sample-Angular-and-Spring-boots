import { ApiUrl } from './constant/api-url.enum';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Post } from './post';
import { ResponseTemplateVo } from './response-template-vo';

@Injectable()
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
