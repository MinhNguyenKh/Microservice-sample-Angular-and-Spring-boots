import {Comment} from './comment';

export class Post {
    id: number | undefined;
    title: string | undefined;
    status: string | undefined;
    comments: Comment[] = [];
}
