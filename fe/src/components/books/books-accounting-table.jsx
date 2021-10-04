import { useQuery } from 'react-query';
import { useState } from 'react';
import TableCell from '@mui/material/TableCell';
import TableRow from '@mui/material/TableRow';

import { getAllBooks } from 'api/books';
import { DataTable } from 'components/ui/data-table';

const bookTableHeadCells = [
  {
    id: 'title',
    disablePadding: true,
    label: 'Title',
  },
  {
    id: 'authors',
    label: 'Authors',
  },
  {
    id: 'publishingHouse',
    label: 'Publisher',
  },
  {
    id: 'publishYear',
    numeric: true,
    label: 'Publication year',
  },
  {
    id: 'actions',
  },
];

export function BooksAccountingTable() {
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(5);

  const { data, isLoading } = useQuery(['books', page, rowsPerPage], () => getAllBooks({ page, size: rowsPerPage }));

  const handleChangePage = (_, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  return (
    <DataTable
      cells={bookTableHeadCells}
      rows={data?.content ?? []}
      isLoading={isLoading}
      totalElements={data?.totalElements}
      page={page}
      rowsPerPage={rowsPerPage}
      onPageChange={handleChangePage}
      onRowsPerPageChange={handleChangeRowsPerPage}
      renderRow={(book) => (
        <TableRow hover key={book.id}>
          <TableCell component="th" scope="row" padding="none" align="left">
            {book.title}
          </TableCell>
          <TableCell align="left">{book.authors.map((author) => author.fullName).join(', ')}</TableCell>
          <TableCell align="left">{book.publishingHouse.title}</TableCell>
          <TableCell align="right">{book.publishYear}</TableCell>
          <TableCell></TableCell>
        </TableRow>
      )}
    />
  );
}
