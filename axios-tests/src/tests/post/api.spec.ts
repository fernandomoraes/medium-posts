import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';

import PostClientApi from '../../post/api';

const mock = new MockAdapter(axios);

const postApi = new PostClientApi(axios);

beforeEach(() => {
  mock.reset();
});

test('should create a new post', async () => {
  const createPostRequest = {
    title: 'First post',
    author: 'John',
    content: 'My first post',
  };

  const responseCreatePost = {
    id: '04f90358-b294-4300-9862-5c5bcaf1c724',
    title: 'First post',
    author: 'John',
    content: 'My first post',
  };

  mock.onPost('posts', createPostRequest).reply(201, responseCreatePost);

  expect(await postApi.create(createPostRequest)).toEqual(responseCreatePost);
});

test('should list posts', async () => {
  const posts = [
    {
      title: 'First post',
      author: 'John',
      content: 'My first post',
    },
    {
      title: 'Second post',
      author: 'John',
      content: 'My second post',
    },
  ];

  mock.onGet('posts').reply(200, posts);

  expect(await postApi.getAll()).toEqual(posts);
});

test('should get post by id', async () => {
  const id = '04f90358-b294-4300-9862-5c5bcaf1c724';

  const post = {
    id,
    title: 'First post',
    author: 'John',
    content: 'My first post',
  };

  mock.onGet(`posts/${id}`).reply(200, post);

  expect(await postApi.getById(id)).toEqual(post);
});

test('should update a post', async () => {
  const id = '04f90358-b294-4300-9862-5c5bcaf1c724';

  const updatePostRequest = {
    title: 'First post',
    author: 'John',
    content: 'My first post',
  };

  const updatePostResponse = {
    id,
    title: 'Updated First post',
    author: 'John',
    content: 'My first post',
  };

  mock.onPut(`posts/${id}`, updatePostRequest).reply(200, updatePostResponse);

  expect(await postApi.update(id, updatePostRequest)).toEqual(
    updatePostResponse
  );
});

test('should delete a post', async () => {
  const id = '04f90358-b294-4300-9862-5c5bcaf1c724';
  mock.onDelete(`posts/${id}`).reply(200);

  await postApi.delete(id);

  expect(mock.history.delete.length).toBe(1);
  expect(mock.history.delete[0].url).toEqual(`posts/${id}`);
});
