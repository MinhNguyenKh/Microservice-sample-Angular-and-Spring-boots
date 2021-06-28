import {Comment} from './comment';

export class Post {
    id: number | undefined;
    title: string | undefined;
    comments: Comment[] = [];
}
