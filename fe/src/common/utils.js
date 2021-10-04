export function getPaginationParams({ page, size }) {
  const params = new URLSearchParams();

  params.set('page', page);
  params.set('size', size);

  return params;
}

export function parsePaginatedResponseData(data) {
  const { content, totalPages, totalElements, first, last } = data;

  return {
    content,
    totalPages,
    totalElements,
    isFirstPage: first,
    isLastPage: last,
  };
}
