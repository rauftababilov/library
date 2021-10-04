import { useState } from 'react';
import { useQuery } from 'react-query';
import TableRow from '@mui/material/TableRow';
import TableCell from '@mui/material/TableCell';
import { getAllAuthors } from 'api/authors';
import { DataTable } from 'components/ui/data-table';
import { DataTableActionsCell } from 'components/ui/data-table/data-table-actions-cell';

const authorsTableHeadCells = [
  {
    id: 'fullName',
    label: 'Full Name',
  },
  {
    id: 'actions',
  },
];

export function AuthorsTable() {
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(5);

  const { data, isLoading } = useQuery(['authors', page, rowsPerPage], () =>
    getAllAuthors({ page, size: rowsPerPage })
  );

  const handleChangePage = (_, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  return (
    <DataTable
      cells={authorsTableHeadCells}
      rows={data?.content}
      isLoading={isLoading}
      totalElements={data?.totalElements}
      page={page}
      rowsPerPage={rowsPerPage}
      onPageChange={handleChangePage}
      onRowsPerPageChange={handleChangeRowsPerPage}
      renderRow={(author) => (
        <TableRow key={author.id}>
          <TableCell align="left">{author.fullName}</TableCell>
          <DataTableActionsCell
            align="right"
            onEdit={() => console.log(author.id)}
            onDelete={() => console.log(author.id)}
          />
        </TableRow>
      )}
    />
  );
}
