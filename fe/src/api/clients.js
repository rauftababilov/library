import { getPaginationParams, parsePaginatedResponseData } from 'common/utils';
import { httpClient } from './http-client';

export async function getClientsById(id) {
  const { data } = await httpClient.get(`/clients/${id}`);
  return data;
}

export async function getAllClients(pagination) {
  let params = null;
  if (pagination) {
    params = getPaginationParams(pagination);
  }

  const response = await httpClient.get('/clients', { params });

  return parsePaginatedResponseData(response.data);
}

export async function createClients({ fullName, birthday }) {
  return await httpClient.post('/clients', { fullName, birthday });
}

export async function editPublisher({ id, fullName, birthday }) {
  return await httpClient.put(`/clients/${id}`, { fullName, birthday });
}

export async function removeClients({id}) {
  return await httpClient.delete(`/clients/${id}`);
}
