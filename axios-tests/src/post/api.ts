import { AxiosInstance } from 'axios';

import { Post } from './model';

export default class PostClientApi {
  constructor(private axios: AxiosInstance) {}

  create(post: Post): Promise<Post> {
    return this.axios.post('posts', post).then((response) => response.data);
  }

  getAll(): Promise<Post[]> {
    return this.axios.get('posts').then((response) => response.data);
  }

  getById(id: string): Promise<Post> {
    return this.axios.get(`posts/${id}`).then((response) => response.data);
  }

  update(id: string, post: Post): Promise<Post> {
    return this.axios
      .put(`posts/${id}`, post)
      .then((response) => response.data);
  }

  delete(id: string): Promise<void> {
    return this.axios.delete(`posts/${id}`);
  }
}
