import { useQuery } from 'react-query';
import { useState } from 'react';
import TableCell from '@mui/material/TableCell';
import TableRow from '@mui/material/TableRow';

import { getAllClients, removeClients } from 'api/clients';
import { DataTable } from 'components/ui/data-table';
import { DataTableActionsCell } from 'components/ui/data-table';
import { EditPublisherDialog } from './edit-clients-dialog'; 

const bookTableHeadCells = [
  {
    id: 'fullName',
    disablePadding: true,
    label: 'Full Name',
  },
  {
    id: 'birthday',
    disablePadding: true,
    label: 'Birthday',
  },
];

export function PublishersTable() {
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(5);
  const [{openEdit, ...valueForm}, setEditDialog] = useState({openEdit: false});

  const { data, isLoading, isError, error } = useQuery(['clients', page, rowsPerPage], () =>
    getAllClients({ page, size: rowsPerPage })
  );

  const handleChangePage = (_, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  const onRemove = ( id ) => {
    removeClients({id});
  }

  const handleDialogClose = () => {
    setEditDialog({openEdit: false, title: ''});
  }

  const onEdit = (publisher) => {
    console.log('publisher', publisher);
    setEditDialog({openEdit: true, ...publisher})
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
        renderRow={(publisher) => (
          <TableRow hover key={publisher.id}>
            <TableCell component="th" scope="row" padding="none" align="left">
              {publisher.fullName}
            </TableCell>
            <TableCell component="th" scope="row" padding="none" align="left">
              {publisher.birthday}
            </TableCell>
            <DataTableActionsCell onDelete={() => onRemove(publisher.id)} />
          </TableRow>
        )}
      />
      <EditPublisherDialog open={openEdit} onClose={handleDialogClose} valueForm={valueForm} />
    </>
  );
}
