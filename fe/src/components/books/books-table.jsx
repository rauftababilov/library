import { useMutation, useQuery, useQueryClient } from 'react-query';
import { useState } from 'react';
import TableCell from '@mui/material/TableCell';
import TableRow from '@mui/material/TableRow';

import { DataTable } from 'components/ui/data-table';
import { DataTableActionsCell } from 'components/ui/data-table';
import { deleteBook, getAllBooks } from 'api/books';
import { EditBookDialog } from './edit-book-dialog';

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

export function BooksTable() {
  const [page, setPage] = useState(0);
  const [{openEdit, ...valueForm}, setEditDialog] = useState({openEdit: false, title: '',});
  const [rowsPerPage, setRowsPerPage] = useState(5);
  const queryClient = useQueryClient();

  const { data, isLoading, isError, error } = useQuery(['books', page, rowsPerPage], () =>
    getAllBooks({ page, size: rowsPerPage })
  );

  const deleteBookMutation = useMutation(deleteBook, {
    onSuccess: () => {
      queryClient.invalidateQueries('books');
    },
  });

  const handleChangePage = (_, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  const handleEdit = (book) => {
    console.log('book', book);
    setEditDialog({openEdit: true, ...book})
  };

  const handleDelete = (bookId) => {
    deleteBookMutation.mutate(bookId);
  };

  const handleDialogClose = () => {
    setEditDialog({openEdit: false, title: ''});
  }

  return (
    <>
      <DataTable
        cells={bookTableHeadCells}
        rows={data?.content ?? []}
        isLoading={isLoading}
        error={isError ? error : null}
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
            <DataTableActionsCell edit={false} onEdit={() => handleEdit(book)} onDelete={() => handleDelete(book.id)} />
          </TableRow>
        )}
      />
      <EditBookDialog open={openEdit} onClose={handleDialogClose} initialValues={valueForm} />
    </>
  );
}
