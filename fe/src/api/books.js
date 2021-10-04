import { getPaginationParams, parsePaginatedResponseData } from 'common/utils';
import { httpClient } from './http-client';

import { getAuthorById } from './authors';
import { getPublisherById } from './publishers';

async function getBookAuthors(authorIds = []) {
  return Promise.all(authorIds.map((authorId) => getAuthorById(authorId)));
}

export async function getAllBooks({ page, size }) {
  const response = await httpClient.get('/books', { params: getPaginationParams({ page, size }) });

  const data = parsePaginatedResponseData(response.data);

  const books = data.content;

  const enrichedBooks = await Promise.all(
    books.map(async (book) => {
      const authors = await getBookAuthors(book.authorIds);
      const publishingHouse = await getPublisherById(book.publishingHouseId);

      return {
        ...book,
        authors,
        publishingHouse,
      };
    })
  );

  return Object.assign(data, { content: enrichedBooks });
}

export async function updateBook(bookId, newData) {}

export async function deleteBook(bookId) {
  return httpClient.delete(`/books/${bookId}`);
}

export async function createBook({ title, authorIds, publishingHouseId, publishYear }) {
  return httpClient.post(`/books`, { title, authorIds, publishingHouseId, publishYear });
}
export async function editBook({ id, title, authorIds, publishingHouseId, publishYear }) {
  return httpClient.put(`/books/${id}`, { title, authorIds, publishingHouseId, publishYear });
}
