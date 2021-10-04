import { getPaginationParams, parsePaginatedResponseData } from 'common/utils';
import { httpClient } from './http-client';

export async function getAllAuthors(pagination) {
  let params = null;
  if (pagination) {
    params = getPaginationParams(pagination);
  }

  const response = await httpClient.get('/authors', { params });

  return parsePaginatedResponseData(response.data);
}

export async function getAuthorById(authorId) {
  const { data } = await httpClient.get(`/authors/${authorId}`);

  return data;
}

export async function createAuthor({ fullName }) {
  return httpClient.post('authors', { fullName });
}

export async function deleteAuthor(authorId) {
  return httpClient.delete(`/authors/${authorId}`);
}
