import { getPaginationParams, parsePaginatedResponseData } from 'common/utils';
import { httpClient } from './http-client';

export async function getPublisherById(id) {
  const { data } = await httpClient.get(`/publishing-houses/${id}`);
  return data;
}

export async function getAllPublishers(pagination) {
  let params = null;
  if (pagination) {
    params = getPaginationParams(pagination);
  }

  const response = await httpClient.get('/publishing-houses', { params });

  return parsePaginatedResponseData(response.data);
}

export async function createPublisher({ title }) {
  return await httpClient.post('/publishing-houses', { title });
}

export async function editPublisher({ id, title }) {
  return await httpClient.put(`/publishing-houses/${id}`, { title });
}

export async function removePublisher({id}) {
  return await httpClient.delete(`/publishing-houses/${id}`);
}
